package br.com.elo.dto;


import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtocoloRequestCriteriaDTO implements Serializable {

    private String id;
    private Long codigo;
    private Integer status;
    private Integer tipo;
    private String descricao;
    private String versao;
}
