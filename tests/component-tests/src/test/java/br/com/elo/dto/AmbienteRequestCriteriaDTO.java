package br.com.elo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmbienteRequestCriteriaDTO implements Serializable {

    private String id;
    private Long ambienteSequence;

    //@EnumNamePattern(regexp = "ATIVO|INATIVO", message = "Status inválido")
    private Integer status;

    @Size(max = 90, message = "Tamanho máximo da descriação deve ser 90 caracteres")
    private String descricao;

    private List<PortaClienteRequestDTO> portasClientes;

    private List<PortaServidorRequestDTO> portasServidores;
}