package tqsua.P2CarManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqsua.P2CarManagement.car.Car;
import tqsua.P2CarManagement.car.CarController;
import tqsua.P2CarManagement.car.CarManagerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTest_withMockService {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostCar_thenCreateCar( ) throws Exception {
        Car mercedes1 = new Car("Mercedes", "1");

        //given(service.save(Mockito.any())).willReturn(mercedes1);
        when( service.save(Mockito.any()) ).thenReturn(mercedes1);

        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(mercedes1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Mercedes")));

        verify(service, times(1)).save(Mockito.any());

    }

    @Test
    public void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car mercedes1 = new Car("Mercedes", "1");
        Car mercedes2 = new Car("Mercedes", "2");
        Car mercedes3 = new Car("Mercedes", "3");

        List<Car> allCars = Arrays.asList(mercedes1, mercedes2, mercedes3);

        given(service.getAllCars()).willReturn(allCars);

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].maker", is(mercedes1.getMaker()))).andExpect(jsonPath("$[1].maker", is(mercedes2.getMaker())))
                .andExpect(jsonPath("$[2].maker", is(mercedes3.getMaker())));
        verify(service, VerificationModeFactory.times(1)).getAllCars();

    }

}
