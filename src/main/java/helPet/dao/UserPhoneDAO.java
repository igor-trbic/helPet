package helPet.dao;

import helPet.dao.mappers.UserPhoneMapper;
import helPet.entity.User;
import helPet.entity.UserPhone;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface UserPhoneDAO extends Transactional<UserAddressDAO> {
    @SqlUpdate("INSERT INTO public.user_phone (id, phone_id, user_id" +
               " ) VALUES ( nextval('user_phone_seq'), :phoneId, :userId)")
    @GetGeneratedKeys
    long insert(@BindBean UserPhone userPhone);

    @SqlUpdate("UPDATE public.user_phone SET phin = :addressId, user_id = :userId WHERE id = :id")
    int update(@BindBean UserPhone userPhone);

    @SqlQuery("SELECT * FROM public.user_phone WHERE id = :id")
    @UseRowMapper(UserPhoneMapper.class)
    User find(@Bind("id") Long id);

    @SqlUpdate("DELETE FROM user_address WHERE id = :id")
    int delete(@Bind("id") Long id);
}
