package helPet.dao.mappers;

import helPet.entity.TherapyFrequency;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TherapyFrequencyMapper implements RowMapper<TherapyFrequency> {
    @Override
    public TherapyFrequency map(ResultSet rs, StatementContext ctx) throws SQLException {
        TherapyFrequency therapy = new TherapyFrequency();

        therapy.setId(rs.getLong("id"));
        therapy.setFromDate(rs.getDate("from_date"));
        therapy.setThruDate(rs.getDate("thru_date"));
        therapy.setDescription(rs.getString("description"));
        therapy.setTimes(rs.getInt("times"));
        therapy.setTimesPer(rs.getString("times_per"));
        therapy.setTherapyId(rs.getLong("therapy_id"));
        therapy.setCreatedBy(rs.getString("created_by"));
        therapy.setCreatedOn(rs.getDate("created_on"));
        therapy.setUpdatedBy(rs.getString("updated_by"));
        therapy.setUpdatedOn(rs.getDate("updated_on"));
        therapy.setStatus(EntityStatus.get(rs.getInt("status")));

        return therapy;
    }
}
