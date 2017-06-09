package demo.chatroom;

import act.cli.CliContext;
import act.cli.Command;
import act.cli.Required;
import act.util.Lazy;
import okhttp3.*;

import javax.inject.Singleton;

@Singleton
@Lazy
public class ConsoleApp {

    private static class EchoListener extends WebSocketListener {

        private CliContext context;

        public EchoListener(CliContext context) {
            this.context = context;
            context.session().attribute("listener", this);
        }

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            webSocket.send("connected");
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            context.println("Closed");
            context.session().removeAttribute("ws");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            context.println(text);
        }
    }

    @Command("open")
    public void open(CliContext context) {
        WebSocket ws = context.session().attribute("ws");
        if (null != ws) {
            context.println("already opened");
            return;
        }
        EchoListener listener =  new EchoListener(context);
        Request request = new Request.Builder().url("ws://localhost:5460/msg").build();
        OkHttpClient client = new OkHttpClient();
        ws = client.newWebSocket(request, listener);
        context.session().attribute("ws", ws);
        client.dispatcher().executorService().shutdown();
    }

    @Command("send")
    public void send(@Required String message, CliContext context) {
        WebSocket ws = context.session().attribute("ws");
        if (null == ws) {
            open(context);
            send(message, context);
        } else {
            ws.send(message);
        }
    }


}
