package niccolosciucco.u5_w1_d5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@NoArgsConstructor
@Getter
@ToString
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "postazione_id", nullable = false)
    private Postazione postazione;

    public Prenotazione(LocalDate data, Utente utente, Postazione postazione) {
        this.data = data;
        this.utente = utente;
        this.postazione = postazione;
    }
}
