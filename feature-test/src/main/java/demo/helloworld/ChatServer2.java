package demo.helloworld;

import act.Act;
import act.controller.annotation.TemplateContext;
import act.controller.annotation.UrlContext;
import act.ws.WebSocketContext;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.WsAction;

@UrlContext("chat")
@TemplateContext("chat")
public class ChatServer2 {

    @GetAction
    public void home() {
    }

    @WsAction("socket")
    public void onMessage(String message, WebSocketContext ctx) {
        ctx.sendToPeers(message);
    }

    public static void main(String[] args) throws Exception {
        Act.start("Chat Demo");
    }

}
