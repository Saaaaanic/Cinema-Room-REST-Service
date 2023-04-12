package cinema.mapper;

import cinema.model.Seat;
import cinema.model.response.PricedSeat;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public Seat toSeat(PricedSeat pricedSeat) {
        return new Seat(pricedSeat.row(), pricedSeat.column());
    }
}
