package br.com.elo.repository;

import br.com.elo.model.Credenciador;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CredenciadorRepository extends MongoRepository<Credenciador, String> {

    Credenciador findByCredenciadorCodigo(Integer credenciadorCodigo);
    boolean existsByCredenciadorCodigo(Integer credenciadorCodigo);
}
