package cinema.model.response;

import lombok.Builder;

@Builder
public record PricedSeat(
        int row,
        int column,
        int price
) {
}
