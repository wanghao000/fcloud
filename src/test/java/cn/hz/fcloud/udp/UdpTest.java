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
			InetAddress address = InetAddress.getByName("server.natappfree.cc");
			int port = 48072;
//			byte[] b = "FA01007457700492H00490D0A".getBytes();
//			byte[] b = "FA02007457700492H00490D0A".getBytes();
			byte[] b = "FA03812345618111100001x1902011501300D0A".getBytes();
//			byte[] b = "551A43CF1D1914D6A9793B4D768F77A23F2F8708B50A9C65FD771EF9BD20D6526518E82D4BB1EDAE0018AE65E8174EE81E52DF4FE67A66EABD55".getBytes();
			DatagramPacket packet = new DatagramPacket(b, b.length, address, port);
			DatagramSocket socket = new DatagramSocket();
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
