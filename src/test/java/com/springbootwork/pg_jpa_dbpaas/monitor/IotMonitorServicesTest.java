package com.springbootwork.pg_jpa_dbpaas.monitor;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

public class IotMonitorServicesTest {

    @Test
    public void testGetJVMInfo() {
        // 创建 IotMonitorServices 对象
        IotMonitorServices iotMonitorServices = new IotMonitorServices();

        // 调用 getJVMInfo 方法获取 JVM 信息
        JSONObject result = iotMonitorServices.getJVMInfo();
        System.out.println("result.toJSONString() = " + result.toJSONString());
        
    }
}
