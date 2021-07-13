package br.com.elo.steps.ambiente;

import br.com.elo.domain.*;
import br.com.elo.dto.*;
import br.com.elo.fixture.AmbienteFixture;
import br.com.elo.fixture.ProtocoloFixture;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AmbientePostSteps {

    AmbienteRequestDTO ambienteRequest;
    AmbienteResponseDTO ambienteResponse;
    ResponseEntity<AmbienteResponseDTO> responseEntity;

    @Autowired
    private AmbienteFixture ambienteFixture;

    @Autowired
    private ProtocoloFixture protocoloFixture;

    private Long protocoloSequence;

    @Dado("^que possua dados de Ambiente para campos Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV$")
    public void que_possua_dados_de_Ambiente_para_campos_Descrição_Tipo_Versao_Status_Chave_de_PIN_e_Chave_CAVV() throws Exception {

        protocoloSequence = protocoloFixture.getResultado().getBody().getProtocoloSequence();
        ChavesRequestDTO chavesRequestDTO = ChavesRequestDTO.builder()
                .chavePin("chavePin")
                .chaveCavv("chaveCavv").build();
        List<PortaClienteRequestDTO> portaClienteRequestDTOList = Arrays.asList(PortaClienteRequestDTO.builder()
                .nomeHost("hostCliente")
                .nomePorta("portaCliente")
                .numeroPorta(0)
                .protocoloSequence(protocoloSequence)
                .build());
        List<PortaServidorRequestDTO> portaServidorRequestDTOList = Arrays.asList(PortaServidorRequestDTO.builder()
                .nomeHost("hostServidor")
                .nomePorta("portaServidor")
                .numeroPorta(0)
                .protocoloSequence(protocoloSequence)
                .build());

        ambienteRequest = AmbienteRequestDTO.builder()
                .descricao("Ambiente teste")
                .chaves(chavesRequestDTO)
                .portasClientes(portaClienteRequestDTOList)
                .portasServidores(portaServidorRequestDTOList)
                .status(Status.ATIVO)
                .build();
    }
    @Dado("^campos válidos de Ambiente de para Porta Cliente$")
    public void campos_válidos_de_Ambiente_de_para_Porta_Cliente() throws Exception {

    }

    @Quando("^realizar o POST de Ambiente$")
    public void realizar_o_POST_de_Ambiente() throws Exception {
        ambienteResponse = ambienteFixture.incluirAmbiente(ambienteRequest).getBody();
    }

    @Então("^o status code é (\\d+)$")
    public void o_status_code_é(int arg1) throws Exception {
        responseEntity = ambienteFixture.getResultado();
        assertEquals(responseEntity.getStatusCodeValue(), arg1);
    }

    @Então("^o response retorna os campos do Ambiente cadastrado$")
    public void o_response_retorna_os_campos_do_Ambiente_cadastrado() throws Exception {
        assertNotNull(ambienteResponse.getAmbienteSequence());
        assertNotNull(ambienteResponse.getDescricao());
        assertNotNull(ambienteResponse.getStatus().getDescricao());
        assertNotNull(ambienteResponse.getPortasClientes().get(0).getNomePorta());
        assertNotNull(ambienteResponse.getPortasServidores().get(0).getNomePorta());
        assertNotNull(ambienteResponse.getChaves().getChavePin());
        assertNotNull(ambienteResponse.getChaves().getChaveCavv());
    }

    @Então("^as informações do Ambiente são salvas na base de dados$")
    public void as_informações_do_Ambiente_são_salvas_na_base_de_dados() throws Exception {
        assertNotNull(ambienteResponse);
    }

    @E("^campos válidos de Ambiente de para Porta Servidor$")
    public void camposValidosDeAmbienteDeParaPortaServidor() {
        protocoloSequence = protocoloFixture.getResultado().getBody().getProtocoloSequence();
        List<PortaServidorRequestDTO> portaServidorRequestDTOList = Arrays.asList(PortaServidorRequestDTO.builder()
                .nomeHost("hostServidor")
                .nomePorta("portaServidor")
                .numeroPorta(0)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .cabecalho(Cabecalho.TCP_MLI)
                .numeroSockets(1)
                .tipoMLI(TipoMLI.EXCLUSIVE)
                .protocoloSequence(1L)
                .tempoTimeout(1.0)
                .tempoTimeoutSockets(1.0)
                .swappedMLI(Boolean.FALSE)
                .protocoloSequence(protocoloSequence)
                .build());
        ambienteRequest.setPortasServidores(portaServidorRequestDTOList);


    }

    @E("^não possua portas cadastradas para este ambiente$")
    public void naoPossuaPortasCadastradasParaEsteAmbiente() {
        List<PortaClienteRequestDTO> portaClienteRequestDTOList = new ArrayList<>();
        List<PortaServidorRequestDTO> portaServidorRequestDTOList = new ArrayList<>();

        ambienteRequest.setPortasClientes(portaClienteRequestDTOList);
        ambienteRequest.setPortasServidores(portaServidorRequestDTOList);
    }

    @E("^o response retorna os campos do Ambiente cadastrado sem nenhuma porta$")
    public void oResponseRetornaOsCamposDoAmbienteCadastradoSemNenhumaPorta() {
        assertNotNull(ambienteResponse.getAmbienteSequence());
        assertNotNull(ambienteResponse.getDescricao());
        assertNotNull(ambienteResponse.getStatus().getDescricao());
        assertEquals(ambienteResponse.getPortasClientes().size(), 0);
        assertEquals(ambienteResponse.getPortasServidores().size(), 0);
        assertNotNull(ambienteResponse.getChaves().getChavePin());
        assertNotNull(ambienteResponse.getChaves().getChaveCavv());
    }

    @Dado("^campos válidos de Ambiente de para Porta Servidor e campo Tipo MLI com valor	BOTH$")
    public void campos_válidos_de_Ambiente_de_para_Porta_Servidor_e_campo_Tipo_MLI_com_valor_BOTH() throws Exception {
        List<PortaServidorRequestDTO> portaServidorRequestDTOList = ambienteRequest.getPortasServidores();
        portaServidorRequestDTOList.get(0).setTipoMLI(TipoMLI.BOTH);
        ambienteRequest.setPortasServidores(portaServidorRequestDTOList);
    }

    @Dado("^campos válidos de Ambiente de para Porta Servidor e campo Tipo MLI com valor	INCLUSIVE$")
    public void campos_válidos_de_Ambiente_de_para_Porta_Servidor_e_campo_Tipo_MLI_com_valor_INCLUSIVE() throws Exception {
        List<PortaServidorRequestDTO> portaServidorRequestDTOList = ambienteRequest.getPortasServidores();
        portaServidorRequestDTOList.get(0).setTipoMLI(TipoMLI.INCLUSIVE);
        ambienteRequest.setPortasServidores(portaServidorRequestDTOList);

    }

    @Dado("^campos válidos de Ambiente de para Porta Servidor e campo Tipo MLI com valor	EXCLUSIVE$")
    public void campos_válidos_de_Ambiente_de_para_Porta_Servidor_e_campo_Tipo_MLI_com_valor_EXCLUSIVE() throws Exception {
        List<PortaServidorRequestDTO> portaServidorRequestDTOList = ambienteRequest.getPortasServidores();
        portaServidorRequestDTOList.get(0).setTipoMLI(TipoMLI.EXCLUSIVE);
        ambienteRequest.setPortasServidores(portaServidorRequestDTOList);

    }

    @Dado("^campos válidos de Ambiente de para Porta Servidor e campo Codificação MLI com valor	BYTES$")
    public void campos_válidos_de_Ambiente_de_para_Porta_Servidor_e_campo_Codificação_MLI_com_valor_BYTES() throws Exception {
        List<PortaServidorRequestDTO> portaServidorRequestDTOList = ambienteRequest.getPortasServidores();
        portaServidorRequestDTOList.get(0).setCodificacaoMLI(CodificacaoMLI.BYTES);
        ambienteRequest.setPortasServidores(portaServidorRequestDTOList);

    }

    @Dado("^campos válidos de Ambiente de para Porta Servidor e campo Comprimento do MLI com valor	TWO$")
    public void campos_válidos_de_Ambiente_de_para_Porta_Servidor_e_campo_Comprimento_do_MLI_com_valor_TWO() throws Exception {
        List<PortaServidorRequestDTO> portaServidorRequestDTOList = ambienteRequest.getPortasServidores();
        portaServidorRequestDTOList.get(0).setComprimentoMLI(ComprimentoMLI.TWO);
        ambienteRequest.setPortasServidores(portaServidorRequestDTOList);
    }

    @Dado("^campos válidos de Ambiente de para Porta Servidor e campo Comprimento do MLI com valor	FOUR$")
    public void campos_válidos_de_Ambiente_de_para_Porta_Servidor_e_campo_Comprimento_do_MLI_com_valor_FOUR() throws Exception {
        List<PortaServidorRequestDTO> portaServidorRequestDTOList = ambienteRequest.getPortasServidores();
        portaServidorRequestDTOList.get(0).setComprimentoMLI(ComprimentoMLI.FOUR);
        ambienteRequest.setPortasServidores(portaServidorRequestDTOList);

    }

    @Dado("^campos válidos de Ambiente de para Porta Servidor e campo status com valor	ATIVO$")
    public void campos_válidos_de_Ambiente_de_para_Porta_Servidor_e_campo_status_com_valor_ATIVO() throws Exception {
        ambienteRequest.setStatus(Status.ATIVO);
    }

    @Dado("^campos válidos de Ambiente de para Porta Servidor e campo status com valor	INATIVO$")
    public void campos_válidos_de_Ambiente_de_para_Porta_Servidor_e_campo_status_com_valor_INATIVO() throws Exception {
        ambienteRequest.setStatus(Status.INATIVO);
    }


    @E("^campos válidos de Ambiente de para (\\d+) Portas Cliente$")
    public void camposVálidosDeAmbienteDeParaPortasCliente(int arg0) {
        protocoloSequence = protocoloFixture.getResultado().getBody().getProtocoloSequence();
        List<PortaClienteRequestDTO> portaClienteRequestDTOList = ambienteRequest.getPortasClientes();
        PortaClienteRequestDTO portaClienteRequestDTO = PortaClienteRequestDTO.builder()
                .nomeHost("hostCliente2")
                .nomePorta("portaCliente2")
                .numeroPorta(1)
                .protocoloSequence(protocoloSequence)
                .build();
        portaClienteRequestDTOList = Arrays.asList(portaClienteRequestDTOList.get(0), portaClienteRequestDTO);
        ambienteRequest.setPortasClientes(portaClienteRequestDTOList);
    }

    @E("^campos válidos de Ambiente de para (\\d+) Portas Servidor$")
    public void camposVálidosDeAmbienteDeParaPortasServidor(int arg0) {
        List<PortaServidorRequestDTO> portaServidorRequestDTOList = ambienteRequest.getPortasServidores();
        PortaServidorRequestDTO portaServidorRequestDTO = PortaServidorRequestDTO.builder()
                .nomeHost("hostServidor2")
                .nomePorta("portaServidor2")
                .numeroPorta(1)
                .protocoloSequence(protocoloSequence)
                .build();
        portaServidorRequestDTOList = Arrays.asList(portaServidorRequestDTOList.get(0), portaServidorRequestDTO);
        ambienteRequest.setPortasServidores(portaServidorRequestDTOList);

    }
}
