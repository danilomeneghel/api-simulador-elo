package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum TipoMensagem {

    @JsonProperty("SOLICITACAO")
    SOLICITACAO(1, "Solicitação"),
    @JsonProperty("RESPOSTA")
    RESPOSTA(2, "Resposta");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    TipoMensagem(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static TipoMensagem get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
