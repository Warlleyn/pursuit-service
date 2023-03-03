package com.example.pursuitservice.repositories.sql;

import com.example.pursuitservice.domain.sql.Endereco;
import com.example.pursuitservice.domain.sql.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {

    @Query("SELECT im FROM Imovel im WHERE dataExclusao IS NULL AND email = :emailUser ")
    List<Imovel> findByEmailAndDataExclusaoIsNull(@Param("emailUser") String emailUser);
    @Query(value = "SELECT im FROM Imovel im WHERE dt_exclusao IS NULL AND cd_enderecos in :enderecos ", nativeQuery = true)
    List<Imovel> findByEnderecosIn(@Param("enderecos") List<Long> enderecos);

    List<Imovel> findByDataExclusaoIsNull();
    String HAVERSINE_PART = "(6371 * acos(cos(radians(:latitude)) * cos(radians(e.latitude)) *" +
            " cos(radians(e.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(e.latitude))))";
    @Query(value = "SELECT * FROM diversos.endereco e INNER JOIN imoveis.imovel i ON i.cd_endereco = e.cd_endereco" +
            "WHERE  " + HAVERSINE_PART + "< :raio e.dt_exclusao IS NULL and i.dt_exclusao IS NULL ", nativeQuery = true)
    List<Imovel> findRadiansByLatLong(@Param("latitude") String latitude,
                                        @Param("longitude") String longitude,
                                        @Param("raio") Integer raio);
}
