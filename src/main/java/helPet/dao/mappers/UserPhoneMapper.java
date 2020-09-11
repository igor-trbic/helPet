package helPet.dao.mappers;

import helPet.entity.UserPhone;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPhoneMapper implements RowMapper<UserPhone> {
    @Override
    public UserPhone map(ResultSet rs, StatementContext ctx) throws SQLException {
        UserPhone userPhone = new UserPhone();

        userPhone.setId(rs.getLong("id"));
        userPhone.setPhoneId(rs.getLong("user_phone_id"));
        userPhone.setUserId(rs.getLong("user_id"));


        return userPhone;
    }
}
