package helPet.dao.mappers;

import helPet.entity.Phone;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneMapper implements RowMapper<Phone> {
    @Override
    public Phone map(ResultSet rs, StatementContext ctx) throws SQLException {
        Phone phone = new Phone();

        phone.setId(rs.getLong("id"));
        phone.setPhoneNumber(rs.getString("phone_number"));
        phone.setPhoneType(rs.getString("phone_type"));
        phone.setCreatedBy(rs.getString("created_by"));
        phone.setCreatedOn(rs.getDate("created_on"));
        phone.setUpdatedBy(rs.getString("updated_by"));
        phone.setUpdatedOn(rs.getDate("updated_on"));
        phone.setStatus(EntityStatus.get(rs.getInt("status")));

        return phone;
    }
}
