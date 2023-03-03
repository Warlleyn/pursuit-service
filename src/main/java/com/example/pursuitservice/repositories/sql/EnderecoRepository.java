package com.example.pursuitservice.repositories.sql;

import com.example.pursuitservice.domain.sql.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
