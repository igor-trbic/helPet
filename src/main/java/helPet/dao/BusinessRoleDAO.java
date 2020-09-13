package helPet.dao;

import helPet.dao.mappers.BusinessRoleMapper;
import helPet.entity.BusinessRole;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface BusinessRoleDAO extends Transactional<BusinessRoleDAO> {
    @SqlUpdate("INSERT INTO public.business_role (id, business_role_type_id, business_staff_id, status, created_on, created_by, updated_on, updated_by " +
               " ) VALUES ( nextval('business_role_seq'), :businessRoleTypeId, :businessStaffId, :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean BusinessRole businessRole);

    @SqlUpdate("UPDATE public.business_role SET business_role_type_id = :businessRoleTypeId, business_staff_id = :businessStaffId, status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean BusinessRole businessRole);

    @SqlQuery("SELECT * FROM public.business_role WHERE id = :id AND status != 109")
    @UseRowMapper(BusinessRoleMapper.class)
    BusinessRole findActive(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.business_role SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);
}
