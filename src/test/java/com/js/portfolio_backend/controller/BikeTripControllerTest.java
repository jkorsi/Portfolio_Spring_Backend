package com.js.portfolio_backend.controller;

import com.js.portfolio_backend.model.BikeTrip;
import com.js.portfolio_backend.service.BikeTripService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@WebMvcTest(BikeTripController.class)
class BikeTripControllerTest {

    BikeTripController bikeTripController;
    List<BikeTrip> bikeTripList = new ArrayList<>();

    @MockBean
    BikeTripService bikeTripService;

    @BeforeEach
    void setUp() {
        bikeTripController = new BikeTripController(bikeTripService);

        bikeTripList = List.of(
                new BikeTrip(1,
                        LocalDateTime.of(2021, 5, 31, 23, 57, 25),
                        LocalDateTime.of(2021, 6, 1, 0, 5, 46),
                        94,
                        100,
                        2043,
                        2043.0*100.0 / 1000.0,
                        500,
                        (Math.floorDiv(500, 60))
                ),
                new BikeTrip(2,
                        LocalDateTime.of(2021, 5, 31, 23, 56, 59),
                        LocalDateTime.of(2021, 6, 1, 0, 7, 14),
                        82,
                        113,
                        1870,
                        1870.0*100.0 / 1000.0,
                        611,
                        (Math.floorDiv(611, 60))
                ),
                new BikeTrip(3,
                        LocalDateTime.of(2021, 5, 31, 23, 56, 44),
                        LocalDateTime.of(2021, 6, 1, 0, 3, 26),
                        132,
                        121,
                        1025,
                        1025*100.0 / 1000.0,
                        399,
                        (Math.floorDiv(399, 60))
                )

        );
    }

}