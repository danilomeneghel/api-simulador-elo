#language: pt
#author: Eduardo Oliveira
#versao: 1.0

#@APIEmissor @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de Emissor por endpoint id e código
	
	#### NEGATIVOS
	  
	## GET id Emissor - NEGATIVO - EMISSOR NÃO ENCONTRADO
	Cenario: CT 01 - GET - Pesquisar Emissor por ID (Endpoint id) inexistente
	  Quando realizo uma busca pelo endpoint de busca de Emissor por id com valor "Não Encontrado"
	  Então o status code é 404
	  E o response exibe a mensagem "Não foi possível localizar o Emissor informado"
	
	## GET codigo Emissor - NEGATIVO - EMISSOR NÃO ENCONTRADO
	Cenario: CT 01 - GET - Pesquisar Emissor por Código (Endpoint código) inexistente
	  Quando realizo uma busca pelo endpoint de busca por código com valor "0"
	  Então o status code é 404
	  E o response exibe a mensagem "Não foi possível localizar o Emissor informado"