package helPet.dao.mappers;

import helPet.entity.Appointment;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentMapper implements RowMapper<Appointment> {
    @Override
    public Appointment map(ResultSet rs, StatementContext ctx) throws SQLException {
        Appointment appointment = new Appointment();

        appointment.setId(rs.getLong("id"));
        appointment.setDate(rs.getTimestamp("date"));
        appointment.setNote(rs.getString("note"));
        appointment.setBusinessId(rs.getLong("business_id"));
        appointment.setUserId(rs.getLong("user_id"));
        appointment.setPetId(rs.getLong("pet_id"));
        appointment.setCreatedBy(rs.getString("created_by"));
        appointment.setCreatedOn(rs.getDate("created_on"));
        appointment.setUpdatedBy(rs.getString("updated_by"));
        appointment.setUpdatedOn(rs.getDate("updated_on"));
        appointment.setStatus(EntityStatus.get(rs.getInt("status")));

        return appointment;
    }
}
