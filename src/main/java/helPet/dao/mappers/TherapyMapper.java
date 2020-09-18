package helPet.dao.mappers;

import helPet.entity.Therapy;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TherapyMapper implements RowMapper<Therapy> {
    @Override
    public Therapy map(ResultSet rs, StatementContext ctx) throws SQLException {
        Therapy therapy = new Therapy();

        therapy.setId(rs.getLong("id"));
        therapy.setCreatedBy(rs.getString("created_by"));
        therapy.setCreatedOn(rs.getDate("created_on"));
        therapy.setUpdatedBy(rs.getString("updated_by"));
        therapy.setUpdatedOn(rs.getDate("updated_on"));
        therapy.setStatus(EntityStatus.get(rs.getInt("status")));

        return therapy;
    }
}
