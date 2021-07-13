package br.com.elo.domain.dto.request;

import br.com.elo.domain.Status;
import br.com.elo.domain.TipoMensagem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemISO8583RequestDTO {

    @NotNull(message = "O codigo da mensagem não pode ser nulo")
    @Max(value = 9999,message = "O codigo da mensagem deve ser menor que 9999")
    @Min(value = 1,message = "O codigo da mensagem deve ser maior que zero")
    private Integer codigoMensagem;

    @NotBlank(message = "A descrição não pode ser em branco e nem nulo")
    @Size(max = 90, message = "Tamanho máximo da descrição deve ser 90 caracteres")
    private String descricao;

    @NotNull(message = "O status não pode ser nulo")
    private TipoMensagem tipoMensagem;

    @NotNull(message = "O status não pode ser nulo")
    private Status status;

    private List<@Valid BitMensagemRequestDTO> bitsMensagem;

    @NotNull(message = "O codigo do protocolo não pode ser nulo")
    private Long protocoloSequence;


}
