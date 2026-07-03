package niccolosciucco.u5_w1_d5.runners;

import niccolosciucco.u5_w1_d5.entities.Edificio;
import niccolosciucco.u5_w1_d5.entities.Postazione;
import niccolosciucco.u5_w1_d5.enums.TipoPostazione;
import niccolosciucco.u5_w1_d5.services.EdificioService;
import niccolosciucco.u5_w1_d5.services.PostazioneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Order(3)
public class PostazioneRunner implements CommandLineRunner {
    private final PostazioneService postazioneService;
    private final EdificioService edificioService;

    public PostazioneRunner(PostazioneService postazioneService, EdificioService edificioService) {
        this.postazioneService = postazioneService;
        this.edificioService = edificioService;
    }

    @Override
    public void run(String... args) throws Exception {
        Edificio ed1 = edificioService.findById(UUID.fromString("15a8339a-3875-4f52-ac25-d44c374b7aca"));
        Edificio ed2 = edificioService.findById(UUID.fromString("2cda19a7-dbdc-4aef-8541-d34a1d14b429"));
        Edificio ed3 = edificioService.findById(UUID.fromString("50d876f9-96e6-4a8b-aadf-6eea7bf2b5ff"));
        Edificio ed4 = edificioService.findById(UUID.fromString("7297bbb3-ae63-47f8-a5db-97d89d9cbc2b"));
        Edificio ed5 = edificioService.findById(UUID.fromString("adf9e56b-efda-4f86-8b5d-ef25598ed1a8"));
        Edificio ed6 = edificioService.findById(UUID.fromString("b24ca9a0-810a-4f7d-9392-6b1ebbeb130e"));
        Edificio ed7 = edificioService.findById(UUID.fromString("bff999d1-2b91-4c30-a6ac-a3e49a0f5ebc"));
        Edificio ed8 = edificioService.findById(UUID.fromString("c68cd8a7-bbd9-4d21-a4a2-f6add1044bb3"));
        Edificio ed9 = edificioService.findById(UUID.fromString("ddd44711-b033-4872-b134-913cbd2bb801"));
        Edificio ed10 = edificioService.findById(UUID.fromString("e279ff11-e5cf-4578-903f-1f00a3edc534"));

        List.of(
                new Postazione("Scrivania privata Ufficio A", TipoPostazione.PRIVATO, 1, ed1),
                new Postazione("Sala Riunioni Alpha", TipoPostazione.SALA_RIUNIONI, 12, ed2),
                new Postazione("Open Space Desk 4", TipoPostazione.OPEN_SPACE, 1, ed3),
                new Postazione("Sala Riunioni Beta", TipoPostazione.SALA_RIUNIONI, 8, ed4),
                new Postazione("Ufficio Singolo Direzione", TipoPostazione.PRIVATO, 1, ed5),
                new Postazione("Open Space Desk 15", TipoPostazione.OPEN_SPACE, 1, ed6),
                new Postazione("Sala Consiglio Grande", TipoPostazione.SALA_RIUNIONI, 20, ed7),
                new Postazione("Scrivania Condivisa Finestra", TipoPostazione.OPEN_SPACE, 1, ed8),
                new Postazione("Ufficio Sviluppatori Privato", TipoPostazione.PRIVATO, 2, ed9),
                new Postazione("Sala Briefing Veloce", TipoPostazione.SALA_RIUNIONI, 6, ed10)
        ).forEach(this.postazioneService::saveNewPostazione);
    }
}
