package helPet.dao;

import helPet.dao.mappers.PhoneMapper;
import helPet.entity.Phone;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.List;

public interface PhoneDAO extends Transactional<AddressDAO> {
    @SqlUpdate("INSERT INTO public.phone (id, phone_number, phone_type, status, created_on, created_by, updated_on, updated_by " +
               " ) VALUES ( nextval('phone_sequence'), :phoneNumber, :phoneType, :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean Phone phone);

    @SqlUpdate("UPDATE public.phone SET phone_number = :phoneNumber, phone_type = :phoneType, status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean Phone phone);

    @SqlQuery("SELECT * FROM public.phone WHERE id = :id AND status != 109")
    @UseRowMapper(PhoneMapper.class)
    Phone findActive(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.phone SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);

    @SqlQuery("SELECT * FROM public.phone p JOIN public.user_phone up ON (up.phone_id = p.id) WHERE up.user_id = :userId AND status != 109")
    @UseRowMapper(PhoneMapper.class)
    List<Phone> findByUserId(@Bind("userId") Long userId);

    @SqlQuery("SELECT * FROM public.phone p JOIN public.business_phone bp ON (bp.phone_id = p.id) WHERE bp.business_id = :businessId AND status != 109")
    @UseRowMapper(PhoneMapper.class)
    List<Phone> findByBusinessId(@Bind("businessId") Long businessId);
}
