package br.com.elo.steps.ambiente;

import br.com.elo.domain.Status;
import br.com.elo.dto.*;
import br.com.elo.fixture.AmbienteFixture;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

public class AmbientePostNegativoSteps {

    AmbienteRequestDTO ambienteRequest;
    AmbienteResponseDTO ambienteResponse;
    ResponseEntity<AmbienteResponseDTO> responseEntity;

    @Autowired
    private AmbienteFixture ambienteFixture;

    @Before
    public void setupAmbiente() {
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
    }

    @Dado("^remova a informação de descricao do payload de Ambiente$")
    public void remova_a_informação_de_descricao_do_payload_de_Ambiente() throws Exception {
        ambienteRequest.setDescricao("");
    }


    @Dado("^remova a informação de status do payload de Ambiente$")
    public void remova_a_informação_de_status_do_payload_de_Ambiente() throws Exception {
        ambienteRequest.setStatus(null);
    }

    @Dado("^remova a informação de chave de pin do payload de Ambiente$")
    public void remova_a_informação_de_chave_de_pin_do_payload_de_Ambiente() throws Exception {
        ChavesRequestDTO chavesRequestDTO = ChavesRequestDTO.builder()
                .chaveCavv("chaveCAVV").build();
        ambienteRequest.setChaves(chavesRequestDTO);
    }

    @Dado("^remova a informação de chave cavv do payload de Ambiente$")
    public void remova_a_informação_de_chave_cavv_do_payload_de_Ambiente() throws Exception {
        ChavesRequestDTO chavesRequestDTO = ChavesRequestDTO.builder()
                .chavePin("chavePIN").build();
        ambienteRequest.setChaves(chavesRequestDTO);
    }

    @Dado("^remova a informação de Nome da Porta do payload de Ambiente - Porta Cliente$")
    public void remova_a_informação_de_Nome_da_Porta_do_payload_de_Ambiente_Porta_Cliente() throws Exception {
        List<PortaClienteRequestDTO> portaClienteRequestDTOList = ambienteRequest.getPortasClientes();
        portaClienteRequestDTOList.get(0).setNomePorta("");
        ambienteRequest.setPortasClientes(portaClienteRequestDTOList);
    }

    @Dado("^remova a informação de Nome do Host do payload de Ambiente - Porta Cliente$")
    public void remova_a_informação_de_Nome_do_Host_do_payload_de_Ambiente_Porta_Cliente() throws Exception {
        List<PortaClienteRequestDTO> portaClienteRequestDTOList = ambienteRequest.getPortasClientes();
        portaClienteRequestDTOList.get(0).setNomeHost("");
        ambienteRequest.setPortasClientes(portaClienteRequestDTOList);

    }

    @Dado("^remova a informação de Número da Porta do payload de Ambiente - Porta Cliente$")
    public void remova_a_informação_de_Número_da_Porta_do_payload_de_Ambiente_Porta_Cliente() throws Exception {
        List<PortaClienteRequestDTO> portaClienteRequestDTOList = ambienteRequest.getPortasClientes();
        portaClienteRequestDTOList.get(0).setNumeroPorta(null);
        ambienteRequest.setPortasClientes(portaClienteRequestDTOList);

    }

