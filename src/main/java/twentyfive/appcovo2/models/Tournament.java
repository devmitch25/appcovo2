package twentyfive.appcovo2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import twentyfive.appcovo2.enums.TournamentStatus;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR")
    private TournamentStatus status;

    private Date date; // year, month, date, hrs, min
    private String name;

    @OneToOne
    private Shop shop;

    private int max_players;

    @OneToMany
    private List<Player> registered_players; //players che vorrebbero partecipare
    @OneToMany
    private List<Player> effective_players; //players accettati meno eventuali players assenti già spostati nell'apposita lista = partecipanti effettivi del torneo
    @OneToMany
    private List<Player> absent_players;

    @OneToOne
    private Game game; //TODO forse posso rimuoverlo visto che ci posso risalire dal formato!!! dipende quante volte si fa la ricerca, forse è meglio lasciarlo
    @OneToOne
    private Format format; //TODO da verificare che il formato appartenga al gioco

    private double entry_price;

    @OneToOne
    @JoinColumn(name = "rules_id")
    private TournamentRules rules;

    //TODO freeplay non è un torneo? SI SENZA SOLDI E CLASSIFICA ==> metto booleano o enum per chiarezza e poi quando faccio la ricerca filtrata per i prossimi tornei ovviamente tolgo quelli free

    //TODO da tenere in conto anche la presenza di player GUEST, ovvero giocatori iscritti manualmente perchè non hanno scaricato l'app


}
