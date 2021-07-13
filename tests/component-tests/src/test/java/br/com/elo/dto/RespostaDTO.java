package br.com.elo.dto;


import java.util.List;

import br.com.elo.dto.MensagemDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Representação básica de respostas de requisições REST.
 *
 * @author <a href="mailto:helenov@ciandt.com">Heleno Valle</a>
 * @version $Id$
 * @param <T>
 *            Tipo do dado contido na resposta.
 */
public class RespostaDTO<T> {

    @JsonInclude(Include.NON_EMPTY)
    private List<T> dados;

    @JsonInclude(Include.NON_EMPTY)
    private List<MensagemDTO> mensagens;

    @JsonInclude(Include.NON_EMPTY)
    private String codigo;

    /**
     * Construtor com resultado
     *
     * @param resultado
     */
    public RespostaDTO(final List<T> resultado) {
        this.dados = resultado;
    }

    /**
     * Construtor genérico
     */
    public RespostaDTO() {
    }

    /**
     * Obtém dados retornados.
     */
    public List<T> getDados() {
        return this.dados;
    }

    /**
     * Define dados retornados.
     */
    public void setDados(final List<T> dados) {
        this.dados = dados;
    }

    /**
     * Obtém mensagens da resposta.
     */
    public List<MensagemDTO> getMensagens() {
        return this.mensagens;
    }

    /**
     * Define mensagens da resposta.
     */
    public void setMensagens(final List<MensagemDTO> mensagens) {
        this.mensagens = mensagens;
    }

    /**
     * Obtem o codigo.
     *
     * @return the codigo
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Define o código de resposta.
     *
     * @param codigo the codigo
     */
    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
        builder.append("dados", this.dados).append("mensagens", this.mensagens)
                .append("codigo", this.codigo);
        return builder.toString();
    }
}
