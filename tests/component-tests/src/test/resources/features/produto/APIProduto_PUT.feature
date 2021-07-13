#language: pt
#author: Marcelo Lopes
#versao: 1.0

@APIProduto @cenarioPositivo @regression
Funcionalidade: API - Alteração de Produto

  Cenario: CT 01 - Atualiza dados do codigoProduto com sucesso
    Dado que possua uma bandeira cadastrada:
      | codigoBandeira     | descricao             |
      | 007                | Bandeira teste get all|
    Dado que possua um produto cadastrado:
      | codigoProduto   | codigoBandeira   |tipoPlataforma   | descricao     |
      | 001 	        | 007              |CREDITO          | produto teste |
    Quando realizo um put com o codigoProduto alterado
    Então a requisição é um sucesso com status 200
    E o response da alteração de Produto exibe os dados alterados corretamente

  Cenario: CT 02 - Atualiza a descricao com sucesso
    Dado que possua uma bandeira cadastrada:
      | codigoBandeira     | descricao             |
      | 007                | Bandeira teste get all|
    Dado que possua um produto cadastrado:
      | codigoProduto   | codigoBandeira   |tipoPlataforma   | descricao     |
      | 001 	        | 007              |CREDITO          | produto teste |
    Quando realizo um put com a descricao do produto alterada
    Então a requisição é um sucesso com status 200
    E o response da alteração de Produto exibe os dados alterados corretamente


  Cenario: CT 03 - Atualiza dados do codigoProduto e descricao com sucesso
    Dado que possua uma bandeira cadastrada:
      | codigoBandeira     | descricao             |
      | 007                | Bandeira teste get all|
    Dado que possua um produto cadastrado:
      | codigoProduto   | codigoBandeira   |tipoPlataforma   | descricao     |
      | 001 	        | 007              |CREDITO          | produto teste |
    Quando realizo um put com o codigoProduto e descricao alterados
    Então a requisição é um sucesso com status 200
    E o response da alteração de Produto exibe os dados alterados corretamente
