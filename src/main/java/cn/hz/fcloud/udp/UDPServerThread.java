package cn.hz.fcloud.udp;

import cn.hz.fcloud.entity.EquipmentData;
import cn.hz.fcloud.service.EquipmentDataService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPServerThread extends Thread {

	private static final Logger logger = LoggerFactory.getLogger(UDPServerThread.class);

	private byte[] b;
	private DatagramPacket p;
	private DatagramSocket socket;
	private EquipmentDataService equipmentDataService;

	public UDPServerThread(byte[] b, DatagramPacket p, DatagramSocket socket, EquipmentDataService equipmentDataService) {
		this.b = b;
		this.p = p;
		this.socket = socket;
		this.equipmentDataService = equipmentDataService;
	}

	@Override
	public void run() {
		String receive = new String(b, 0, p.getLength());
//		System.out.println("receive:  "+receive);
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
//			System.out.println("flag:  "+flag);
//			System.out.println("date:  "+date);
			System.out.println("date:  "+year+"/"+month+"/"+day+" "+hour+":"+minute+":"+seconds);
			
			switch (flag) {
				case "1111":{
					System.out.println("火灾报警");
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", flag);
					jsonObject.put("msg", "火灾报警");
					jsonObject.put("datetime", date);
                    equipmentDataService.addRecord(new EquipmentData(imei, jsonObject.toString(), new Date()));
					break;
				}
				case "3111":
					System.out.println("火灾自动报警恢复");
					{
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("code", flag);
						jsonObject.put("msg", "火灾自动报警恢复");
						jsonObject.put("datetime", date);
                        equipmentDataService.addRecord(new EquipmentData(imei, jsonObject.toString(), new Date()));
					}
					break;
				case "1384":
					System.out.println("电池低电压报警");
					{
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("code", flag);
						jsonObject.put("msg", "电池低电压报警");
						jsonObject.put("datetime", date);
                        equipmentDataService.addRecord(new EquipmentData(imei, jsonObject.toString(), new Date()));
					}
					break;
				case "3384":
					System.out.println("电池低电压恢复");
					{
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("code", flag);
						jsonObject.put("msg", "电池低电压恢复");
						jsonObject.put("datetime", date);
                        equipmentDataService.addRecord(new EquipmentData(imei, jsonObject.toString(), new Date()));
					}
					break;
				case "1601":
					System.out.println("手动测试报告");
					break;
				case "1800":
					System.out.println("手动解除报警");
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
