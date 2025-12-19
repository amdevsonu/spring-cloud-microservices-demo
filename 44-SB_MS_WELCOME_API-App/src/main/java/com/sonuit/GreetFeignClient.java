package com.sonuit;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(url="http://localhost:9091/" , name="greet")  if your api is running in other server like aws cloud (not recomended)
@FeignClient(name="greet-api")  //recomended
public interface GreetFeignClient {
	@GetMapping("/greet")
	public String invokeGreetApi();
}
