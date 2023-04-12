package cinema.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReturnedTicket(
        @JsonProperty("returned_ticket")
        PricedSeat returnedTicket
) {
}
