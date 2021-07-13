#language: pt
#author: Glauber Oliveira
#versao: 1.0

@APICartao @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de cartão por filtros

  ## GET FindAll Cartões
  Cenario: CT 01 - Pesquisar por todos as Cartoes
    Dado que possua um cartao cadastrado
    Quando realizo o getAll da API de cartao
    Então a requisição é um sucesso com status 200
    E o response apresenta uma lista com o cartao cadastrado