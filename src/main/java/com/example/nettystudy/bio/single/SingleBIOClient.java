package com.example.nettystudy.bio.single;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: shanzhaozhen
 * @Date: 2022-10-26
 * @Description:
 */
public class SingleBIOClient {

    /**
     * 目标: Socket网络编程。
     * <p>
     * Java提供了一个包：java.net下的类都是用于网络通信。
     * Java提供了基于套接字（端口）Socket的网络通信模式，我们基于这种模式就可以直接实现TCP通信。
     * 只要用Socket通信，那么就是基于TCP可靠传输通信。
     * <p>
     * 功能1：客户端发送一个消息，服务端接口一个消息，通信结束！！
     * <p>
     * 创建客户端对象：
     * （1）创建一个Socket的通信管道，请求与服务端的端口连接。
     * （2）从Socket管道中得到一个字节输出流。
     * （3）把字节流改装成自己需要的流进行数据的发送
     * 创建服务端对象：
     * （1）注册端口
     * （2）开始等待接收客户端的连接,得到一个端到端的Socket管道
     * （3）从Socket管道中得到一个字节输入流。
     * （4）把字节输入流包装成自己需要的流进行数据的读取。
     * <p>
     * Socket的使用：
     * 构造器：public Socket(String host, int port)
     * 方法：  public OutputStream getOutputStream()：获取字节输出流
     * public InputStream getInputStream() :获取字节输入流
     * <p>
     * ServerSocket的使用：
     * 构造器：public ServerSocket(int port)
     * <p>
     * 小结：
     * 通信是很严格的，对方怎么发你就怎么收，对方发多少你就只能收多少！！
     */

    public static void main(String[] args) {
        System.out.println("客户端的启动");
        //创建一个socket通信管道，请求与服务端端口连接
        try (Socket socket = new Socket("127.0.0.1", 9999)) {
            //从socket通信管道中得到一个字节输出流
            OutputStream outputStream = socket.getOutputStream();
            //把字节流改装成自己需要的流进行数据发送
            PrintStream printStream = new PrintStream(outputStream);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("请输入:");
                String s = scanner.nextLine();
                printStream.println(s);
                printStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
