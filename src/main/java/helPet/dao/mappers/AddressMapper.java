package helPet.dao.mappers;

import helPet.entity.Address;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements RowMapper<Address> {
    @Override
    public Address map(ResultSet rs, StatementContext ctx) throws SQLException {
        Address address = new Address();

        address.setId(rs.getLong("id"));
        address.setStreetName(rs.getString("street_name"));
        address.setAddressType(rs.getString("address_type"));
        address.setHouseNumber(rs.getString("house_number"));
        address.setPostalCode(rs.getString("postal_code"));
        address.setAddressType(rs.getString("address_type"));
        address.setCreatedBy(rs.getString("created_by"));
        address.setCreatedOn(rs.getDate("created_on"));
        address.setUpdatedBy(rs.getString("updated_by"));
        address.setUpdatedOn(rs.getDate("updated_on"));
        address.setStatus(EntityStatus.get(rs.getInt("status")));

        return address;
    }
}
