package br.com.elo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemPadraoRequestDTO implements Serializable {

    private String idMensagem;

    private String mtiTransacaoRecebida;

    private String mensagemPadrao;

}