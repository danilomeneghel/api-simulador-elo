#language: pt
#author: Halison Vitorino
#versao: 1.0
@APIFluxoTransacional @CenarioPositivo @regression
Funcionalidade: API - Cadastro de Fluxo de Transacoes de Autorizacao

  ## POST - Cadastro de Fluxos de Transacoes de Autorizacao com Sucesso
  Esquema do Cenario: CT <CT> - <CENARIO>
    Dado que possua um protocolo cadastrado:
      |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
      | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
    Dado que possua uma lista de mensagensISO8583 cadastradas:
      | codigoMensagem       | descricao       | tipoMensagem               | status         |
      | 200                  | Credito a Vista | SOLICITACAO                | ATIVO          |
      | 210                  | Credito a Vista | RESPOSTA                   | ATIVO          |
    Dado que possua um fluxo de transação com o payload:
      | descricao     |
      | <exDescricao> |
    Quando realizo POST deste fluxo de transaçao
    Então a requisição é um sucesso com status 201
    E o response apresenta todos os campos referente ao fluxo de transação preenchidos corretamente

    Exemplos: 
      | CT | CENARIO                                  | exDescricao |
      | 01 | Inclusao fluxo transacional pernal 1 e 4 | FLUXO1E4    |
      | 02 | Inclusao fluxo transacional pernal 2 e 3 | FLUXO2E3    |