package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum EncodeDadosCampo {

    @JsonProperty("NROBCD")
    NROBCD(1, "Número BCD"),
    @JsonProperty("NROASCII")
    NROASCII(2, "Número ASCII"),
    @JsonProperty("TXTASCII")
    TXTASCII(3, "Texto ASCII"),
    @JsonProperty("DADOSBINARIOS")
    DADOSBINARIOS(4, "Dados Binários"),
    @JsonProperty("NROEBCDIC")
    NROEBCDIC(5, "Número EBCDIC"),
    @JsonProperty("TXTEBCDIC")
    TXTEBCDIC(6, "Texto EBCDIC");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    EncodeDadosCampo(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static EncodeDadosCampo get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
