package kz.lab.reportservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class RequestLoggingWebFilter implements WebFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggingWebFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest req = exchange.getRequest();
        String auth = req.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        System.out.println("[STDOUT] Incoming request " + req.getMethod() + " " + req.getURI() + " from " + exchange.getRequest().getRemoteAddress() + " Authorization=" + mask(auth));
        log.info("Incoming request {} {} from {} Authorization={}",
            req.getMethod(), req.getURI(), exchange.getRequest().getRemoteAddress(), mask(auth));
        return chain.filter(exchange);
    }

    private String mask(String value) {
        if (value == null) return "<none>";
        if (value.length() <= 10) return value;
        return value.substring(0, 7) + "...";
    }
}
