package bip.online.biplio2023.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class   BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name ="author_id")
    private AuthorEntity author;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublisherEntity publisher;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;//:Жанр
    private String year;
}
