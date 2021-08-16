package bogdanov.physdb.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class JournalDTO {

    private Long id;
    private String name;
    private String abbreviation;
    private String website;

}
