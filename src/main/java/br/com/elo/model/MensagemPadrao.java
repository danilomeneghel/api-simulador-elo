package br.com.elo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemPadrao {

    @Column
    private String idMensagem;

    @Column
    private String mtiTransacaoRecebida;

    @Column
    private String mensagemPadrao;

}
