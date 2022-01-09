package cn.project.demo.iaas.gateway.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;

@Slf4j
@RestController
public class HystrixFallbackController {

	@GetMapping("/hystrix-fallback")
	public String hystrixFallback(ServerWebExchange exchange) {
		URI requestUrl = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
		Throwable executionException = exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);
		log.error("hystrixExecutionException  uri@{}", requestUrl, executionException);
		assert executionException != null;
		return "HystrixFallback..." + executionException.getMessage();
	}
}