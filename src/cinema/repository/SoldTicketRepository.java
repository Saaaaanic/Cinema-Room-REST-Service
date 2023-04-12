package cinema.repository;

import cinema.model.SoldTicket;
import cinema.model.request.Token;
import cinema.model.response.PricedSeat;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SoldTicketRepository {
    private int totalIncome;
    private Map<String, PricedSeat> data = new HashMap<>();

    public SoldTicket save(PricedSeat ticket) {
        String token = UUID.randomUUID().toString();
        SoldTicket res = new SoldTicket(token, ticket);
        totalIncome += ticket.price();
        data.put(token, ticket);
        return res;
    }

    public Optional<PricedSeat> remove(Token token) {
        var pricedSeat = Optional.ofNullable(data.remove(token.token()));
        pricedSeat.ifPresent(seat -> totalIncome -= seat.price());
        return pricedSeat;
    }

    public int count() {
        return data.size();
    }

    public int getIncome() {
        return totalIncome;
    }
}
