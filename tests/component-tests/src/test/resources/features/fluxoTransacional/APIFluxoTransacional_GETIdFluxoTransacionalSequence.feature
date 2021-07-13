#language: pt
#author: Halison Vitorino
#versao: 1.0
@APIFluxoTransacional @CenarioNegativo @regression
Funcionalidade: API - Get Id e FluxoTransacionalSequence de Fluxo de Transacoes de Autorizacao


  ## GET - Consulta Fluxo de Transacao de Autorizacao por ID com Sucesso
  Cenario: CT <CT> - <CENARIO>
    Dado que possua um protocolo cadastrado:
      |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
      | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
    Dado que possua uma mensagemISO8583 cadastrada:
      | codigoMensagem       | descricao       | tipoMensagem               | status         |
      | 200                  | Credito a Vista | SOLICITACAO                | ATIVO          |
    Dado que possua um fluxoTransacional cadastrado:
      | descricao                  |
      | Fluxo Transacional teste ID|
    Quando eu realizo uma busca de fluxo transacional pelo endpoint de busca por fluxoTransacionalSequence
    Entao a requisição é um sucesso com status 200
    E o response apresenta os campos do fluxo transacional de acordo com a base de dados


  ## GET - Consulta Fluxo de Transacao de Autorizacao por FluxoTransacionalSequence com Sucesso
  Cenario: CT <CT> - <CENARIO>
    Dado que possua um protocolo cadastrado:
      |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
      | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
    Dado que possua uma mensagemISO8583 cadastrada:
      | codigoMensagem       | descricao       | tipoMensagem               | status         |
      | 200                  | Credito a Vista | SOLICITACAO                | ATIVO          |
    Dado que possua um fluxoTransacional cadastrado:
      | descricao                      |
      | Fluxo Transacional teste FluxoTransacionalSequence|
    Quando eu realizo uma busca de fluxo transacional pelo endpoint de busca por fluxoTransacionalSequence
    Entao a requisição é um sucesso com status 200
    E o response apresenta os campos do fluxo transacional de acordo com a base de dados

