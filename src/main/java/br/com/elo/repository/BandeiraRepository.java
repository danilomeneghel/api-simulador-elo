package br.com.elo.repository;


import br.com.elo.model.Bandeira;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandeiraRepository extends MongoRepository<Bandeira, String> {

    Bandeira findByCodigoBandeira(Integer codigoBandeira);
    boolean existsByCodigoBandeira(Integer codigoBandeira);
}
