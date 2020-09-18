package helPet.dao;

import helPet.dao.mappers.DiagnoseMapper;
import helPet.entity.Diagnose;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface DiagnoseDAO extends Transactional<DiagnoseDAO> {
    @SqlUpdate("INSERT INTO public.diagnose (id, name, description" +
               " ) VALUES ( nextval('diagnose_seq'), :name, :description)")
    @GetGeneratedKeys
    long insert(@BindBean Diagnose diagnose);

    @SqlUpdate("UPDATE public.diagnose SET name = :name, description = :description WHERE id = :id")
    int update(@BindBean Diagnose diagnose);

    @SqlQuery("SELECT * FROM public.diagnose WHERE id = :id")
    @UseRowMapper(DiagnoseMapper.class)
    Diagnose find(@Bind("id") Long id);

    @SqlUpdate("DELETE FROM diagnose WHERE id = :id")
    int delete(@Bind("id") Long id);
}
