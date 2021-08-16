package bogdanov.physdb.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class TagDTO {

    private Long id;
    private String abbreviation;
    private String name;
    private Long projectId;

}
