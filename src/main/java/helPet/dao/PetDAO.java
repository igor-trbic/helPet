package helPet.dao;

import helPet.dao.mappers.PetMapper;
import helPet.entity.Pet;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.List;

public interface PetDAO extends Transactional<PetDAO> {
    @SqlUpdate("INSERT INTO public.pet (id, name, date_of_birth, note, status, created_on, created_by, updated_on, updated_by " +
               " ) VALUES ( nextval('pet_seq'), :name, :dateOfBirth, :note, :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean Pet pet);

    @SqlUpdate("UPDATE public.pet SET name = :name, date_of_birth = :dateOfBirth, note = :note, status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean Pet pet);

    @SqlQuery("SELECT * FROM public.pet WHERE id = :id AND status != 109")
    @UseRowMapper(PetMapper.class)
    Pet findActive(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.pet SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);

    @SqlQuery("SELECT * FROM public.pet p JOIN public.pet_owner po ON (po.pet_id = p.id) WHERE po.user_id = :userId AND p.status != 109")
    @UseRowMapper(PetMapper.class)
    List<Pet> findByUserId(@Bind("userId") Long userId);
}
