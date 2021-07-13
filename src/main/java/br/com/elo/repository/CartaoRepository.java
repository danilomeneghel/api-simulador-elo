package br.com.elo.repository;

import br.com.elo.model.Cartao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoRepository extends MongoRepository<Cartao, String> {

    Cartao findByPan(String pan);
    boolean existsByPan(String pan);
}
