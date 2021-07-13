#language: pt
#author: Glauber Oliveira
#versao: 1.0

@APICartao @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de Cartao por endpoint id e pan

  ## GET id - Cartao
  Cenario: CT 01 - Pesquisar Cartao por ID (api/cartoes/$id)
    Dado que possua um cartao cadastrado
    Quando eu realizo uma busca pelo endpoint de busca de Cartao por id
    Então a requisição é um sucesso com status 200
    E o response apresenta os campos do Cartao corretamente

  ## GET pan - Cartao
  Cenario: CT 02 - Pesquisar Cartao por ID (api/cartoes/seq/$pan)
    Dado que possua um cartao cadastrado
    Quando eu realizo uma busca pelo endpoint de busca de Cartao por pan
    Então a requisição é um sucesso com status 200
    E o response apresenta os campos do Cartao corretamente