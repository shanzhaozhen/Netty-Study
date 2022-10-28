package com.example.nettystudy.bio.multi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: shanzhaozhen
 * @Date: 2022-10-26
 * @Description:
 */
public class MultiBIOClient {

    /**
     * 目标: Socket网络编程。
     * <p>
     * 功能1：客户端可以反复发，一个服务端可以接收无数个客户端的消息！！
     * <p>
     * 小结：
     * 服务器如果想要接收多个客户端，那么必须引入线程，一个客户端一个线程处理！！
     */

    public static void main(String[] args) {
        System.out.println("客户端的启动");
        // （1）创建一个Socket的通信管道，请求与服务端的端口连接。
        try (Socket socket = new Socket("127.0.0.1", 9999);) {
            // （2）从Socket通信管道中得到一个字节输出流。
            OutputStream outputStream = socket.getOutputStream();
            // （3）把字节流改装成自己需要的流进行数据的发送
            PrintStream printStream = new PrintStream(outputStream);
            // （4）开始发送消息
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("请说:");
                String msg = scanner.nextLine();
                printStream.println(msg);
                printStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
