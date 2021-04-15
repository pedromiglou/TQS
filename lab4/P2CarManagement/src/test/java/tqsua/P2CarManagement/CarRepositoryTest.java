package tqsua.P2CarManagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tqsua.P2CarManagement.car.Car;
import tqsua.P2CarManagement.car.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindMercedes1ByCarId_thenReturnMercedes1Car() {
        Car mercedes1 = new Car("Mercedes", "1");
        entityManager.persistAndFlush(mercedes1);

        Car found = carRepository.findByCarId(mercedes1.getCarId());
        assertThat( found ).isEqualTo(mercedes1);
    }

    @Test
    public void whenInvalidCarId_thenReturnNull() {
        Car fromDb = carRepository.findByCarId(-1L);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car mercedes1 = new Car("Mercedes", "1");
        Car mercedes2 = new Car("Mercedes", "2");
        Car mercedes3 = new Car("Mercedes", "3");

        entityManager.persist(mercedes1);
        entityManager.persist(mercedes2);
        entityManager.persist(mercedes3);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getModel).containsOnly(mercedes1.getModel(), mercedes2.getModel(), mercedes3.getModel());
    }
}
