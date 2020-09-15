package helPet.dao;

import helPet.dao.mappers.PetAttributeMapper;
import helPet.entity.PetAttribute;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface PetAttributeDAO extends Transactional<PetAttributeDAO> {
    @SqlUpdate("INSERT INTO public.pet_attribute (id, name, value, pet_id, status, created_on, created_by, updated_on, updated_by " +
            " ) VALUES ( nextval('pet_attribute_seq'), :name, :value, :petId, :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean PetAttribute petAttribute);

    @SqlUpdate("UPDATE public.pet_attribute SET name = :name, value = :value, pet_id = :petId, status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean PetAttribute petAttribute);

    @SqlQuery("SELECT * FROM public.pet_attribute WHERE id = :id AND status != 109")
    @UseRowMapper(PetAttributeMapper.class)
    PetAttribute findActive(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.pet_attribute SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);
}
