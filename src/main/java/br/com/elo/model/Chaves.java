package br.com.elo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chaves {

    @Column
    private String chavePin;

    @Column
    private String chaveCavv;

}
