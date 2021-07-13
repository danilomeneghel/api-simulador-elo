package br.com.elo.domain.dto.request;

import br.com.elo.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationRulesRequestDTO implements Serializable {

    @NotBlank
    private String name;

    @NotNull
    private Status status;

    @NotNull
    private List<@Valid ValidationBitsRequestDTO> validationBits;

}
