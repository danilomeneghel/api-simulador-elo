#language: pt
#author: Eduardo Oliveira
#versao: 1.0
#@APIAmbiente @regression
Funcionalidade: API - Pesquisa de Ambiente
  ## NEGATIVOS
  @cenarioNegativo
  Esquema do Cenario: CT <CT> - <CENARIO>
  Quando realizo uma busca por <CAMPO> com o valor <VALOR>
  Então o status code é 404
  E o response exibe a mensagem "Não foi possível localizar o Ambiente informado"

  Exemplos:
  | CT | CENARIO                                                       | CAMPO               | VALOR          |
  | 01 | FindAll - Pesquisar Ambiente Inexistente - ambienteSequence   | ambienteSequence    |           6666 |
  | 02 | FindAll - Pesquisar Ambiente Inexistente - Identificador (ID) | id                  | Não Encontrado |
  | 05 | FindAll - Pesquisar Ambiente Inexistente - Versão             | versao              | Não Encontrado |
  | 06 | FindAll - Pesquisar Ambiente Inexistente - Descrição          | descricao           | Não Encontrado |