package br.com.elo.dto;


import br.com.elo.domain.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.elo.domain.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private String dataCriacao;
    private String dataAlteracao;
    List<LayoutBitsProtocoloResponseDTO> bitsProtocolo;
    List<MensagemPadraoResponseDTO> mensagensPadrao;
}
