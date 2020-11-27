package helPet.managers;

import helPet.entity.Sample;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleManager {

    private static final Logger LOG = LoggerFactory.getLogger(SampleManager.class);
    private Jdbi dbi;

    public SampleManager(Jdbi dbi) {
        this.dbi=dbi;
    }

    public Boolean sampleMe(Sample sample) {
        if (sample.getReturnMe()) {
            return true;
        }
        return false;
    }

}
