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
public class ChavesRequestDTO implements Serializable {

    @Size(max = 32, message = "Tamanho máximo da versão deve ser 32 caracteres")
    private String chavePin;

    private String chaveCavv;

}
