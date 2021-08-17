package bogdanov.physdb.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "is_enabled")
    private boolean isEnabled = false;

    @OneToMany(targetEntity = ProjectEntity.class, mappedBy = "createdBy", fetch = FetchType.LAZY)
    private List<ProjectEntity> createdProjects = new ArrayList<>();

}
