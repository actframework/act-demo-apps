package demo.chatroom;

import act.Act;
import act.ws.WebSocketContext;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.WsAction;
import org.osgl.util.S;

@SuppressWarnings("unused")
public class ChatApp {

    @GetAction
    public void home() {
    }

    @WsAction("msg")
    public void onMessage(String message, WebSocketContext context) {
        if (S.notBlank(message)) {
            context.sendToPeers(message);
        }
        // otherwise we strip off the empty blank lines
    }

    public static void main(String[] args) throws Exception {
        Act.start("chat room");
    }
}
