package helPet.dao;

import helPet.dao.mappers.MedicationMapper;
import helPet.entity.Medication;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface MedicationDAO extends Transactional<PetAttributeDAO> {
    @SqlUpdate("INSERT INTO public.medication (id, name, description, dose, status, created_on, created_by, updated_on, updated_by " +
               " ) VALUES ( nextval('medication_seq'), :name, :description, :dose, :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean Medication medication);

    @SqlUpdate("UPDATE public.medication SET name = :name, description = :description, dose = :dose, status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean Medication medication);

    @SqlQuery("SELECT * FROM public.medication WHERE id = :id AND status != 109")
    @UseRowMapper(MedicationMapper.class)
    Medication findActive(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.medication SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);
}
