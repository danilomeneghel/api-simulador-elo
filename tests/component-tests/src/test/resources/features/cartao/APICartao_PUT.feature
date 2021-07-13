#language: pt
#author: Glauber Oliveira
#versao: 1.0
@APICartao @regression
Funcionalidade: API - Alteração de Cartao


  @cenarioPositivo
  Cenario: CT 01 - Atualiza dados do Cartao - Nome
    Dado que possua um cartao cadastrado
    Quando realizo uma alteração do nome do Cartao
    Então a requisição é um sucesso com status 200
    E o response da alteração de Cartao exibe a data de atualização corretamente