#language: pt
#author: Marcelo Lopes
#versao: 1.0

@APIMensagemISO8583 @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de MensagensISO8583 por endpoint id e código
	
	## GET id - MENSAGEMISO8583
	@teste
	Cenario: CT 01 - Pesquisar MensagemISO8583 por ID (api/mensagens/$id)
	  Dado que possua um protocolo cadastrado:
	  |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
	  | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
	  Dado que possua uma mensagemISO8583 cadastrada:
	  | codigoMensagem       | descricao       | tipoMensagem               | status         |
	  | 200                  | Credito a Vista | SOLICITACAO                | ATIVO          |
	  Quando eu realizo uma busca de mensagemISO8583 pelo endpoint de busca por id
	  Então a requisição é um sucesso com status 200
	  E o response apresenta os campos do MensagemISO8583 de acordo com a base de dados
	  
	## GET codigo - MENSAGEMISO8583
	Cenario: CT 01 - Pesquisar MensagemISO8583 por Codigo (api/mensagens/seq/$codigo)
		Dado que possua um protocolo cadastrado:
			|descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
			| TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
		Dado que possua uma mensagemISO8583 cadastrada:
			| codigoMensagem       | descricao       | tipoMensagem               | status         |
			| 200                  | Credito a Vista | SOLICITACAO                | ATIVO          |
	  Quando eu realizo uma busca de mensagemISO8583 pelo endpoint de busca por código
	  Então a requisição é um sucesso com status 200
	  E o response apresenta os campos do MensagemISO8583 de acordo com a base de dados