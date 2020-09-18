package helPet.dao.mappers;

import helPet.entity.Diagnose;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiagnoseMapper implements RowMapper<Diagnose> {
    @Override
    public Diagnose map(ResultSet rs, StatementContext ctx) throws SQLException {
        Diagnose diagnose = new Diagnose();

        diagnose.setId(rs.getLong("id"));
        diagnose.setName(rs.getString("name"));
        diagnose.setDescription(rs.getString("description"));

        return diagnose;
    }
}
