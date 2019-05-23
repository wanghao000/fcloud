package cn.hz.fcloud.udp;

import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.service.EquipmentDataService;
import cn.hz.fcloud.service.EquipmentService;
import cn.hz.fcloud.service.SysUserService;
import cn.hz.fcloud.utils.UDPServerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Properties;
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
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private CompanyService companyService;

	private static volatile DatagramSocket socket;
	static {
		try {
			Resource r = new ClassPathResource("/base.properties");
			Properties prop = new Properties();
			prop.load(new FileInputStream(r.getFile()));
			String port = prop.getProperty("port");
			socket = new DatagramSocket(Integer.parseInt(port));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static volatile byte[] b = new byte[1024];
	private static volatile DatagramPacket packet = new DatagramPacket(b, b.length);
	
	private void start() {
		try {
			while (true) {
				socket.receive(packet);
				new UDPServerThread(b, packet, socket, equipmentService, equipmentDataService, sysUserService, companyService).start();
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
				//心跳超时离线的时间（毫秒）
				UDPServerUtil.findExceedTimeRecord(equipmentService, 1000*60*60*24);
			}
		//项目启动延时10秒启动，判断时间（毫秒）
		}, 10000, 1000*60*10);
	}

}
