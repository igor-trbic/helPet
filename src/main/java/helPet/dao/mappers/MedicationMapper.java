package helPet.dao.mappers;

import helPet.entity.Medication;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicationMapper implements RowMapper<Medication> {
    @Override
    public Medication map(ResultSet rs, StatementContext ctx) throws SQLException {
        Medication medication = new Medication();

        medication.setId(rs.getLong("id"));
        medication.setName(rs.getString("name"));
        medication.setDescription(rs.getString("description"));
        medication.setDose(rs.getString("dose"));
        medication.setCreatedBy(rs.getString("created_by"));
        medication.setCreatedOn(rs.getDate("created_on"));
        medication.setUpdatedBy(rs.getString("updated_by"));
        medication.setUpdatedOn(rs.getDate("updated_on"));
        medication.setStatus(EntityStatus.get(rs.getInt("status")));

        return medication;
    }
}
