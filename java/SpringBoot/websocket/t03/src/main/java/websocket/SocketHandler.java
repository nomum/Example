package websocket;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
@Component
public class SocketHandler extends  BinaryWebSocketHandler {
	// @Override
	// public void handleTextMessage(WebSocketSession session, TextMessage message)
	// 		throws InterruptedException, IOException {

	// 	String payload = message.getPayload();
	// 	JSONObject jsonObject = new JSONObject(payload);
	// 	session.sendMessage(new TextMessage("Hi " + jsonObject.get("user") + " how may we help you?"));
	// }
    private ArrayList< WebSocketSession > users;
    public SocketHandler(){
        users = new ArrayList<>();
    }

	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 接続が確立されたら呼ばれる.
		System.out.println("[Call]afterConnectionEstablished");
        // 接続確立時、配列にWebSocketSessionの情報を追加.
        if(users.stream()
                .noneMatch(user -> user.getId().equals(session.getId()))){
            users.add(session);
        }
	}
    // @Override
    // public void handleTextMessage(WebSocketSession session, TextMessage message) {
    //     // WebSocketクライアントからメッセージを受信した時に呼ばれる.
    // }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 接続が切られたら呼ばれる.
		System.out.println("[Call]afterConnectionClosed");
        // 接続が切れたら配列から削除.
        users.stream()
                .filter(user -> user.getId().equals(session.getId()))
                .findFirst()
				.ifPresent(user -> users.remove(user));
	}
	@Override
	public void handleBinaryMessage(WebSocketSession session,
	BinaryMessage message){
        // WebSocketクライアントからメッセージを受信した時に呼ばれる.
		System.out.println("[Call]handleBinaryMessage");
		
	}

}