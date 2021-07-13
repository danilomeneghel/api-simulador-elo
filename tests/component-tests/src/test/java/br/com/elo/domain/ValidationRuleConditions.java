package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum ValidationRuleConditions {

    @JsonProperty("BITPM")
    BITPM(1, "BITPM"),
    @JsonProperty("BITNPM")
    BITNPM(2, "BITNPM"),
    @JsonProperty("BITIGUAL")
    BITIGUAL(1, "BITIGUAL"),
    @JsonProperty("BITDIF")
    BITDIF(2, "BITDIF"),
    @JsonProperty("BITCONTEM")
    BITCONTEM(1, "BITCONTEM"),
    @JsonProperty("BITCOMECACOM")
    BITCOMECACOM(2, "BITCOMECACOM"),
    @JsonProperty("BITTERMINACOM")
    BITTERMINACOM(2, "BITTERMINACOM");


    //@JsonValue
    private final Integer id;
    private final String descricao;

    ValidationRuleConditions(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static ValidationRuleConditions get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }
}
