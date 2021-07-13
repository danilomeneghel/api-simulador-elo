package br.com.elo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.elo.domain.*;
import java.io.Serializable;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmissorRequestDTO implements Serializable {
    private String nomeEmissor;
    private Long bandeiraCodigo;
    private Integer codigoEmissor;
    private Integer codigoProcessadora;
    private Plataforma plataforma;

}
