package helPet.dao;

import helPet.entity.Diagnose;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DiagnoseDAOTest extends BaseTest {
    @Test
    public void testDiagnose() {
        Handle h = dbi.open();
        DiagnoseDAO diagnoseDAO = h.attach(DiagnoseDAO.class);

        Diagnose diagnose = new Diagnose();
        diagnose.setDescription(STR_SAMPLE_1);
        diagnose.setName(STR_SAMPLE_2);

        h.begin();

        long diagnoseId = diagnoseDAO.insert(diagnose);
        diagnose.setId(diagnoseId);

        Diagnose found = diagnoseDAO.find(diagnose.getId());
        assertNotNull(found);
        assertEquals(found.getDescription(), STR_SAMPLE_1);

        diagnose.setDescription(STR_SAMPLE_3);

        int updated = diagnoseDAO.update(diagnose);
        assertNotNull(updated);
        assertEquals(diagnose.getDescription(), STR_SAMPLE_3);

        int del = diagnoseDAO.delete(diagnose.getId());
        assertNotNull(del);

        Diagnose deleted = diagnoseDAO.find(diagnose.getId());
        assertNull(deleted);

        h.rollback();
        h.close();
    }
}
