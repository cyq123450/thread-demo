package com.cyq.java8.noi.demo03;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 阻塞式通道演示
 */
public class BlockingDemo01 {

    /**
     * 客户端
     */
    @Test
    public void test1() throws IOException {
        // 1.获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        FileChannel fileChannel = FileChannel.open(Paths.get("resources/images/1.txt"), StandardOpenOption.READ);
        // 2.分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 3.读取数据并写入缓冲区
        while (fileChannel.read(buffer) != -1) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        socketChannel.shutdownOutput();     // 不加该句会一直处于阻塞状态

        // 4.等到接收服务端响应信息
        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            System.out.println(new String(buffer.array(), buffer.position(), buffer.limit()));
            buffer.clear();
        }
        // 5.关闭通道
        fileChannel.close();
        socketChannel.close();
    }

    /**
     * 服务端
     */
    @Test
    public void test2() throws IOException {
        // 1.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9898));
        FileChannel fileChannel = FileChannel.open(Paths.get("resources/images/2_1.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        // 2.获取客户端
        SocketChannel socketChannel = serverSocketChannel.accept();
        // 3.分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 4.读取数据并写入
        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }

        // 5.接收数据后进行响应
        buffer.put("服务端接收数据成功".getBytes());
        buffer.flip();
        socketChannel.write(buffer);

        // 6.释放资源
        socketChannel.close();
        fileChannel.close();
        serverSocketChannel.close();
    }

}
