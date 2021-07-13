package br.com.elo.dto.emissor;


import br.com.elo.domain.Plataforma;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmissorRequestCriteriaDTO implements Serializable {

    private String id;
    private Integer codigoBandeira;
    private Integer codigoEmissor;
    private String nomeEmissor;
    private Plataforma plataforma;
    private Integer codigoProcessadora;


}
