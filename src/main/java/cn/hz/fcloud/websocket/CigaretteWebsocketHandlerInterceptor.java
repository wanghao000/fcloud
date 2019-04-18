package cn.hz.fcloud.websocket;

import cn.hz.fcloud.utils.ShiroUtil;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CigaretteWebsocketHandlerInterceptor extends HttpSessionHandshakeInterceptor {
    public static List<String> usernames = new ArrayList<String>();

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        System.out.println("Before Handshake");
        if (request instanceof ServletServerHttpRequest) {
//            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
//            HttpSession session = servletRequest.getServletRequest().getSession(false);
//            if (session != null) {
//                //使用userName区分WebSocketHandler，以便定向发送消息
//                String userName = (String) session.getAttribute("SESSION_USERNAME");
//                if (userName==null) {
//                    userName="default-system";
//                }
//                attributes.put("WEBSOCKET_USERNAME",userName);
//            }

//            String username = ((ServletServerHttpRequest) request).getServletRequest().getParameter("username");
            String username = ShiroUtil.getUserEntity().getUsername();
            usernames.add(username);
            attributes.put("WEBSOCKET_USERNAME", username);
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);

    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
