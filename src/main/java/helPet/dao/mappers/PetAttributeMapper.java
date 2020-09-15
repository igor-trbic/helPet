package helPet.dao.mappers;

import helPet.entity.PetAttribute;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetAttributeMapper implements RowMapper<PetAttribute> {
    @Override
    public PetAttribute map(ResultSet rs, StatementContext ctx) throws SQLException {
        PetAttribute petAttribute = new PetAttribute();

        petAttribute.setId(rs.getLong("id"));
        petAttribute.setName(rs.getString("name"));
        petAttribute.setValue(rs.getString("value"));
        petAttribute.setPetId(rs.getLong("pet_id"));
        petAttribute.setCreatedBy(rs.getString("created_by"));
        petAttribute.setCreatedOn(rs.getDate("created_on"));
        petAttribute.setUpdatedBy(rs.getString("updated_by"));
        petAttribute.setUpdatedOn(rs.getDate("updated_on"));
        petAttribute.setStatus(EntityStatus.get(rs.getInt("status")));

        return petAttribute;
    }
}

