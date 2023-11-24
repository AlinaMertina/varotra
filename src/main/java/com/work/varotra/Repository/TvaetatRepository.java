package com.work.varotra.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.work.varotra.Entity.Tvaetat;

public interface TvaetatRepository extends  JpaRepository<Tvaetat,Long>{
    @Query(value = "select valeur from Tvaetat  order by date desc limit 1", nativeQuery = true)
    public Double getTva();
}
