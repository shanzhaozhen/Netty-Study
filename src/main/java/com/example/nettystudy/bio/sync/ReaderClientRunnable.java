package com.example.nettystudy.bio.sync;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

/**
 * @Author: shanzhaozhen
 * @Date: 2022-10-28
 * @Description:
 */
public class ReaderClientRunnable implements Runnable {

    private final Socket socket;

    public ReaderClientRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream()) {
            // 读取一行数据
            Reader reader = new InputStreamReader(inputStream);
            // 转成一个缓冲字符流
            BufferedReader bufferedReader = new BufferedReader(reader);
            // 一行一行的读取数据
            String line;
            while ((line = bufferedReader.readLine()) != null) { // 阻塞式的！！
                System.out.println("服务端收到：" + socket.getRemoteSocketAddress() + ":" + line);
            }
        } catch (Exception e) {
            System.out.println("有人下线了");
        }

    }
}