#language: pt
#author: Eduardo Oliveira
#versao: 1.0

@APIBandeira
Funcionalidade: API - Bandeiras - Negativos

	## POST
	Cenario: CT 01 - Incluir Bandeira duplicada
	  Dado que se possua uma bandeira cadastrada
	  Quando realizo o POST para o mesmo código da Bandeira
	  Então o status code é 400
	  E o response exibe a mensagem "Código de Bandeira já existente"

	Cenario: CT 02 - Incluir Bandeira sem preencher campo Código da Bandeira
	  Quando realizo o POST de uma bandeira sem envio do campo código bandeira
	  Então o status code é 400
	  E o response exibe a mensagem "-confirmar mensagem-"

	Cenario: CT 03 - Incluir Bandeira sem preencher campo Descrição da Bandeira
	  Quando realizo o POST de uma bandeira sem envio do campo descrição
	  Então o status code é 400
	  E o response exibe a mensagem "-confirmar mensagem-"

	Cenario: CT 04 - Incluir Bandeira sem preencher campos Código e Descrição da Bandeira
	  Quando realizo o POST de uma bandeira sem envio do campos código bandeira e descrição
	  Então o status code é 400
	  E o response apresenta a mensagem "-Confirmar mensagem-"

	Cenario: CT 05 - Incluir Bandeira com Código com Letra
	  Quando realizo o POST de uma bandeira enviando letra no campo código
	  Então o status code é 400
	  E o response exibe a mensagem "-confirmar mensagem-"

	## GET
	Cenario: CT 06 - Pesquisar por Bandeira Inexistente - Código Bandeira
	  Dado que se possua bandeiras cadastradas
	  Quando realizo um GET para um código inexistente
	  Então o status code é 404
	  E o response exibe a mensagem "-confirmar mensagem-"
	  
	Cenario: CT 07 - Pesquisar por Bandeira Inexistente - Descrição
	  Dado que se possua bandeiras cadastradas
	  Quando realizo um GET para uma descrição inexistente
	  Então o status code é 404
	  E o response exibe a mensagem "-confirmar mensagem-"
	  
	Cenario: CT 08 - Pesquisar por Bandeira com base vazia - Código Bandeira
	  Dado que não exista nenhuma bandeira cadastrada
	  Quando realizo um GET para um código inexistente
	  Então o status code é 404
	  E o response exibe a mensagem "-confirmar mensagem-"

	Cenario: CT 09 - Pesquisar por Bandeira Inexistente - ID
	  Dado que se possua bandeiras cadastradas
	  Quando realizo um GET para uma descrição inexistente
	  Então o status code é 404
	  E o response apresenta a mensagem "-Confirmar mensagem-"
	  
	Cenario: CT 10 - Pesquisar por Bandeira Inexistente - Código
	  Dado que se possua bandeiras cadastradas
	  Quando realizo um GET para uma descrição inexistente
	  Então o status code é 404
	  E o response exibe a mensagem "-confirmar mensagem-"
	  
  # PUT
  Cenario: CT 10 - Atualizar Bandeira inexistente
  	Quando realizo um PUT para uma bandeira inexistente
  	Então o status code é 404
  	E o response exibe a mensagem "-confirmar mensagem-"
  	
	Cenario: CT 11 - Atualizar Bandeira para Código existente
		Dado que se possua uma bandeira cadastrada
		Quando realizo um PUT com o código de bandeira já existente
		Então o status code é 400
		E o response exibe a mensagem "-confirmar mensagem-"
		
	Cenario: CT 12 - Atualizar Bandeira sem enviar nenhuma informação
		Dado que se possua uma bandeira cadastrada
		Quando realizo um PUT sem preenchimento de nenhum campo
		Então o status code é 400
		E o response exibe a mensagem "-confirmar mensagem-"
		E a Bandeira é apresentada na base de dados sem nenhuma alteração
		
	# GET por ID ou Código não encontrado
	Cenario: CT 13 - GET - Pesquisar Bandeira por ID (Endpoint id) inexistente
		Quando realizo uma busca de Bandeira pelo endpoint de busca por id com valor "Não Encontrado"
		Então o status code é 404
		E o response exibe a mensagem "Não foi possível localizar o simulador informado"
		
	Cenario: CT 14 - GET - Pesquisar Bandeira por Código (Endpoint código) inexistente
		Quando realizo uma busca de Bandeira pelo endpoint de busca por código com valor "0"
		Então o status code é 404
		E o response exibe a mensagem "Não foi possível localizar o simulador informado"