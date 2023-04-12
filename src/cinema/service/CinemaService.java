package cinema.service;

import cinema.configuration.CinemaProps;
import cinema.exception.AlreadyPurchasedException;
import cinema.exception.OutOfBoundsCoordinatesException;
import cinema.exception.WrongTokenException;
import cinema.mapper.Mapper;
import cinema.model.Seat;
import cinema.model.SoldTicket;
import cinema.model.request.Token;
import cinema.model.response.CinemaStatistics;
import cinema.model.response.PricedSeat;
import cinema.model.response.ReturnedTicket;
import cinema.repository.SeatRepository;
import cinema.repository.SoldTicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class CinemaService {
    SeatRepository seatRepository;
    CinemaProps props;
    SoldTicketRepository soldTicketRepository;
    Mapper mapper;

    public List<PricedSeat> getAvailableSeats() {
        return seatRepository.getAvailableSeats()
                .stream()
                .map(seat -> addPrice(seat))
                .toList();
    }

    private int calcPrice(Seat seat) {
        return seat.row() <= props.getFirstRows() ? props.getHighPrice() : props.getLowPrice();
    }

    private PricedSeat addPrice(Seat seat) {
        return PricedSeat.builder()
                .price(calcPrice(seat))
                .row(seat.row())
                .column(seat.column())
                .build();
    }

    public SoldTicket purchase(Seat seat) {
        if (illegalCoordinates(seat)) throw new OutOfBoundsCoordinatesException();
        if (!seatRepository.contains(seat)) throw new AlreadyPurchasedException();
        seatRepository.remove(seat);
        PricedSeat ticket = addPrice(seat);
        return soldTicketRepository.save(ticket);
    }

    private boolean illegalCoordinates(Seat seat) {
        return !(seat.column() > 0 && seat.column() <= props.getTotalColumns()
                && seat.row() > 0 && seat.row() <= props.getTotalRows());
    }

    public PricedSeat returnTicket(Token token) {
        var pricedSeat = soldTicketRepository.remove(token)
                .orElseThrow(WrongTokenException::new);
        seatRepository.save(mapper.toSeat(pricedSeat));
        return pricedSeat;
    }

    public CinemaStatistics stats() {
        return CinemaStatistics.builder()
                .numberOfAvailableSeats(seatRepository.count())
                .numberOfPurchasedTickets(soldTicketRepository.count())
                .currentIncome(soldTicketRepository.getIncome())
                .build();
    }
}
