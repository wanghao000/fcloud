package cn.hz.fcloud.udp;

import cn.hz.fcloud.service.EquipmentDataService;
import cn.hz.fcloud.service.EquipmentService;
import cn.hz.fcloud.utils.UDPServerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author 田国栋
 */
@Component
public class UDPServer {

	@Autowired
	private EquipmentService equipmentService;

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
				new UDPServerThread(b, packet, socket, equipmentService, equipmentDataService).start();
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
		new Thread(new Runnable() {
			@Override
			public void run() {
				start();
			}
		}).start();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				UDPServerUtil.findExceedTimeRecord(equipmentService, 1000*60*2*1);
			}
		}, 10000, 1000*60*10);
	}

}
