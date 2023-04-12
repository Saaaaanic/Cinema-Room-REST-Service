package cinema.model;

import cinema.model.response.PricedSeat;
import lombok.Value;

@Value
public class SoldTicket {
    String token;
    PricedSeat ticket;
}
