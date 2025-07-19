package hu.zlaval.springcourse.book;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(unique = true)
    private String title;

    @Basic
    private String author;

    private String publisher;

    private Integer pages;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Instant publishedAt;

    private Boolean available;

    public boolean isNew() {
        return id == null;
    }
}

