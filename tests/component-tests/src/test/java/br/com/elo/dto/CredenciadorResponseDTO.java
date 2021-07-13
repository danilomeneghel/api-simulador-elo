package br.com.elo.dto;


import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredenciadorResponseDTO implements Serializable {

    private String id;
    //private Long codigo;
    private Long credenciadorCodigo;
    private String nome;
    //private String estabelecimentoComercial = "";
    private Status status;
    private String dataCriacao;
    private String dataAlteracao;
}
