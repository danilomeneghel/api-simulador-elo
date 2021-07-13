package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum EncodeBCDLLVARLLLVAR {

    @JsonProperty("NROBYTES")
    NROBYTES(1, "Número em Bytes"),
    @JsonProperty("NRODIGITOS")
    NRODIGITOS(2, "Número em Digitos");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    EncodeBCDLLVARLLLVAR(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static EncodeBCDLLVARLLLVAR get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }
}
