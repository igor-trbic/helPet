package helPet.dao.mappers;

import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet rs, StatementContext ctx) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("id"));
        user.setDateOfBirth(rs.getDate("date_of_birth"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setUsername(rs.getString("username"));
        user.setCreatedBy(rs.getString("created_by"));
        user.setCreatedOn(rs.getDate("created_on"));
        user.setUpdatedBy(rs.getString("updated_by"));
        user.setUpdatedOn(rs.getDate("updated_on"));
        user.setPassword(rs.getString("password"));
        user.setStatus(EntityStatus.get(rs.getInt("status")));

        return user;
    }
}
