package be.bstorm.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = {"id","title","description"}) @ToString(of = {"id","title"})
@Entity
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 100)
    private String title;

    private String description;

    public Book(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
