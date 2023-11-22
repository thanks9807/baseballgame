import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
public class Main {
    public static void main(String[] args) {
        Client client = new Client();

        try(ServerSocket server = new ServerSocket(9807)){
            while(true){
                System.out.println("연결 대기 중");
                Socket connection = server.accept();
                Thread task = new ServerSocketThread(connection,client);
                Thread game = new BaseBallGame(client);

                task.start();
                game.start();
            }
        }catch(IOException e){
         //   logger.error(e);
        }


    }//main 끝
}
