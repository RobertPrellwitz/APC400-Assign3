import java.io.IOException;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class PingClient extends UDPPinger{
    Date now = new Date();
    LocalDateTime sentTime;
    LocalDateTime recieveTime;

    public static void main(String[] args) throws Exception {
        PingClient pinger = new PingClient();
        pinger.run();
    }
    public void run () throws Exception {

        DatagramSocket pingSock = null;
        byte [] buff;
        DatagramPacket outPacket;
        DatagramPacket inPacket;
        UDPPinger ping = new UDPPinger();
        PingMessage message = new PingMessage();
        System.out.println("Contacting Host: " + message.internet + " at port: " + message.portNumber);

        try {
            pingSock = new DatagramSocket();
            pingSock.setSoTimeout(1000);


        } catch (IOException exp) {
            System.out.println("Error: " + exp);
        }
        for (int i = 0; i < 10; i++)
        {
            String payLoad = message.getPayload(i);
            buff = payLoad.getBytes();
            outPacket = new DatagramPacket(buff, buff.length,message.internet,message.portNumber);
            try{
            pingSock.send(outPacket);
            sentTime = LocalDateTime.now();
            System.out.println(sentTime);
            }
            catch(IOException exp){
                System.out.println("Recieve Ping: " + exp);
            }
            inPacket = new DatagramPacket(buff, buff.length);
            pingSock.receive(inPacket);
            recieveTime = LocalDateTime.now();
            System.out.println(recieveTime);
            String Data = inPacket.getData().toString();
            String Address = inPacket.getAddress().toString();
            int Port = inPacket.getPort();
            System.out.println("Recieved Packet " + Data + " from: " + Address + "on port: " + Port  );
            Duration RTT = Duration.between(sentTime , recieveTime);
            long milliSeconds = RTT.toMillis();
            message.printData(inPacket, milliSeconds);

        }


    }

}
