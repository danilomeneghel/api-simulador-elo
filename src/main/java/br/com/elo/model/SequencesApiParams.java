package br.com.elo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sequences_api_params")
public class SequencesApiParams {
    @Id
    private String id;

    private long seq;

}
