package helPet.dao.mappers;

import helPet.dto.AppointmentDTO;
import helPet.entity.Business;
import helPet.entity.Pet;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDTOMapper implements RowMapper<AppointmentDTO> {
    @Override
    public AppointmentDTO map(ResultSet rs, StatementContext ctx) throws SQLException {
        AppointmentDTO appointmentDTO = new AppointmentDTO();

        appointmentDTO.setId(rs.getLong("id"));
        appointmentDTO.setDate(rs.getTimestamp("date"));
        appointmentDTO.setNote(rs.getString("note"));
        appointmentDTO.setBusinessId(rs.getLong("business_id"));
        appointmentDTO.setUserId(rs.getLong("user_id"));
        appointmentDTO.setPetId(rs.getLong("pet_id"));
        appointmentDTO.setCreatedBy(rs.getString("created_by"));
        appointmentDTO.setCreatedOn(rs.getDate("created_on"));
        appointmentDTO.setUpdatedBy(rs.getString("updated_by"));
        appointmentDTO.setUpdatedOn(rs.getDate("updated_on"));
        appointmentDTO.setStatus(EntityStatus.get(rs.getInt("status")));

        Business business =  new Business();
        business.setId(rs.getLong("business_id"));
        business.setBusinessName(rs.getString("business_name"));
        appointmentDTO.setBusiness(business);

        Pet pet = new Pet();
        pet.setId(rs.getLong("pet_id"));
        pet.setName(rs.getString("pet_name"));
        appointmentDTO.setPet(pet);

        return appointmentDTO;
    }
}
