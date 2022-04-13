/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import org.java_websocket.client.WebSocketClient;

/**
 *
 * @author Hassen chouadah
 */
public interface IRealTimeService {
    public void openConnection();
    public void closeConnection();
    public WebSocketClient getWebSocket();
    public void setWebSocket(WebSocketClient ws);
}
