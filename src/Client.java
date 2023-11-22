import java.util.Scanner;

public class Client {
    public Client() {
        isReadclientInput = true;
        isReadMsg = true;
    }

    public synchronized void setClientInput(String clientInput) {
        if(this.isReadclientInput == false){
            System.out.println("아직 읽지 않은 입력이 있습니다.");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("유저 입력이 있습니다.");
        this.clientInput = clientInput;
        this.isReadclientInput = false;
        notify();
    }

    public synchronized void setMsg(String msg) {
        if(this.isReadMsg == false){
            System.out.println("아직 전달 못한 전달값이 있습니다.");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("전달값이 있습니다.");
        this.msg = msg;
        this.isReadMsg = false;
        notify();
    }
    public synchronized String getClientInput(){
        if(this.isReadclientInput == true){
            System.out.println("새로운 값이 있을 때까지 대기합니다.");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String str = this.clientInput;
        this.isReadclientInput = true;
        notify();
        return str;
    }
    public synchronized String getMsg() {
        if(this.isReadMsg == true){
            System.out.println("새로운 값이 있을 때까지 대기합니다.");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.isReadclientInput = true;
        notify();
        return this.msg;
    }
    String clientInput;
    Boolean isReadclientInput;


    String msg;
    Boolean isReadMsg;

}
