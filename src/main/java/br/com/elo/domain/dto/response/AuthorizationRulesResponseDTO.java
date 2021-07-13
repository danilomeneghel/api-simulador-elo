package br.com.elo.domain.dto.response;

import br.com.elo.domain.ShouldReturn;
import br.com.elo.domain.Status;
import br.com.elo.model.ExcludedBits;
import br.com.elo.model.ModifiedBits;
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
public class AuthorizationRulesResponseDTO implements Serializable {

    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateDate;

    private String name;

    private Integer timeoutMilliseconds;

    private ShouldReturn shouldReturn;

    private List<ModifiedBits> modifiedBits;

    private List<ExcludedBits> excludedBits;

    private Status status;

    private String schemaVersion;
}