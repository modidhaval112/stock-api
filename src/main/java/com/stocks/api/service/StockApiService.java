package com.stocks.api.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stocks.api.entity.StockApiData;
import com.stocks.api.mapper.StockApiMapper;
import com.stocks.api.repository.StockApiRepository;

@Service
public class StockApiService {
	
	@Autowired
	StockApiRepository stockApiRepo;
	
	@Autowired
	StockApiMapper stockApiMapper;

	public String uploadMissingData(String clientId, StockApiData data) {
		
		data.setClientId(clientId);
		stockApiRepo.save(data);
		
		return "Added Successfully";
	}

	public List<StockApiData> getRecords(String clientId, String stock) {
		List<StockApiData> listRecordsApiDatas = stockApiRepo.findByStockAndClientId(stock, clientId);
		return listRecordsApiDatas;
	}

	public String bulkInsertData(String clientId, MultipartFile file) throws IOException {
		
		List<StockApiData> rawData = stockApiMapper.map(file);
		List<StockApiData> data = rawData.stream().peek(stock -> stock.setClientId(clientId)).collect(Collectors.toList());
		
		int totalRecords = data.size();
		int insertSize = 50;
		
		for (int i = 0; i < totalRecords; i = i + 50) {
		    if( i + insertSize > totalRecords){
		        List<StockApiData> records = data.subList(i, totalRecords);
		        stockApiRepo.saveAll(records);
		        break;
		    }
		    List<StockApiData> records = data.subList(i, i + insertSize);
		    stockApiRepo.saveAll(records);
		}
		
		return "Inserted Bulk Records Successfully";
	}
	

}
