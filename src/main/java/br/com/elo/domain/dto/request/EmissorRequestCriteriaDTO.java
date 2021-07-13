package br.com.elo.domain.dto.request;

import br.com.elo.domain.Plataforma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
