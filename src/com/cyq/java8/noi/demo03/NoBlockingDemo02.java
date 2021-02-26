package com.cyq.java8.noi.demo03;

import org.junit.Test;
import org.springframework.expression.spel.ast.Selection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * NOI非阻塞模式
 */
public class NoBlockingDemo02 {

    /**
     * 客户端
     */
    @Test
    public void test1() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("DatagramChannel通道".getBytes());
        buffer.flip();
        datagramChannel.send(buffer, new InetSocketAddress("127.0.0.1", 9998));
        datagramChannel.close();
    }

    /**
     * 服务端
     */
    @Test
    public void test2() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        datagramChannel.bind(new InetSocketAddress(9998));
        Selector selector = Selector.open();
        datagramChannel.register(selector, SelectionKey.OP_READ);
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    datagramChannel.receive(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(), buffer.position(), buffer.limit()));
                    buffer.clear();
                }
            }
            iterator.remove();
        }
        datagramChannel.close();
    }

}
