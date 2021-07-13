package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum CvnCartao {

    @JsonProperty("CVN05")
    CVN05(1, "05"),
    @JsonProperty("CVN15")
    CVN15(2, "15");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    CvnCartao(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static CvnCartao get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
