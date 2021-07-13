#language: pt
#author: Marcelo Lopes
#versao: 1.0
@APIProtocolo @cenarioPositivo @regression @dev
Funcionalidade: API - Cadastro de Protocolo

  @cenarioPositivo
  Esquema do Cenario: CT <CT> - <CENARIO>
    Dado que possua um protocolo com payload:
      |descricao    |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | encodeMapaDeBits     | status     | tipo     |
      |<exDESCRICAO>| <exVERSAO>     |<exENCODEBCDLLVARLLLVAR>  | <exENCODECODIGOMENSAGEM> | <exENCODEMAPADEBITS> | <exSTATUS> | <exTIPO> |
    Quando realizo um POST deste protocolo
    Então a requisição é um sucesso com status 201
    E o response apresenta todos os campos referente o protocolo preenchidos corretamente

    Exemplos: 
      | CT | CENARIO                                           |exDESCRICAO          |exVERSAO| exENCODEBCDLLVARLLLVAR | exENCODECODIGOMENSAGEM | exENCODEMAPADEBITS | exSTATUS | exTIPO        |
      | 01 | Incluir Protocolo com variações de campos padrões | TestAut1 Protocolo  |17.2    | NROBYTES               | ASCII                  | ASCII              | ATIVO    | BANDEIRA      |
      | 02 | Incluir Protocolo com variações de campos padrões | TestAut2 Protocolo  |17.3    | NRODIGITOS             | BCD                    | BINARIO            | INATIVO  | CREDENCIADORA |
      | 03 | Incluir Protocolo com variações de campos padrões | TestAut3 Protocolo  |17.4    | NROBYTES               | EBCDIC                 | EBCDIC             | ATIVO    | EMISSOR       |

