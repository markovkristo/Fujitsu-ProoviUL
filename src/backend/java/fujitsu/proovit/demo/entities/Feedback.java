package fujitsu.proovit.demo.entities;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Feedback{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String category;
    private String text;

    // Constructor for Feedback object.
    public Feedback(Long id, String name, String email, String category, String text) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.category = category;
        this.text = text;
    }

}
