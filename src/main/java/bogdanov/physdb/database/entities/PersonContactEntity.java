package bogdanov.physdb.database.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class PersonContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private long id;

    @Column(name = "firstname_ru", nullable = false)
    private String firstnameRu;

    @Column(name = "lastname_ru", nullable = false)
    private String lastnameRu;

    @Column(name = "email_1")
    private String email1;

    @Column(name = "email_2")
    private String email2;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @MapsId
    private PersonEntity person;

    @Column(name = "info_id")
    private long infoId;
}
