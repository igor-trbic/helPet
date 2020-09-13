package helPet.dao;

import helPet.dao.mappers.BusinessAddressMapper;
import helPet.entity.BusinessAddress;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface BusinessAddressDAO extends Transactional<BusinessAddressDAO> {
    @SqlUpdate("INSERT INTO public.business_address (id, address_id, business_id" +
               " ) VALUES ( nextval('business_address_seq'), :addressId, :businessId)")
    @GetGeneratedKeys
    long insert(@BindBean BusinessAddress businessAddress);

    @SqlUpdate("UPDATE public.business_address SET address_id = :addressId, business_id = :businessId WHERE id = :id")
    int update(@BindBean BusinessAddress businessAddress);

    @SqlQuery("SELECT * FROM public.business_address WHERE id = :id")
    @UseRowMapper(BusinessAddressMapper.class)
    BusinessAddress find(@Bind("id") Long id);

    @SqlUpdate("DELETE FROM public.business_address WHERE id = :id")
    int delete(@Bind("id") Long id);
}
