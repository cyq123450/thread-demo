package com.cyq.java8.noi.demo03;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、使用NIO完成网络通信的三个核心：
 *      1.通道(Channel)：负责连接
 *          java.nio.channels.Channel接口：
 *              |--SelectableChannel
 *                  |--SocketChannel
 *                  |--ServerSocketChannel
 *
 *                  |--Pipe.SinkChannel
 *                  |--Pipe.SourceChannel
 *
 *      2.缓冲区(Buffer)：负责数据的存取
 *
 *      3.选择器(Selector)：是SelectableChannel的多路复用器。用于SelectableChannel的IO状况
 *
 */
public class NoiDemo01 {

    /**
     *  利用SocketChannel演示服务端
     */
    @Test
    public void test1() throws IOException {
        // 1.获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        FileChannel fileChannel = FileChannel.open(Paths.get("resources/images/1.jpg"), StandardOpenOption.READ);
        // 2.分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 3.读取数据并写入
        while (fileChannel.read(buffer) != -1) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }
        // 4.关闭通道
        fileChannel.close();
        socketChannel.close();
    }

    /**
     * 利用SocketChannel演示客户端
     */
    @Test
    public void test2() throws IOException {
        // 1.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        FileChannel fileChannel = FileChannel.open(Paths.get("resources/images/2_1.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        // 2.绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));
        // 3.获取客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();
        // 4.分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 5.从通道读取数据并写入
        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }
        // 6.关闭连接
        socketChannel.close();
        fileChannel.close();
        serverSocketChannel.close();
    }

}
