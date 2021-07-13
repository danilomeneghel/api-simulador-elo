package br.com.elo.domain.dto.request;

import br.com.elo.common.utils.annotations.EnumNamePattern;
import br.com.elo.domain.Status;
import br.com.elo.domain.Tipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmbienteRequestDTO implements Serializable {

    @NotNull(message = "Status inválido ou nulo")
    private Status status;

    @NotBlank(message = "A descrição não pode ser em branco e nem nulo")
    @Size(max = 90, message = "Tamanho máximo da descriação deve ser 90 caracteres")
    private String descricao;

    private List<@Valid PortaClienteRequestDTO> portasClientes;
    private List<@Valid PortaServidorRequestDTO> portasServidores;

    private ChavesRequestDTO chaves;

}
