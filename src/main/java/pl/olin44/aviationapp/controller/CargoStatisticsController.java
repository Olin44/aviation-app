package pl.olin44.aviationapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.olin44.aviationapp.payload.response.CargoStatisticsResponse;
import pl.olin44.aviationapp.service.CargoStatisticsService;

@RestController
@RequiredArgsConstructor
public class CargoStatisticsController {

    private final CargoStatisticsService cargoStatisticsService;

    @GetMapping(value = "cargo/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CargoStatisticsResponse> cargoStatistics(@RequestParam int flightNumber, @RequestParam String date) {
        return ResponseEntity.ok(cargoStatisticsService.cargoStatistics(flightNumber, date));
    }
}
