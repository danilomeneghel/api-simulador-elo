#language: pt
#author: Eduardo Oliveira
#versao: 1.0
@APIAmbiente @regression
Funcionalidade: API - Cadastro de Credenciador

  @cenarioPositivo
  Cenario: CT 01 - Cadastrar Ambiente com Porta Cliente
    Dado que possua um protocolo cadastrado:
      |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
      | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
    Dado que possua dados de Ambiente para campos Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV
    E campos válidos de Ambiente de para Porta Cliente
    Quando realizar o POST de Ambiente
    Então o status code é 201
    E o response retorna os campos do Ambiente cadastrado
    E as informações do Ambiente são salvas na base de dados

  @cenarioPositivo
  Cenario: CT 02 - Cadastrar Ambiente com Porta Servidor
    Dado que possua um protocolo cadastrado:
      |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
      | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
    Dado que possua dados de Ambiente para campos Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV
    E campos válidos de Ambiente de para Porta Servidor
    Quando realizar o POST de Ambiente
    Então o status code é 201
    E o response retorna os campos do Ambiente cadastrado
    E as informações do Ambiente são salvas na base de dados

  @cenarioPositivo
  Cenario: CT 03 - Cadastrar Ambiente com portas Cliente e Servidor
    Dado que possua um protocolo cadastrado:
      |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
      | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
    Dado que possua dados de Ambiente para campos Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV
    E campos válidos de Ambiente de para Porta Cliente
    E campos válidos de Ambiente de para Porta Servidor
    Quando realizar o POST de Ambiente
    Então o status code é 201
    E o response retorna os campos do Ambiente cadastrado
    E as informações do Ambiente são salvas na base de dados

  @cenarioPositivo
  Cenario: CT 04 - Cadastrar Ambiente sem nenhuma porta
    Dado que possua um protocolo cadastrado:
      |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
      | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
    Dado que possua dados de Ambiente para campos Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV
    E não possua portas cadastradas para este ambiente
    Quando realizar o POST de Ambiente
    Então o status code é 201
    E o response retorna os campos do Ambiente cadastrado sem nenhuma porta
    E as informações do Ambiente são salvas na base de dados

  @cenarioPositivo
  Esquema do Cenario: CT <CT> - <CENARIO>
    Dado que possua um protocolo cadastrado:
      |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
      | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
    Dado que possua dados de Ambiente para campos Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV
    E campos válidos de Ambiente de para Porta Servidor e campo <CAMPO> com valor	<VALOR>
    Quando realizar o POST de Ambiente
    Então o status code é 201
    E o response retorna os campos do Ambiente cadastrado
    E as informações do Ambiente são salvas na base de dados

    Exemplos:
      | CT | CENARIO                                                        | CAMPO              | VALOR     |
      | 05 | Cadastrar Ambiente com Porta Servidor Tipo MLI: BOTH           | Tipo MLI           | BOTH      |
      | 06 | Cadastrar Ambiente com Porta Servidor Tipo MLI: INCLUSIVE      | Tipo MLI           | INCLUSIVE |
      | 07 | Cadastrar Ambiente com Porta Servidor Tipo MLI: EXCLUSIVE      | Tipo MLI           | EXCLUSIVE |
      | 08 | Cadastrar Ambiente com Porta Servidor Codificação MLI: BYTES   | Codificação MLI    | BYTES     |
      | 09 | Cadastrar Ambiente com Porta Servidor Comprimento do MLI: TWO  | Comprimento do MLI | TWO       |
      | 10 | Cadastrar Ambiente com Porta Servidor Comprimento do MLI: FOUR | Comprimento do MLI | FOUR      |
      | 11 | Cadastrar Ambiente com Porta Servidor Status: ATIVO            | status             | ATIVO     |
      | 12 | Cadastrar Ambiente com Porta Servidor Status: INATIVO          | status             | INATIVO   |

  @cenarioPositivo
  Cenario: CT 13 - Cadastrar Ambiente com 2 Portas Cliente e 2 Portas Servidor
    Dado que possua um protocolo cadastrado:
      |descricao               |versao          | encodeBCDLLVARLLLVAR     | encodeCodigoMensagem     | EncodeMapaDeBits     | status     | tipo               |
      | TestAut4Put Protocolo  |17.5            | NROBYTES                 | EBCDIC                   | EBCDIC               | ATIVO      | BANDEIRA           |
    Dado que possua dados de Ambiente para campos Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV
    E campos válidos de Ambiente de para 2 Portas Cliente
    E campos válidos de Ambiente de para 2 Portas Servidor
    Quando realizar o POST de Ambiente
    Então o status code é 201
    E o response retorna os campos do Ambiente cadastrado
    E as informações do Ambiente são salvas na base de dados