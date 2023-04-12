package cinema.configuration;

import cinema.model.Seat;
import cinema.repository.SeatRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@AllArgsConstructor
@Component
public class InitSeats {
    SeatRepository repo;
    CinemaProps props;

    @PostConstruct
    void init() {
        for(int i = 1; i <= props.totalRows; i++)
            for (int j = 1; j <= props.totalColumns; j++)
                repo.save(new Seat(i, j));
    }
}
