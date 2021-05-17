package pl.olin44.aviationapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.olin44.aviationapp.payload.response.FlightCargoStatisticsByIATACodeResponse;
import pl.olin44.aviationapp.service.FlightService;

@RestController
@RequiredArgsConstructor
public class FlightStatisticsController {

    private final FlightService flightService;

    @GetMapping(value = "flight/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FlightCargoStatisticsByIATACodeResponse> flightCargoStatisticsByIATACode(@RequestParam String iataCode) {
        return ResponseEntity.ok(flightService.getStatisticsByIATACode(iataCode));
    }
}
