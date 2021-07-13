package br.com.elo.domain.dto.request;

import br.com.elo.common.utils.annotations.EnumNamePattern;
import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProtocoloRequestDTO implements Serializable {

    @NotNull(message = "O status não pode ser nulo")
    private Status status;

    @NotNull(message = "O tipo do não pode ser nulo")
    private TipoProtocolo tipo;

    @NotBlank(message = "A descrição não pode ser em branco e nem nulo")
    @Size(max = 90, message = "Tamanho máximo da descriação deve ser 90 caracteres")
    private String descricao;

    @NotBlank(message = "A versão não pode ser em branco e nem nulo")
    @Size(max = 30, message = "Tamanho máximo da versão deve ser 30 caracteres")
    private String versao;

    @NotNull(message = "A codificação do codigo da mensagem não pode ser nula")
    private EncodeCodigoMensagem encodeCodigoMensagem;

    @NotNull(message = "A codificação do mapa de bits não pode ser nula")
    private EncodeMapaDeBits encodeMapaDeBits;

    //Configuracao obrigatoria para informar como sera indicado o tamanho variavel de campos com dados em bcd. Podendo ser numero de digitos ou numero de bytes
    @NotNull(message = "A codificação do tamanho de campo BCD não pode ser nula")
    private EncodeBCDLLVARLLLVAR encodeBCDLLVARLLLVAR;

    List<@Valid LayoutBitsProtocoloRequestDTO> bitsProtocolo;

    List<MensagemPadraoRequestDTO> mensagensPadrao;

}
