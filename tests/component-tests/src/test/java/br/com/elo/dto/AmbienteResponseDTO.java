package br.com.elo.dto;

import br.com.elo.domain.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmbienteResponseDTO implements Serializable {
    private String id;
    private Status status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataAlteracao;
    private String descricao;
    private Long ambienteSequence;
    private List<PortaClienteResponseDTO> portasClientes;
    private List<PortaServidorResponseDTO> portasServidores;
    private ChavesResponseDTO chaves;

}