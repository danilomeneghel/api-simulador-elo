package br.com.elo.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemPadraoResponseDTO implements Serializable {

    private String idMensagem;

    private String mtiTransacaoRecebida;

    private String mensagemPadrao;

}
