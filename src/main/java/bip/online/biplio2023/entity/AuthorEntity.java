package bip.online.biplio2023.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    private String lastname;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
private List<BookEntity> books;
}
