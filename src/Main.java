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
                task.start();
            }
        }catch(IOException e){
         //   logger.error(e);
        }


        System.out.println("숫자 야구");
        int size = 3; //숫자 자리 수
        int strake = 0;
        int ball = 0;
        Scanner sc = new Scanner(System.in);
        List<Integer> player = new ArrayList<>();
        Home_team home_team = new Home_team();
        Away_team away_team = new Away_team(size);


        away_team.printlist(); //답안지

        //게임 시작!
        while(strake != size){
            strake = 0;
            ball = 0;
            player.clear();

            //player 입력 받기.
            System.out.println("숫자를 입력해주세요");
            client.setMsg("숫자를 입력해주세요");
            int player_input = 0;
            try {
                //player_input = sc.nextInt(); //Scanner 이용해서 player 입력값 받기
                player_input = Integer.parseInt(client.getClientInput());
            }catch (InputMismatchException e){
                System.out.println("Int 범위를 넘은 값입니다.");
                break;
            }
            // 역순으로 저장된다. ex) 123 -> 321
            // 1의 자리부터 list에 추가한다.  다양한 자료구조의 차이를 알아야한다.

            while (player_input != 0) {
                int n = player_input%10;
                //입력된 값을 검증한다.
                if(player.contains(n)){
                    System.out.println("잘못된 입력입니다. : 중복된 숫자가 입력됨.");
                    break;
                }
                else if( n == 0){
                    System.out.println("잘못된 입력입니다. : 0이 입력됨.");
                    break;
                }
                player.add(n);
                player_input /= 10;
            }
            
            if(player.size() != size) {
                System.out.println("잘못된 입력입니다. : 숫자의 자릿수가 맞지 않음.");
                continue;
            }

            //player List를 유저가 입력한 순서로 바꿈
            Collections.reverse(player);

            home_team.setList(player);
            home_team.printList();

            //결과 판독
            for(int i = 0 ; i< size;i++) {
                if (away_team.numberlist.get(i) == home_team.numberlist.get(i)) {
                    //System.out.println("Strlike!");
                    strake++;
                }
                else if(away_team.numberlist.contains(home_team.numberlist.get(i))){
                    //System.out.println("BALL.");
                    ball++;
                }
            }

            // 결과 출력
            if(strake==0 && ball==0){
                System.out.println("나띳띵");
            }
            else{
                System.out.println("S : " + strake);
                System.out.println("B : " + ball);
            }
        }// while( strake == size)

        System.out.println("끝");
    }//main 끝
}
