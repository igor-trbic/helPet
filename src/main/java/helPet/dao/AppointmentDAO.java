package helPet.dao;

import helPet.dao.mappers.AppointmentMapper;
import helPet.entity.Appointment;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface AppointmentDAO extends Transactional<AppointmentDAO> {
    @SqlUpdate("INSERT INTO public.appointment (id, business_id, user_id, pet_id, date, note, status, created_on, created_by, updated_on, updated_by " +
            " ) VALUES ( nextval('appointment_seq'), :businessId, :userId, :petId, :date, :note, :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean Appointment appointment);

    @SqlUpdate("UPDATE public.appointment SET business_id = :businessId, user_id = :userId, pet_id = :petId, note = :note, date = :date, status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean Appointment appointment);

    @SqlQuery("SELECT * FROM public.appointment WHERE id = :id AND status != 109")
    @UseRowMapper(AppointmentMapper.class)
    Appointment findActive(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.appointment SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);
}
