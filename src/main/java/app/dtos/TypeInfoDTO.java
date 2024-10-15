package app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class TypeInfoDTO
{
    private String name;

    public TypeInfoDTO(String name)
    {
        this.name = name;
    }
}
