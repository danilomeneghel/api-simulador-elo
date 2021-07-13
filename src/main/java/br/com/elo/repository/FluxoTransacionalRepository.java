package br.com.elo.repository;


import br.com.elo.model.FluxoTransacional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FluxoTransacionalRepository extends MongoRepository<FluxoTransacional, String> {

    FluxoTransacional findByFluxoTransacionalSequence(Long fluxoTransacionalSequence);
    boolean existsByFluxoTransacionalSequence(Long fluxoTransacionalSequence);
}
