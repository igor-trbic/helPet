package helPet.dao;

import helPet.dao.mappers.UserAddressMapper;
import helPet.entity.User;
import helPet.entity.UserAddress;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface UserAddressDAO extends Transactional<UserAddressDAO> {
    @SqlUpdate("INSERT INTO public.user_address (id, address_id, user_id" +
               " ) VALUES ( nextval('user_address_seq'), :addressId, :userId)")
    @GetGeneratedKeys
    long insert(@BindBean UserAddress userAddress);

    @SqlUpdate("UPDATE public.user_address SET address_id = :addressId, user_id = :userId WHERE id = :id")
    int update(@BindBean UserAddress user);

    @SqlQuery("SELECT * FROM public.user_address WHERE id = :id")
    @UseRowMapper(UserAddressMapper.class)
    User find(@Bind("id") Long id);

    @SqlUpdate("DELETE FROM user_address WHERE id = :id")
    int delete(@Bind("id") Long id);
}
