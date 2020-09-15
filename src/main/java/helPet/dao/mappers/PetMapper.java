package helPet.dao.mappers;

import helPet.entity.Pet;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetMapper implements RowMapper<Pet> {
    @Override
    public Pet map(ResultSet rs, StatementContext ctx) throws SQLException {
        Pet pet = new Pet();

        pet.setId(rs.getLong("id"));
        pet.setName(rs.getString("name"));
        pet.setNote(rs.getString("note"));
        pet.setDateOfBirth(rs.getDate("date_of_birth"));
        pet.setCreatedBy(rs.getString("created_by"));
        pet.setCreatedOn(rs.getDate("created_on"));
        pet.setUpdatedBy(rs.getString("updated_by"));
        pet.setUpdatedOn(rs.getDate("updated_on"));
        pet.setStatus(EntityStatus.get(rs.getInt("status")));

        return pet;
    }
}
