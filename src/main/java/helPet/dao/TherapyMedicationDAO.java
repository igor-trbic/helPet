package helPet.dao;

import helPet.dao.mappers.TherapyMedicationMapper;
import helPet.entity.TherapyMedication;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface TherapyMedicationDAO extends Transactional<TherapyMedicationDAO> {
    @SqlUpdate("INSERT INTO public.therapy_medication (id, from_date, thru_date, description, times, times_per, therapy_id, medication_id, status, created_on, created_by, updated_on, updated_by " +
               " ) VALUES ( nextval('therapy_medication_seq'), :fromDate, :thruDate, :description, :times, :timesPer, :therapyId, :medicationId, :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean TherapyMedication therapyMedication);

    @SqlUpdate("UPDATE public.therapy_medication SET from_date = :fromDate, thru_date =:thruDate, description = :description, times = :times, times_per = :timesPer, therapy_id = :therapyId, medication_id = :medicationId, status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean TherapyMedication therapyMedication);

    @SqlQuery("SELECT * FROM public.therapy_medication WHERE id = :id and STATUS != 109")
    @UseRowMapper(TherapyMedicationMapper.class)
    TherapyMedication findActive(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.therapy_medication SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);
}
