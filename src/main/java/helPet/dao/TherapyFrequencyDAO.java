package helPet.dao;

import helPet.dao.mappers.TherapyFrequencyMapper;
import helPet.entity.TherapyFrequency;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface TherapyFrequencyDAO extends Transactional<TherapyFrequencyDAO> {
    @SqlUpdate("INSERT INTO public.therapy_frequency (id, from_date, thru_date, description, times, times_per, therapy_id, status, created_on, created_by, updated_on, updated_by " +
               " ) VALUES ( nextval('therapy_frequency_seq'), :fromDate, :thruDate, :description, :times, :timesPer, :therapyId, :status, localtimestamp, :createdBy, null, null)")
    @GetGeneratedKeys
    long insert(@BindBean TherapyFrequency therapyFrequency);

    @SqlUpdate("UPDATE public.therapy_frequency SET from_date = :fromDate, thru_date =:thruDate, description = :description, times = :times, times_per = :timesPer, therapy_id = :therapyId, status = :status, updated_on = localtimestamp, updated_by = :updatedBy WHERE id = :id")
    int update(@BindBean TherapyFrequency therapyFrequency);

    @SqlQuery("SELECT * FROM public.therapy_frequency WHERE id = :id and STATUS != 109")
    @UseRowMapper(TherapyFrequencyMapper.class)
    TherapyFrequency findActive(@Bind("id") Long id);

    @SqlUpdate("UPDATE public.therapy_frequency SET status = 109, updated_by = :user, updated_on = localtimestamp WHERE id = :id")
    int remove(@Bind("id") Long id, @Bind("user") String user);
}
