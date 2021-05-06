package tqsua.P2CarManagement;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import tqsua.P2CarManagement.car.Car;
import tqsua.P2CarManagement.car.CarController;
import tqsua.P2CarManagement.car.CarManagerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@Testcontainers
@WebMvcTest(CarController.class)
public class CarRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void whenPostCar_thenCreateCar( ) throws Exception {
        Car mercedes1 = new Car("Mercedes", "1");

        Mockito.when( service.save(Mockito.any()) ).thenReturn(mercedes1);

        RestAssuredMockMvc.given().auth().none()
                .contentType("application/json")
                .body(JsonUtil.toJson(mercedes1))
                .when().post("/api/cars")
                .then().statusCode(201)
                .body("maker", Matchers.equalTo("Mercedes"));

        verify(service, times(1)).save(Mockito.any());

    }

    @Test
    public void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car mercedes1 = new Car("Mercedes", "1");
        Car mercedes2 = new Car("Mercedes", "2");
        Car mercedes3 = new Car("Mercedes", "3");

        List<Car> allCars = Arrays.asList(mercedes1, mercedes2, mercedes3);

        given(service.getAllCars()).willReturn(allCars);

        RestAssuredMockMvc.given().auth().none()
                .when().get("/api/cars")
                .then().statusCode(200)
                .body("$", hasSize(3))
                .body("[0].maker", is(mercedes1.getMaker()))
                .body("[1].maker", is(mercedes2.getMaker()))
                .body("[2].maker", is(mercedes3.getMaker()));
        verify(service, VerificationModeFactory.times(1)).getAllCars();

    }
}
