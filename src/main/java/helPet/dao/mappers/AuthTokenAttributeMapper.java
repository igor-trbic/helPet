package helPet.dao.mappers;

import helPet.entity.AuthTokenAttribute;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthTokenAttributeMapper implements RowMapper<AuthTokenAttribute> {
    @Override
    public AuthTokenAttribute map(ResultSet rs, StatementContext ctx) throws SQLException {
        AuthTokenAttribute authTokenAttribute = new AuthTokenAttribute();

        authTokenAttribute.setAttrName(rs.getString("attr_name"));
        authTokenAttribute.setAttrValue(rs.getString("attr_value"));
        authTokenAttribute.setCreatedBy(rs.getString("created_by"));
        authTokenAttribute.setCreatedOn(rs.getDate("created_on"));
        authTokenAttribute.setTokenUserAuthToken(rs.getString("token_user_auth_token"));

        return authTokenAttribute;
    }
}
