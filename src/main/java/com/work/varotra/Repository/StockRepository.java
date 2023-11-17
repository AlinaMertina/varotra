package com.work.varotra.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.varotra.Entity.Stock;

public interface StockRepository  extends  JpaRepository<Stock,Long>{
    
}
