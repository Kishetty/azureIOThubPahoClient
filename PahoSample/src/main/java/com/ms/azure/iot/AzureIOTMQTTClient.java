package com.ms.azure.iot;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

import com.google.gson.Gson;
import com.ms.azure.iot.data.TempSensorData;
import com.ms.azure.iot.data.sender.MqttDataSender;

public class AzureIOTMQTTClient {
	
	//Give your connection string
	private static String connString = "HostName=xxxx.azure-devices.net;DeviceId=myfirstjavadevice;SharedAccessKey=2ETLSMVCfwYGpPVhqOmA/w==";
	private static String deviceId = "myfirstjavadevice";
	
    public static void main( String[] args ) throws URISyntaxException, InterruptedException, IOException
    {
        System.out.println( "Device Injestion - Single Device" );
		
		MqttDataSender deviceThread= new MqttDataSender(deviceId,  connString);
		//Bangalore Temperature Sensor - Summer
		long  minTemp = 18;
		long maxTemp = 45;
		Random random = new Random();

        Gson gson = null;
		//TempSensorData sensorData = null;
		TempSensorData sensorData = null;
		while (true)
		{
			sensorData = new TempSensorData();
			//sensorData.setMsgId( System.currentTimeMillis());
			sensorData.setDeviceId( deviceId );
			sensorData.setLocation( "BAN-EGL" );
			sensorData.setTempValue( getTemperatureValue( random, minTemp, maxTemp));
            gson = new Gson();
          	//deviceThread.setMessage("message. "+msg+" : "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm:ss")));
            deviceThread.setMessage( gson.toJson(sensorData ));
            deviceThread.run();
            Thread.sleep(4000);
		}
    }

	private static long getTemperatureValue( Random random, long min, long max)
	{
		long range = (long)max- (long)min + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long)(range * random.nextDouble());
		return fraction + min;
	}

}
