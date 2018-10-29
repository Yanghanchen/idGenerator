package hnu.yhc.converter;

import hnu.yhc.entity.Id;
import hnu.yhc.entity.IdMeta;

public class IdConverter{
    private IdMeta idMeta = new IdMeta();

    public long convert(Id id) {
        return doConvert(id);
    }

    protected long doConvert(Id id){
        long ret=0;
        ret |=id.getMachine();
        ret |=id.getSeq()<<idMeta.getSeqBitsStartPos();
        ret |=id.getTime()<<idMeta.getTimeBitsStartPos();
        return ret;
    }

    protected Id doConvert(long id){
        Id ret=new Id();
        ret.setMachine(id&idMeta.getMachineBitsMask());
        ret.setSeq((id>>>idMeta.getSeqBitsStartPos())&idMeta.getSeqBitsMask());
        ret.setTime((id>>>idMeta.getTimeBitsStartPos())&idMeta.getTimeBitsMask());
        return ret;
    }

    public Id convert(long id) {
        return doConvert(id);
    }
}
