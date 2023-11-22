import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSocketThread extends Thread {
    private Socket socket;
    private Client client;
    public ServerSocketThread(Socket connection,Client client) {
        this.socket = connection;
        this.client = client;

    }


    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        try{
            String connIp = socket.getInetAddress().getHostAddress();
            System.out.println(connIp + "에서 연결 시도.");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());
            UserInputChecker userInputChecker = new UserInputChecker(br);
            // 클라이언트에서 보낸 문자열 출력
            System.out.println(br.readLine());

            // 클라이언트에 문자열 전송
            pw.println("수신되었다. 오버");
            pw.flush();

            //연결이 확인된 이후 연결이 끊기면 안된다.
            while(true){
                //System.out.println("연결은 유지 중입니다.");
                //System.out.println(client.getMsg());

                System.out.println("입력을 기다리는 중입니다.");
                String str = null; // 입력이 들어올 때까지 대기한다.
                str = br.readLine(); // 입력이 들어올 때까지 대기한다.


                System.out.println("Client : " + str);

            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(pw != null) { pw.close();}
                if(br != null) { br.close();}
                if(socket != null){socket.close();}
            }catch(IOException e){
                //logger.error(e);
            }
        }
    }
}
