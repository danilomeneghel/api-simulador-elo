package br.com.elo.domain.dto.response;

import br.com.elo.domain.Plataforma;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmissorResponseDTO implements Serializable {

    private String id;
    private Integer codigoBandeira;
    private Integer codigoEmissor;
    private String nomeEmissor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataAlteracao;
    private Integer codigoProcessadora;
    private Plataforma plataforma;


}
