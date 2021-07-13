package br.com.elo.repository;

import br.com.elo.model.Ambiente;
import br.com.elo.model.AuthorizationRules;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRulesRepository extends MongoRepository<AuthorizationRules, String> {

    AuthorizationRules findByAuthorizationRulesSequence(Long authorizationRulesSequence);

    //boolean existsByAuthorizationRulesSequence(Long authorizationRulesSequence);
}
