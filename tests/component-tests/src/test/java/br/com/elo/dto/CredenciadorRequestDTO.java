package br.com.elo.dto;


import br.com.elo.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredenciadorRequestDTO implements Serializable {

    private Long credenciadorCodigo;
    private String nome;
    private Status status;
}
