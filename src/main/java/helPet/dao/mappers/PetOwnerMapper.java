package helPet.dao.mappers;

import helPet.entity.PetOwner;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetOwnerMapper implements RowMapper<PetOwner> {
    @Override
    public PetOwner map(ResultSet rs, StatementContext ctx) throws SQLException {
        PetOwner petOwner = new PetOwner();

        petOwner.setId(rs.getLong("id"));
        petOwner.setPetId(rs.getLong("pet_id"));
        petOwner.setUserId(rs.getLong("user_id"));

        return petOwner;
    }
}
