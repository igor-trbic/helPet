package helPet.dao;

import helPet.dao.mappers.TherapyMapper;
import helPet.entity.Therapy;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface TherapyDAO extends Transactional<TherapyDAO> {
    @SqlUpdate("INSERT INTO public.therapy (id, status, created_on, created_by, updated_on, updated_by " +
               " ) VALUES ( nextval('therapy_seq'), :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean Therapy therapy);

    @SqlUpdate("UPDATE public.therapy SET status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean Therapy therapy);

    @SqlQuery("SELECT * FROM public.therapy WHERE id = :id AND status != 109")
    @UseRowMapper(TherapyMapper.class)
    Therapy findActive(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.therapy SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);
}
