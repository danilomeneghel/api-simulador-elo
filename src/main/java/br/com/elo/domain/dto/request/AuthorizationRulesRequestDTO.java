package br.com.elo.domain.dto.request;

import br.com.elo.domain.ShouldReturn;
import br.com.elo.domain.Status;
import br.com.elo.model.ExcludedBits;
import br.com.elo.model.ModifiedBits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorizationRulesRequestDTO implements Serializable {

    @NotBlank(message = "O nome não pode ser em branco e nem nulo")
    @Size(max = 90, message = "Tamanho máximo do nome deve ser 90 caracteres")
    private String name;

    private Integer timeoutMilliseconds;

    private ShouldReturn shouldReturn;

    private List<ModifiedBits> modifiedBits;

    private List<ExcludedBits> excludedBits;

    private Status status;
}