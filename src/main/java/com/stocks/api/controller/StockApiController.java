package com.stocks.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stocks.api.entity.StockApiData;
import com.stocks.api.service.StockApiService;

@RestController
@RequestMapping("/api/stock-data")
public class StockApiController {
	
	@Autowired
	StockApiService stockApiService;

	@PostMapping("/")
	public ResponseEntity<String> uploadMissingData(@RequestHeader(value="X-Client_Id") String clientId, @RequestBody StockApiData data) {
		String response = null;
		try {
			response = stockApiService.uploadMissingData(clientId, data);
		} catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/{stock}")
	public ResponseEntity<?> getRecords(@RequestHeader(value="X-Client_Id") String clientId, @PathVariable String stock) {
		
		List<StockApiData> listRecords;
		try {
			listRecords = stockApiService.getRecords(clientId, stock);
		} catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return  new ResponseEntity<>(listRecords, HttpStatus.OK);
		
	}
	
	@PostMapping("/bulk-insert")
	public ResponseEntity<String> bulkInsertData(@RequestHeader(value="X-Client_Id") String clientId, @RequestParam("file") MultipartFile file) {
		
		String response = null;
		
		try {
			 response = stockApiService.bulkInsertData(clientId, file);
		} catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
}
