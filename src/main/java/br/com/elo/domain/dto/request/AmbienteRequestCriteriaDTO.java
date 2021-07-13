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
public class AmbienteRequestCriteriaDTO {

    private String id;
    private Long ambienteSequence;
    private Status status;

    @Size(max = 90, message = "Tamanho máximo da descriação deve ser 90 caracteres")
    private String descricao;

    private PortaClienteRequestCriteriaDTO portaCliente;

    private PortaServidorRequestCriteriaDTO portaServidor;
    private Long nsu;
}
