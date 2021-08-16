package bogdanov.physdb.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class PersonEntity {

    @Id
    @Column(name = "person_id")
    private long id;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @OneToOne(targetEntity = PersonContactEntity.class)
    private PersonContactEntity contact;


}
