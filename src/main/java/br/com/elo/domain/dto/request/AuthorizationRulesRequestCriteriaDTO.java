package br.com.elo.domain.dto.request;

import br.com.elo.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorizationRulesRequestCriteriaDTO implements Serializable {

    private String id;

    private Status status;

}
