package cn.hz.fcloud.udp;

import cn.hz.fcloud.entity.Equipment;
import cn.hz.fcloud.entity.EquipmentData;
import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.service.EquipmentDataService;
import cn.hz.fcloud.service.EquipmentService;
import cn.hz.fcloud.service.SysUserService;
import cn.hz.fcloud.utils.UDPServerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class UDPServerThread extends Thread {

	private static final Logger logger = LoggerFactory.getLogger(UDPServerThread.class);

	private byte[] b;
	private DatagramPacket p;
	private DatagramSocket socket;
	private EquipmentService equipmentService;
	private EquipmentDataService equipmentDataService;
	private SysUserService sysUserService;
	private CompanyService companyService;
	private Timer timer = new Timer();

	public UDPServerThread(byte[] b, DatagramPacket p, DatagramSocket socket,EquipmentService equipmentService, EquipmentDataService equipmentDataService, SysUserService sysUserService, CompanyService companyService) {
		this.b = b;
		this.p = p;
		this.socket = socket;
		this.equipmentService = equipmentService;
		this.equipmentDataService = equipmentDataService;
		this.sysUserService = sysUserService;
		this.companyService = companyService;
	}

	@Override
	public void run() {
		String receive = new String(b, 0, p.getLength());
		logger.info("receive:  "+receive);
		
		InetAddress address = p.getAddress();
		int port = p.getPort();
		System.out.println(address+":"+port);
		
		String imei = receive.substring(4, 11);
		System.out.println("imei:  "+imei);
		
		byte[] rb = null;
		if (receive.startsWith("FA01")) {
			rb = ("F581"+imei+"0D0A").getBytes();
		}
		if (receive.startsWith("FA02")) {
			equipmentService.updateReportTimeAndOnline(new Equipment(imei, 1, new Date()));
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			String formatTime = sdf.format(new Date());
			rb = ("F582"+imei+formatTime+"0D0A").getBytes();
		}
		if (receive.startsWith("FA03")) {
			String flag = receive.substring(13, 17);
			String date = receive.substring(23, 35);
			String year = receive.substring(23, 25);
			String month = receive.substring(25, 27);
			String day = receive.substring(27, 29);
			String hour = receive.substring(29, 31);
			String minute = receive.substring(31, 33);
			String seconds = receive.substring(33, 35);
			System.out.println("date:  "+year+"/"+month+"/"+day+" "+hour+":"+minute+":"+seconds);
			String msg;
			switch (flag) {
				case "1111":
				    msg = "火灾报警";
					equipmentDataService.addRecord(new EquipmentData(imei, UDPServerUtil.toJsonString(flag, msg, date), 0, new Date()));
					UDPServerUtil.sendMsgIf(sysUserService, companyService, equipmentService, imei, UDPServerUtil.returnHtmlJson(imei, msg, 0, date));
					break;
				case "3111":
				    msg = "火灾自动报警恢复";
					equipmentDataService.addRecord(new EquipmentData(imei, UDPServerUtil.toJsonString(flag, msg, date), 1, new Date()));
					UDPServerUtil.sendMsgIf(sysUserService, companyService, equipmentService, imei, UDPServerUtil.returnHtmlJson(imei, msg, 0, date));
					break;
				case "1384":
				    msg = "电池低电压报警";
					equipmentDataService.addRecord(new EquipmentData(imei, UDPServerUtil.toJsonString(flag, msg, date), 0, new Date()));
					UDPServerUtil.sendMsgIf(sysUserService, companyService, equipmentService, imei, UDPServerUtil.returnHtmlJson(imei, msg, 0, date));
					break;
				case "3384":
				    msg = "电池低电压恢复";
					equipmentDataService.addRecord(new EquipmentData(imei, UDPServerUtil.toJsonString(flag, msg, date), 1, new Date()));
					UDPServerUtil.sendMsgIf(sysUserService, companyService, equipmentService, imei, UDPServerUtil.returnHtmlJson(imei, msg, 0, date));
					break;
				case "1601":
				    msg = "手动测试报告";
					equipmentDataService.addRecord(new EquipmentData(imei, UDPServerUtil.toJsonString(flag, msg, date), 0, new Date()));
					UDPServerUtil.sendMsgIf(sysUserService, companyService, equipmentService, imei, UDPServerUtil.returnHtmlJson(imei, msg, 0, date));
					break;
				case "1800":
				    msg = "手动解除报警";
					equipmentDataService.addRecord(new EquipmentData(imei, UDPServerUtil.toJsonString(flag, msg, date), 1, new Date()));
					UDPServerUtil.sendMsgIf(sysUserService, companyService, equipmentService, imei, UDPServerUtil.returnHtmlJson(imei, msg, 0, date));
					break;
				default:
					break;
			}
			rb = ("F583"+imei+"0D0A").getBytes();
		}
		
		DatagramPacket rpacket = new DatagramPacket(rb, rb.length, address, port);
		try {
			String string = new String(rpacket.getData(), "utf-8");
			System.out.println("return:  "+string);
			socket.send(rpacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println();
	}

}
