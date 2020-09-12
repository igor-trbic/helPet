package helPet.dao.mappers;

import helPet.entity.BusinessPhone;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessPhoneMapper implements RowMapper<BusinessPhone> {
    @Override
    public BusinessPhone map(ResultSet rs, StatementContext ctx) throws SQLException {
        BusinessPhone businessPhone = new BusinessPhone();

        businessPhone.setId(rs.getLong("id"));
        businessPhone.setBusinessId(rs.getLong("business_id"));
        businessPhone.setPhoneId(rs.getLong("phone_id"));

        return businessPhone;
    }
}
