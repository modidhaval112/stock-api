package com.stocks.api.mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.stocks.api.entity.StockApiData;

@Component
public class StockApiMapper {

	public List<StockApiData> map(MultipartFile file) throws IOException {

		Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
		Map<String, String> columnMapping = new HashMap<String, String>();
		columnMapping.put("days_to_next_dividend", "daysToNextDividend");
		columnMapping.put("next_weeks_open", "nextWeeksOpen");
		columnMapping.put("next_weeks_close", "nextWeeksClose");
		columnMapping.put("percent_change_next_weeks_price", "percentChangeNextWeeksPrice");
		columnMapping.put("percent_change_price", "percentChangePrice");
		columnMapping.put("percent_change_volume_over_last_wk", "percentChangeVolumeOverLastWk");
		columnMapping.put("percent_return_next_dividend", "percentReturnNextDividend");
		columnMapping.put("previous_weeks_volume", "previousWeeksVolume");
		columnMapping.put("close", "close");
		columnMapping.put("date", "date");
		columnMapping.put("high", "high");
		columnMapping.put("low", "low");
		columnMapping.put("open", "open");
		columnMapping.put("quarter", "quarter");
		columnMapping.put("stock", "stock");
		columnMapping.put("volume", "volume");

		HeaderColumnNameTranslateMappingStrategy<StockApiData> strategy = new HeaderColumnNameTranslateMappingStrategy<StockApiData>();
		strategy.setType(StockApiData.class);
		strategy.setColumnMapping(columnMapping);

		CsvToBean<StockApiData> csvToBean = new CsvToBeanBuilder(reader).withType(StockApiData.class)
				.withIgnoreLeadingWhiteSpace(true).withMappingStrategy(strategy).build();

		List<StockApiData> records = csvToBean.parse();

		return records;

	}

}
