package twentyfive.appcovo2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String name;
    private String image;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Format> formats;


}
