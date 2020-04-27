package com.zygh.lz.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class slave {
    private static List<SocketChannel> objects = new ArrayList<>();
    //客户端
    public static void main(String[] args) throws Exception {
        //slave test = new slave();
        ServerSocketChannel open =  ServerSocketChannel.open();
        open.bind(new InetSocketAddress("127.0.0.1",6666));
        open.configureBlocking(false);//非阻塞的
        while (true){
            for(SocketChannel socketChannel : objects){
                ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                int read = socketChannel.read(byteBuffer);
                if(read>0){
                    byteBuffer.flip();
                    byte[] bs = new byte[read];
                    byteBuffer.get(bs);
                    System.out.println("content:"+new String(bs));
                    byteBuffer.flip();
                }
            }
            SocketChannel client = open.accept();
            if(client!=null){
                client.configureBlocking(false);
                objects.add(client);
            }
        }
    }

    SocketChannel client;
    Selector selector;
    ByteBuffer buffer;

    public slave() throws IOException {
        client = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9800));
        selector = Selector.open();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        buffer = ByteBuffer.allocate(5555);
        connect();
    }

    /*
     * 连接服务端，并且把传递过来的数据读取到缓冲区
     */
    public void connect() throws IOException {
        while (true) {
            if (selector.select() > 0) {
                Iterator<SelectionKey> ikeys = selector.selectedKeys().iterator();
                while (ikeys.hasNext()) {
                    SelectionKey selectionKey = ikeys.next();
                    if (selectionKey.isReadable()) {
                        reads();
                    }
                    ikeys.remove();
                }
            }

        }

    }

    public void reads() throws IOException {
        buffer.clear();
        client.read(buffer);
        buffer.flip();
        System.out.println(new String(buffer.array(),
                0, buffer.limit(), "UTF-8"));
    }
}
