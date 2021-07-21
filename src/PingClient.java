import java.net.Inet4Address;
import java.net.InetAddress;

public class PingClient extends UDPPinger{

    public PingMessage(InetAddress adr, int port, String payload){}

    public InetAddress getIP(){
        Inet4Address ip=0.0.0.0;
        return ip;
    }

    public int getPort() {
        int port =0;
        return port;
    }

    public String getPayload(){
        String payload = "";
        return payload;
    }

    run();
}
