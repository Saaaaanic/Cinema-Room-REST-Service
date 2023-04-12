package cinema.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
@PropertySource("classpath:cinema.properties")
@ConfigurationProperties("cinema")
public class CinemaProps {
    int totalRows;
    int totalColumns;
    int firstRows;
    int lowPrice;
    int highPrice;
}
