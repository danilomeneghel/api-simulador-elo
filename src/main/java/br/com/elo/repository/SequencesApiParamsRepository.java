package br.com.elo.repository;

import br.com.elo.model.SequencesApiParams;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequencesApiParamsRepository extends MongoRepository<SequencesApiParams, String> {
}
