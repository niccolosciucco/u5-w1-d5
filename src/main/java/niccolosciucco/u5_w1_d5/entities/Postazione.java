package niccolosciucco.u5_w1_d5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import niccolosciucco.u5_w1_d5.enums.TipoPostazione;

import java.util.UUID;

@Entity
@Table(name = "postazioni")
@NoArgsConstructor
@Getter
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String descrizione;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPostazione tipo;
    @Column(name = "max_occupanti", nullable = false)
    private int maxOccupanti;
    @ManyToOne
    @JoinColumn(name = "edificio_id", nullable = false)
    private Edificio edificio;

    public Postazione(String descrizione, TipoPostazione tipo, int maxOccupanti, Edificio edificio) {
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.maxOccupanti = maxOccupanti;
        this.edificio = edificio;
    }
}
