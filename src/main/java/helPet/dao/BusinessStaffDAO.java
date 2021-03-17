package helPet.dao;

import helPet.dao.mappers.BusinessStaffMapper;
import helPet.entity.BusinessStaff;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface BusinessStaffDAO extends Transactional<UserAddressDAO> {
    @SqlUpdate("INSERT INTO public.business_staff (id, business_id, user_id" +
            " ) VALUES ( nextval('business_staff_seq'), :businessId, :userId)")
    @GetGeneratedKeys
    long insert(@BindBean BusinessStaff businessStaff);

    @SqlUpdate("UPDATE public.business_staff SET business_id = :businessId, user_id = :userId WHERE id = :id")
    int update(@BindBean BusinessStaff businessStaff);

    @SqlQuery("SELECT * FROM public.business_staff WHERE id = :id")
    @UseRowMapper(BusinessStaffMapper.class)
    BusinessStaff find(@Bind("id") Long id);

    @SqlUpdate("DELETE FROM business_staff WHERE id = :id")
    int delete(@Bind("id") Long id);

    @SqlQuery("SELECT * FROM public.business_staff WHERE user_id = :userId")
    @UseRowMapper(BusinessStaffMapper.class)
    BusinessStaff findByUserId(@Bind("userId") Long userId);
}
