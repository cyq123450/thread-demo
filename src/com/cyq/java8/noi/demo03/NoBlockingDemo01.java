package com.cyq.java8.noi.demo03;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 非阻塞通道演示
 */
public class NoBlockingDemo01 {

    /**
     * 客户端
     */
    @Test
    public void test1() throws IOException, InterruptedException {
        // 1.获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        // 2.设置通道为非阻塞模式
        socketChannel.configureBlocking(false);
        // 3.分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 4.发数据
        buffer.put("客户端发送数据".getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
        // 5.关闭连接
        socketChannel.close();
    }

    /**
     * 服务端
     */
    @Test
    public void test2() throws IOException {
        // 1.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2.设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 3.绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));
        // 4.获取选择器、
        Selector selector = Selector.open();
        // 5.注册选择器监听的类型
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 6.使用轮询方式获取已经准备就绪的通道
        while (selector.select() > 0) {
            // 7.使用迭代器获取选择器
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 8.判断是否是什么状态
                if (selectionKey.isAcceptable()) {
                    // 9.获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 10.客户端通道切换为非阻塞
                    socketChannel.configureBlocking(false);
                    // 11.将客户端注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    // 获取通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 分配指定大小的缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    // 读取数据
                    while (socketChannel.read(buffer) != -1) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), buffer.position(), buffer.limit()));
                        buffer.clear();
                    }
                }
                // 12.取消选择键
                iterator.remove();
            }
        }
        // 13.关闭连接
        serverSocketChannel.close();
    }

}
