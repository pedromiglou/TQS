package tqsua.P2Test_Containers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeWithPostgresITest {
    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
            .withUsername("duke")
            .withPassword("password")
            .withDatabaseName("test");

    @Autowired
    EmployeeRepository employeeRepository;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    @Order(1)
    void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setName("Paulo");
        employee.setEmail("paulo@something.com");
        employeeRepository.save(employee);
        System.out.println("Employee Created!");
    }

    @Test
    @Order(2)
    void testReadEmployee() {
        Employee employee = employeeRepository.findByName("Paulo");
        assert employee != null;
        System.out.println("Employee Read!");
    }
}
