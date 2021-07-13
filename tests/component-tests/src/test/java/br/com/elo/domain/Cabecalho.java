package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum Cabecalho {

    @JsonProperty("TPDU")
    TPDU(1, "TPDU"),
    @JsonProperty("TCP_MLI")
    TCP_MLI(2, "TCP_MLI");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    Cabecalho(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Cabecalho get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }
}
