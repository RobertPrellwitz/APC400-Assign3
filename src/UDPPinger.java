import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;


public class UDPPinger {
    byte[] buff = new byte[512];

    DatagramPacket outPacket;
    DatagramPacket inPacket;
   LocalDateTime sentTime;    LocalDateTime recieveTime;



    public void sendPing ( int i, PingMessage message, DatagramSocket pingSock){
        String payLoad = message.getPayload(i);
        buff = payLoad.getBytes();
        outPacket = new DatagramPacket(buff, buff.length,message.internet,message.portNumber);
        try{
            pingSock.send(outPacket);
        }
        catch(IOException exp){
            System.out.println("Recieve Ping: " + exp);
        }


    }

    public  DatagramPacket receivePing(int i, DatagramSocket pingSock) throws Exception {
        inPacket = new DatagramPacket(buff, buff.length);
        pingSock.receive(inPacket);
        String Data = inPacket.getData().toString();
        String Address = inPacket.getAddress().toString();
        int Port = inPacket.getPort();
        System.out.println("Recieved Packet " + Data + " from: " + Address + "on port: " + Port  );
        return inPacket;

    }
    public void pingTime(long [] roundTrip, int timeoutCount ){
    System.out.println("\nTimeout Count: " + timeoutCount+ " which is a " + (timeoutCount*10)+"% failure rate!" );
    System.out.println("Longest Ping Time: " + Arrays.stream(roundTrip).max().getAsLong() + "  milliseconds");
    System.out.println("Shortest Ping Time: " + Arrays.stream(roundTrip).min().getAsLong() + "  milliseconds");
    System.out.println("Average Ping Time: " + Arrays.stream(roundTrip).average().getAsDouble() + "  milliseconds");
    }
}
