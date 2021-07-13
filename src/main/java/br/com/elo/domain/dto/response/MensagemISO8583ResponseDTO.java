package br.com.elo.domain.dto.response;

import br.com.elo.domain.*;
import br.com.elo.domain.dto.request.BitMensagemRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemISO8583ResponseDTO implements Serializable {

    private String id;
    private Long mensagemSequence;
    private Integer codigoMensagem;
    private String descricao;
    private TipoMensagem tipoMensagem;
    private Status status;
    private List<BitMensagemResponseDTO> bitsMensagem;
    private Long protocoloSequence;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataAlteracao;

}
