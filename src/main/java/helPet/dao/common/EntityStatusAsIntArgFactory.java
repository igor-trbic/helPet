package helPet.dao.common;

import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class EntityStatusAsIntArgFactory extends AbstractArgumentFactory<EntityStatus> {

    public EntityStatusAsIntArgFactory() {
        super(Types.INTEGER);
    }

    @Override
    public Argument build(final EntityStatus value, ConfigRegistry config) {
        return new Argument() {
            @Override
            public void apply(int position, PreparedStatement statement, StatementContext ctx) throws SQLException {
                statement.setInt(position, value.getCode());
            }
        };
    }
}
