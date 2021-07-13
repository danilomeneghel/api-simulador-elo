package br.com.elo.domain.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstabelecimentoComercialRequestDTO implements Serializable {

    @Size(max = 60, message = "Tamanho máximo do nome estabelecimento deve ser 60 caracteres")
    private String nome;
    private Integer codigoPais;
    private Integer mcc;
    @Size(max = 14, message = "Tamanho cpfCnpj do estabelecimento deve ser 14 caracteres")
    private String cpfCnpj;
}