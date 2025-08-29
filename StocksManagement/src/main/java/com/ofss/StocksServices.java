package com.ofss;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class StocksServices {
	@Autowired
	StocksRepository stockRepository;
	
	
	
	// adding new stocks
	public ResponseEntity<Object> addAStock(Stocks newStock) {
		// save() method generates INSERT INTO query
		return ResponseEntity.status(201).body(stockRepository.save(newStock));
	}
	
	
	// getting all stocks
	
	public ResponseEntity<Object> fetchAllStocks()
	{
		return ResponseEntity.status(201).body(stockRepository.findAll());
	}
	
	public ResponseEntity<Object> fetchStockById(int sid)
	{
		Optional<Stocks> stock=stockRepository.findById(sid);
		
		 if (stock.isPresent()) {
		        return ResponseEntity.ok(stock.get());
		    } else {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                             .body("Stock with ID " + sid + " not found");
		    }
		
		
	}
	
	// deleting a stock
	
	public ResponseEntity<Object> deleteStockById(int sid)
	{
		Optional<Stocks> stock=stockRepository.findById(sid);
		if(stock.isPresent())
		{
			stockRepository.deleteById(sid);
	        return ResponseEntity.ok(stock.get()+" deleted");
		}
		 else {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                             .body("Stock with ID " + sid + " not found");
		    }
	}
	
//	public ResponseEntity<Object> stockUpdateById(int sid,Stocks newStock)
//	{
//		
//		if(stockRepository.existsById(sid))
//		{
//			Stocks existStock=stockRepository.findById(sid).get();
//			existStock.setStock_Id(sid);
//			if(newStock.getStock_Name()!="")
//				existStock.setStock_Name(newStock.getStock_Name());
//			if(newStock.getExchange_Name()!="")
//				existStock.setExchange_Name(newStock.getExchange_Name());
//			if(newStock.getStock_Price()!=0)
//			  existStock.setStock_Price(newStock.getStock_Price());
//			
//			stockRepository.save(existStock);
//	        return ResponseEntity.ok("Stock with stockID "+existStock.getStock_Id() +" stock got Updated");
//		}
//		else
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Stock with ID " + sid + " not found");
//		}
//	}
	
}