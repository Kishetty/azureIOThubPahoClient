package com.ms.azure.iot.data;

/**
 * Created by kishetty on 4/1/2016.
 */
public class TempSensorData

{
    private String deviceId;
    private long tempValue;
    private String location;


    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public void setTempValue(long tempValue)
    {
        this.tempValue = tempValue;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }


    public String getDeviceId()
    {
        return deviceId;
    }

    public long getTempValue()
    {
        return tempValue;
    }

    public String getLocation()
    {
        return location;
    }
}

