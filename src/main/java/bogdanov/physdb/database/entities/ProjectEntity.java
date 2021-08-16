package bogdanov.physdb.database.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = TagEntity.class, mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<TagEntity> tags = new ArrayList<>();

}
