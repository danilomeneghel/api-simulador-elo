package br.com.elo.dto.emissor;


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
public class EmissorRequestDTO implements Serializable {
    private Integer codigoEmissor;
    private Integer codigoBandeira;
    private String nomeEmissor;
    private Integer codigoProcessadora;
    private Plataforma plataforma;
}
