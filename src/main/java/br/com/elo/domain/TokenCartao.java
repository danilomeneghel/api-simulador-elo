package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum TokenCartao {

    @JsonProperty("SIM")
    SIM(1, "Sim"),
    @JsonProperty("NAO")
    NAO(2, "Não");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    TokenCartao(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static TokenCartao get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
