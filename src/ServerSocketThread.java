import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSocketThread extends Thread {
    private Socket socket;

    public ServerSocketThread(Socket connection) {
        this.socket = connection;
    }


    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        try{
            String connIp = socket.getInetAddress().getHostAddress();
            System.out.println(connIp + "에서 연결 시도.");

            /*
             * 접근한 소켓 계정의 ip를 체크한다. KTOA 연동 모듈인지 체크
             * 정상이면 먼저 정상 접근되었음을 알린다.
             **/
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            pw = new PrintWriter(socket.getOutputStream());

            // 클라이언트에서 보낸 문자열 출력
            System.out.println(br.readLine());

            // 클라이언트에 문자열 전송
            pw.println("수신되었다. 오버");
            pw.flush();

            //연결이 확인된 이후 연결이 끊기면 안된다.
            while(true){
                Thread.sleep(1000);
                System.out.println("연결은 유지 중입니다.");
                String str = br.readLine(); // 왜 여기서 멈출까?
                if(str == null){
                    System.out.println("아무말도 없네요.");

                }
                else{
                    System.out.println(str);
                }
            }

        }catch(IOException e){
            //logger.error(e);
        } catch (InterruptedException e) { //sleep에 필요
            throw new RuntimeException(e);
        } finally{
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
