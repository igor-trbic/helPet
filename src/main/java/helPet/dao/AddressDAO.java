package helPet.dao;

import helPet.dao.mappers.AddressMapper;
import helPet.entity.Address;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.List;

public interface AddressDAO extends Transactional<AddressDAO> {
    @SqlUpdate("INSERT INTO public.address (id, street_name, house_number, postal_code, address_type, status, created_on, created_by, updated_on, updated_by " +
               " ) VALUES ( nextval('address_seq'), :streetName, :houseNumber, :postalCode, :addressType, :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean Address address);

    @SqlUpdate("UPDATE public.address SET street_name = :streetName, house_number = :houseNumber, postal_code = :postalCode, address_type = :addressType, status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean Address address);

    @SqlQuery("SELECT * FROM public.address WHERE id = :id AND status != 109")
    @UseRowMapper(AddressMapper.class)
    Address findActive(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.address SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);

    @SqlQuery("SELECT * FROM public.address a JOIN public.user_address ua ON (ua.address_id = a.id) WHERE ua.user_id = :userId AND status != 109")
    @UseRowMapper(AddressMapper.class)
    List<Address> findByUserId(@Bind("userId") Long userId);
}
