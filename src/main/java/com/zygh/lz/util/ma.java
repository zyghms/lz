package com.zygh.lz.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class ma {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1",6666);
        socket.connect(socketAddress);
        while (true){
            Scanner scanner = new Scanner(System.in);
            socket.getOutputStream().write(scanner.next().getBytes());
        }
    }

    ServerSocketChannel server;
    Selector selector;
    ByteBuffer buffer;
    /*
     * 构造函数，初始化各个变量，对ServerSocketChannel进行注册
     */
    public ma() throws IOException {
        selector = Selector.open();
        server = ServerSocketChannel.open();
        server.configureBlocking(false);//设置为非阻塞模式
        server.socket().bind(new InetSocketAddress(9800));
        server.register(selector, SelectionKey.OP_ACCEPT);
        buffer = ByteBuffer.allocate(25550);
        openserver();
    }
    /*
     * 此方法把一个字符串传递给客户端
     */
    public void openserver() throws IOException {
        System.out.println("服务器开启");
        while (true) {

            if (selector.select()>0) {
                Iterator<SelectionKey> ikeys = selector.selectedKeys().iterator();
                while (ikeys.hasNext()) {
                    SelectionKey selectionKey = ikeys.next();
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        send(socketChannel, "7758258");

                    }
                    ikeys.remove();
                }
            }
        }
    }
    /*
     * 传递数据到客户端函数
     */
    public void send(SocketChannel socketChannel,String string) throws IOException {
        buffer.clear();//清除缓冲区，在使用一系列通道读取或放置 操作填充此缓冲区之前调用此方法
        buffer.put(string.getBytes());
        buffer.flip();//反转此缓冲区，在一系列通道读取或放置 操作之后，调用此方法为一系列通道写入或相对获取 操作做好准备
        socketChannel.write(buffer);

    }
}
