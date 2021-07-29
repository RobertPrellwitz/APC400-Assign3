import javax.swing.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.time.*;


public class PingMessage {
    Console console = System.console();
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
        //System.out.println("Please enter the destination ip address in form: d.d.d.d");
        //input = console.readLine();
        input =  JOptionPane.showInputDialog("Please enter the destination ip address in form: d.d.d.d");
        //input = System.console().readLine();

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
       // port = Integer.parseInt(console.readLine());
        port = Integer.parseInt(JOptionPane.showInputDialog("Please enter the Port number :"));
        return port;
    }

    public String getPayload(int i){
        String payload = "Ping "  + i + " " + LocalDateTime.now();
        return payload;
    }

    public String printData(DatagramPacket request, long RTT) throws Exception
    {

        byte[] buf = request.getData();
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);
        InputStreamReader isr = new InputStreamReader(bais);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();
        System.out.println(
                "Received from " +
                        request.getAddress().getHostAddress() +
                        ": " +
                        new String(line) + " Delay: " + RTT );
        String Data = String.format("Received from " +
                request.getAddress().getHostAddress() +
                ": " +
                new String(line) + " Delay: " + RTT );
        return Data;
    }
    public int port(int port) {
        return port;
    }

}
