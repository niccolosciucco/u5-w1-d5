package niccolosciucco.u5_w1_d5.runners;

import niccolosciucco.u5_w1_d5.enums.TipoPostazione;
import niccolosciucco.u5_w1_d5.services.PostazioneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class RicercaPostazioniRunner implements CommandLineRunner {
    private final PostazioneService postazioneService;

    public RicercaPostazioniRunner(PostazioneService postazioneService) {
        this.postazioneService = postazioneService;
    }

    @Override
    public void run(String... args) throws Exception {
        postazioneService.findByTipoAndCitta(TipoPostazione.SALA_RIUNIONI, "Roma");
        postazioneService.findByTipoAndCitta(TipoPostazione.PRIVATO, "Milano");
        postazioneService.findByTipoAndCitta(TipoPostazione.OPEN_SPACE, "Torino");
    }
}