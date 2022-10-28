package com.example.nettystudy.bio.file;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: shanzhaozhen
 * @Date: 2022-10-28
 * @Description: 服务端开发，可以实现接收客户端的任意类型文件，并保存到服务端磁盘。
 */

public class FileBIOServer {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9999);
            while (true) {
                Socket socket = ss.accept();
                // 交给一个独立的线程来处理与这个客户端的文件通信需求。
                new ServerReaderThread(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
