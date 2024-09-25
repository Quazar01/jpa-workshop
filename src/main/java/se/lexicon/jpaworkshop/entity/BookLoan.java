package se.lexicon.jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private LocalDate loanDate;
    @Setter
    private LocalDate dueDate;
    @Setter
    private boolean returned;
    @Setter
    @ManyToOne
    private AppUser borrower;
    @Setter
    @ManyToOne
    private Book book;

}
