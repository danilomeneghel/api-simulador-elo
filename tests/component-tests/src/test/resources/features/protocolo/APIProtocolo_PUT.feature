#language: pt
#author: Marcelo lope
#versao: 1.0

@APIProtocolo @cenarioPositivo @regression @dev
Funcionalidade: API - Alteração de Protocolos
 	
	Cenario: CT 01 - Atualiza o tipo Protocolo com sucesso
	  Dado que possua um protocolo cadastrado:
	  |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
	  | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
	  Quando realizo um put com o tipo protocolo alterado
	  Então a requisição é um sucesso com status 200
	  E o response da alteração do Protocolo exibe os dados alterados corretamente
	  

	Cenario: CT 02 - Atualiza a descricao Protocolo com sucesso
	  Dado que possua um protocolo cadastrado:
	  |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
	  | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
	  Quando realizo um put com a descricao do protocolo alterada
	  Então a requisição é um sucesso com status 200
	  E o response da alteração do Protocolo exibe os dados alterados corretamente

	Cenario: CT 03 - Atualiza a descricao e tipo  Protocolo com sucesso
	  Dado que possua um protocolo cadastrado:
	  |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
	  | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
	  Quando realizo um put com a descricao e o tipo protocolo alterados
	  Então a requisição é um sucesso com status 200
	  E o response da alteração do Protocolo exibe os dados alterados corretamente