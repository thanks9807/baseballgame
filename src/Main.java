//camp.nextstep.edu.missionutils에서 제공하는 Randoms 및 Console API를 사용하여 구현해야 한다.
//Random 값 추출은 camp.nextstep.edu.missionutils.Randoms의 pickNumberInRange()를 활용한다.
//사용자가 입력하는 값은 camp.nextstep.edu.missionutils.Console의 readLine()을 활용한다.

import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        System.out.println("숫자 야구");
        int size = 3; //숫자 자리 수
        int strake = 0;
        int ball = 0;
        Scanner sc = new Scanner(System.in);
        List<Integer> player = new ArrayList<>();

        Away_team away_team = new Away_team(size);


//        away_team.printlist(); //답안지

        //게임 시작!
        while(strake != size){
            strake = 0;
            ball = 0;
            player.clear();

            //player 입력 받기.
            System.out.println("숫자를 입력해주세요");
            int player_input = 0;
            player_input = sc.nextInt(); //Scanner 이용해서 player 입력값 받기




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


            //플레이어가 입력한 숫자입니다.
            System.out.println("플레이어가 입력한 숫자입니다.");
            for (int i = player.size() - 1; i >= 0;i--){
                System.out.print(player.get(i));
            }
            System.out.print("\n");

            //결과 판독
            for(int i = 0 ; i< size;i++) {
                if (away_team.numberlist.get(i) == player.get(size-i-1)) {
                    //System.out.println("Strlike!");
                    strake++;
                }
                else if(away_team.numberlist.contains(player.get(size-i-1))){
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
        System.out.println("정답입니다. 끝");
    }//main 끝



}
