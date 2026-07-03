package niccolosciucco.u5_w1_d5.runners;

import niccolosciucco.u5_w1_d5.entities.Edificio;
import niccolosciucco.u5_w1_d5.services.EdificioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class EdificioRunner implements CommandLineRunner {
    private final EdificioService edificioService;

    public EdificioRunner(EdificioService edificioService) {
        this.edificioService = edificioService;
    }

    @Override
    public void run(String... args) throws Exception {
        List.of(
                new Edificio("Sede Centrale", "Via Nazionale 12", "Roma"),
                new Edificio("Hub Innovazione", "Corso Buenos Aires 45", "Milano"),
                new Edificio("Polo Tecnologico", "Via Magenta 8", "Torino"),
                new Edificio("Torre Garibaldi", "Piazza Gae Aulenti 1", "Milano"),
                new Edificio("Uffici Eur", "Viale Europa 120", "Roma"),
                new Edificio("Sede Vesuvio", "Via Toledo 200", "Napoli"),
                new Edificio("Spazio Coworking", "Via Indipendenza 4", "Bologna"),
                new Edificio("Distretto Digitale", "Lungarno Pacinotti 15", "Pisa"),
                new Edificio("Palazzo degli Affari", "Piazza della Libertà 8", "Firenze"),
                new Edificio("Sede Riviera", "Via Roma 55", "Bari")
        ).forEach(this.edificioService::saveNewEdificio);
    }
}
