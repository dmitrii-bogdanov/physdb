package bogdanov.physdb.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PersonContactDTO {

    private Long id;
    private String firstnameRu;
    private String lastnameRu;
    private String patronymicRu;
    private String email;
    private String email2;
    private String phoneNumber;
    private Long personId;
    private Long infoId;

}
