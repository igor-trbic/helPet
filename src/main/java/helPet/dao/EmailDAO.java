package helPet.dao;

import helPet.dao.mappers.EmailMapper;
import helPet.entity.Email;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface EmailDAO extends Transactional<EmailDAO> {
    @SqlUpdate("INSERT INTO public.email (id, email_address, is_primary, email_type, user_id, status, created_on, created_by, updated_on, updated_by " +
               " ) VALUES ( nextval('email_seq'), :emailAddress, :isPrimary, :emailType, :userId, :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean Email email);

    @SqlUpdate("UPDATE public.email SET email_address = :emailAddress, is_primary = :isPrimary, email_type = :emailType, user_id = :userId, status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean Email email);

    @SqlQuery("SELECT * FROM public.email WHERE id = :id AND status != 109")
    @UseRowMapper(EmailMapper.class)
    Email findActive(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.email SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);
}
