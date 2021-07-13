package br.com.elo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ModifiedBits")
public class ModifiedBits {

    @Column
    private Integer bitNumber;

    @Column
    private String fillingMode;

    @Column
    private String value;


}
