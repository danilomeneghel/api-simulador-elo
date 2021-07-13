package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum TipoCartao {

    @JsonProperty("CREDITO")
    CREDITO(1, "Crédito"),
    @JsonProperty("DEBITO")
    DEBITO(2, "Débito"),
    @JsonProperty("MULTIPLO")
    MULTIPLO(3, "Multiplo"),
    @JsonProperty("PREPAGO")
    PREPAGO(4, "Pré-Pago");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    TipoCartao(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static TipoCartao get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
