package br.com.elo.model;

import br.com.elo.domain.ShouldReturn;
import br.com.elo.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "AuthorizationRules")
public class AuthorizationRules {

    @Id
    private String id;

    @Column(unique = true)
    private Long authorizationRulesSequence;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateDate;

    @Column(length = 90)
    private String name;

    @Column
    private Integer timeoutMilliseconds;

    @Column
    private ShouldReturn shouldReturn;

    @Column
    private List<ModifiedBits> modifiedBits;

    @Column
    private List<ExcludedBits> excludedBits;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private String schemaVersion;

}