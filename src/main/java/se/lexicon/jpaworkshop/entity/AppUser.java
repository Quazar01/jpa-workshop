package se.lexicon.jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

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
    private String uesrname;

    @Setter
    @Column(nullable = false, length = 100)
    private String password;

    LocalDate regDate;

    @Setter
    @OneToOne
    @JoinColumn(name = "details_id")
    private Details details;

    // Constructor
    public AppUser(String uesrname, String password) {
        this.uesrname = uesrname;
        this.password = password;
        this.regDate = LocalDate.now();
    }
}
