import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;


public class UDPPinger {
    byte[] buff = new byte[512];
//    DatagramPacket packet = new DatagramPacket(buff,512);
//    PingMessage message = new PingMessage();
//    DatagramSocket pingSock = null;
    DatagramPacket outPacket;
    DatagramPacket inPacket;
   LocalDateTime sentTime;    LocalDateTime recieveTime;
//    long [] roundTrip = new long[10];


    public void sendPing ( int i, PingMessage message, DatagramSocket pingSock){
        String payLoad = message.getPayload(i);
        buff = payLoad.getBytes();
        outPacket = new DatagramPacket(buff, buff.length,message.internet,message.portNumber);
        try{
            pingSock.send(outPacket);
//            sentTime = LocalDateTime.now();
//            System.out.println(sentTime);
        }
        catch(IOException exp){
            System.out.println("Recieve Ping: " + exp);
        }


    }

    public  DatagramPacket receivePing(int i, DatagramSocket pingSock) throws Exception {
        inPacket = new DatagramPacket(buff, buff.length);
        pingSock.receive(inPacket);
        //recieveTime = LocalDateTime.now();
        //System.out.println(recieveTime);
        String Data = inPacket.getData().toString();
        String Address = inPacket.getAddress().toString();
        int Port = inPacket.getPort();
        System.out.println("Recieved Packet " + Data + " from: " + Address + "on port: " + Port  );
        // RTT = Duration.between(sentTime , recieveTime);
        //long milliSeconds = RTT.toMillis();
        //roundTrip[i] = milliSeconds;
        //message.printData(inPacket, milliSeconds);
        return inPacket;

    }
    public void pingTime(long [] roundTrip ){
    System.out.println("Longest Ping Time: " + Arrays.stream(roundTrip).max());
    System.out.println("Shortest Ping Time: " + Arrays.stream(roundTrip).min());
    System.out.println("Average Ping Time: " + Arrays.stream(roundTrip).average());
    }
}
