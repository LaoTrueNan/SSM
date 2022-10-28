package com.byd.gzq.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Leonard
 * @date 2022/10/25 16:58
 */
@ServerEndpoint("/message")
public class MQEndpoint {

    private Logger logger = LogManager.getLogger(MQEndpoint.class);

    private static CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();

    private Object obj;


    @OnOpen
    public void addClient(Session s){
        logger.info("{} connect the socket",s);
        sessions.add(s);
    }

    @OnMessage
    public void messageHandler(int l){
        if(l>0 && obj!=null){
            send();
        }
    }

    @OnClose
    public void closeHandler(Session session){
        logger.fatal("{} closed",session);
        sessions.remove(session);
    }
    public void setObj(Object obj) {
        this.obj = obj;
    }

    public void send(){
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(obj.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getName()+hashCode();
    }
}
