package helPet.dao;

import helPet.dao.mappers.UserMapper;
import helPet.entity.User;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface UserDAO extends Transactional<UserDAO> {
    @SqlUpdate("INSERT INTO public.user (id, username, password, first_name, last_name, date_of_birth, status, created_on, created_by, updated_on, updated_by " +
               " ) VALUES ( nextval('users_sequence'), :username, :password, :firstName, :lastName, :dateOfBirth, :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean User user);

    @SqlUpdate("UPDATE public.user SET username = :username, password = :password, first_name = :firstName, last_name = :lastName, date_of_birth = :dateOfBirth, status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean User user);

    @SqlQuery("SELECT * FROM public.user WHERE id = :id AND status != 109")
    @UseRowMapper(UserMapper.class)
    User find(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.user SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);

}
