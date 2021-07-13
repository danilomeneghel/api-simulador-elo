package br.com.elo.dto;


import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtocoloRequestDTO implements Serializable {

    private Status status;
    private TipoProtocolo tipo;
    private String descricao;
    private String versao;
    private EncodeCodigoMensagem encodeCodigoMensagem;
    private EncodeMapaDeBits encodeMapaDeBits;
    private EncodeBCDLLVARLLLVAR encodeBCDLLVARLLLVAR;
    List<LayoutBitsProtocoloRequestDTO> bitsProtocolo;
    List<MensagemPadraoRequestDTO> mensagensPadrao;
}
