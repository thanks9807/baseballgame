import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Away_team {
    List<Integer> numberlist = new ArrayList<>();
    public Away_team(int size){
        Random random = new Random(); // 랜덤 객체 생성
        random.setSeed(System.currentTimeMillis());

        while (numberlist.size() < size) {
            int randomNumber = random.nextInt(9);
            if (randomNumber != 0 && !numberlist.contains(randomNumber)) { // 0이랑 숫자 중복 막음.
                numberlist.add(randomNumber);
            }
        }

    }

    public void printlist(){
        //상대방의 숫자 출력
        System.out.print("Away Team의 숫자입니다.");
        for (int i = 0; i < numberlist.size();i++) {
            System.out.print(numberlist.get(i));
        }
        System.out.print("\n");

    }

}
