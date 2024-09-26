package se.lexicon.jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Setter
    @Column(nullable = false, length = 100)
    private String password;

    LocalDate regDate;

    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id")
    private Details details;

    @Setter
    @OneToMany(mappedBy = "borrower")
    private Set<BookLoan> loans = new HashSet<>();

    // Constructor
    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.regDate = LocalDate.now();
    }
}
