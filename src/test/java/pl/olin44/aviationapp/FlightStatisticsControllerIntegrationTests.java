package pl.olin44.aviationapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.olin44.aviationapp.controller.FlightStatisticsController;
import pl.olin44.aviationapp.payload.response.FlightCargoStatisticsByIATACodeResponse;
import pl.olin44.aviationapp.service.FlightService;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightStatisticsController.class)
public class FlightStatisticsControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FlightService service;

    @Test
    void getStatisticsByIATACodeTest() throws Exception {
//      Arrange
        int numberOfArrivingFlights = 5;
        int piecesOfBaggageArriving = 6;
        int numberOfDepartingFlight = 2;
        int piecesOfBaggageDeparting = 1;
        String iataCodeAsString = "YYT";

        FlightCargoStatisticsByIATACodeResponse flightCargoStatisticsByIATACodeResponse = getFlightStatisticsResponse(numberOfArrivingFlights,
                piecesOfBaggageArriving, numberOfDepartingFlight, piecesOfBaggageDeparting);

        given(service.getStatisticsByIATACode(iataCodeAsString))
                .willReturn(flightCargoStatisticsByIATACodeResponse);
//      Act
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/flight/statistics")
                .param("iataCode", iataCodeAsString)
                .contentType(MediaType.APPLICATION_JSON));
//      Assert
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("numberOfArrivingFlights", is(numberOfArrivingFlights)))
                .andExpect(jsonPath("piecesOfBaggageArriving", is(piecesOfBaggageArriving)))
                .andExpect(jsonPath("numberOfDepartingFlight", is(numberOfDepartingFlight)))
                .andExpect(jsonPath("piecesOfBaggageDeparting", is(piecesOfBaggageDeparting)));
    }

    private FlightCargoStatisticsByIATACodeResponse getFlightStatisticsResponse(int numberOfArrivingFlights, int piecesOfBaggageArriving, int numberOfDepartingFlight, int piecesOfBaggageDeparting) {
        return FlightCargoStatisticsByIATACodeResponse.builder()
                .numberOfArrivingFlights(numberOfArrivingFlights)
                .piecesOfBaggageArriving(piecesOfBaggageArriving)
                .numberOfDepartingFlight(numberOfDepartingFlight)
                .piecesOfBaggageDeparting(piecesOfBaggageDeparting)
                .build();
    }

}
