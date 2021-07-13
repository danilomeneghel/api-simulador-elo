package br.com.elo.domain.dto.request;

import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BitMensagemRequestDTO {

    //Obrigatorio e somente sera valido se fizer parte dos bits do protocolo.
    @NotNull(message = "O numero do bit não pode ser nulo")
    @Min(value = 2, message = "O valor minimo para o numero de bit deve ser 2" )
    @Max(value = 256, message = "O valor máximo para o numero de bit é 256")
    private Integer numeroDoBit;

    @NotNull(message = "A forma de preenchimento não pode ser nula")
    private FillingMode fillingMode;

    @NotNull(message = "A condição de presença não pode ser nula")
    private CondicaoPresencaBit condicaoPresenca;

    private String conteudoBit;

}
