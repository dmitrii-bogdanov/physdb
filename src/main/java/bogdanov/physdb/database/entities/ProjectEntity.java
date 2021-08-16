package bogdanov.physdb.database.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    private UserEntity createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_modification_date")
    private Date lastModificationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sup_project_id", referencedColumnName = "project_id")
    private ProjectEntity supProject;

    @OneToMany(targetEntity = ProjectEntity.class, mappedBy = "supProject", fetch = FetchType.LAZY)
    private List<ProjectEntity> subprojects = new ArrayList<>();

    @OneToMany(targetEntity = TagEntity.class, mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<TagEntity> tags = new ArrayList<>();

}
