package br.com.elo.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BandeiraRequestDTO implements Serializable {

    @NotNull(message = "O codigo Bandeira não pode ser nulo")
    @Max(value = 999, message = "Tamanho máximo da Bandeira deve ser 3 caracteres")
    @Min(value = 1, message = "O valor deve ser maior que zero" )
    private Integer codigoBandeira;

    @NotBlank(message = "A descrição não pode ser em branco e nem nulo")
    @Size(max = 90, message = "Tamanho máximo da descriação deve ser 90 caracteres")
    private String descricao;

}
