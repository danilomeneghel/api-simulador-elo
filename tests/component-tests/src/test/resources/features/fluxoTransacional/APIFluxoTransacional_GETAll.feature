#language: pt
#author: Halison Vitorino
#versao: 1.0
@APIFluxoTransacional @CenarioPositivo @regression
Funcionalidade: API - Pesquisa de Fluxo de Transacoes de Autorizacao


	## GET FindAll MensagemISO8583
  Cenario: CT <CT> - <CENARIO> - Retorno único

    Dado que possua um protocolo cadastrado:
      |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
      | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
    Dado que possua uma mensagemISO8583 cadastrada:
      | codigoMensagem       | descricao       | tipoMensagem               | status         |
      | 200                  | Credito a Vista | SOLICITACAO                | ATIVO          |
    Dado que possua um fluxoTransacional cadastrado:
      | descricao                      |
      | Fluxo Transacional teste FluxoTransacionalSequence|
    Quando realizo o getAll da API de fluxotransacional
    Então a requisição é um sucesso com status 200
    E o response apresenta uma lista com um fluxotransacional cadastrado
