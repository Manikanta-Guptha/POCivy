package com.cachedemo.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoResource {

	public static int retries = 0;

	@Value("${retry.limit}")
	private int limit;

	@GetMapping("details")
	public String getDetails() {
		serviceMethod();
		return "Success";
	}

	/**
	 * Service method to make a call to dao layer to get data
	 * 
	 * @return
	 */
	private String serviceMethod() {

		if (retries < limit) {
			return fetchData();
		}
//		else {
//			return fetchDataFromDB();
//		}
		return null;
	}

	/**
	 * Method to fetch data either from Cache or database
	 * 
	 * @return
	 */
	private String fetchData() {
		try {
			return fetchFromCache();
		} catch (Exception e) {
			return fetchDataWithCondition();
		}
	}

	/**
	 * Method to get data from Cache
	 * 
	 * @return
	 * @throws Exception
	 */
	private String fetchFromCache() throws Exception {
		System.out.println(":: fetch data from cache :: " + retries);
		return "Success";
//		System.out.println("Exception occurs while fetching data from cache " + retries);
//		throw new Exception();
	}

	/**
	 * Check the condition with limit and fetch data accordingly
	 * 
	 * @return String
	 */
	private String fetchDataWithCondition() {
		if (retries++ < limit)
			return fetchData();
		else
			return fetchDataFromDB();
	}

	/**
	 * Method to get data from Database
	 * 
	 * @return String
	 */
	private String fetchDataFromDB() {
		System.out.println(":: fetch data from db :: ");
		return "Success";
	}
}
