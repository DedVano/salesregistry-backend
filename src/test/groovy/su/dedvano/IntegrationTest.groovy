package su.dedvano

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
abstract class IntegrationTest extends Specification {

    private static final def DB = 'sales'
    private static final def USER = 'sales'
    private static final def PWD = '12345'
    private static final def EXPOSED_PORT = 5432

    private static final GenericContainer PG =
            new GenericContainer("postgres:15")
                    .withEnv('POSTGRES_USER', USER)
                    .withEnv('POSTGRES_DB', DB)
                    .withEnv('POSTGRES_PASSWORD', PWD)
                    .withExposedPorts(EXPOSED_PORT)

    def setupSpec() {
        PG.start()
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add('spring.datasource.url') { jdbcUrlToContainer() }
        propertyRegistry.add('spring.datasource.username') { USER }
        propertyRegistry.add('spring.datasource.password') { PWD }
    }

    private static def jdbcUrlToContainer() {
        def host = PG.getHost()
        def port = PG.getMappedPort(EXPOSED_PORT)

        return "jdbc:postgresql://$host:$port/$DB"
    }

}
