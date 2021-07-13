package br.com.elo.domain.dto.request;

import br.com.elo.domain.Plataforma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmissorRequestDTO implements Serializable {

    @NotBlank(message = "A descrição não pode ser em branco e nem nulo")
    @Size(max = 90, message = "Tamanho máximo do nome deve ser 90 caracteres")
    private String nomeEmissor;

    @NotNull(message = "O código da bandeira não pode ser nulo")
    @Max(value = 9999, message = "Tamanho máximo do código da bandeira deve ser 4 caracteres")
    @Min(value = 1, message = "O tamanho mínimo do código da bandeira deve ser maior que zero" )
    private Integer codigoBandeira;

    @NotNull(message = "O código do emissor não pode ser nulo")
    @Max(value = 9999, message = "Tamanho máximo do código do emissor deve ser 4 caracteres")
    @Min(value = 1, message = "O tamanho mínimo do código do emissor deve ser maior que zero" )
    private Integer codigoEmissor;

    @NotNull(message = "O codigo processadora não pode ser nulo")
    @Max(value = 9999, message = "Tamanho máximo do código da processadora deve ser 4 caracteres")
    @Min(value = 1, message = "O tamanho mínimo do código da processadora deve ser maior que zero" )
    private Integer codigoProcessadora;

    @NotNull(message = "A plataforma não pode ser nula")
    private Plataforma plataforma;

}
