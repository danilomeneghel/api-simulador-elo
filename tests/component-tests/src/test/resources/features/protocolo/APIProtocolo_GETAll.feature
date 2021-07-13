#language: pt
#author: Marcelo Lopes
#versao: 1.0

@APIProtocolo @cenarioPositivo @regression @dev
Funcionalidade: API - Pesquisa de Protocolos
	## GET FindAll Protocolo
    Cenario: CT <CT> - <CENARIO> - Retorno único
	  Dado que possua um protocolo cadastrado:
	  |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
	  | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
	  Quando realizo o getAll da API de protocolo
	  Então a requisição é um sucesso com status 200
	  E o response apresenta uma lista com um protocolo cadastrado
	
