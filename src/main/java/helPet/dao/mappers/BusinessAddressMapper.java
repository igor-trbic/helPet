package helPet.dao.mappers;

import helPet.entity.BusinessAddress;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessAddressMapper implements RowMapper<BusinessAddress> {
    @Override
    public BusinessAddress map(ResultSet rs, StatementContext ctx) throws SQLException {
        BusinessAddress businessAddress = new BusinessAddress();

        businessAddress.setId(rs.getLong("id"));
        businessAddress.setBusinessId(rs.getLong("business_id"));
        businessAddress.setAddressId(rs.getLong("address_id"));

        return businessAddress;
    }
}
