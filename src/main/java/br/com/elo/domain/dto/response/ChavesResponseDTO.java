package br.com.elo.domain.dto.response;

import br.com.elo.domain.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChavesResponseDTO implements Serializable {

    private String chavePin;
    private String chaveCavv;

}
