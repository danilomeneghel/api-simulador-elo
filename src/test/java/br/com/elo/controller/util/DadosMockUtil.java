package br.com.elo.controller.util;

import br.com.elo.domain.*;
import br.com.elo.domain.dto.request.*;
import br.com.elo.domain.dto.response.*;
import br.com.elo.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DadosMockUtil {

    //Produto
    public static ProdutoRequestDTO criaNovoProdutoRequestDTO() {
        return ProdutoRequestDTO
                .builder()
                .codigoBandeira(1)
                .codigoProduto(1)
                .tipoPlataforma(TipoPlataforma.CREDITO)
                .descricao("Cadastro de Produto")
                .build();
    }

    public static ProdutoRequestCriteriaDTO criaNovoProdutoRequestCriteriaDTO() {
        return ProdutoRequestCriteriaDTO
                .builder()
                .codigoProduto(1)
                .descricao("Produto Teste")
                .build();
    }

    public static Produto criaNovoProduto() {
        return Produto
                .builder()
                .codigoBandeira(1)
                .codigoProduto(1)
                .tipoPlataforma(TipoPlataforma.CREDITO)
                .descricao("Cadastro de Produto")
                .build();
    }

    public static Produto produtoSalvo() {
        return Produto
                .builder()
                .id("5feb955b37b9fb7770ec3156")
                .codigoBandeira(1)
                .codigoProduto(1)
                .tipoPlataforma(TipoPlataforma.CREDITO)
                .descricao("Cadastro de Produto")
                .build();
    }

    public static Produto criaProdutoTestRepository() {
        return Produto
                .builder()
                .codigoBandeira(1)
                .codigoProduto(1)
                .tipoPlataforma(TipoPlataforma.CREDITO)
                .descricao("Cadastro de Produto")
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    public static ProdutoResponseDTO produtoResponseDTO() {
        return ProdutoResponseDTO
                .builder()
                .id("5feb955b37b9fb7770ec3156")
                .codigoBandeira(1)
                .codigoProduto(1)
                .tipoPlataforma(TipoPlataforma.CREDITO)
                .descricao("Cadastro de Produto")
                .build();
    }

    public static ProdutoRequestCriteriaDTO dadosProdutoFindAllCriteria(){
        return ProdutoRequestCriteriaDTO
                .builder()
                .codigoBandeira(1)
                .codigoProduto(1)
                .tipoPlataforma(TipoPlataforma.CREDITO)
                .descricao("Cadastro de Produto")
                .build();
    }

    //Ambiente
    public static AmbienteRequestDTO criaNovoSimuladorRequestDTO() {
        List<PortaClienteRequestDTO> portaClienteRequestDTOS = new ArrayList<>();
        List<PortaServidorRequestDTO> portaServidorRequestDTOS = new ArrayList<>();
        portaClienteRequestDTOS.add(PortaClienteRequestDTO
                .builder()
                .nomeHost("hostCliente")
                .nomePorta("portaCliente")
                .numeroPorta(0)
                .numeroSockets(0)
                .tempoTimeout(0.0)
                .status(Status.ATIVO)
                .protocoloSequence(1L)
                .cabecalho(Cabecalho.TCP_MLI)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .swappedMLI(Boolean.FALSE)
                .tipoMLI(TipoMLI.INCLUSIVE).build());
        portaServidorRequestDTOS.add(PortaServidorRequestDTO
                .builder()
                .protocoloSequence(1L)
                .nomeHost("hostServidor")
                .nomePorta("portaServidor")
                .numeroPorta(0)
                .numeroSockets(1)
                .tempoTimeout(1.0)
                .status(Status.ATIVO)
                .cabecalho(Cabecalho.TCP_MLI)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .swappedMLI(Boolean.FALSE)
                .tipoMLI(TipoMLI.INCLUSIVE).build());

        ChavesRequestDTO chavesRequestDTO = ChavesRequestDTO.builder()
                .chavePin("chave PIN")
                .chaveCavv("chave CAVV").build();

        return AmbienteRequestDTO
                .builder()
                .status(Status.ATIVO)
                .descricao("Cadastro de injetor")
                .portasClientes(portaClienteRequestDTOS)
                .portasServidores(portaServidorRequestDTOS)
                .chaves(chavesRequestDTO)
                .build();
    }

    public static AmbienteRequestDTO updateInvalidoRequestDTO() {
        return AmbienteRequestDTO
                .builder()
                .status(Status.INATIVO)
                .descricao("Cadastro de injetor")
                .build();
    }

    public static Ambiente criaNovoSimulador() {
        List<PortaCliente> portasClientes = new ArrayList<>();
        List<PortaServidor> portasServidores = new ArrayList<>();
        portasClientes.add(PortaCliente
                .builder()
                .nomeHost("hostCliente")
                .nomePorta("portaCliente")
                .numeroPorta(0)
                .numeroSockets(0)
                .tempoTimeout(0.0)
                .protocoloSequence(1L)
                .cabecalho(Cabecalho.TCP_MLI)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .swappedMLI(Boolean.FALSE)
                .tipoMLI(TipoMLI.INCLUSIVE).build());
        portasServidores.add(PortaServidor
                .builder()
                .protocoloSequence(1L)
                .nomeHost("hostServidor")
                .nomePorta("portaServidor")
                .numeroPorta(0)
                .numeroSockets(1)
                .tempoTimeout(1.0)
                .cabecalho(Cabecalho.TCP_MLI)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .swappedMLI(Boolean.FALSE)
                .tipoMLI(TipoMLI.INCLUSIVE).build());

        Chaves chaves = Chaves.builder()
                .chavePin("chave PIN")
                .chaveCavv("chave CAVV").build();

        return Ambiente
                .builder()
                .status(Status.ATIVO)
                .descricao("Cadastro de injetor")
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .portasClientes(portasClientes)
                .portasServidores(portasServidores)
                .chaves(chaves)
                .build();
    }

    public static Ambiente criaSimuladorTestRepository() {
        return Ambiente
                .builder()
                .dataCriacao(LocalDateTime.now())
                .ambienteSequence(1L)
                .status(Status.ATIVO)
                .descricao("Cadastro de injetor")
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    public static Ambiente simuladorSalvo() {
        List<PortaCliente> portasClientes = new ArrayList<>();
        List<PortaServidor> portasServidores = new ArrayList<>();
        portasClientes.add(PortaCliente
                .builder()
                .nomeHost("hostCliente")
                .nomePorta("portaCliente")
                .numeroPorta(0)
                .numeroSockets(0)
                .tempoTimeout(0.0)
                .protocoloSequence(1L)
                .cabecalho(Cabecalho.TCP_MLI)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .swappedMLI(Boolean.FALSE)
                .tipoMLI(TipoMLI.INCLUSIVE).build());

        portasServidores.add(PortaServidor
                .builder()
                .protocoloSequence(1L)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .nomeHost("hostServidor")
                .nomePorta("portaServidor")
                .numeroPorta(0)
                .numeroSockets(1)
                .tempoTimeout(1.0)
                .cabecalho(Cabecalho.TCP_MLI)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .swappedMLI(Boolean.FALSE)
                .tipoMLI(TipoMLI.INCLUSIVE).build());

        Chaves chaves = Chaves.builder()
                .chavePin("chave PIN")
                .chaveCavv("chave CAVV").build();

        return Ambiente
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .ambienteSequence(1L)
                .status(Status.ATIVO)
                .descricao("Cadastro de injetor")
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .portasClientes(portasClientes)
                .portasServidores(portasServidores)
                .chaves(chaves)
                .build();
    }


    public static AmbienteResponseDTO ambienteResponseDTO() {
        List<PortaClienteResponseDTO> portaClienteResponseDTOS = new ArrayList<>();
        List<PortaServidorResponseDTO> portaServidorResponseDTOS = new ArrayList<>();
        portaClienteResponseDTOS.add(PortaClienteResponseDTO
                .builder()
                .nomeHost("hostCliente")
                .nomePorta("portaCliente")
                .numeroPorta(0)
                .numeroSockets(0)
                .tempoTimeout(0.0)
                .protocoloSequence(1L)
                .cabecalho(Cabecalho.TCP_MLI)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .swappedMLI(Boolean.FALSE)
                .tipoMLI(TipoMLI.INCLUSIVE).build());

        portaServidorResponseDTOS.add(PortaServidorResponseDTO
                .builder()
                .protocoloSequence(1L)
                .nomeHost("hostServidor")
                .nomePorta("portaServidor")
                .numeroPorta(0)
                .numeroSockets(1)
                .tempoTimeout(1.0)
                .cabecalho(Cabecalho.TCP_MLI)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .swappedMLI(Boolean.FALSE)
                .tipoMLI(TipoMLI.INCLUSIVE).build());

        ChavesResponseDTO chavesResponseDTO = ChavesResponseDTO.builder()
                .chavePin("chave PIN")
                .chaveCavv("chave CAVV").build();

        return AmbienteResponseDTO
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .ambienteSequence(1L)
                .status(Status.ATIVO)
                .descricao("Cadastro de injetor")
                .portasClientes(portaClienteResponseDTOS)
                .portasServidores(portaServidorResponseDTOS)
                .chaves(chavesResponseDTO)
                .build();
    }

    public static AmbienteRequestCriteriaDTO dadosFindAllCriteriaSimulador(){
        return AmbienteRequestCriteriaDTO
                .builder()
                //.codigo(1L)
                //.descricao("Cadastro de injetor")
                .build();
    }

    public static Ambiente criaAmbientePortaServidorProtocoloInexistente() {
        List<PortaCliente> portasClientes = new ArrayList<>();
        List<PortaServidor> portasServidores = new ArrayList<>();
        portasClientes.add(PortaCliente
                .builder()
                .nomeHost("hostCliente")
                .nomePorta("portaCliente")
                .numeroPorta(0)
                .numeroSockets(0)
                .tempoTimeout(0.0)
                .protocoloSequence(1L)
                .cabecalho(Cabecalho.TCP_MLI)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .swappedMLI(Boolean.FALSE)
                .tipoMLI(TipoMLI.INCLUSIVE).build());
        portasServidores.add(PortaServidor
                .builder()
                .protocoloSequence(100L)
                .nomeHost("hostServidor")
                .nomePorta("portaServidor")
                .numeroPorta(0)
                .numeroSockets(1)
                .tempoTimeout(1.0)
                .cabecalho(Cabecalho.TCP_MLI)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .swappedMLI(Boolean.FALSE)
                .tipoMLI(TipoMLI.INCLUSIVE).build());

        Chaves chaves = Chaves.builder()
                .chavePin("chave PIN")
                .chaveCavv("chave CAVV").build();

        return Ambiente
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .ambienteSequence(1L)
                .status(Status.ATIVO)
                .descricao("Cadastro de injetor")
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .portasClientes(portasClientes)
                .portasServidores(portasServidores)
                .chaves(chaves)
                .build();
    }

    public static Ambiente criaAmbientePortaClienteProtocoloInexistente() {
        List<PortaCliente> portasClientes = new ArrayList<>();
        List<PortaServidor> portasServidores = new ArrayList<>();
        portasClientes.add(PortaCliente
                .builder()
                .nomeHost("hostCliente")
                .nomePorta("portaCliente")
                .numeroPorta(0)
                .numeroSockets(0)
                .tempoTimeout(0.0)
                .protocoloSequence(100L)
                .cabecalho(Cabecalho.TCP_MLI)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .swappedMLI(Boolean.FALSE)
                .tipoMLI(TipoMLI.INCLUSIVE).build());
        portasServidores.add(PortaServidor
                .builder()
                .protocoloSequence(1L)
                .nomeHost("hostServidor")
                .nomePorta("portaServidor")
                .numeroPorta(0)
                .numeroSockets(1)
                .tempoTimeout(1.0)
                .cabecalho(Cabecalho.TCP_MLI)
                .codificacaoMLI(CodificacaoMLI.BYTES)
                .comprimentoMLI(ComprimentoMLI.FOUR)
                .swappedMLI(Boolean.FALSE)
                .tipoMLI(TipoMLI.INCLUSIVE).build());

        Chaves chaves = Chaves.builder()
                .chavePin("chave PIN")
                .chaveCavv("chave CAVV").build();

        return Ambiente
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .ambienteSequence(1L)
                .status(Status.ATIVO)
                .descricao("Cadastro de injetor")
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .portasClientes(portasClientes)
                .portasServidores(portasServidores)
                .chaves(chaves)
                .build();
    }

    //Bandeira
    public static BandeiraRequestDTO criaNovaBandeiraRequestDTO() {
        return BandeiraRequestDTO
                .builder()
                .codigoBandeira(7)
                .descricao("Bandeira ELO")
                .build();
    }

    public static Bandeira criaBandeiraTestRepository() {
        return Bandeira
                .builder()
                .codigoBandeira(7)
                .descricao("Bandeira ELO")
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    public static BandeiraRequestCriteriaDTO criaNovaBandeiraRequestCriteriaDTO() {
        return BandeiraRequestCriteriaDTO
                .builder()
                .codigoBandeira(7)
                .descricao("Bandeira ELO")
                .build();
    }

    public static Bandeira criaNovaBandeira() {
        return Bandeira
                .builder()
                .codigoBandeira(7)
                .descricao("Bandeira ELO")
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    public static Bandeira bandeiraDB() {
        return Bandeira
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .codigoBandeira(7)
                .descricao("Bandeira ELO")
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    public static BandeiraResponseDTO bandeiraResponseDTO() {
        return BandeiraResponseDTO
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .codigoBandeira(7)
                .descricao("Bandeira ELO")
                .build();
    }

    //Protocolo
    public static ProtocoloRequestDTO criaNovoProtocoloRequestDTO() {
        return   ProtocoloRequestDTO
                .builder()
                .descricao("Protocolo ELO")
                .tipo(TipoProtocolo.BANDEIRA)
                .status(Status.ATIVO)
                .versao("17.2")
                .encodeCodigoMensagem(EncodeCodigoMensagem.ASCII)
                .encodeMapaDeBits(EncodeMapaDeBits.ASCII)
                .encodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.NROBYTES)
                .build();
    }

    public static Protocolo criaProtocoloTestRepository() {
        return Protocolo
                .builder()
                .dataCriacao(LocalDateTime.now())
                .protocoloSequence(1L)
                .tipo(TipoProtocolo.BANDEIRA)
                .status(Status.ATIVO)
                .descricao("Protocolo ELO")
                .versao("1.0")
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    public static Protocolo criaNovoProtocolo() {
        return   Protocolo
                .builder()
                .descricao("Protocolo ELO")
                .tipo(TipoProtocolo.BANDEIRA)
                .status(Status.ATIVO)
                .versao("17.2")
                .encodeCodigoMensagem(EncodeCodigoMensagem.ASCII)
                .encodeMapaDeBits(EncodeMapaDeBits.ASCII)
                .encodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.NROBYTES)
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    public static Protocolo protocoloSalvo() {
        return  Protocolo
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .protocoloSequence(1L)
                .descricao("Protocolo ELO")
                .tipo(TipoProtocolo.BANDEIRA)
                .status(Status.ATIVO)
                .versao("17.2")
                .encodeCodigoMensagem(EncodeCodigoMensagem.ASCII)
                .encodeMapaDeBits(EncodeMapaDeBits.ASCII)
                .encodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.NROBYTES)
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    public static ProtocoloResponseDTO protocoloResponseDTO() {
        return ProtocoloResponseDTO
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .protocoloSequence(1L)
                .descricao("Protocolo ELO")
                .tipo(TipoProtocolo.BANDEIRA)
                .status(Status.ATIVO)
                .versao("17.2")
                .encodeCodigoMensagem(EncodeCodigoMensagem.ASCII)
                .encodeMapaDeBits(EncodeMapaDeBits.ASCII)
                .encodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.NROBYTES)
                .build();
    }

    public static ProtocoloRequestCriteriaDTO criaNovoProtocoloRequestCriteriaDTO() {
        return ProtocoloRequestCriteriaDTO
                .builder()
                .build();
    }

    //MensagemISO8583
    public static MensagemISO8583 criaMensagemISO8583TestRepository() {
        return MensagemISO8583
                .builder()
                .dataCriacao(LocalDateTime.now())
                .mensagemSequence(1L)
                .status(Status.ATIVO)
                .descricao("Mensagem Credito a Vista")
                .codigoMensagem(200)
                .protocoloSequence(1L)
                .tipoMensagem(TipoMensagem.SOLICITACAO)
                .build();
    }
    public static MensagemISO8583RequestDTO criaNovaMensagemISO8583RequestDTO() {
        return   MensagemISO8583RequestDTO
                .builder()
                .status(Status.ATIVO)
                .descricao("Mensagem Credito a Vista")
                .codigoMensagem(200)
                .protocoloSequence(1L)
                .tipoMensagem(TipoMensagem.SOLICITACAO)
                .build();
    }

    public static MensagemISO8583 criaNovaMensagemISO8583() {
        return   MensagemISO8583
                .builder()
                .mensagemSequence(1L)
                .descricao("Mensagem Credito a Vista")
                .tipoMensagem(TipoMensagem.SOLICITACAO)
                .codigoMensagem(200)
                .protocoloSequence(1L)
                .build();
    }

    public static MensagemISO8583 mensagemISO8583Salva() {
        return  MensagemISO8583
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .mensagemSequence(1L)
                .descricao("Mensagem Credito a Vista")
                .tipoMensagem(TipoMensagem.SOLICITACAO)
                .codigoMensagem(200)
                .protocoloSequence(1L)
                .build();
    }

    public static MensagemISO8583ResponseDTO mensagemISO8583ResponseDTO() {
        return MensagemISO8583ResponseDTO
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .mensagemSequence(1L)
                .descricao("Mensagem Credito a Vista")
                .tipoMensagem(TipoMensagem.SOLICITACAO)
                .codigoMensagem(200)
                .protocoloSequence(1L)
                .build();
    }

    public static MensagemISO8583RequestCriteriaDTO criaNovaMensagemISO8583RequestCriteriaDTO() {
        return MensagemISO8583RequestCriteriaDTO
                .builder()
                .build();
    }

    //Emissor
    public static EmissorRequestDTO criaNovoEmissorRequestDTO() {
        return EmissorRequestDTO
                .builder()
                .codigoEmissor(100)
                .nomeEmissor("Cadastro de emissor")
                .codigoBandeira(1)
                .codigoProcessadora(1)
                .plataforma(Plataforma.CREDITO)
                .build();
    }

    public static EmissorRequestDTO criaNovoEmissorSemBandeiraDTO() {
        return EmissorRequestDTO
                .builder()
                .codigoEmissor(1)
                .nomeEmissor("Cadastro de emissor")
                .codigoProcessadora(1)
                .plataforma(Plataforma.CREDITO)
                .build();
    }

    public static EmissorRequestDTO criaNovoEmissorComBandeiraDTO() {
        return EmissorRequestDTO
                .builder()
                .codigoEmissor(1)
                .codigoBandeira(1)
                .nomeEmissor("Cadastro de emissor")
                .codigoProcessadora(1)
                .plataforma(Plataforma.CREDITO)
                .build();
    }


    public static EmissorRequestDTO updateInvalidoEmissorRequestDTO() {
        return EmissorRequestDTO
                .builder()
                .nomeEmissor("Cadastro de emissor")
                .build();
    }

    public static Emissor criaNovoEmissor() {
        return Emissor
                .builder()
                .nomeEmissor("Cadastro de emissor")
                .build();
    }

    public static Emissor criaNovoEmissorSemBandeiraService() {
        return Emissor
                .builder()
                .codigoEmissor(1)
                .nomeEmissor("Cadastro de emissor")
                .codigoProcessadora(1)
                .plataforma(Plataforma.CREDITO)
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    public static Emissor criaNovoEmissorComBandeiraService() {
        return Emissor
                .builder()
                .codigoEmissor(1)
                .codigoBandeira(1)
                .nomeEmissor("Cadastro de emissor")
                .codigoProcessadora(1)
                .plataforma(Plataforma.CREDITO)
                .dataCriacao(LocalDateTime.now())
                .build();
    }


    public static Emissor emissorSemBandeiraSalvo() {
        return Emissor
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .codigoEmissor(1)
                .nomeEmissor("Cadastro de emissor")
                .codigoProcessadora(1)
                .plataforma(Plataforma.CREDITO)
                .dataCriacao(LocalDateTime.now())
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    public static Emissor emissorComBandeiraSalvo() {
        return Emissor
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .codigoEmissor(1)
                .codigoBandeira(1)
                .nomeEmissor("Cadastro de emissor")
                .codigoProcessadora(1)
                .plataforma(Plataforma.CREDITO)
                .dataCriacao(LocalDateTime.now())
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    public static Emissor criaEmissorTestRepository() {
        return Emissor
                .builder()
                .dataCriacao(LocalDateTime.now())
                .codigoEmissor(1)
                .codigoBandeira(1)
                .nomeEmissor("Cadastro de emissor")
                .plataforma(Plataforma.CREDITO)
                .codigoProcessadora(1)
                .build();
    }

    public static Emissor emissorSalvo() {
        return Emissor
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .codigoEmissor(1)
                .codigoBandeira(1)
                .nomeEmissor("Cadastro de emissor")
                .codigoProcessadora(1)
                .plataforma(Plataforma.CREDITO)
                .dataCriacao(LocalDateTime.now())
                .build();
    }


    public static EmissorResponseDTO emissorSemBandeiraResponseDTO() {
        return EmissorResponseDTO
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .codigoEmissor(1)
                .codigoProcessadora(1)
                .nomeEmissor("Cadastro de emissor")
                .build();
    }

    public static EmissorResponseDTO emissorComBandeiraResponseDTO() {
        return EmissorResponseDTO
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .codigoEmissor(1)
                .codigoBandeira(1)
                .codigoProcessadora(1)
                .nomeEmissor("Cadastro de emissor")
                .plataforma(Plataforma.CREDITO)
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    public static EmissorRequestCriteriaDTO dadosFindAllCriteriaEmissor(){
        return EmissorRequestCriteriaDTO
                .builder()
                .build();
    }

    //Credenciador
    public static CredenciadorRequestDTO criaNovoCredenciadorRequestDTO() {
        return CredenciadorRequestDTO
                .builder()
                .credenciadorCodigo(1)
                .nome("Nome credenciador")
                .status(Status.ATIVO)
                .build();
    }

    public static Credenciador criaCredenciadorTestRepository() {
        return Credenciador
                .builder()
                .id("601239085059924aea7e7db4")
                // .codigo(1L)
                .credenciadorCodigo(1)
                .nome("Nome credenciador")
                .status(Status.ATIVO)
                .build();
    }

    public static CredenciadorRequestCriteriaDTO dadosFindAllCriteriaCredenciador(){
        return CredenciadorRequestCriteriaDTO
                .builder()
                .build();
    }

    public static CredenciadorResponseDTO credenciadorResponseDTO(){
        return CredenciadorResponseDTO
                .builder()
                .id("601239085059924aea7e7db4")
                //.codigo(1L)
                .credenciadorCodigo(1)
                .nome("Nome credenciador")
                .status(Status.ATIVO)
                .build();
    }

    public static Credenciador criaNovoCredenciador() {
        return Credenciador
                .builder()
                .id("601239085059924aea7e7db4")
                .credenciadorCodigo(1)
                .nome("Nome credenciador")
                .status(Status.ATIVO)
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    public static Credenciador credenciadorSalvo() {
        return Credenciador
                .builder()
                .id("601239085059924aea7e7db4")
                .credenciadorCodigo(1)
                .nome("Nome credenciador")
                .status(Status.ATIVO)
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    //FluxoTransacional
    public static FluxoTransacional criaFluxoTransacionalTestRepository() {
        return FluxoTransacional
                .builder()
                .fluxoTransacionalSequence(1L)
                .descricao("Cadastro de fluxo transacional")
                .mensagemSolicitacaoPernaUmSequence(1L)
                .mensagemSolicitacaoPernaDoisSequence(1L)
                .mensagemRespostaPernaTresSequence(1L)
                .mensagemRespostaPernaQuatroSequence(1L)
                .bitVinculacaoMensagensPernasUmQuatro(11)
                .bitVinculacaoMensagensPernasDoisTres(11)
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    public static FluxoTransacional fluxoTransacionalSalvo() {
        return  FluxoTransacional
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .fluxoTransacionalSequence(1L)
                .mensagemSolicitacaoPernaUmSequence(1L)
                .mensagemRespostaPernaQuatroSequence(1L)
                .bitVinculacaoMensagensPernasUmQuatro(11)
                .descricao("Descrição Fluxo Transacional")
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    public static FluxoTransacionalRequestDTO criaNovoFluxoTransacionalRequestDTO() {
        return   FluxoTransacionalRequestDTO
                .builder()
                .descricao("Cadastro de fluxo transacional")
                .mensagemSolicitacaoPernaUmSequence(1L)
                .mensagemSolicitacaoPernaDoisSequence(1L)
                .mensagemRespostaPernaTresSequence(1L)
                .mensagemRespostaPernaQuatroSequence(1L)
                .bitVinculacaoMensagensPernasUmQuatro(11)
                .bitVinculacaoMensagensPernasDoisTres(11)
                .build();
    }

    public static FluxoTransacional criaNovoFluxoTransacional() {
        return FluxoTransacional
                .builder()
                .descricao("Cadastro de fluxo transacional")
                .mensagemSolicitacaoPernaUmSequence(1L)
                .mensagemSolicitacaoPernaDoisSequence(1L)
                .mensagemRespostaPernaTresSequence(1L)
                .mensagemRespostaPernaQuatroSequence(1L)
                .bitVinculacaoMensagensPernasUmQuatro(11)
                .bitVinculacaoMensagensPernasDoisTres(11)
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .build();
    }

    public static FluxoTransacionalResponseDTO fluxoTransacionalDB() {
        return FluxoTransacionalResponseDTO
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .fluxoTransacionalSequence(1L)
                .descricao("Cadastro de fluxo transacional")
                .mensagemSolicitacaoPernaUmSequence(1L)
                .mensagemSolicitacaoPernaDoisSequence(1L)
                .mensagemRespostaPernaTresSequence(1L)
                .mensagemRespostaPernaQuatroSequence(1L)
                .bitVinculacaoMensagensPernasUmQuatro(11)
                .bitVinculacaoMensagensPernasDoisTres(11)
                .build();
    }

    public static FluxoTransacionalRequestCriteriaDTO criaNovoFluxoTransacionalRequestCriteriaDTO() {
        return FluxoTransacionalRequestCriteriaDTO
                .builder()
                .build();
    }

    //Cartao
    public static CartaoRequestDTO criaNovoCartaoRequestDTO() {
        return CartaoRequestDTO
                .builder()
                .codigoBandeira(1)
                .codigoEmissor(1)
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Nome Cartao")
                .pan("1234567890123456789")
                .pin("123456")
                .cardSequenceNumber(Integer.valueOf("123"))
                .cve2("321")
                .dataExpiracao("03/2021")
                .tokenCartao(TokenCartao.SIM)
                .applicationIdentifier("55555")
                .cardAppInterchangeProfile("666666")
                .issuerDiscrData("777777")
                .cardAppTransactionCounter(555)
                .track1("111")
                .track2("222")
                .track2EquivalentData("333")
                .cvnCartao(CvnCartao.CVN05)
                .cvr("22222")
                .dkiKdi("55555")
                .build();
    }

    public static CartaoRequestCriteriaDTO criaNovoCartaoRequestCriteriaDTO() {
        return CartaoRequestCriteriaDTO
                .builder()
                .codigoBandeira(1)
                .codigoEmissor(1)
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Nome Cartao")
                .pan("1234567890123456789")
                .pin("123456")
                .cardSequenceNumber(Integer.valueOf("123"))
                .cve2("321")
                .dataExpiracao("03/2021")
                .tokenCartao(TokenCartao.SIM)
                .applicationIdentifier("55555")
                .cardAppInterchangeProfile("666666")
                .issuerDiscrData("777777")
                .cardAppTransactionCounter(555)
                .track1("111")
                .track2("222")
                .track2EquivalentData("333")
                .cvnCartao(CvnCartao.CVN05)
                .cvr("22222")
                .dkiKdi("55555")
                .build();
    }

    public static Cartao criaNovoCartao() {
        return Cartao
                .builder()
                .codigoBandeira(1)
                .codigoEmissor(1)
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Nome Cartao")
                .pan("1234567890123456789")
                .pin("123456")
                .cardSequenceNumber(Integer.valueOf("123"))
                .cve2("321")
                .dataExpiracao("03/2021")
                .tokenCartao(TokenCartao.SIM)
                .applicationIdentifier("55555")
                .cardAppInterchangeProfile("666666")
                .issuerDiscrData("777777")
                .cardAppTransactionCounter(555)
                .track1("111")
                .track2("222")
                .track2EquivalentData("333")
                .cvnCartao(CvnCartao.CVN05)
                .cvr("22222")
                .dkiKdi("55555")
                .build();
    }

    public static Cartao cartaoSalvo() {
        return Cartao
                .builder()
                .id("5feb955b37b9fb7770ec3158")
                .codigoBandeira(1)
                .codigoEmissor(1)
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Nome Cartao")
                .pan("1234567890123456789")
                .pin("123456")
                .cardSequenceNumber(Integer.valueOf("123"))
                .cve2("321")
                .dataExpiracao("03/2021")
                .tokenCartao(TokenCartao.SIM)
                .applicationIdentifier("55555")
                .cardAppInterchangeProfile("666666")
                .issuerDiscrData("777777")
                .cardAppTransactionCounter(555)
                .track1("111")
                .track2("222")
                .track2EquivalentData("333")
                .cvnCartao(CvnCartao.CVN05)
                .cvr("22222")
                .dkiKdi("55555")
                .build();
    }

    public static Cartao criaCartaoTestRepository() {
        return Cartao
                .builder()
                .codigoBandeira(1)
                .codigoEmissor(1)
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Nome Cartao")
                .pan("1234567890123456789")
                .pin("123456")
                .cardSequenceNumber(Integer.valueOf("123"))
                .cve2("321")
                .dataExpiracao("03/2021")
                .tokenCartao(TokenCartao.SIM)
                .applicationIdentifier("55555")
                .cardAppInterchangeProfile("666666")
                .issuerDiscrData("777777")
                .cardAppTransactionCounter(555)
                .track1("111")
                .track2("222")
                .track2EquivalentData("333")
                .cvnCartao(CvnCartao.CVN05)
                .cvr("22222")
                .dkiKdi("55555")
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    public static CartaoResponseDTO cartaoResponseDTO() {
        return CartaoResponseDTO
                .builder()
                .id("5feb955b37b9fb7770ec3158")
                .codigoBandeira(1)
                .codigoEmissor(1)
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Nome Cartao")
                .pan("1234567890123456789")
                .pin("123456")
                .cardSequenceNumber(Integer.valueOf("123"))
                .cve2("321")
                .dataExpiracao("03/2021")
                .tokenCartao(TokenCartao.SIM)
                .applicationIdentifier("55555")
                .cardAppInterchangeProfile("666666")
                .issuerDiscrData("777777")
                .cardAppTransactionCounter(555)
                .track1("111")
                .track2("222")
                .track2EquivalentData("333")
                .cvnCartao(CvnCartao.CVN05)
                .cvr("22222")
                .dkiKdi("55555")
                .build();
    }

    public static CartaoRequestCriteriaDTO dadosCartaoFindAllCriteria(){
        return CartaoRequestCriteriaDTO
                .builder()
                .codigoBandeira(1)
                .codigoEmissor(1)
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Nome Cartao")
                .pan("1234567890123456789")
                .pin("123456")
                .cardSequenceNumber(Integer.valueOf("123"))
                .cve2("321")
                .dataExpiracao("03/2021")
                .tokenCartao(TokenCartao.SIM)
                .applicationIdentifier("55555")
                .cardAppInterchangeProfile("666666")
                .issuerDiscrData("777777")
                .cardAppTransactionCounter(555)
                .track1("111")
                .track2("222")
                .track2EquivalentData("333")
                .cvnCartao(CvnCartao.CVN05)
                .cvr("22222")
                .dkiKdi("55555")
                .build();
    }

    public static ValidationRulesRequestDTO criaNovaValidationRulesRequestDTO() {

        ValidationBitsRequestDTO validationBits = ValidationBitsRequestDTO.builder().bitNumber(2).validationRule(ValidationRuleConditions.BITDIF).fillingMode(FillingMode.VALORFIXO).value("12345678").build();
        ArrayList validatiobBitsList = new ArrayList<ValidationBitsRequestDTO>();
        validatiobBitsList.add(validationBits);
        return ValidationRulesRequestDTO
                .builder()
                .name("Regra validacao teste mock")
                .validationBits(validatiobBitsList)
                .status(Status.ATIVO)
                .build();
    }

    public static ValidationRulesRequestCriteriaDTO criaNovaValidationRulesRequestCriteriaDTO() {

        return ValidationRulesRequestCriteriaDTO
                .builder()
                .name("Regra validacao teste mock")
                .build();
    }

    public static ValidationRules criaNovaValidationRulesRepository() {

        ValidationBits validationBits = ValidationBits.builder().bitNumber(2).validationRule(ValidationRuleConditions.BITDIF).fillingMode(FillingMode.VALORFIXO).value("12345678").build();
        ArrayList validatiobBitsList = new ArrayList<ValidationBits>();
        validatiobBitsList.add(validationBits);
        return ValidationRules
                .builder()
                .name("Regra validacao teste mock")
                .validationBits(validatiobBitsList)
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .status(Status.ATIVO)
                .build();
    }

    public static ValidationRules criaNovaValidationRules() {

        ValidationBits validationBits = ValidationBits.builder().bitNumber(2).validationRule(ValidationRuleConditions.BITDIF).fillingMode(FillingMode.VALORFIXO).value("12345678").build();
        ArrayList validatiobBitsList = new ArrayList<ValidationBits>();
        validatiobBitsList.add(validationBits);
        return ValidationRules
                .builder()
                .id("5feb955b37b9fb7770ec3158")
                .validationRulesSequence(1L)
                .name("Regra validacao teste mock")
                .validationBits(validatiobBitsList)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .schemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion())
                .status(Status.ATIVO)
                .build();
    }

    public static ValidationRulesResponseDTO validationRulesResponseDTO() {
        ValidationBitsResponseDTO validationBits = ValidationBitsResponseDTO.builder().bitNumber(2).validationRule(ValidationRuleConditions.BITDIF.name()).value("12345678").build();
        ArrayList validatiobBitsList = new ArrayList<ValidationBitsResponseDTO>();
        validatiobBitsList.add(validationBits);
        return ValidationRulesResponseDTO
                .builder()
                .id("5feb955b37b9fb7770ec3158")
                .validationRulesSequence(1L)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .name("Regra validacao teste mock")
                .validationBits(validatiobBitsList)
                .status(Status.ATIVO)
                .build();
    }
}