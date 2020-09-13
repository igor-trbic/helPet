package helPet.dao;

import helPet.dao.mappers.BusinessRoleTypeMapper;
import helPet.entity.BusinessRoleType;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface BusinessRoleTypeDAO extends Transactional<BusinessRoleTypeDAO> {
    @SqlUpdate("INSERT INTO public.business_role_type (id, business_role_name, status, created_on, created_by, updated_on, updated_by " +
               " ) VALUES ( nextval('business_role_type_seq'), :businessRoleName, :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean BusinessRoleType businessRoleType);

    @SqlUpdate("UPDATE public.business_role_type SET business_role_name = :businessRoleName, status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean BusinessRoleType businessRoleType);

    @SqlQuery("SELECT * FROM public.business_role_type WHERE id = :id AND status != 109")
    @UseRowMapper(BusinessRoleTypeMapper.class)
    BusinessRoleType findActive(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.business_role_type SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);
}
