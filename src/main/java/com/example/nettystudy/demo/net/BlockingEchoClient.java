/**
 * Welcome to https://waylau.com
 */
package com.example.nettystudy.demo.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Blocking Echo Client.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2019年9月28日
 */
public class BlockingEchoClient {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int portNumber = 7;

        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            while (true) {
                System.out.print("请输入:");
                String userInput = stdIn.readLine();
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("不明主机，主机名为： " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("不能从主机中获取I/O，主机名为：" + hostName);
            System.exit(1);
        }
    }

}
