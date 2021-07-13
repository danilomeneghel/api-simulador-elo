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
public class DescricaoDadosRequestDTO implements Serializable {

    @Size(max = 80, message = "Tamanho máximo do conteudoCampo deve ser 80 caracteres")
    private String conteudoCampo;

    @Size(max = 80, message = "Tamanho máximo da descrição deve ser 80 caracteres")
    private String descricao;

}
