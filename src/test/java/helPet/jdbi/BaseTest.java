package helPet.jdbi;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import helPet.dao.common.EntityStatusAsIntArgFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.lifecycle.setup.LifecycleEnvironment;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.guava.GuavaPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class BaseTest {

    protected static final JdbiFactory factory = new JdbiFactory();
    protected static Jdbi dbi;
    protected static Environment environment = mock(Environment.class);
    private static final HealthCheckRegistry healthChecks = mock(HealthCheckRegistry.class);
    private static final LifecycleEnvironment lifecycleEnvironment = mock(LifecycleEnvironment.class);
    private static final MetricRegistry metricRegistry = new MetricRegistry();
    private static Handle h;

    protected static final String STR_SAMPLE_1 = "_DOG_";
    protected static final String STR_SAMPLE_2 = "_WANTS_";
    protected static final String STR_SAMPLE_3 = "_OUT_";
    protected static final String CREATED_BY = "__test__";

    @BeforeClass
    public static void setUpOnce() throws Exception {

        DataSourceFactory postgresqlConfig = new DataSourceFactory();
        postgresqlConfig.setUrl("jdbc:postgresql://localhost/helpet");
        postgresqlConfig.setUser("helpet");
        postgresqlConfig.setPassword("helpet");
        postgresqlConfig.setDriverClass("org.postgresql.Driver");
        postgresqlConfig.setValidationQuery("SELECT 1");
        postgresqlConfig.setAutoCommitByDefault(false);
        postgresqlConfig.setMaxSize(40);
        postgresqlConfig.setMinSize(8);
        if (dbi == null) {

            when(environment.healthChecks()).thenReturn(healthChecks);
            when(environment.lifecycle()).thenReturn(lifecycleEnvironment);
            when(environment.metrics()).thenReturn(metricRegistry);

            dbi = factory.build(environment, postgresqlConfig, "postgresql");
            dbi.registerArgument(new EntityStatusAsIntArgFactory());

            dbi.installPlugin(new SqlObjectPlugin());
            dbi.installPlugin(new GuavaPlugin());

        }

        h = dbi.open().begin();

    }

    @AfterClass
    public static void tearDownOnce() throws Exception {
        h.rollback().close();
    }
}
