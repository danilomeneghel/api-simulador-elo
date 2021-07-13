#language: pt
#author: Halison Vitorino
#versao: 1.0
@APIFluxoTransacional @CenarioNegativo @regression
Funcionalidade: API - Cadastro de Fluxo de Transacoes de Autorizacao  Invalidos

  ## POST - Cadastro de Fluxos de Transacoes de Autorizacao - Negativo - Pernas 1 2 3 e 4 invalidas
  Esquema do Cenario: CT 01 - Codigo Resposta Mensagem Quatro Nao Cadastrado no Banco de Dados
    Dado que quero cadastrar um fluxo transacional com payload com "<CENARIO>"
      | descricao                    |
      | <exDescricao>                |
    Quando realizo POST deste fluxo transacional com dados invalidos
    Entao a requisição falha com erro 400
    E o response exibe a mensagem "<MENSAGEM>"

    Exemplos:
      | CT | CENARIO       | exDescricao                     |  MENSAGEM                                                                       |
      | 01 | PERNA1INVALIDA| Teste Fluxo Perna 1 invalida    |  Não foi possível localizar a mensagem de solicitação perna um informada        |
      | 02 | PERNA2INVALIDA| Teste Fluxo Perna 2 invalida    |  Não foi possível localizar a mensagem de solicitação perna dois informada      |
      | 03 | PERNA3INVALIDA| Teste Fluxo Perna 3 invalida    |  Não foi possível localizar a mensagem de resposta perna três informada         |
      | 04 | PERNA4INVALIDA| Teste Fluxo Perna 4 invalida    |  Não foi possível localizar a mensagem de resposta perna quatro informada       |


  ## POST - Cadastro de Fluxos de Transacoes de Autorizacao - Negativo - Bit de Vinculacao das Pernas Um e Quatro Invalido e Bit de Vinculacao das Pernas Dois e Tres Invalido
  Esquema do Cenario: CT 02 - Bit de Vinculacao das Pernas Um e Quatro Invalido
    Dado que quero cadastrar um fluxo transacional com payload com "<CENARIO>"
      | descricao                  |
      | <exDescricao>              |
    Quando realizo POST deste fluxo transacional com dados invalidos
    Entao a requisição falha com erro 400
    E o response exibe a mensagem "<MENSAGEM>"
    Exemplos:
      | CT | CENARIO       | exDescricao                     |  MENSAGEM                                                                                                  |
      | 01 | BIT14INVALIDO | Teste Fluxo BIT1E4 invalido     |  Não foi possível localizar o bit para o fluxo das pernas um e quatro,na mensagem de solicitação informada |
      | 02 | BIT23INVALIDO | Teste Fluxo BIT2E3 invalido     |  Não foi possível localizar o bit para o fluxo das pernas dois e tres, na mensagem de resposta informada   |

  ## POST - Cadastro de Fluxos de Transacoes de Autorizacao - Negativo - Descricao Maior que 90
  Cenario: CT 03 - Descricao do Fluxo Ausente na Requisicao
    Dado que quero cadastrar um fluxo transacional com descricao invalida:
      | descricao                  |
      | Mussum Ipsum, cacilds vidis litro abertis. Interagi no mé, cursus quis, vehicula ac nisi. Viva Forevis aptent taciti sociosqu ad litora torquent. Admodum accumsan disputationi eu sit. Vide electram sadipscing et per. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!|
    Quando realizo POST deste fluxo transacional com dados invalidos
    Entao a requisição falha com erro 400
    E o response exibe a mensagem "descricao : Tamanho máximo da descriação deve ser 90 caracteres"
