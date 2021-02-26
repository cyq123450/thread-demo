package com.cyq.java8.noi.demo02;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * Channel演示。
 *      一、通道(Channel)：用于源节点与目标节点的连接。在Java NIO中负责缓冲区中数据的传输。Channel本身不存储数据，因此需要配合缓冲区进行传输。
 *      二、通道的主要实现类：
 *          java.nio.channels.Channel接口:
 *              |--FileChannel
 *              |--SocketChannel
 *              |--ServerSocketChannel
 *              |--DatagramChannel
 *      三、获取通道
 *          1.Java针对支持通道的类提供了getChannel()方法
 *              本地IO：
 *                  FileInputStream/FileOutputStream
 *                  RandomAccessFile
 *              网络IO：
 *                  Socket
 *                  ServerSocket
 *                  DatagramSocket
 *           2.在JDK1.7 中的NIO.2针对各个通道提供了静态方法open()
 *           3.在JDK1.7 中的NIO.2的Files工具类的newByteChannel()
 *
 *      四、通道之间的数据传输
 *          transferFrom()
 *          transferTo()
 *
 *      五、分散与聚集
 *          分散读取：将通道中的数据分散到多个缓冲区中
 *          聚集写入：将多个缓冲区中的数据聚集到通道中
 *
 *      六、字符集：Charset
 *          编码：字符串 -> 字符数组
 *          解码：字符数组 -> 字符串
 *
 */
public class ChannelDemo01 {

    /**
     * 利用通道完成文件的复制(非直接存储)
     */
    @Test
    public void test1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fisChannel = null;
        FileChannel fosChannel = null;
        try {
            // 1.获取文件流
            fis = new FileInputStream("resources/images/1.jpg");
            fos = new FileOutputStream("resources/images/1_copy.jpg");
            // 2.根据流获取对应的通道
            fisChannel = fis.getChannel();
            fosChannel = fos.getChannel();

            // 3.获取非直接缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 4.开始读数据并写入
            while (fisChannel.read(buffer) != -1) {
                buffer.flip();
                fosChannel.write(buffer);
                buffer.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fisChannel != null) {
                try {
                    fisChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fosChannel != null) {
                try {
                    fosChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  使用直接缓存1区完成文件复制(内存映射文件)
     */
    @Test
    public void test2() throws IOException {
        // 打开通道
        FileChannel inChannel = FileChannel.open(Paths.get("resources/images/1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel =FileChannel.open(Paths.get("resources/images/1_1_copy.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ,StandardOpenOption.CREATE);

        // 内存映射文件
        MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outBuffer = outChannel. map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        byte[] bytes = new byte[inBuffer.limit()];
        inBuffer.get(bytes);
        outBuffer.put(bytes);

        outChannel.close();
        inChannel.close();

    }

    /**
     * 通道之间的数据传输(直接缓冲区)
     */
    @Test
    public void test3() throws IOException {
        // 打开通道
        FileChannel inChannel = FileChannel.open(Paths.get("resources/images/1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel =FileChannel.open(Paths.get("resources/images/1_2_copy.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ,StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);

        outChannel.close();
        inChannel.close();

    }

    /**
     * 分散与聚集
     */
    @Test
    public void test4() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("resources/images/1.txt", "rw");

        // 1.获取通道
        FileChannel channel = randomAccessFile.getChannel();
        // 2.分配指定大小的缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);

        ByteBuffer[] buffers = new ByteBuffer[]{buffer1, buffer2};
        channel.read(buffers);

        for(ByteBuffer byteBuffer : buffers) {
            byteBuffer.flip();
        }

        System.out.println(new String(buffer1.array(), 0, buffer1.limit()));
        System.out.println("----------------------------------------------------");
        System.out.println(new String(buffer2.array(), 0, buffer2.limit()));

        RandomAccessFile accessFile = new RandomAccessFile("resources/images/1_1.txt", "rw");
        FileChannel channel1 = accessFile.getChannel();
        channel1.write(buffers);

    }

    /**
     * 字符集
     */
    @Test
    public void test5() {
        SortedMap<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = map.entrySet();
        for (Map.Entry<String, Charset> entry : entries) {
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        }
    }

    /**
     * 字符集
     */
    @Test
    public void test6() throws CharacterCodingException {
        Charset charset = Charset.forName("GBK");

        // 获取编码和解码器
        CharsetEncoder ce = charset.newEncoder();
        CharsetDecoder cd = charset.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("NIO管道流");
        cBuf.flip();

        // 编码
        ByteBuffer bBuf = ce.encode(cBuf);
        for(int i = 0; i < bBuf.limit(); i++) {
            System.out.println(bBuf.get());
        }

        // 解码
        bBuf.flip();
        CharBuffer cBuf2 = cd.decode(bBuf);
        System.out.println(cBuf2.toString());

        // 按UTF-8解码
        Charset cs2 = Charset.forName("UTF-8");
        bBuf.flip();
        CharBuffer cBuf3 = cs2.decode(bBuf);
        System.out.println(cBuf3.toString());

    }

}
