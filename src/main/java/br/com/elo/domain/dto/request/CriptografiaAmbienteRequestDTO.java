package br.com.elo.domain.dto.request;

import br.com.elo.common.utils.annotations.EnumNamePattern;
import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriptografiaAmbienteRequestDTO implements Serializable {

    @NotBlank(message = "A descrição não pode ser em branco e nem nulo")
    @Size(max = 32, message = "Tamanho máximo da descriação deve ser 32 caracteres")
    private String chavePin;

    @NotBlank(message = "A descrição não pode ser em branco e nem nulo")
    @Size(max = 122, message = "Tamanho máximo da descriação deve ser 122 caracteres")
    private String chaveCavv;

}
