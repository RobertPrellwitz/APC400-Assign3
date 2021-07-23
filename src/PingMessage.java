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

    public void printData(DatagramPacket request, long RTT) throws Exception
    {
        // Obtain references to the packet's array of bytes.
        byte[] buf = request.getData();

        // Wrap the bytes in a byte array input stream,
        // so that you can read the data as a stream of bytes.
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);

        // Wrap the byte array output stream in an input stream reader,
        // so you can read the data as a stream of characters.
        InputStreamReader isr = new InputStreamReader(bais);

        // Wrap the input stream reader in a bufferred reader,
        // so you can read the character data a line at a time.
        // (A line is a sequence of chars terminated by any combination of \r and \n.)
        BufferedReader br = new BufferedReader(isr);

        // The message data is contained in a single line, so read this line.
        String line = br.readLine();

        // Print host address and data received from it.
        System.out.println(
                "Received from " +
                        request.getAddress().getHostAddress() +
                        ": " +
                        new String(line) + " Delay: " + RTT );
    }
    public int port(int port) {
        return port;
    }

}
