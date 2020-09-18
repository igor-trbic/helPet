package helPet.dao.mappers;

import helPet.entity.Business;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessMapper implements RowMapper<Business> {
    @Override
    public Business map(ResultSet rs, StatementContext ctx) throws SQLException {
        Business business = new Business();

        business.setId(rs.getLong("id"));
        business.setBusinessName(rs.getString("business_name"));
        business.setBusinessOwnerId(rs.getLong("business_owner_id"));
        business.setTaxId(rs.getString("tax_id"));
        business.setNationalId(rs.getString("national_id"));
        business.setCreatedBy(rs.getString("created_by"));
        business.setCreatedOn(rs.getDate("created_on"));
        business.setUpdatedBy(rs.getString("updated_by"));
        business.setUpdatedOn(rs.getDate("updated_on"));
        business.setStatus(EntityStatus.get(rs.getInt("status")));

        return business;
    }
}
