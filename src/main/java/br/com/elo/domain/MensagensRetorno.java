package br.com.elo.domain;

import lombok.Getter;

@Getter
public enum MensagensRetorno {
    BAD_REQUEST("Não foi possível processar a solicitação, dados de request inválidos"),
    AMBIENTE_NAO_LOCALIZADO("Não foi possível localizar o ambiente informado"),
    EMISSOR_NAO_LOCALIZADO("Não foi possível localizar o emissor informado"),
    NAO_HA_REGISTROS_CADASTRADOS("Não há registros cadastrados"),
    EMISSOR_COD_BAD_EXISTE("Não foi possível processar a solicitação para código emissor e bandeira já existente"),
    MENSAGEM_NAO_LOCALIZADA("Não foi possível localizar a mensagem informada"),
    MENSAGEM_BAD_REQUEST("Não foi possível processar a solicitação, dados de request inválidos"),
    MENSAGEM_SOL_PERNA_UM_NAO_LOCALIZADA("Não foi possível localizar a mensagem de solicitação perna um informada"),
    MENSAGEM_SOL_PERNA_DOIS_NAO_LOCALIZADA("Não foi possível localizar a mensagem de solicitação perna dois informada"),
    MENSAGEM_RESP_PERNA_TRES_NAO_LOCALIZADA("Não foi possível localizar a mensagem de resposta perna três informada"),
    MENSAGEM_RESP_PERNA_QUATRO_NAO_LOCALIZADA("Não foi possível localizar a mensagem de resposta perna quatro informada"),
    MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_UM_QUATRO("Não foi possível localizar o bit para o fluxo das pernas um e quatro,na mensagem de solicitação informada"),
    MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_DOIS_TRES("Não foi possível localizar o bit para o fluxo das pernas dois e tres, na mensagem de resposta informada"),
    CARTAO_NAO_LOCALIZADO("Não foi possível localizar o cartão informado"),
    CARTAO_EMISSOR_BANDEIRA_NAO_LOCALIZADO("Não foi possível localizar o emissor e bandeira informados"),
    CARTAO_EMISSOR_BANDEIRA_LOCALIZADO("Cartão já cadastrado para o mesmo Emissor e Bandeira"),
    CARTAO_PAN_EXISTENTE("PAN do cartão existente"),
    CARTAO_EMISSOR_INEXISTENTE("Emissor inexistente para este cartão"),
    CARTAO_BANDEIRA_INEXISTENTE("Bandeira inexistente para o emissor deste cartão"),
    PROTOCOLO_NAO_LOCALIZADO("Não foi possível localizar o protocolo informado"),
    BANDEIRA_NAO_LOCALIZADA("Não foi possível localizar a bandeira informada"),
    BANDEIRA_BAD_REQUEST("Não foi possível processar a solicitação, dados de request inválidos"),
    BANDEIRA_BAD_EXISTE("Não foi possível processar a solicitação para essa bandeira existente"),
    BANDEIRA_COD_BAD_EXISTE("Não foi possível processar a solicitação para código bandeira existente"),
    PRODUTO_COD_BAD_EXISTE("Não foi possível processar a solicitação para código produto e bandeira já existente"),
    PRODUTO_NAO_LOCALIZADO("Não foi possível localizar o produto informado"),
    PRODUTO_BANDEIRA_LOCALIZADO("Produto já cadastrado para a mesma bandeira"),
    PRODUTO_TIPO_PLATAFORMA_NAO_LOCALIZADO("Não foi possível localizar o tipo plataforma informado"),
    FLUXO_TRANSACIONAL_NAO_LOCALIZADO("Não foi possível localizar o fluxo transacional informado"),
    CREDENCIADOR_NAO_LOCALIZADO("Não foi possível localizar o credenciador informado"),
    PORTA_SERVIDOR_JA_CADASTRADA("Porta do servidor já cadastrada"),
    PORTA_SERVIDOR_DUPLICADA("Lista de porta servidores contem portas duplicadas"),
    PORTA_CLIENTE_JA_CADASTRADA("Porta do cliente já cadastrada"),
    PORTA_CLIENTE_DUPLICADA("Lista de porta cliente contem portas duplicadas"),
    NOME_PORTA_CLIENTE_DUPLICADO("Lista de porta cliente contem nome de portas duplicados"),
    CREDENCIADOR_COD_BAD_EXISTE("Não foi possível processar a solicitação para código credenciador existente"),
    ESTABELECIMENTO_COMERCIAL_DUPLICADA("Lista de estabelecimento comercial contem estabelecimentos duplicados"),
    VALIDATION_RULES_VALIDATION_BIT_BAD_REQUEST("Não foi possível processar a solicitação, validation bit necessita de pelos menos uma regra cadastrada"),
    VALIDATION_RULES_NAO_LOCALIZADO("Não foi possível localizar o validation rule informado"),;

    private final String descricao;

    MensagensRetorno(String descricao) {
        this.descricao = descricao;
    }
}
