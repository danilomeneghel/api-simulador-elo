#language: pt
#author: Marcelo Lopes
#versao: 1.0

@APIMensagemISO8583 @cenarioPositivo @regression
Funcionalidade: API - Cadastro de MensagensISO8583
  
  ## POST - MENSAGEMISO8583
  Esquema do Cenario: CT <CT> - <CENARIO>
    Dado que possua um protocolo cadastrado:
    |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
    | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
    Dado que possua uma mensagemISO8583 com payload:
    | codigoMensagem       | descricao     | tipoMensagem     | status     |
    | <exCODIGOMENSAGEM>   | <exDESCRICAO> | <exTIPOMENSAGEM> | <exSTATUS> |
    Quando realizo um POST desta mensagemISO8583
    Então a requisição é um sucesso com status 201
    E o response apresenta todos os campos referente a mensagemISO8583 preenchidos corretamente
		
    Exemplos: 
		| CT | CENARIO                               | exCODIGOMENSAGEM | exDESCRICAO              | exTIPOMENSAGEM | exSTATUS |
		| 01 | Incluir Mensagem 200 Sol - Ativa      | 200              | TestAut1 Credito a Vista | SOLICITACAO    | ATIVO    |
		| 02 | Incluir Mensagem 200 Res - Ativa      | 210              | TestAut2 Credito a Vista | RESPOSTA       | ATIVO    |
		| 03 | Incluir Mensagem 100 Sol - Inativa    | 100              | TestAut3 Debito          | SOLICITACAO    | INATIVO  |
		| 04 | Incluir Mensagem 100 Res - Inativa    | 110              | TestAut4 Debito          | RESPOSTA       | INATIVO  |
    