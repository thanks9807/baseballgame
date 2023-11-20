import java.util.ArrayList;
import java.util.List;

public class Home_team {
    List<Integer> numberlist = new ArrayList<>();

    public void setList(List<Integer> list){
        numberlist = list;
    }

    public void printList(){
        System.out.print("Home Team의 숫자입니다.");
        for (int i = 0; i < numberlist.size() ;i++){
            System.out.print(numberlist.get(i));
        }
        System.out.print("\n");
    }
    public Home_team(){

    }
}
