package hnu.yhc.provider;

import hnu.yhc.utils.IpUtils;

import java.util.HashMap;
import java.util.Map;

public class IpMachineIdProvider implements MachineIdProvider {
    private long machineId;

    private Map<String,Long> ipsMap=new HashMap<>();

    public IpMachineIdProvider(String ips) {
        setIps(ips);
        init();
    }

    public void init(){
        String ip= IpUtils.getHostIp();
        if(ip.trim().length()==0)
            throw new IllegalStateException("Failed to get host IP address.");
        if(!ipsMap.containsKey(ip)){
            throw new IllegalStateException("Failed to configure ID for this host IP address.");
        }
        machineId=ipsMap.get(ip);
    }

    private void setIps(String ips){
        if(ips.trim().length()!=0){
            String[] ipArray=ips.split(",");
            for(int i=0;i<ipArray.length;i++){
                ipsMap.put(ipArray[i], (long) i);
            }
        }
    }

    @Override
    public long getMachineId() {
        return 0;
    }
}
