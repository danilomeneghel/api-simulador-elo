package br.com.elo.dto;


import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Representação das mensagens de resposta retornadas pelas requisições REST.
 *
 * @author <a href="mailto:vivian@ciandt.com">Vivian Barbosa</a>
 */
public class MensagemDTO implements Serializable {

    private static final long serialVersionUID = 6349098367129447952L;

    @JsonInclude(Include.NON_NULL)
    private String campo;

    private String mensagem;

    /**
     * Construtor default.
     */
    public MensagemDTO() {
    }

    /**
     * Construtor.
     *
     * @param mensagem
     *            Mensagem de retorno.
     */
    public MensagemDTO(final String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * Construtor.
     *
     * @param campo
     *            Campo da requisição a que ser refere a mensagem.
     * @param mensagem
     *            Mensagem de retorno.
     */
    public MensagemDTO(final String campo, final String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    /**
     * Obtém campo da mensagem.
     */
    public String getCampo() {
        return this.campo;
    }

    /**
     * Define campo da mensagem.
     */
    public void setCampo(final String campo) {
        this.campo = campo;
    }

    /**
     * Obtém mensagem.
     */
    public String getMensagem() {
        return this.mensagem;
    }

    /**
     * Define campo da mensagem.
     */
    public void setMensagem(final String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("campo", this.getCampo())
                .append("mensagem", this.getMensagem())
                .toString();
    }

}