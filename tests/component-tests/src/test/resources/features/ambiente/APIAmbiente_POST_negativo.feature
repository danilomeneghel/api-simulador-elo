#language: pt
#author: Eduardo Oliveira
#versao: 1.0
#  @APIAmbiente @regression
  Funcionalidade: API - Cadastro de Credenciador

  ## NEGATIVOS
  @cenarioNegativo
  Esquema do Cenario: CT <CT> - Cadastrar Ambiente sem portas sem preenchimento do campo obrigatório <CAMPO>
  Dado que possua dados de Ambiente para campos Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV
  E remova a informação de <CAMPO> do payload de Ambiente
  Quando realizar o POST de Ambiente
  Então o código de retorno é 400
  E o response exibe os campos de retorno de erro
  E o response exibe a mensagem "<MENSAGEM>"

  Exemplos:
  | CT | CAMPO        | MENSAGEM |
  | 14 | descricao    |          |
  | 17 | status       |          |
  | 18 | chave de pin |          |
  | 19 | chave cavv   |          |

  @cenarioNegativo
  Esquema do Cenario: CT <CT> - Cadastrar Ambiente com Porta Cliente sem preenchimento do campo obrigatório <CAMPO>
  Dado que possua dados de Ambiente para campos Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV
  E campos válidos de Ambiente de para Porta Cliente
  E remova a informação de <CAMPO> do payload de Ambiente - Porta Cliente
  Quando realizar o POST de Ambiente
  Então o código de retorno é 400
  E o response exibe os campos de retorno de erro
  E o response exibe a mensagem "<MENSAGEM>"

  Exemplos:
  | CT | CAMPO              | MENSAGEM |
  | 20 | Nome da Porta      |          |
  | 21 | Nome do Host       |          |
  | 22 | Número da Porta    |          |
  | 23 | Tempo de Timeout   |          |
  | 24 | Protocolo          |          |
  | 25 | Números de Sockets |          |
  | 26 | status             |          |

  @cenarioNegativo
  Esquema do Cenario: CT <CT> - Cadastrar Ambiente com Porta Servidor sem preenchimento do campo obrigatório <CAMPO>
  Dado que possua dados de Ambiente para campos Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV
  E campos válidos de Ambiente de para Porta Servidor
  E remova a informação de <CAMPO> do payload de Ambiente - Porta Servidor
  Quando realizar o POST de Ambiente
  Então o código de retorno é 400
  E o response exibe os campos de retorno de erro
  E o response exibe a mensagem "<MENSAGEM>"

  Exemplos:
  | CT | CAMPO                        | MENSAGEM |
  | 27 | Nome da Porta                |          |
  | 28 | Tempo de Timeout             |          |
  | 29 | Tempo de Timeout dos Sockets |          |
  | 30 | Protocolo                    |          |
  | 31 | Cabeçalho                    |          |
  | 32 | Número de Sockets            |          |
  | 33 | Tipo MLI                     |          |
  | 34 | Codificação MLI              |          |
  | 35 | Comprimento do MLI           |          |
  | 36 | Swapped MLI                  |          |
  | 37 | status                       |          |

  @cenarioNegativo
  Esquema do Cenario: CT <CT> - Cadastrar Ambiente com preenchimento do campo <CAMPO> inválido
  Dado que possua dados de Ambiente para campos Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV
  E campos válidos de Ambiente de para Porta Cliente
  E campos válidos de Ambiente de para Porta Servidor
  E insira informação inválida para o campo <CAMPO>
  Quando realizar o POST de Ambiente
  Então o código de retorno é 400
  E o response exibe os campos de retorno de erro
  E o response exibe a mensagem "<MENSAGEM>"

  Exemplos:
  | CT | CAMPO           | MENSAGEM |
  | 38 | status          |          |
  | 40 | Protocolo       |          |
  | 41 | Cabeçalho       |          |
  | 42 | Tipo MLI        |          |
  | 43 | Comprimento MLI |          |

  @cenarioNegativo
  Esquema do Cenario: CT <CT> - Cadastrar Ambiente com Porta Cliente com <CAMPO> com letras
  Dado que possua dados de Ambiente para campos Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV
  E campos válidos de Ambiente de para Porta Cliente
  E campos válidos de Ambiente de para Porta Servidor
  E insira informação com letras para o campo <CAMPO> da Porta Cliente
  Quando realizar o POST de Ambiente
  Então o código de retorno é 400
  E o response exibe os campos de retorno de erro
  E o response exibe a mensagem "<MENSAGEM>"

  Exemplos:
  | CT | CAMPO             | MENSAGEM |
  | 44 | Nome do Host      |          |
  | 45 | Número da Porta   |          |
  | 46 | Tempo de Timeout  |          |
  | 47 | Número de Sockets |          |
  | 48 | status            |          |

  @cenarioNegativo
  Esquema do Cenario: CT <CT> - Cadastrar Ambiente com Porta Servidor com <CAMPO> com letras
  Dado que possua dados de Ambiente para campos Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV
  E campos válidos de Ambiente de para Porta Cliente
  E campos válidos de Ambiente de para Porta Servidor
  E insira informação com letras para o campo <CAMPO> da Porta Servidor
  Quando realizar o POST de Ambiente
  Então o código de retorno é 400
  E o response exibe os campos de retorno de erro
  E o response exibe a mensagem "<MENSAGEM>"

  Exemplos:
  | CT | CAMPO                        | MENSAGEM |
  | 49 | Número da Porta              |          |
  | 50 | Tempo de Timeout             |          |
  | 51 | Tempo de Timeout dos Sockets |          |
  | 52 | Número de Sockets            |          |
  | 53 | status                       |          |