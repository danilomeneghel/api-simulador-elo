package br.com.elo.steps.ambiente;

import br.com.elo.domain.*;
import br.com.elo.dto.*;
import br.com.elo.fixture.AmbienteFixture;
import br.com.elo.fixture.ProtocoloFixture;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AmbientePutSteps {

    @Autowired
    private AmbienteFixture ambienteFixture;

    @Autowired
    private ProtocoloFixture protocoloFixture;

    AmbienteResponseDTO ambienteCadastrado;
    AmbienteRequestDTO ambienteAlterado;
    AmbienteRequestDTO ambienteRequest;
    AmbienteResponseDTO ambienteResponse;

    private Long protocoloSequence;

    @Quando("^eu realizo o PUT deste ambiente alterando os dados de Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV$")
    public void eu_realizo_o_PUT_deste_ambiente_alterando_os_dados_de_Descrição_Tipo_Versão_Status_Chave_de_PIN_e_Chave_CAVV() throws Exception {
        ambienteCadastrado = this.ambienteFixture.getResultado().getBody();
        ambienteAlterado = AmbienteRequestDTO.builder()
                .descricao ("Descrição alterada")
                .status(ambienteCadastrado.getStatus())
                .build();
        ambienteResponse = ambienteFixture.atualizaAmbiente(ambienteAlterado, ambienteCadastrado.getAmbienteSequence()).getBody();


    }

    @Então("^o response retorna os campos do Ambiente alterado$")
    public void o_response_retorna_os_campos_do_Ambiente_alterado() throws Exception {
        assertEquals(ambienteAlterado.getDescricao(), ambienteResponse.getDescricao());

    }

    @Então("^os dados do ambiente são alterados na base de dados$")
    public void os_dados_do_ambiente_são_alterados_na_base_de_dados() throws Exception {
        assertNotNull(ambienteResponse);
        assertEquals(ambienteResponse.getDescricao(), ambienteAlterado.getDescricao());
        assertEquals(ambienteResponse.getStatus(), ambienteAlterado.getStatus());

    }

    @Dado("^que possua um Ambiente criado sem nenhuma porta cadastrada$")
    public void quePossuaUmAmbienteCriadoSemNenhumaPortaCadastrada() {

        ambienteRequest = AmbienteRequestDTO.builder()
                .descricao("Descrição alterada")
                .status(Status.ATIVO)
                .build();
        ambienteResponse = ambienteFixture.incluirAmbiente(ambienteRequest).getBody();

    }

    @Quando("^eu realizo o PUT deste ambiente para incluir uma nova Porta Cliente$")
    public void euRealizoOPUTDesteAmbienteParaIncluirUmaNovaPortaCliente() {
        ambienteCadastrado = this.ambienteFixture.getResultado().getBody();
        List<PortaClienteRequestDTO> portaClienteRequestDTOList = Arrays.asList(PortaClienteRequestDTO.builder()
                .nomeHost("hostCliente")
                .nomePorta("portaCliente")
                .numeroPorta(0)
                .build());

        ambienteAlterado = AmbienteRequestDTO.builder()
                .descricao (ambienteCadastrado.getDescricao())
                .status(ambienteCadastrado.getStatus())
                .portasClientes(portaClienteRequestDTOList)
                .build();
        ambienteResponse = ambienteFixture.atualizaAmbiente(ambienteAlterado, ambienteCadastrado.getAmbienteSequence()).getBody();
    }

    @Quando("^eu realizo o PUT deste ambiente para incluir uma nova Porta Servidor$")
    public void euRealizoOPUTDesteAmbienteParaIncluirUmaNovaPortaServidor() {
        ambienteCadastrado = this.ambienteFixture.getResultado().getBody();
        List<PortaServidorRequestDTO> portaServidorRequestDTOList = Arrays.asList(PortaServidorRequestDTO.builder()
                .nomeHost("hostServidor")
                .nomePorta("portaServidor")
                .numeroPorta(0)
                .build());

        ambienteAlterado = AmbienteRequestDTO.builder()
                .descricao (ambienteCadastrado.getDescricao())
                .status(ambienteCadastrado.getStatus())
                .portasServidores(portaServidorRequestDTOList)
                .build();
        ambienteResponse = ambienteFixture.atualizaAmbiente(ambienteAlterado, ambienteCadastrado.getAmbienteSequence()).getBody();
    }

    @Dado("^que possua um Ambiente criado com Porta Cliente cadastrada$")
    public void quePossuaUmAmbienteCriadoComPortaClienteCadastrada() {
        List<PortaClienteRequestDTO> portaClienteRequestDTOList = Arrays.asList(PortaClienteRequestDTO.builder()
                .nomeHost("hostCliente")
                .nomePorta("portaCliente")
                .numeroPorta(0)
                .build());

        ambienteRequest = AmbienteRequestDTO.builder()
                .descricao("Ambiente teste")
                .portasClientes(portaClienteRequestDTOList)
                .status(Status.ATIVO)
                .build();
        ambienteResponse = ambienteFixture.incluirAmbiente(ambienteRequest).getBody();
    }

    @Quando("^eu realizo o PUT deste ambiente para incluir uma segunda Porta Cliente$")
    public void euRealizoOPUTDesteAmbienteParaIncluirUmaSegundaPortaCliente() {
        ambienteCadastrado = this.ambienteFixture.getResultado().getBody();
        PortaClienteRequestDTO portaClienteNova = PortaClienteRequestDTO.builder()
                .nomeHost("hostCliente2")
                .nomePorta("portaCliente2")
                .numeroPorta(1)
                .build();

        PortaClienteResponseDTO portaClienteCadastrada = ambienteCadastrado.getPortasClientes().get(0);
        PortaClienteRequestDTO portaClienteExistente = PortaClienteRequestDTO.builder()
                .nomeHost(portaClienteCadastrada.getNomeHost())
                .nomePorta(portaClienteCadastrada.getNomePorta())
                .numeroPorta(portaClienteCadastrada.getNumeroPorta())
                .build();

        ambienteAlterado = AmbienteRequestDTO.builder()
                .descricao(ambienteCadastrado.getDescricao())
                .status(ambienteCadastrado.getStatus())
                .portasClientes(Arrays.asList(portaClienteExistente, portaClienteNova))
                .build();

        ambienteResponse = ambienteFixture.atualizaAmbiente(ambienteAlterado, ambienteCadastrado.getAmbienteSequence()).getBody();

    }

    @Dado("^que possua um Ambiente criado com Porta Servidor cadastrada$")
    public void quePossuaUmAmbienteCriadoComPortaServidorCadastrada() {
        List<PortaServidorRequestDTO> portaServidorRequestDTOList = Arrays.asList(PortaServidorRequestDTO.builder()
                .nomeHost("hostServidor")
                .nomePorta("portaServidor")
                .numeroPorta(0)
                .build());

        ambienteRequest = AmbienteRequestDTO.builder()
                .descricao("Ambiente teste")
                .portasServidores(portaServidorRequestDTOList)
                .status(Status.ATIVO)
                .build();
        ambienteResponse = ambienteFixture.incluirAmbiente(ambienteRequest).getBody();
    }

    @Quando("^eu realizo o PUT deste ambiente para incluir uma segunda Porta Servidor$")
    public void euRealizoOPUTDesteAmbienteParaIncluirUmaSegundaPortaServidor() {
        ambienteCadastrado = this.ambienteFixture.getResultado().getBody();
        PortaServidorRequestDTO portaServidorNova = PortaServidorRequestDTO.builder()
                .nomeHost("hostServidor2")
                .nomePorta("portaServidor2")
                .numeroPorta(1)
                .build();

        PortaServidorResponseDTO portaServidorCadastrada = ambienteCadastrado.getPortasServidores().get(0);
        PortaServidorRequestDTO portaServidorExistente = PortaServidorRequestDTO.builder()
                .nomeHost(portaServidorCadastrada.getNomeHost())
                .nomePorta(portaServidorCadastrada.getNomePorta())
                .numeroPorta(portaServidorCadastrada.getNumeroPorta())
                .build();

        ambienteAlterado = AmbienteRequestDTO.builder()
                .descricao(ambienteCadastrado.getDescricao())
                .status(ambienteCadastrado.getStatus())
                .portasServidores(Arrays.asList(portaServidorExistente, portaServidorNova))
                .build();

        ambienteResponse = ambienteFixture.atualizaAmbiente(ambienteAlterado, ambienteCadastrado.getAmbienteSequence()).getBody();
    }

    @Quando("^eu realizo o PUT deste ambiente para remover uma nova Porta Cliente$")
    public void euRealizoOPUTDesteAmbienteParaRemoverUmaNovaPortaCliente() {
        ambienteCadastrado = this.ambienteFixture.getResultado().getBody();
        ambienteAlterado = AmbienteRequestDTO.builder()
                .descricao(ambienteCadastrado.getDescricao())
                .status(ambienteCadastrado.getStatus())
                .portasClientes(new ArrayList<PortaClienteRequestDTO>())
                .build();

        ambienteResponse = ambienteFixture.atualizaAmbiente(ambienteAlterado, ambienteCadastrado.getAmbienteSequence()).getBody();
    }

    @Quando("^eu realizo o PUT deste ambiente para remover uma nova Porta Servidor$")
    public void euRealizoOPUTDesteAmbienteParaRemoverUmaNovaPortaServidor() {
        ambienteCadastrado = this.ambienteFixture.getResultado().getBody();
        ambienteAlterado = AmbienteRequestDTO.builder()
                .descricao(ambienteCadastrado.getDescricao())
                .status(ambienteCadastrado.getStatus())
                .portasServidores(new ArrayList<PortaServidorRequestDTO>())
                .build();

        ambienteResponse = ambienteFixture.atualizaAmbiente(ambienteAlterado, ambienteCadastrado.getAmbienteSequence()).getBody();
    }

    @Dado("^que possua um Ambiente criado com Porta Cliente e Porta Servidor cadastrada$")
    public void quePossuaUmAmbienteCriadoComPortaClienteEPortaServidorCadastrada() {
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

    @Quando("^eu realizo o PUT deste ambiente para editar todos os dados de uma Porta Cliente e de uma Porta Servidor$")
    public void euRealizoOPUTDesteAmbienteParaEditarTodosOsDadosDeUmaPortaClienteEDeUmaPortaServidor() {

        ambienteCadastrado = this.ambienteFixture.getResultado().getBody();
        PortaClienteRequestDTO portaClienteAlterada = PortaClienteRequestDTO.builder()
                .nomeHost("Host cliente alterado")
                .nomePorta("Porta cliente alterada")
                .numeroPorta(8080)
                .cabecalho(Cabecalho.TPDU)
                .protocoloSequence(protocoloSequence)
                .tempoTimeout(5.0)
                .build();

        PortaServidorRequestDTO portaServidorAlterada = PortaServidorRequestDTO.builder()
                .nomeHost("Host servidor alterado")
                .nomePorta("Porta servidor alterado")
                .numeroPorta(8081)
                .swappedMLI(Boolean.TRUE)
                .tipoMLI(TipoMLI.EXCLUSIVE)
                .protocoloSequence(protocoloSequence)
                .tempoTimeoutSockets(6.0)
                .numeroSockets(2)
                .tempoTimeout(1.4)
                .cabecalho(Cabecalho.TPDU)
                .comprimentoMLI(ComprimentoMLI.TWO)
                .codificacaoMLI(CodificacaoMLI.ASCII)
                .tempoTimeout(1.0)
                .build();

        ambienteAlterado = AmbienteRequestDTO.builder()
                .descricao(ambienteCadastrado.getDescricao())
                .status(ambienteCadastrado.getStatus())
                .portasServidores(Arrays.asList(portaServidorAlterada))
                .portasClientes(Arrays.asList(portaClienteAlterada))
                .build();

        ambienteResponse = ambienteFixture.atualizaAmbiente(ambienteAlterado, ambienteCadastrado.getAmbienteSequence()).getBody();
    }
}
