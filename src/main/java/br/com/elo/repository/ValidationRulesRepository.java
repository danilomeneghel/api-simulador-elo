package br.com.elo.repository;

import br.com.elo.model.ValidationRules;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRulesRepository extends MongoRepository<ValidationRules, String> {
}
