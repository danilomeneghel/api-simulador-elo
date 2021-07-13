package br.com.elo.domain.dto.response;

import br.com.elo.domain.*;
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
public class ProtocoloResponseDTO implements Serializable {

    private String id;
    private Long protocoloSequence;
    private Status status;
    private TipoProtocolo tipo;
    private String descricao;
    private String versao;
    private EncodeCodigoMensagem encodeCodigoMensagem;
    private EncodeMapaDeBits encodeMapaDeBits;
    private EncodeBCDLLVARLLLVAR encodeBCDLLVARLLLVAR;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataAlteracao;

    List<LayoutBitsProtocoloResponseDTO> bitsProtocolo;
    List<MensagemPadraoResponseDTO> mensgensPadrao;

}
