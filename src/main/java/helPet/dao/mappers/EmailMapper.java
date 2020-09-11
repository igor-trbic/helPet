package helPet.dao.mappers;

import helPet.entity.Email;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailMapper implements RowMapper<Email> {
    @Override
    public Email map(ResultSet rs, StatementContext ctx) throws SQLException {
        Email email = new Email();

        email.setId(rs.getLong("id"));
        email.setEmailAddress(rs.getString("email_address"));
        email.setEmailType(rs.getString("email_type"));
        email.setIsPrimary(rs.getBoolean("is_primary"));
        email.setUserId(rs.getLong("user_id"));
        email.setCreatedBy(rs.getString("created_by"));
        email.setCreatedOn(rs.getDate("created_on"));
        email.setUpdatedBy(rs.getString("updated_by"));
        email.setUpdatedOn(rs.getDate("updated_on"));
        email.setStatus(EntityStatus.get(rs.getInt("status")));

        return email;
    }
}
