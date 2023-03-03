package com.example.pursuitservice.repositories.nosql;

import com.example.pursuitservice.domain.nosql.Alocacoes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlocacoesRepository extends MongoRepository<Alocacoes, String> {
}
