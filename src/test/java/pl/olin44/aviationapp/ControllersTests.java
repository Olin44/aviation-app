package pl.olin44.aviationapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.olin44.aviationapp.controller.CargoStatisticsController;
import pl.olin44.aviationapp.controller.FlightStatisticsController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ControllersTests {

    @Autowired
    private FlightStatisticsController controller;

    @Autowired
    private CargoStatisticsController cargoStatisticsController;

    @Test
    @DisplayName("FlightStatisticsController smoke test")
    public void flightStatisticsControllerSmokeTest() {
        assertThat(controller).isNotNull();
    }

    @Test
    @DisplayName("CargoStatisticsController smoke test")
    public void cargoStatisticsControllerSmokeTest() {
        assertThat(cargoStatisticsController).isNotNull();
    }

}



