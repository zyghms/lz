package com.zygh.lz.config;
<<<<<<< HEAD

=======
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{id}")
public class WebSocketServer {
    static Logger log = LogManager.getLogger(WebSocketServer.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    //private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    public static ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, String> sessionIds = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String id = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        this.session = session;
        //webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听:"+id+",当前在线人数为" + getOnlineCount());
        this.id = id;
        sessionPool.put(id, session);
        sessionIds.put(session.getId(), id);
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //webSocketSet.remove(this);  //从set中删除
        sessionPool.remove(sessionIds.get(session.getId()));
        sessionIds.remove(session.getId());
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口"+id+"的信息:"+message);
        //群发消息
        /*for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        // getBasicRemote是阻塞式的，getAsyncRemote是非阻塞式的
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 一对一发送
     * */
    public static void sendInfo(String message,String id) throws IOException {
        log.info("推送消息到窗口"+id+"，推送内容:"+message);
        Session s = sessionPool.get(id);
        if (s != null) {
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 群发
     * @param message
     * @throws IOException
     */
    public void sendAll(String message) throws IOException{
        for(int i = 0;i < sessionPool.size();i++){
            sendInfo(message,sessionPool.get(i).getId());
        }
    }


    public static synchronized int getOnlineCount() {
        if(onlineCount < 0){
            onlineCount = 0;
        }
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}