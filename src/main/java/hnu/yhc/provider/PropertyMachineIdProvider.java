package hnu.yhc.provider;

import hnu.yhc.utils.IpUtils;
import hnu.yhc.utils.PropertyUtil;

public class PropertyMachineIdProvider implements MachineIdProvider{

    @Override
    public long getMachineId() {
        try {
            long machine=Long.parseLong(PropertyUtil.getProperty("id.cur.machine"));
            return machine;
        }catch (Exception e){
            return new IpMachineIdProvider(IpUtils.getHostIp()).getMachineId();
        }
    }
}
