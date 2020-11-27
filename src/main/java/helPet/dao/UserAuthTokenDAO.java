package helPet.dao;

import helPet.dao.mappers.UserAuthTokenMapper;
import helPet.entity.UserAuthToken;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface UserAuthTokenDAO extends Transactional<UserDAO> {
    @SqlUpdate("INSERT INTO public.user_auth_token (token, user_id, created_on, expiry_time" +
               " ) VALUES ( :token, :userId, :createdOn, :expiryTime)")
    @GetGeneratedKeys
    long insert(@BindBean UserAuthToken userAuthToken);

    @SqlQuery("SELECT * FROM public.user_auth_token WHERE token = :token")
    @UseRowMapper(UserAuthTokenMapper.class)
    UserAuthToken findByToken(@Bind("token") String token);

    @SqlUpdate("DELETE FROM public.user_auth_token WHERE userId = :userId AND token = :token")
    int delete(@Bind("userId") Long userId, @Bind("user") String user);
}
