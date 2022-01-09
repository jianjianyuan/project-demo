package cn.project.demo.iaas.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Order(0)
@Component
public class GlobalAuthFilter implements GlobalFilter {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private static final String TOKEN_HEADER_KEY = "Authorization";

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		//获取token
		String token = getToken(request);

		ServerHttpResponse response = exchange.getResponse();
		response.getHeaders().set("Content-Type", "application/json");

		//验证token
		if (StringUtils.isEmpty(token)) {
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			return response.setComplete();
		}
		//获取用户
		String userId = getUserIdByToken(token);
		if (StringUtils.isEmpty(userId)) {
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			return response.setComplete();
		}

		//刷新redis过期信息
		resetTokenExpirationTime(token, userId);

		//重写请求头
		ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(
				httpHeader -> {
					httpHeader.set("user_id", userId);
					httpHeader.set("Authorization", token);
				}
		).build();
		exchange.mutate().request(serverHttpRequest).build();

		return chain.filter(exchange);
	}

	/**
	 * 获取token
	 */
	private static String getToken(ServerHttpRequest request) {
		HttpHeaders headers = request.getHeaders();
		// 从请求头获取token
		String token = headers.getFirst(TOKEN_HEADER_KEY);
		// 请求头无token则从url获取token
		if (StringUtils.isEmpty(token)) {
			token = request.getQueryParams().getFirst(TOKEN_HEADER_KEY);
		}
		// 请求头和url都没有token则从cookies获取
		if (StringUtils.isEmpty(token)) {
			HttpCookie cookie = request.getCookies().getFirst(TOKEN_HEADER_KEY);
			if (cookie != null) {
				token = cookie.getValue();
			}
		}
		return StringUtils.isEmpty(token) ? token : token.replace("Bearer ", "");
	}

	/**
	 * 重置token过期时间
	 */
	private void resetTokenExpirationTime(String token, String userId) {
		String redisKey = String.join(":", "auth_token", token);
		redisTemplate.opsForValue().set(redisKey, userId, 24, TimeUnit.HOURS);
	}

	/**
	 * 获取用户id
	 */
	private String getUserIdByToken(String token) {
		/*String redisKey = String.join(":", "auth_token", token);
		return redisTemplate.opsForValue().get(redisKey);*/
		return "hello";
	}
}
