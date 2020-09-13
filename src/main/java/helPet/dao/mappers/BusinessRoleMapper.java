package helPet.dao.mappers;

import helPet.entity.BusinessRole;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessRoleMapper implements RowMapper<BusinessRole> {
    @Override
    public BusinessRole map(ResultSet rs, StatementContext ctx) throws SQLException {
        BusinessRole businessRole = new BusinessRole();

        businessRole.setId(rs.getLong("id"));
        businessRole.setBusinessRoleTypeId(rs.getLong("business_role_type_id"));
        businessRole.setBusinessStaffId(rs.getLong("business_staff_id"));
        businessRole.setCreatedBy(rs.getString("created_by"));
        businessRole.setCreatedOn(rs.getDate("created_on"));
        businessRole.setUpdatedBy(rs.getString("updated_by"));
        businessRole.setUpdatedOn(rs.getDate("updated_on"));
        businessRole.setStatus(EntityStatus.get(rs.getInt("status")));

        return businessRole;
    }
}
