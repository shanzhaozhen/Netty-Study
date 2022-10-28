package com.example.nettystudy.bio.multi;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: shanzhaozhen
 * @Date: 2022-10-26
 * @Description:
 */
public class MultiBIOServer {


    public static void main(String[] args) {
        System.out.println("==服务器启动==");
        // （1）注册端口
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            while (true) {
                //（2）开始在这里暂停等待接收客户端的连接,得到一个端到端的Socket管道
                Socket socket = serverSocket.accept();
                // 创建一个独立的线程对socket进行处理
                new ServerReadThread(socket).start();
                System.out.println(socket.getRemoteSocketAddress() + "上线了！");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
