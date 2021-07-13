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
@Document(collection = "ExcludedBits")
public class ExcludedBits {

    @Column
    private Integer bitNumber;
}