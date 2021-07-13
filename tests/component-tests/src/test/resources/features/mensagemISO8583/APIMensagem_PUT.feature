#language: pt
#author: Marcelo Lopes
#versao: 1.0

@APIMensagemISO8583 @cenarioPositivo @regression @dev
Funcionalidade: API - Alteração de MensagensISO8583
 	
	Cenario: CT 01 - Atualiza o codigoMensagem com sucesso
	  Dado que possua um protocolo cadastrado:
	  |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
	  | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
	  Dado que possua uma mensagemISO8583 cadastrada:
	  | codigoMensagem       | descricao       | tipoMensagem               | status         |
	  | 200                  | Credito a Vista | SOLICITACAO                | ATIVO          |
	  Quando realizo um put com o codigoMensagem alterado
	  Então a requisição é um sucesso com status 200
	  E o response da alteração de mensagemISO8583 exibe os dados alterados corretamente


	Cenario: CT 02 - Atualiza a descricao com sucesso
	  Dado que possua um protocolo cadastrado:
	  |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
	  | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
	  Dado que possua uma mensagemISO8583 cadastrada:
	  | codigoMensagem       | descricao       | tipoMensagem               | status         |
	  | 200                  | Credito a Vista | SOLICITACAO                | ATIVO          |
	  Quando realizo um put com a descricao da mensagemISO8583 alterada
	  Então a requisição é um sucesso com status 200
	  E o response da alteração de mensagemISO8583 exibe os dados alterados corretamente


	Cenario: CT 03 - Atualiza a descricao e codigoMensagem com sucesso
		Dado que possua um protocolo cadastrado:
		|descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
		| TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
		Dado que possua uma mensagemISO8583 cadastrada:
		| codigoMensagem       | descricao       | tipoMensagem               | status         |
		| 200                  | Credito a Vista | SOLICITACAO                | ATIVO          |
		Quando realizo um put com o codigoMensagem e descricao alterados
		Então a requisição é um sucesso com status 200
		E o response da alteração de mensagemISO8583 exibe os dados alterados corretamente
