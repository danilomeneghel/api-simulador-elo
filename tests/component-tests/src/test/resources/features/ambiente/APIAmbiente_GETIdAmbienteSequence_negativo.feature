#language: pt
#author: Eduardo Oliveira
#versao: 1.0
#@APIAmbientes @regression
Funcionalidade: API - Pesquisa de Ambientes por endpoint id e ambienteSequence
  ## NEGATIVOS
  ## GET id Ambiente - NEGATIVO - Ambiente NÃO ENCONTRADO
  @cenarioNegativo
  Cenario: CT 01 - Pesquisar Ambiente por ID (Endpoint id) inexistente
  Quando realizo uma busca de Ambiente pelo endpoint de busca por id com valor "Não Encontrado"
  Então o status code é 404
  E o response exibe a mensagem "Não foi possível localizar o Ambiente informado"

  ## GET ambienteSequence Ambiente - NEGATIVO - Ambiente NÃO ENCONTRADO
  @cenarioNegativo
  Cenario: CT 02 - Pesquisar Ambiente por ambienteSequence (Endpoint ambienteSequence) inexistente
  Quando realizo uma busca de Ambiente pelo endpoint de busca por ambienteSequence com valor "Não encontrado"
  Então o status code é 404
  E o response exibe a mensagem "Não foi possível localizar o Ambiente informado"