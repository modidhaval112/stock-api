package com.stocks.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stocks.api.entity.StockApiData;

public interface StockApiRepository extends CrudRepository<StockApiData, Integer>{
	
	@Query("select s from StockApiData s where s.stock = :stock and s.clientId= :clientId")
	List<StockApiData> findByStockAndClientId(@Param("stock") String stock, @Param("clientId") String clientId);
	
}
