import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.time.*;

public class PingMessage {
    int portNumber;
    InetAddress internet = null;

    public PingMessage(InetAddress adr, int port, String payload){

        getIP();
        getPort();
        getPayload(4);
    }
    public PingMessage(){
         internet = getIP();
        portNumber = getPort();
    }

    public InetAddress getIP(){
       String input ;
        InetAddress ip = null;
        System.out.println("Please enter the destination ip address in form: d.d.d.d");
        input = System.console().readLine();
        try {
           ip = Inet4Address.getByName(input);
        }
        catch (IOException exp){
            System.out.println("IO Exception :" + exp);
        }
        return ip;
    }

    public int getPort() {
        int port =0;
        System.out.println("Please enter the Port number :");
        port = Integer.parseInt(System.console().readLine());
        return port;
    }

    public String getPayload(int i){
        String payload = "Ping "  + i + " " + LocalDateTime.now();
        return payload;
    }
    public int port(int port) {
        return port;
    }

}
