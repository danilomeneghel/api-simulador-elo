package br.com.elo.domain.dto.request;

import br.com.elo.common.utils.annotations.EnumNamePattern;
import br.com.elo.domain.Status;
import br.com.elo.model.EstabelecimentoComercial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredenciadorRequestDTO implements Serializable {

    @NotNull(message = "O codigo do credenciador não pode ser nulo")
    @Max(value = 9999, message = "Tamanho máximo do codigo deve ser 4 caracteres")
    @Min(value = 1, message = "O valor deve ser maior que zero" )
    private Integer credenciadorCodigo;

    @NotBlank(message = "Nome não pode ser em branco e nem nulo")
    @Size(max = 60, message = "Tamanho máximo do nome credenciador ser 60 caracteres")
    private String nome;

    @NotNull(message = "Status invalido ou nulo")
    private Status status;

    private List<EstabelecimentoComercialRequestDTO> estabelecimentoComercial;
}