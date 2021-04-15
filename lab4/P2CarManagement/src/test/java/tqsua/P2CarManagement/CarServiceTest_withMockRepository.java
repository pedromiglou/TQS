package tqsua.P2CarManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import tqsua.P2CarManagement.car.Car;
import tqsua.P2CarManagement.car.CarManagerService;
import tqsua.P2CarManagement.car.CarRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest_withMockRepository {

    // lenient is required because we load some expectations in the setup
    // that are not used in all the tests. As an alternative, the expectations
    // could move into each test method and be trimmed: no need for lenient
    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @BeforeEach
    public void setUp() {
        Car mercedes1 = new Car("Mercedes", "1");
        mercedes1.setCarId(111L);

        Car mercedes2 = new Car("Mercedes", "2");
        Car mercedes3 = new Car("Mercedes", "3");

        List<Car> allCars = Arrays.asList(mercedes1, mercedes2, mercedes3);

        Mockito.when(carRepository.findByCarId(mercedes1.getCarId())).thenReturn(mercedes1);
        Mockito.when(carRepository.findByCarId(mercedes3.getCarId())).thenReturn(mercedes3);
        Mockito.when(carRepository.findByCarId(-1L)).thenReturn(null);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
    }

    @Test
    public void whenValidCarId_thenCarShouldBeFound() {
        Long id = 111L;
        Car found = carService.getCarDetails(id);

        assertThat(found.getCarId()).isEqualTo(id);
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(id);
    }

    @Test
    public void whenInValidCarId_thenCarShouldNotBeFound() {
        Car fromDb = carService.getCarDetails(-1L);
        assertThat(fromDb).isNull();

        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(-1L);
    }

    @Test
    public void given3Cars_whengetAll_thenReturn3Records() {
        Car mercedes1 = new Car("Mercedes", "1");
        Car mercedes2 = new Car("Mercedes", "2");
        Car mercedes3 = new Car("Mercedes", "3");

        List<Car> allCars = carService.getAllCars();
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
        assertThat(allCars).hasSize(3).extracting(Car::getModel).contains(mercedes1.getModel(), mercedes2.getModel(), mercedes3.getModel());
    }
}
