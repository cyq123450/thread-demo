package com.cyq.java8.noi.demo03;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeDemo01 {

    @Test
    public void test1() throws IOException {
        // 1.获取通道
        Pipe pipe = Pipe.open();
        // 2.想缓冲区中写入数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Pipe.SinkChannel sinkChannel = pipe.sink();
        buffer.put("Pipe管道流".getBytes());
        buffer.flip();
        sinkChannel.write(buffer);

        // 3.读取数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        buffer.clear();
        sourceChannel.read(buffer);
        buffer.flip();
        System.out.println(new String(buffer.array(), buffer.position(), buffer.limit()));
    }


}
