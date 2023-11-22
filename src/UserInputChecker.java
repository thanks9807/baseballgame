import java.io.BufferedReader;
import java.io.IOException;

public class UserInputChecker extends Thread{
    BufferedReader br;

    public UserInputChecker(BufferedReader br) {
        this.br = br;
    }
    @Override
    public void run() {
        try {
            String str = br.readLine(); // 입력이 들어올 때까지 대기한다.
            System.out.println(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
