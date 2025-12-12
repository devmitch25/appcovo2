package twentyfive.appcovo2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Award {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String image;
    private double price;
    private String description;
    private int max_quantity;
    private int max_for_single_player;


}
