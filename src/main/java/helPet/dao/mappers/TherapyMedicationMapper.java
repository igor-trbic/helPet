package helPet.dao.mappers;

import helPet.entity.TherapyMedication;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TherapyMedicationMapper implements RowMapper<TherapyMedication> {
    @Override
    public TherapyMedication map(ResultSet rs, StatementContext ctx) throws SQLException {
        TherapyMedication therapyMedication = new TherapyMedication();

        therapyMedication.setId(rs.getLong("id"));
        therapyMedication.setFromDate(rs.getDate("from_date"));
        therapyMedication.setThruDate(rs.getDate("thru_date"));
        therapyMedication.setDescription(rs.getString("description"));
        therapyMedication.setTimes(rs.getInt("times"));
        therapyMedication.setTimesPer(rs.getString("times_per"));
        therapyMedication.setTherapyId(rs.getLong("therapy_id"));
        therapyMedication.setMedicationId(rs.getLong("medication_id"));
        therapyMedication.setCreatedBy(rs.getString("created_by"));
        therapyMedication.setCreatedOn(rs.getDate("created_on"));
        therapyMedication.setUpdatedBy(rs.getString("updated_by"));
        therapyMedication.setUpdatedOn(rs.getDate("updated_on"));
        therapyMedication.setStatus(EntityStatus.get(rs.getInt("status")));

        return therapyMedication;
    }
}
