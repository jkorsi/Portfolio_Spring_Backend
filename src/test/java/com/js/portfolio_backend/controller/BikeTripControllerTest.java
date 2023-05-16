package com.js.portfolio_backend.controller;

import com.js.portfolio_backend.model.BikeTrip;
import com.js.portfolio_backend.repository.BikeTripCollectionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BikeTripController.class)
class BikeTripControllerTest {

    BikeTripController bikeTripController;
    List<BikeTrip> bikeTripList = new ArrayList<>();

    @MockBean
    BikeTripCollectionRepository bikeTripCollectionRepository;

    @BeforeEach
    void setUp() {
        bikeTripController = new BikeTripController(bikeTripCollectionRepository);

        bikeTripList = List.of(
                new BikeTrip(1,
                        LocalDateTime.of(2021, 5, 31, 23, 57, 25),
                        LocalDateTime.of(2021, 6, 1, 0, 5, 46),
                        94,
                        100,
                        2043,
                        500
                ),
                new BikeTrip(2,
                        LocalDateTime.of(2021, 5, 31, 23, 56, 59),
                        LocalDateTime.of(2021, 6, 1, 0, 7, 14),
                        82,
                        113,
                        1870,
                        611
                ),
                new BikeTrip(3,
                        LocalDateTime.of(2021, 5, 31, 23, 56, 44),
                        LocalDateTime.of(2021, 6, 1, 0, 3, 26),
                        132,
                        121,
                        1025,
                        399
                )

        );
    }

    @Test
    void shouldReturnAllTrips(){
        Mockito.when(bikeTripCollectionRepository.getBikeTripList()).thenReturn(bikeTripList);
        assertEquals(3, bikeTripController.getBikeTripList().spliterator().getExactSizeIfKnown());
    }
}