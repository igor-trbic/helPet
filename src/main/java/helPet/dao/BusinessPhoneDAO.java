package helPet.dao;

import helPet.dao.mappers.BusinessPhoneMapper;
import helPet.entity.BusinessPhone;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface BusinessPhoneDAO extends Transactional<UserAddressDAO> {
    @SqlUpdate("INSERT INTO public.business_phone (id, phone_id, business_id" +
            " ) VALUES ( nextval('business_phone_seq'), :phoneId, :businessId)")
    @GetGeneratedKeys
    long insert(@BindBean BusinessPhone businessPhone);

    @SqlUpdate("UPDATE public.business_phone SET phin = :addressId, business_id = :businessId WHERE id = :id")
    int update(@BindBean BusinessPhone businessPhone);

    @SqlQuery("SELECT * FROM public.business_phone WHERE id = :id")
    @UseRowMapper(BusinessPhoneMapper.class)
    BusinessPhone find(@Bind("id") Long id);

    @SqlUpdate("DELETE FROM public.business_phone WHERE id = :id")
    int delete(@Bind("id") Long id);
}
