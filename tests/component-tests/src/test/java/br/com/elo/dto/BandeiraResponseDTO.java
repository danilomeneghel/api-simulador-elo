package br.com.elo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BandeiraResponseDTO implements Serializable {

    private String id;
    private String descricao;
    private Integer codigoBandeira;
    private String dataCriacao;
    private String dataAlteracao;
}
