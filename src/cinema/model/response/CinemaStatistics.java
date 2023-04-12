package cinema.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CinemaStatistics(
        @JsonProperty("current_income")
        int currentIncome,
        @JsonProperty("number_of_available_seats")
        int numberOfAvailableSeats,
        @JsonProperty("number_of_purchased_tickets")
        int numberOfPurchasedTickets
) {
}
