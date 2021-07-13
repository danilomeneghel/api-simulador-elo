package br.com.elo.domain.dto.request;


import br.com.elo.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredenciadorRequestCriteriaDTO {

    private String id;

    private Long codigo;

    private Long credenciadorCodigo;

    @Size(max = 60, message = "Tamanho máximo do nome credenciador ser 60 caracteres")
    private String nome;

    private Status status;
}