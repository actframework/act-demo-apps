package demo.featuretest;

/*-
 * #%L
 * actframework Feature Test App
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
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.jboss.logging.Logger;
import org.xnio.OptionMap;
import org.xnio.Xnio;
import org.xnio.XnioWorker;

import javax.servlet.ServletException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import static io.undertow.servlet.Servlets.defaultContainer;
import static io.undertow.servlet.Servlets.deployment;
import static io.undertow.websockets.jsr.WebSocketDeploymentInfo.ATTRIBUTE_NAME;

public class WebSocketServer {

    private static final Logger LOGGER = Logger.getLogger(WebSocketServer.class);

    @ServerEndpoint("/")
    public static class SocketProxy {

        @OnOpen
        public void onOpen() {
            LOGGER.info("onOpen");
        }

        @OnClose
        public void onClose() {
            LOGGER.info("onClose");
        }

        @OnMessage
        public void onMessage(String message) {
            LOGGER.info("onMessage:" + message);
        }

    }

    public static void main(String[] args) throws ServletException, IOException {
        final Xnio xnio = Xnio.getInstance("nio", Undertow.class.getClassLoader());
        final XnioWorker xnioWorker = xnio.createWorker(OptionMap.builder().getMap());
        final WebSocketDeploymentInfo webSockets = new WebSocketDeploymentInfo()
                .addEndpoint(SocketProxy.class)
                .setWorker(xnioWorker);
        final DeploymentManager deployment = defaultContainer()
                .addDeployment(deployment()
                        .setClassLoader(WebSocketServer.class.getClassLoader())
                        .setContextPath("/")
                        .setDeploymentName("embedded-websockets")
                        .addServletContextAttribute(ATTRIBUTE_NAME, webSockets));

        deployment.deploy();
        Undertow.builder().
                addListener(8080, "localhost")
                .setHandler(deployment.start())
                .setHandler(Handlers.path().addPrefixPath("/test", Handlers.resource(new ClassPathResourceManager(WebSocketServer.class.getClassLoader(), WebSocketServer.class.getPackage())).addWelcomeFiles("index.html")))
                .build()
                .start();

    }

}
