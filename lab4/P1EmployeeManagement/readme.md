# a) Identify a couple of examples on the use of AssertJ expressive methods chaining.

EmployeeRepositoryTest line 65:
``` assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName()); ```

EmployeeService_UnitTest line 103:
``` assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName()); ```


# b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).
In the test class "EmployeeService_UnitTest" we mock the behavior of the repository to avoid involving a database and test only the "EmployeeServiceImpl".

# c) What is the difference between standard @Mock and @MockBean?
The @Mock annotation is useful to create a mock object of a class or an interface, allowing that object to take the place of a real implementation of a class. It should be used in a Unit Test class.
The @MockBean annotation also creates a mock object but adds that object to a Spring test context allowing it to be used to test the integration of multiple classes.
More information in:
 - https://www.baeldung.com/java-spring-mockito-mock-mockbean
 - https://rieckpil.de/difference-between-mock-and-mockbean-spring-boot-applications/?fbclid=IwAR0810pOVT--atBthjXesRIfMS4sDI1I0UA5fLzk0N8jMJ1UTs_shfF41Vk

# d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?
This file can contain several spring boot properties. In this case it tells our application which database should be used and how to access it. It is used when we have @TestPropertySource annotation with the argument "locations" referring to it in one of our test classes allowing us to make tests with a real database.