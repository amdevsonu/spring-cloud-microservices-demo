package com.sonuit.rest;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class DataRestController {
	@GetMapping("/data")
	@HystrixCommand (
			fallbackMethod="getDataFromDB",  //this is the fallback method name need to specify
			commandProperties= {
					@HystrixProperty (name="circuitBreaker.requestVolumeThreshold", value="5"),
					@HystrixProperty (name="circuitBreaker.sleepWindowInMilliseconds", value="10000")
			}
	)
	public String getDataFromRedis() { //method-1 main logic here
		System.out.println("Redis() method is called");
		//logic to access data from Redis
		if(new Random().nextInt(10) <= 10) {
			throw new RuntimeException("Redis Server is Down");
		}
		return "data access from Redis(Main Logic)...";
	}
	
	
	public String getDataFromDB() {  //method-2 fallback logic here
		System.out.println("DB() method is called");
		//logic to access data from DB
		return "data accessed from database(Fallback Logic)...";
	}
}	
