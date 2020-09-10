package helPet.dao.mappers;

import helPet.entity.UserAddress;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAddressMapper implements RowMapper<UserAddress> {
    @Override
    public UserAddress map(ResultSet rs, StatementContext ctx) throws SQLException {
        UserAddress user = new UserAddress();

        user.setId(rs.getLong("id"));
        user.setAddressId(rs.getLong("address_id"));
        user.setUserId(rs.getLong("user_id"));

        return user;
    }
}
