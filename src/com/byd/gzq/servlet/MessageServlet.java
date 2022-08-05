package com.byd.gzq.servlet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/message")
public class MessageServlet extends BaseServlet{
    private static CopyOnWriteArraySet<MessageServlet> message = new CopyOnWriteArraySet<>();
    private Session session = null;

    public static CopyOnWriteArraySet<MessageServlet> getMessage(){
        return message;
    }
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        message.add(this);
    }

    @OnMessage
    public void onMessage(int count) throws IOException {
        aaa(count);
    }

    @OnError
    public void onError(Throwable e){
        System.out.println(e.getMessage());
    }

    @OnClose
    public void onClose(){
        message.remove(this);
    }


    public void aaa(int count) throws IOException {
        for (MessageServlet messageServlet : message) {
            messageServlet.session.getBasicRemote().sendText(String.valueOf(count));
        }

    }

}
