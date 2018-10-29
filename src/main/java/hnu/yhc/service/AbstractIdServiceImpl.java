package hnu.yhc.service;

import hnu.yhc.converter.IdConverter;
import hnu.yhc.entity.Id;
import hnu.yhc.entity.IdMeta;
import hnu.yhc.provider.IpMachineIdProvider;
import hnu.yhc.provider.MachineIdProvider;
import hnu.yhc.utils.IpUtils;
import hnu.yhc.utils.TimeUtils;

import java.util.Date;

public abstract class AbstractIdServiceImpl implements IdService {
    protected long machineId=-1;
    protected IdMeta idMeta;
    protected IdConverter idConverter;

    public AbstractIdServiceImpl() {
        this.machineId = new IpMachineIdProvider(IpUtils.getHostIp()).getMachineId();
    }

    public AbstractIdServiceImpl(long machineId) {
        this.machineId = machineId;
    }

    public AbstractIdServiceImpl(MachineIdProvider machineIdProvider) {
        this.machineId = machineIdProvider.getMachineId();
    }

    @Override
    public long getOtherId() {
        Id id=new Id();
        id.setMachine(machineId);
        populateId(id);
        long ret=idConverter.convert(id);
        return ret;
    }

    protected abstract void populateId(Id id);

    public Id expId(long id) {
        return idConverter.convert(id);
    }

    public long makeId(long time, long seq) {
        return makeId(time, seq, machineId);
    }

    public long makeId(long time,long seq,long machine) {
        Id id = new Id(machine, seq, time);
        idConverter = new IdConverter();
        return idConverter.convert(id);
    }

    public Date transTime(final long time) {
        return new Date(time + TimeUtils.START);
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public void setIdConverter(IdConverter idConverter) {
        this.idConverter = idConverter;
    }

    public void setIdMeta(IdMeta idMeta) {
        this.idMeta = idMeta;
    }
}
