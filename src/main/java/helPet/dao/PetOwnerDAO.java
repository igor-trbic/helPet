package helPet.dao;

import helPet.dao.mappers.PetOwnerMapper;
import helPet.entity.PetOwner;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface PetOwnerDAO extends Transactional<PetOwnerDAO> {
    @SqlUpdate("INSERT INTO public.pet_owner (id, pet_id, user_id" +
               " ) VALUES ( nextval('pet_owner_seq'), :petId, :userId)")
    @GetGeneratedKeys
    long insert(@BindBean PetOwner petOwner);

    @SqlUpdate("UPDATE public.pet_owner SET pet_id = :petId, user_id = :userId WHERE id = :id")
    int update(@BindBean PetOwner petOwner);

    @SqlQuery("SELECT * FROM public.pet_owner WHERE id = :id")
    @UseRowMapper(PetOwnerMapper.class)
    PetOwner find(@Bind("id") Long id);

    @SqlUpdate("DELETE FROM public.pet_owner WHERE id = :id")
    int delete(@Bind("id") Long id);
}
