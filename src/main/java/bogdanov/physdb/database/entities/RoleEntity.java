package bogdanov.physdb.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity implements GrantedAuthority {

    @Id
    @Column(name = "role_id")
    private Long id;

    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;

    public RoleEntity(long id) {
        this.id = id;
    }

    public RoleEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
