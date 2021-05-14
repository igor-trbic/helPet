package helPet.dao;

import helPet.dao.mappers.BusinessMapper;
import helPet.entity.Business;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.List;

public interface BusinessDAO extends Transactional<AddressDAO> {
    @SqlUpdate("INSERT INTO public.business (id, business_name, business_owner_id, tax_id, national_id, status, created_on, created_by, updated_on, updated_by " +
               " ) VALUES ( nextval('business_seq'), :businessName, :businessOwnerId, :taxId, :nationalId, :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean Business business);

    @SqlUpdate("UPDATE public.business SET business_name = :businessName, business_owner_id = :businessOwnerId, tax_id = :taxId, national_id = :nationalId, status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean Business business);

    @SqlQuery("SELECT * FROM public.business WHERE id = :id AND status != 109")
    @UseRowMapper(BusinessMapper.class)
    Business findActive(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.business SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);

    @SqlQuery("SELECT * FROM public.business WHERE status != 109")
    @UseRowMapper(BusinessMapper.class)
    List<Business> findAllActive();
}
