package com.ms.azure.iot.data.sender;

import java.io.IOException;
import java.net.URISyntaxException;

import com.ms.azure.iot.mqtt.DeviceClient;
import com.ms.azure.iot.mqtt.IotHubEventCallback;
import com.ms.azure.iot.mqtt.IotHubStatusCode;
import com.ms.azure.iot.mqtt.Message;

public class MqttDataSender implements Runnable {
	
private String deviceID = null;
private String connString = null;
private DeviceClient iotclient = null;
private String message = null;

	public MqttDataSender(String deviceID, String connString) throws URISyntaxException, IOException
	{
	  this.deviceID = deviceID;
	  this.connString = connString;
	  iotclient = new DeviceClient(this.connString);
	  iotclient.open();
	}

	
	public void run()
	{
		System.out.println("Device: " + this.deviceID +" sending message: \t"+ this.message);
		Message msg = new Message(this.message);
		EventCallback eventCallback = new EventCallback();
		this.iotclient.sendEventAsync(msg, eventCallback,this.deviceID);
		System.out.println("Device: " + this.deviceID +" message sent successfully");
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}

	protected static class EventCallback implements IotHubEventCallback
	{
	    public void execute(IotHubStatusCode status, Object context)
		{
	        String i = (String) context;
	        System.out.println("IoT Hub responded to message for device: "+i
	            + " with status " + status.name());
	    }
	}

}
