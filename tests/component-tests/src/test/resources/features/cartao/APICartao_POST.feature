#language: pt
#author: Halison Vitorino
#versao: 1.0

@APICadastroCartoes @CenarioPositivo @regression
Funcionalidade: API - Cadastro de Cartões de Teste por Emissor e Bandeira

## POST - Cadastro de Cartoes Com Sucesso
  Esquema do Cenario: CT <CT> - <CENARIO>
    Dado que possua uma Bandeira cadastrada
    E que possua um Emissor cadastrado no sistema
    Quando realizo POST de novo cartao para a Bandeira e Emissor com o payload:
      | CODBANDEIRA     | CODEMISSOR     | TIPOCARTAO     | CARDNAME     | PAN     | PIN     | CVE2     | EXPIRYDATE     | TOKEN     | APPLICATIONIDENTIFIER     | CARDSEQUENCENUMBER     | CARDAPPINTERCHANGEPROFILE     | CARDAPPTRANSACTIONCOUNTER     | ISSUERDISCRDATA     | TRACK1     | TRACK2     | TRACK2EQUIVALENTDATA     | CVN     | CVR     | DKIKDI     |
      | <exCODBANDEIRA> | <exCODEMISSOR> | <exTipoCartao> | <exCardName> | <exPAN> | <exPIN> | <exCVE2> | <exExpiryDate> | <exToken> | <exApplicationIdentifier> | <exCardSequenceNumber> | <exCardAppInterchangeProfile> | <exCardAppTransactionCounter> | <exIssuerDiscrData> | <exTrack1> | <exTrack2> | <exTrack2Equivalentdata> | <exCVN> | <exCVR> | <exDKIKDI> |
    Então a requisição é um sucesso com status 201
    E o cartao salvo no banco de dados

    Exemplos:
      | CT | CENARIO                            | exCODBANDEIRA | exCODEMISSOR | exTipoCartao | exCardName   | exPAN              | exPIN  | exCVE2 | exExpiryDate | exToken   | exApplicationIdentifier | exCardSequenceNumber | exCardAppInterchangeProfile | exCardAppTransactionCounter | exIssuerDiscrData              | exTrack1                     | exTrack2                              | exTrack2Equivalentdata                | exCVN      | exCVR      | exDKIKDI |
      | 01 | Inclusao Cartao Debito             | 1             | 1            | DEBITO       | Cartao ct 01 | 1234567891234567   | 999999 | 123    | 2108         | NAO       | A0000004941010          | 2                    | 5800                        | 1                           | 0FA501A230C0000000000000000000 | B5067230010203010^ELO_506793 | 5067930010203011=21082260063864001966 | 5067930010203011D21082260063864001966 | CVN05      | 2400000000 | 01        |
      | 02 | Inclusao Cartao Credito            | 1             | 1            | CREDITO      | Cartao ct 02 | 2345678912345678   | 999999 | 234    | 2108         | NAO       | A0000004941010          | 2                    | 5800                        | 1                           | 0FA501A230C0000000000000000000 | B5067230010203010^ELO_506793 | 5067930010203012=21082260063864001966 | 5067930010203012D21082260063864001966 | CVN15      | 2400000000 | 01        |
