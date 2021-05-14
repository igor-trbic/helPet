package helPet.managers;

import helPet.dao.AppointmentDAO;
import helPet.dao.BusinessDAO;
import helPet.dto.AppointmentDTO;
import helPet.entity.Appointment;
import helPet.entity.Business;
import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;

    public AppointmentManager(Jdbi dbi) {
        this.dbi=dbi;
    }

    public List<AppointmentDTO> get(User user) {
        Handle h = dbi.open();
        List<AppointmentDTO> appointments = new ArrayList<>();
        try {
            h.begin();
            AppointmentDAO appointmentDAO = h.attach(AppointmentDAO.class);

            // TODO: Maybe include userId as well???
            appointments = appointmentDAO.findAllForUser(user.getId());

            h.commit();
        } catch (Exception ex) {
            appointments = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return appointments;
    }

    public Appointment create(Appointment appointment, User user) {
        Handle h = dbi.open();
        try {
            h.begin();
            BusinessDAO businessDAO = h.attach(BusinessDAO.class);
            AppointmentDAO appointmentDAO = h.attach(AppointmentDAO.class);

            Business business = businessDAO.findActive(appointment.getBusinessId());
            if (business == null) {
                // TODO: implement client error
                throw new Exception("Cannot find business");
            }

            appointment.setUserId(user.getId());
            appointment.setCreatedBy(user.getUsername());
            appointment.setStatus(EntityStatus.PENDING);
            long businessRoleId = appointmentDAO.insert(appointment);
            if (businessRoleId == 0) {
                throw new Exception("Cannot insert pet attribute");
            }
            appointment.setId(businessRoleId);

            h.commit();
        } catch (Exception ex) {
            appointment = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return appointment;
    }

    public Appointment update(Appointment appointment, User user) {
        Handle h = dbi.open();
        try {
            h.begin();
            AppointmentDAO appointmentDAO = h.attach(AppointmentDAO.class);

            appointment.setUpdatedBy(user.getUsername());
            long updatedFlag = appointmentDAO.update(appointment);
            if (updatedFlag == 0) {
                // TODO: implement client error
                throw new Exception("Cannot update appointment");
            }

            h.commit();
        } catch (Exception ex) {
            appointment = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return appointment;
    }

    public Boolean remove(Long appointmentId, User user) {
        Boolean success = false;
        Handle h = dbi.open();
        try {
            h.begin();
            AppointmentDAO appointmentDAO = h.attach(AppointmentDAO.class);

            long updatedFlag = appointmentDAO.remove(appointmentId, user.getUsername());
            if (updatedFlag == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert pet attribute");
            }
            success = true;

            h.commit();
        } catch (Exception ex) {
            success = false;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return success;
    }

    public List<AppointmentDTO> getForBusiness(Long businessId, User user) {
        Handle h = dbi.open();
        List<AppointmentDTO> appointments = new ArrayList<>();
        try {
            h.begin();
            AppointmentDAO appointmentDAO = h.attach(AppointmentDAO.class);

            appointments = appointmentDAO.findAllForBusiness(businessId);

            h.commit();
        } catch (Exception ex) {
            appointments = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return appointments;
    }
}
