package br.com.elo.steps.ambiente;

import br.com.elo.domain.Status;
import br.com.elo.dto.*;
import br.com.elo.fixture.AmbienteFixture;
import cucumber.api.java.pt.Dado;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class AmbienteCommonSteps {

    AmbienteRequestDTO ambienteRequest;
    AmbienteResponseDTO ambienteResponse;

    @Autowired
    private AmbienteFixture ambienteFixture;

    @Dado("que eu possua um Ambiente")
    public void queEuPossuaUmAmbiente() {
        ChavesRequestDTO chavesRequestDTO = ChavesRequestDTO.builder()
                .chavePin("chavePin")
                .chaveCavv("chaveCavv").build();
        List<PortaClienteRequestDTO> portaClienteRequestDTOList = Arrays.asList(PortaClienteRequestDTO.builder()
                .nomeHost("hostCliente")
                .nomePorta("portaCliente")
                .numeroPorta(0)
                .build());
        List<PortaServidorRequestDTO> portaServidorRequestDTOList = Arrays.asList(PortaServidorRequestDTO.builder()
                .nomeHost("hostServidor")
                .nomePorta("portaServidor")
                .numeroPorta(0)
                .build());

        ambienteRequest = AmbienteRequestDTO.builder()
                .descricao("Ambiente teste")
                .chaves(chavesRequestDTO)
                .portasClientes(portaClienteRequestDTOList)
                .portasServidores(portaServidorRequestDTOList)
                .status(Status.ATIVO)
                .build();
        ambienteResponse = ambienteFixture.incluirAmbiente(ambienteRequest).getBody();
    }
}
