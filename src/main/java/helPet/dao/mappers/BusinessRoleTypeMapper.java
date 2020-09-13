package helPet.dao.mappers;

import helPet.entity.BusinessRoleType;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessRoleTypeMapper implements RowMapper<BusinessRoleType> {
    @Override
    public BusinessRoleType map(ResultSet rs, StatementContext ctx) throws SQLException {
        BusinessRoleType businessRoleType = new BusinessRoleType();

        businessRoleType.setId(rs.getLong("id"));
        businessRoleType.setBusinessRoleName(rs.getString("business_role_name"));
        businessRoleType.setCreatedBy(rs.getString("created_by"));
        businessRoleType.setCreatedOn(rs.getDate("created_on"));
        businessRoleType.setUpdatedBy(rs.getString("updated_by"));
        businessRoleType.setUpdatedOn(rs.getDate("updated_on"));
        businessRoleType.setStatus(EntityStatus.get(rs.getInt("status")));

        return businessRoleType;
    }
}
