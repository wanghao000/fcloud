package cn.hz.fcloud.udp;

import cn.hz.fcloud.service.EquipmentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author 田国栋
 */
@Component
public class UDPServer {

	@Autowired
	private EquipmentDataService equipmentDataService;

	private static volatile DatagramSocket socket;
	static {
		try {
			socket = new DatagramSocket(9999);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	private static volatile byte[] b = new byte[1024];
	private static volatile DatagramPacket packet = new DatagramPacket(b, b.length);
	
	private void start() {
		try {
			while (true) {
				socket.receive(packet);
				new UDPServerThread(b, packet, socket, equipmentDataService).start();
				Thread.sleep(100);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void startUdp(){
		new Thread(()->{start();}).start();
	}

}
