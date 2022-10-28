package com.example.nettystudy.bio.sync;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: shanzhaozhen
 * @Date: 2022-10-27
 * @Description:
 */
public class SyncBIOServer {

    public static void main(String[] args) {
        System.out.println("----------服务端启动成功------------");
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            // 一个服务端只需要对应一个线程池
            HandlerSocketThreadPool handlerSocketThreadPool = new HandlerSocketThreadPool(3, 1000);
            // 客户端可能有很多个
            while (true) {
                Socket socket = serverSocket.accept(); // 阻塞式的！
                System.out.println("用户上线：" + socket.getRemoteSocketAddress());
                // 每次收到一个客户端的socket请求，都需要为这个客户端分配一个
                // 独立的线程 专门负责对这个客户端的通信！！
                handlerSocketThreadPool.execute(new ReaderClientRunnable(socket));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
