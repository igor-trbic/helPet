package helPet.dao;

import helPet.dao.mappers.AuthTokenAttributeMapper;
import helPet.entity.AuthTokenAttribute;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.List;

public interface AuthTokenAttributeDAO extends Transactional<AuthTokenAttributeDAO> {
    @SqlUpdate("INSERT INTO public.auth_token_attribute (attr_name, attr_value, created_on, created_by, token_user_auth_token" +
            " ) VALUES ( :attrName, :attrValue, localtimestamp, :createdBy, :tokenUserAuthToken)")
    @GetGeneratedKeys("token_user_auth_token")
    String insert(@BindBean AuthTokenAttribute authTokenAttribute);

    @SqlQuery("SELECT * FROM public.auth_token_attribute WHERE token_user_auth_token = :token")
    @UseRowMapper(AuthTokenAttributeMapper.class)
    List<AuthTokenAttribute> findByToken(@Bind("token") String token);

    @SqlQuery("SELECT * FROM public.auth_token_attribute WHERE token_user_auth_token = :token and attr_name = :attrName")
    @UseRowMapper(AuthTokenAttributeMapper.class)
    AuthTokenAttribute findByTokenAndAttrName(@Bind("token") String token,
                                              @Bind("attrName") String attrName);

    @SqlUpdate("DELETE public.auth_token_attribute  WHERE token_user_auth_token = :token")
    int remove(@Bind("token") String token);
}
