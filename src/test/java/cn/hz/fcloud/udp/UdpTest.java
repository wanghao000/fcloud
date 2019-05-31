package cn.hz.fcloud.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author 田国栋
 */
public class UdpTest {

	public static void main(String[] args) {
		try {
			final String IMEI = "1234567";
			InetAddress address = InetAddress.getByName("localhost");
			int port = 9999;
			byte[] b = ("FA01"+IMEI+"00492H00490D0A").getBytes();//开机
			DatagramPacket packet = new DatagramPacket(b, b.length, address, port);
			DatagramSocket socket = new DatagramSocket();
			socket.send(packet);
			b = ("FA02"+IMEI+"00492H00490D0A").getBytes();//心跳
			packet = new DatagramPacket(b, b.length, address, port);
			socket = new DatagramSocket();
			socket.send(packet);
			b = ("FA03"+IMEI+"18111100001x1902011501300D0A").getBytes();
//			byte[] b = "FA03007546618111100001x1902011501300D0A".getBytes();
//			byte[] b = "FA03812345618311100001x1902011501300D0A".getBytes();
			packet = new DatagramPacket(b, b.length, address, port);
			socket = new DatagramSocket();
			socket.send(packet);

			byte[] rb = new byte[1024];
			DatagramPacket rpacket = new DatagramPacket(rb, packet.getLength());
			socket.receive(rpacket);
			String info = new String(rb, 0, rpacket.getLength());
			System.out.println(rpacket.getSocketAddress()+":  "+info);
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
