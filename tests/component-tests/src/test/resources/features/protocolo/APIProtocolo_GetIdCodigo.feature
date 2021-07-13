#language: pt
#author: Marcelo Lopes
#versao: 1.0

@APIProtocolo @cenarioPositivo @regression @dev
Funcionalidade: API - Pesquisa de Protocolo por endpoint id e código
	
	## GET id - PROTOCOLO
	@teste
	Cenario: CT 01 - Pesquisar Protocolo por ID (api/mensagens/$id)
	  Dado que possua um protocolo cadastrado:
	  |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
	  | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
	  Quando eu realizo uma busca de protocolo pelo endpoint de busca por id
	  Então a requisição é um sucesso com status 200
	  E o response apresenta os campos do Protocolo de acordo com a base de dados
	  
	## GET codigo - PROTOCOLO
	Cenario: CT 01 - Pesquisar Protocolo por Codigo (api/mensagens/seq/$codigo)
	  Dado que possua um protocolo cadastrado:
	  |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
	  | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
	  Quando eu realizo uma busca de protocolo pelo endpoint de busca por código
	  Então a requisição é um sucesso com status 200
	  E o response apresenta os campos do Protocolo de acordo com a base de dados