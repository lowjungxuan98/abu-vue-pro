package my.abu.pp.framework.websocket.core.sender.local;

import my.abu.pp.framework.websocket.core.sender.AbstractWebSocketMessageSender;
import my.abu.pp.framework.websocket.core.sender.WebSocketMessageSender;
import my.abu.pp.framework.websocket.core.session.WebSocketSessionManager;

/**
 * 本地的 {@link WebSocketMessageSender} 实现类
 *
 * 注意：仅仅适合单机场景！！！
 *
 * @author 阿布源码
 */
public class LocalWebSocketMessageSender extends AbstractWebSocketMessageSender {

    public LocalWebSocketMessageSender(WebSocketSessionManager sessionManager) {
        super(sessionManager);
    }

}
