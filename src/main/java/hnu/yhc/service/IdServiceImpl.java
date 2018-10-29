package hnu.yhc.service;

import hnu.yhc.entity.Id;
import hnu.yhc.populator.AtomicIdPopulator;
import hnu.yhc.populator.IdPopulator;
import hnu.yhc.populator.LockIdPopulator;
import hnu.yhc.provider.MachineIdProvider;
import hnu.yhc.utils.PropertyUtil;

public class IdServiceImpl extends AbstractIdServiceImpl {
    private IdPopulator idPopulator;

    public IdServiceImpl() {
        super();
        initPopulator();
    }

    public IdServiceImpl(long machineId) {
        super(machineId);
        initPopulator();
    }

    public IdServiceImpl(MachineIdProvider machineIdProvider) {
        super(machineIdProvider);
        initPopulator();
    }

    @Override
    protected void populateId(Id id) {
        idPopulator.populateId(id, this.idMeta);
    }

    public void initPopulator() {
        try {
            int type=Integer.parseInt(PropertyUtil.getProperty("id.gen.type"));
            if(type==2){
                idPopulator=new AtomicIdPopulator();
            }else{
                idPopulator=new LockIdPopulator();
            }
        }catch (Exception e){
            idPopulator=new LockIdPopulator();
        }
    }
}
