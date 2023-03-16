package com.web.store.file.upload.utility;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.web.store.StoreDto;
import com.web.store.model.Product;
import com.web.store.model.Store;
import com.web.store.repository.StoreRepository;

public class CSVHelper {
	 public static String TYPE = "text/csv";
	  static String[] HEADERs = { "Id", "Title", "Description", "Published" };

	  @Autowired
	  public static StoreRepository storeRepo;
	  
	  public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static void csvToProducts(InputStream is) {
		 //StoreRepository storeRepo;
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withHeader().withTrim());) {

	      List<StoreDto> productList = new ArrayList<StoreDto>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	      
	      Store store = new Store();
	      List<Product> list = new ArrayList();
		
	      for (CSVRecord csvRecord : csvRecords) {
	    	  System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+csvRecord.get("productname")+Double.valueOf(csvRecord.get("price")));
	    	  list.add(new Product(csvRecord.get("productname"),Double.valueOf(csvRecord.get("price"))));
	    	  store.setStoreId(Long.getLong(csvRecord.get("storeId")));
	    	  storeRepo.save(store);
	    	 }
	       }      
	    catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }
	  public static ByteArrayInputStream productsToCSV(List<Product> products) {
		    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
		        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
		      for (Product product : products) {
		        List<String> data = Arrays.asList(
		            //  String.valueOf(product.getId()),
		            //  product.getTitle(),
		            //  product.getDescription(),
		            //  String.valueOf(product.isPublished())
		            );

		        csvPrinter.printRecord(data);
		      }

		      csvPrinter.flush();
		      return new ByteArrayInputStream(out.toByteArray());
		    } catch (IOException e) {
		      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		    }
		  }
}
