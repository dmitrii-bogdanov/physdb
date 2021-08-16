package bogdanov.physdb.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO {

    private Long id;
    private String name;
    private Long infoId;
    private Long createdById;
    private Date creationDate;
    private Boolean isActive;
    private Long supProject;
    private List<Long> subprojects;

}
