package se.lexicon.jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString


@Entity
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Setter
    @Column(nullable = false, length = 100)
    private String name;

    LocalDate birthDate;

    @OneToOne(mappedBy = "details")
    AppUser appUser;

    //Constructor
    // Bypassing the birthDate field for now!
    public Details(String name, String email) {
        this.email = email;
        this.name = name;
    }
}
