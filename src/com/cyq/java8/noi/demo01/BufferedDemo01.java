package com.cyq.java8.noi.demo01;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 缓冲区演示。
 *      一、在Java NIO中负责数据的存取，缓冲区就是数组，用于存储不同数据类型的数据。
 *
 *      二、根据数据类型不同(boolean除外)，提供了相应类型的缓冲区：
 *          ByteBuffer
 *          CharBuffer
 *          ShortBuffer
 *          IntBuffer
 *          LongBuffer
 *          FloatBuffer
 *          DoubleBuffer
 *      上述缓冲区的管理方式几乎一致，通过allocate()获取缓冲区。
 *
 *      三、缓冲区存取数据的两个核心方法：
 *          put()：存入数据到缓冲区中
 *          get()：获取缓冲区中的数据
 *
 *      四、缓冲区中的四大核心属性：
 *          capacity：容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 *          limit：界限，表示缓冲区中可以操作数据的大小。(limit后数据不能进行读写)
 *          position：位置，表示缓冲区中正在操作数据的位置。
 *          mark：位置，表示记录当前position的位置。可以通过reset()恢复到mark的位置。
 *               0 <= mark <= position <= limit <= capacity
 *
 *      五、直接缓冲区与非直接缓冲区：
 *          非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在JVM的内存中
 *          直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区建立在物理内存中。可以具有效率。
 *
 */
public class BufferedDemo01 {

    @Test
    public void test1() {
        // 1.分配缓冲区大小
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println("------------1.allocate()------------");
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        // 2.存放数据
        System.out.println("------------2.put()------------");
        String data = "hello";
        buffer.put(data.getBytes());
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        // 3.切换成读取数据模式
        buffer.flip();
        System.out.println("------------3.flip()------------");
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        // 4.读取缓冲区中的数据
        System.out.println("------------4.get()------------");
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes, buffer.position(), buffer.limit());
        System.out.println("Read Result:" + new String(bytes));
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        // 5.重复读数据
        buffer.rewind();
        System.out.println("------------5.rewind()------------");
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        // 6.清空缓冲区(注意：缓冲区里的数据依然存在，但是处于“被遗忘状态”)
        buffer.clear();
        System.out.println("------------6.clear()------------");
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());
    }

    @Test
    public void test2() {
        ByteBuffer buffer = ByteBuffer.allocate(20);
        System.out.println("---------1.allocate()----------");
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        String data = "hellohello";
        buffer.put(data.getBytes());
        System.out.println("---------2.put()----------");
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        byte[] bytes1 = new byte[10];
        buffer.flip();
        buffer.get(bytes1, buffer.position(), 2);
        System.out.println("---------3.get()----------");
        System.out.println("Read Result:" + new String(bytes1, 0, 6));
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        buffer.mark();
        byte[] bytes2 = new byte[10];
        buffer.get(bytes2, buffer.position(), 2);
        System.out.println("---------4.get() after mark()----------");
        System.out.println("Read Result:" + new String(bytes2, 0, 6));
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        buffer.reset();
        byte[] bytes3 = new byte[10];
        buffer.get(bytes3, buffer.position(), 2);
        System.out.println("---------5.get() after reset()----------");
        System.out.println("Read Result:" + new String(bytes3, 0, 6));
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        // 判断缓冲区中是否还有数据
        if (buffer.hasRemaining()) {
            // 获取缓冲区数据的个数
            System.out.println(buffer.remaining());
        }

    }

    @Test
    public void test3() {
        // 非直接缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(10);
        // 直接缓冲区
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(10);
        System.out.println("是否是直接缓冲区：" + allocate.isDirect());
        System.out.println("是否是直接缓冲区：" + allocateDirect.isDirect());
    }

}
