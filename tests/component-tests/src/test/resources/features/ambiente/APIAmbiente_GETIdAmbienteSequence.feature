#language: pt
#author: Eduardo Oliveira
#versao: 1.0
@APIAmbientes @regression
Funcionalidade: API - Pesquisa de Ambientes por endpoint id e ambienteSequence

  ## GET id - Ambiente
  @cenarioPositivo
  Cenario: CT 01 - Pesquisar Ambiente por ID (api/ambientes/$id)
    Dado que eu possua um Ambiente
    Quando eu realizo uma busca de Ambiente pelo endpoint de busca por id
    Então o status code é 200
    E o response apresenta os dados de Ambiente corretamente conforme a base de dados

  ## GET ambienteSequence - Ambiente
  @cenarioPositivo
  Cenario: CT 02 - Pesquisar Ambiente por ID (api/ambientes/seq/$ambienteSequence)
    Dado que eu possua um Ambiente
    Quando eu realizo uma busca de Ambiente pelo endpoint de busca por ambienteSequence
    Então o status code é 200
    E o response apresenta os dados de Ambiente corretamente conforme a base de dados
