package demo.chatroom;

/*-
 * #%L
 * chatroom
 * %%
 * Copyright (C) 2018 ActFramework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import act.cli.CliContext;
import act.cli.Command;
import act.cli.Required;
import act.util.Lazy;
import okhttp3.*;
import org.osgl.util.S;

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
            context.println(S.concat("[ws]", text));
        }
    }

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

    @Command(name = "send", help = "Send message to websocket server")
    public void send(@Required("specify the message to be sent") String message, CliContext context) {
        WebSocket ws = context.session().attribute("ws");
        if (null == ws) {
            open(context);
            send(message, context);
        } else {
            ws.send(message);
        }
    }


}
