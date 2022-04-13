/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Tools.*;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.application.Platform;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

/**
 *
 * @author Hassen chouadah
 */
public class RealTimeService implements IRealTimeService {

    private WebSocketClient ws;

    public RealTimeService() throws URISyntaxException {
        ws = new WebSocketClient(new URI(Statics.wsURI), new Draft_6455()) {
            @Override
            public void onOpen(ServerHandshake sh) {
                System.out.println("opened connection");
            }

            @Override
            public void onMessage(String message) {
                System.out.println("received message :" + message);
            }

            @Override
            public void onClose(int i, String string, boolean bln) {
                System.out.println("closed connection");
            }

            @Override
            public void onError(Exception ex) {
                System.out.println("error :" + ex.getMessage());
            }
        };
    }

    public void openConnection() {
        ws.connect();
    }

    public void closeConnection() {
        ws.close();
    }

    public WebSocketClient getWebSocket() {
        return ws;
    }

    public void setWebSocket(WebSocketClient ws) {
        this.ws = ws;
    }

}
