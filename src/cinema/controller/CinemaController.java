package cinema.controller;

import cinema.configuration.CinemaProps;
import cinema.exception.AlreadyPurchasedException;
import cinema.exception.OutOfBoundsCoordinatesException;
import cinema.exception.WrongPasswordException;
import cinema.model.Seat;
import cinema.model.SoldTicket;
import cinema.model.request.Token;
import cinema.model.response.CinemaResponse;
import cinema.model.response.CinemaStatistics;
import cinema.model.response.PricedSeat;
import cinema.model.response.ReturnedTicket;
import cinema.service.CinemaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@AllArgsConstructor
@RestController
public class CinemaController {

    @Autowired
    CinemaService cinemaService;
    CinemaProps cinemaProps;

    @GetMapping("/seats")
    CinemaResponse Seats()
    {
        var seats = cinemaService.getAvailableSeats();

        return new CinemaResponse(
                cinemaProps.getTotalRows(),
                cinemaProps.getTotalColumns(),
                seats
        );
    }

    @PostMapping("/purchase")
    SoldTicket purchase(@RequestBody Seat seat)
    {
        log.info("Purchase seat = {}", seat);
        return cinemaService.purchase(seat);
    }

    @PostMapping("/return")
    ReturnedTicket returnHandler(@RequestBody Token token) {
        return new ReturnedTicket(cinemaService.returnTicket(token));
    }

    @PostMapping("/stats")
    CinemaStatistics stats(@RequestParam(required = false) String password) {
        if(!"super_secret".equals(password)) throw new WrongPasswordException();
        return cinemaService.stats();
    }
}
