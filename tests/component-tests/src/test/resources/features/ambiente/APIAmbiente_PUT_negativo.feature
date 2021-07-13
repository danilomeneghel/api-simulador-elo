#language: pt
#author: Eduardo Oliveira
#versao: 1.0
# @APIAmbiente @regression @dev
 Funcionalidade: API - Alteração de Ambientes

  ## NEGATIVOS
  @cenarioNegativo
  Cenario: CT 11 - Alterar Ambiente inexistente
  Quando realizo uma requisição de PUT para um código de Ambiente inexistente
  Então o status code é 404
  E o response exibe a mensagem "Não foi possível localizar o ambiente informado"
  E o response exibe os campos de retorno de erro

  @cenarioNegativo
  Cenario: CT 12 - Alterar Ambiente incluindo número de porta existente para Portal Cliente
  Dado que possua um Ambiente
  Quando realizo o PUT deste ambiente incluindo uma Porta Cliente com um número de porta já existente
  Então o status code é 400
  E o response exibe a mensagem "Número de porta existente"
  E o response exibe os campos de retorno de erro

  @cenarioNegativo
  Cenario: CT 13 - Alterar Ambiente incluindo número de porta existente para Porta Servidor
  Dado que possua um Ambiente
  Quando realizo o PUT deste ambiente incluindo uma Porta Servidor com um número de porta já existente
  Então o status code é 400
  E o response exibe a mensagem "Número de porta existente"
  E o response exibe os campos de retorno de erro