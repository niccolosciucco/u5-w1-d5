package niccolosciucco.u5_w1_d5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "utenti")
@NoArgsConstructor
@Getter
@ToString
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;
    @Column(nullable = false)
    private String email;

    public Utente(String username, String nomeCompleto, String email) {
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
    }
}
