package my.abu.pp.module.infra.websocket;

import my.abu.pp.framework.common.enums.UserTypeEnum;
import my.abu.pp.framework.websocket.core.listener.WebSocketMessageListener;
import my.abu.pp.framework.websocket.core.sender.WebSocketMessageSender;
import my.abu.pp.framework.websocket.core.util.WebSocketFrameworkUtils;
import my.abu.pp.module.infra.websocket.message.DemoReceiveMessage;
import my.abu.pp.module.infra.websocket.message.DemoSendMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import jakarta.annotation.Resource;

/**
 * WebSocket 示例：单发消息
 *
 * @author 阿布源码
 */
@Component
public class DemoWebSocketMessageListener implements WebSocketMessageListener<DemoSendMessage> {

    @Resource
    private WebSocketMessageSender webSocketMessageSender;

    @Override
    public void onMessage(WebSocketSession session, DemoSendMessage message) {
        Long fromUserId = WebSocketFrameworkUtils.getLoginUserId(session);
        // 情况一：单发
        if (message.getToUserId() != null) {
            DemoReceiveMessage toMessage = new DemoReceiveMessage().setFromUserId(fromUserId)
                    .setText(message.getText()).setSingle(true);
            webSocketMessageSender.sendObject(UserTypeEnum.ADMIN.getValue(), message.getToUserId(), // 给指定用户
                    "demo-message-receive", toMessage);
            return;
        }
        // 情况二：群发
        DemoReceiveMessage toMessage = new DemoReceiveMessage().setFromUserId(fromUserId)
                .setText(message.getText()).setSingle(false);
        webSocketMessageSender.sendObject(UserTypeEnum.ADMIN.getValue(), // 给所有用户
                "demo-message-receive", toMessage);
    }

    @Override
    public String getType() {
        return "demo-message-send";
    }

}
