package cn.hz.fcloud.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class CigaretteWebsocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(webSocketHandler(),"/websocket/socketServer.do").addInterceptors(new CigaretteWebsocketHandlerInterceptor());
//        registry.addHandler(webSocketHandler(), "/sockjs/socketServer.do").addInterceptors(new CigaretteWebsocketHandlerInterceptor()).withSockJS();
        registry.addHandler(webSocketHandler(), "/");
    }

    @Bean
    public TextWebSocketHandler webSocketHandler(){
        return new CigaretteWebsocketHandler();
    }
}
