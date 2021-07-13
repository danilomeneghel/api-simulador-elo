#language: pt
#author: Eduardo Oliveira
#versao: 1.0
@APIEmissor @regression
Funcionalidade: API - Alteração de Emissor


  @cenarioPositivo
  Cenario: CT 02 - Atualiza dados do Emissor - Nome
    Dado que possua uma bandeira cadastrada para o emissor
    Dado que eu possua um Emissor cadastrado
      | codigoEmissor | nomeEmissor  | codigoProcessadora | plataforma | codigoBandeira |
      | 1             | Emissor CT 1 | 2222               | CREDITO    | 1              |
    Quando realizo uma alteração do nome do Emissor
    Então a requisição é um sucesso com status 200
    E o response da alteração de Emissor exibe a data de atualização corretamente


  @cenarioPositivo
  Cenario: CT 03 - Atualiza dados do Emissor - Código Processadora
    Dado que possua uma bandeira cadastrada para o emissor
    Dado que eu possua um Emissor cadastrado
      | codigoEmissor | nomeEmissor  | codigoProcessadora | plataforma | codigoBandeira |
      | 1             | Emissor CT 1 | 2222               | CREDITO    | 1              |
      | 1111          | Emissor CT 2 | 2                  | DEBITO     | 1              |
      | 1111          | Emissor CT 3 | 1003               | CREDITO    | 1              |
      | 456           | Emissor CT 4 | 123                | MULTIPLOS  | 1              |
    Quando realizo uma alteração do Código da Processadora do Emissor
    Então a requisição é um sucesso com status 200
    E o response da alteração de Emissor exibe a data de atualização corretamente

  @cenarioPositivo
  Cenario: CT 05 - Atualiza dados do Emissor - Bandeira
    Dado que possua uma bandeira cadastrada para o emissor
    Dado que eu possua um Emissor cadastrado
      | codigoEmissor | nomeEmissor  | codigoProcessadora | plataforma | codigoBandeira |
      | 1             | Emissor CT 1 | 2222               | CREDITO    | 1              |
      | 1111          | Emissor CT 2 | 2                  | DEBITO     | 1              |
      | 1111          | Emissor CT 3 | 1003               | CREDITO    | 1              |
      | 456           | Emissor CT 4 | 123                | MULTIPLOS  | 1              |
    Quando realizo uma alteração de Código da Bandeira do Emissor
    Então a requisição é um sucesso com status 200
    E o response da alteração de Emissor exibe a data de atualização corretamente

  @cenarioPositivo
  Cenario: CT 06 - Atualiza dados do Emissor - Todos os dados
    Dado que possua uma bandeira cadastrada para o emissor
    Dado que eu possua um Emissor cadastrado
      | codigoEmissor | nomeEmissor  | codigoProcessadora | plataforma | codigoBandeira |
      | 1             | Emissor CT 1 | 2222               | CREDITO    | 1              |
      | 1111          | Emissor CT 2 | 2                  | DEBITO     | 1              |
      | 1111          | Emissor CT 3 | 1003               | CREDITO    | 1              |
      | 456           | Emissor CT 4 | 123                | MULTIPLOS  | 1              |
    Quando realizo uma alteração de todos os dados do Emissor
    Então a requisição é um sucesso com status 200
    E o response da alteração de Emissor exibe a data de atualização corretamente

