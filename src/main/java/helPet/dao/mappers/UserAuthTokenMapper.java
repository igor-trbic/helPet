package helPet.dao.mappers;

import helPet.entity.UserAuthToken;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthTokenMapper implements RowMapper<UserAuthToken> {
    @Override
    public UserAuthToken map(ResultSet rs, StatementContext ctx) throws SQLException {
        UserAuthToken userAuthToken = new UserAuthToken();

        userAuthToken.setToken(rs.getString("token"));
        userAuthToken.setCreatedOn(rs.getTimestamp("created_on"));
        userAuthToken.setExpiryTime(rs.getTimestamp("expiry_time"));
        userAuthToken.setUserId(rs.getLong("user_id"));

        return userAuthToken;
    }
}
