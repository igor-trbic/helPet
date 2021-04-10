package helPet.dao.mappers;

import helPet.dto.BusinessStaffDTO;
import helPet.entity.BusinessRoleType;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessStaffDTOMapper implements RowMapper<BusinessStaffDTO> {
    @Override
    public BusinessStaffDTO map(ResultSet rs, StatementContext ctx) throws SQLException {
        BusinessStaffDTO businessStaff = new BusinessStaffDTO();

        businessStaff.setId(rs.getLong("id"));
        businessStaff.setBusinessId(rs.getLong("business_id"));
//        businessStaff.setBusinessName(rs.getString("business_name"));
        businessStaff.setDateOfBirth(rs.getDate("date_of_birth"));
        businessStaff.setFirstName(rs.getString("first_name"));
        businessStaff.setLastName(rs.getString("last_name"));
        businessStaff.setUsername(rs.getString("username"));
        businessStaff.setCreatedBy(rs.getString("created_by"));
        businessStaff.setCreatedOn(rs.getTimestamp("created_on"));

        BusinessRoleType businessRoleType = new BusinessRoleType();
        businessRoleType.setBusinessRoleName(rs.getString("business_role_name"));
        businessRoleType.setBusinessId(rs.getLong("business_id"));
        businessRoleType.setId(rs.getLong("business_role_type_id"));

        businessStaff.setBusinessRoleType(businessRoleType);


        return businessStaff;
    }
}
