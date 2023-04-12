package cinema.model.response;

import cinema.model.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public record CinemaResponse (
        @JsonProperty("total_rows")
        int totalRows,
        @JsonProperty("total_columns")
        int totalColumns,
        @JsonProperty("available_seats")
        Collection<PricedSeat> availableSeats
) {}