    @Dado("^remova a informação de Tempo de Timeout do payload de Ambiente - Porta Cliente$")
    public void remova_a_informação_de_Tempo_de_Timeout_do_payload_de_Ambiente_Porta_Cliente() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de Protocolo do payload de Ambiente - Porta Cliente$")
    public void remova_a_informação_de_Protocolo_do_payload_de_Ambiente_Porta_Cliente() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de Números de Sockets do payload de Ambiente - Porta Cliente$")
    public void remova_a_informação_de_Números_de_Sockets_do_payload_de_Ambiente_Porta_Cliente() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de status do payload de Ambiente - Porta Cliente$")
    public void remova_a_informação_de_status_do_payload_de_Ambiente_Porta_Cliente() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de Nome da Porta do payload de Ambiente - Porta Servidor$")
    public void remova_a_informação_de_Nome_da_Porta_do_payload_de_Ambiente_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de Tempo de Timeout do payload de Ambiente - Porta Servidor$")
    public void remova_a_informação_de_Tempo_de_Timeout_do_payload_de_Ambiente_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de Tempo de Timeout dos Sockets do payload de Ambiente - Porta Servidor$")
    public void remova_a_informação_de_Tempo_de_Timeout_dos_Sockets_do_payload_de_Ambiente_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de Protocolo do payload de Ambiente - Porta Servidor$")
    public void remova_a_informação_de_Protocolo_do_payload_de_Ambiente_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de Cabeçalho do payload de Ambiente - Porta Servidor$")
    public void remova_a_informação_de_Cabeçalho_do_payload_de_Ambiente_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de Número de Sockets do payload de Ambiente - Porta Servidor$")
    public void remova_a_informação_de_Número_de_Sockets_do_payload_de_Ambiente_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de Tipo MLI do payload de Ambiente - Porta Servidor$")
    public void remova_a_informação_de_Tipo_MLI_do_payload_de_Ambiente_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de Codificação MLI do payload de Ambiente - Porta Servidor$")
    public void remova_a_informação_de_Codificação_MLI_do_payload_de_Ambiente_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de Comprimento do MLI do payload de Ambiente - Porta Servidor$")
    public void remova_a_informação_de_Comprimento_do_MLI_do_payload_de_Ambiente_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de Swapped MLI do payload de Ambiente - Porta Servidor$")
    public void remova_a_informação_de_Swapped_MLI_do_payload_de_Ambiente_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^remova a informação de status do payload de Ambiente - Porta Servidor$")
    public void remova_a_informação_de_status_do_payload_de_Ambiente_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação inválida para o campo status$")
    public void insira_informação_inválida_para_o_campo_status() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação inválida para o campo tipo$")
    public void insira_informação_inválida_para_o_campo_tipo() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação inválida para o campo Protocolo$")
    public void insira_informação_inválida_para_o_campo_Protocolo() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação inválida para o campo Cabeçalho$")
    public void insira_informação_inválida_para_o_campo_Cabeçalho() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação inválida para o campo Tipo MLI$")
    public void insira_informação_inválida_para_o_campo_Tipo_MLI() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação inválida para o campo Comprimento MLI$")
    public void insira_informação_inválida_para_o_campo_Comprimento_MLI() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação com letras para o campo Nome do Host da Porta Cliente$")
    public void insira_informação_com_letras_para_o_campo_Nome_do_Host_da_Porta_Cliente() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação com letras para o campo Número da Porta da Porta Cliente$")
    public void insira_informação_com_letras_para_o_campo_Número_da_Porta_da_Porta_Cliente() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação com letras para o campo Tempo de Timeout da Porta Cliente$")
    public void insira_informação_com_letras_para_o_campo_Tempo_de_Timeout_da_Porta_Cliente() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação com letras para o campo Número de Sockets da Porta Cliente$")
    public void insira_informação_com_letras_para_o_campo_Número_de_Sockets_da_Porta_Cliente() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação com letras para o campo status da Porta Cliente$")
    public void insira_informação_com_letras_para_o_campo_status_da_Porta_Cliente() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação com letras para o campo Número da Porta da Porta Servidor$")
    public void insira_informação_com_letras_para_o_campo_Número_da_Porta_da_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação com letras para o campo Tempo de Timeout da Porta Servidor$")
    public void insira_informação_com_letras_para_o_campo_Tempo_de_Timeout_da_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação com letras para o campo Tempo de Timeout dos Sockets da Porta Servidor$")
    public void insira_informação_com_letras_para_o_campo_Tempo_de_Timeout_dos_Sockets_da_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação com letras para o campo Número de Sockets da Porta Servidor$")
    public void insira_informação_com_letras_para_o_campo_Número_de_Sockets_da_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }

    @Dado("^insira informação com letras para o campo status da Porta Servidor$")
    public void insira_informação_com_letras_para_o_campo_status_da_Porta_Servidor() throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }
}
