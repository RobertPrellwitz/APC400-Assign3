import java.io.IOException;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class PingClient extends UDPPinger{

    LocalDateTime sentTime;
    LocalDateTime recieveTime;

    public static void main(String[] args) throws Exception {
        PingClient pinger = new PingClient();
        pinger.run();
    }
    public void run () throws Exception {

        DatagramSocket pingSock = null;
        byte [] buff = new byte[512];
        DatagramPacket outPacket;
        DatagramPacket inPacket;
        UDPPinger ping = new UDPPinger();
        PingMessage message = new PingMessage();
        System.out.println("Contacting Host: " + message.internet + " at port: " + message.portNumber);

        try {
            pingSock = new DatagramSocket();
            pingSock.setSoTimeout(5000);


        } catch (IOException exp) {
            System.out.println("Error: " + exp);
        }
        long [] roundTrip = new long[10];
        for (int i = 0; i < 10; i++)
        {
            ping.sendPing(i,message,pingSock);
            sentTime=LocalDateTime.now();

            inPacket = ping.receivePing(i, pingSock);
            recieveTime = LocalDateTime.now();
            System.out.println(recieveTime);

            Duration RTT = Duration.between(sentTime , recieveTime);
            long milliSeconds = RTT.toMillis();
            roundTrip[i] = milliSeconds;
            message.printData(inPacket, milliSeconds);
            Thread.sleep(1000);
        }

    pingTime(roundTrip);
    }

}
