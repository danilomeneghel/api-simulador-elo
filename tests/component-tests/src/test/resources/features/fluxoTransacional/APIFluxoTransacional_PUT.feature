#language: pt
#author: Halison Vitorino
#versao: 1.0
@APIFluxoTransacional @CenarioPositivo @Regression
Funcionalidade: API - Alteração de Fluxo de Transacoes de Autorizacao

  ## PUT - Altera Fluxo de Transacao de Autorizacao por CODIGO com Sucesso
  Cenario: CT 1 - Atualiza dados do FluxoTransacional - Descrição
    Dado que possua um protocolo cadastrado:
      |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
      | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
    Dado que possua uma mensagemISO8583 cadastrada:
      | codigoMensagem       | descricao       | tipoMensagem               | status         |
      | 200                  | Credito a Vista | SOLICITACAO                | ATIVO          |
    Dado que possua um fluxoTransacional cadastrado:
      | descricao     |
      | Fluxo transacional teste |
    Quando realizo um put com a descricao do fluxoTransacional alterada
    Então a requisição é um sucesso com status 200
    E o response da alteração de FluxoTransacional exibe os dados alterados corretamente