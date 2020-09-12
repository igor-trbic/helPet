package helPet.dao.mappers;

import helPet.entity.BusinessStaff;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessStaffMapper implements RowMapper<BusinessStaff> {
    @Override
    public BusinessStaff map(ResultSet rs, StatementContext ctx) throws SQLException {
        BusinessStaff businessStaff = new BusinessStaff();

        businessStaff.setId(rs.getLong("id"));
        businessStaff.setBusinessId(rs.getLong("business_id"));
        businessStaff.setUserId(rs.getLong("user_id"));

        return businessStaff;
    }
}
