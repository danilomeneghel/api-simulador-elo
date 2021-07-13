#language: pt
#author: Marcelo Lopes
#versao: 1.0

@APIMensagemISO8583 @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de MensagensISO8583
	## GET FindAll MensagemISO8583
	Cenario: CT <CT> - <CENARIO> - Retorno único

	  Dado que possua um protocolo cadastrado:
	  |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
	  | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |

	  Dado que possua uma mensagemISO8583 cadastrada:
	  | codigoMensagem       | descricao       | tipoMensagem               | status         |
	  | 200                  | Credito a Vista | SOLICITACAO                | ATIVO          |
	  Quando realizo o getAll da API de mensagemISO8583
	  Então a requisição é um sucesso com status 200
	  E o response apresenta uma lista com uma mensagemISO8583 cadastrada

	
