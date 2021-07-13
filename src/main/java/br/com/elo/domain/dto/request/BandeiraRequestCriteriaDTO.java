package br.com.elo.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BandeiraRequestCriteriaDTO implements Serializable {

    private String id;

    private Long codigo;

    private Integer codigoBandeira;

    private String descricao;

}
