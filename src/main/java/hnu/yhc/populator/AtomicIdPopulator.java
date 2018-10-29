package hnu.yhc.populator;

import hnu.yhc.entity.Id;
import hnu.yhc.entity.IdMeta;
import hnu.yhc.utils.TimeUtils;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicIdPopulator implements IdPopulator{
    public class Variant {
        private long sequence=0;
        private long lastTimestamp=-1;
    }
    private AtomicReference<Variant> variant=new AtomicReference<>(new Variant());

    public AtomicIdPopulator() {
        super();
    }

    @Override
    public void populateId(Id id, IdMeta idMeta) {
        Variant oldVariant,newVariant;
        long timeStamp,sequence;
        while(true){
            oldVariant=variant.get();
            timeStamp= TimeUtils.genTime();
            sequence=oldVariant.sequence;
            if(timeStamp==oldVariant.lastTimestamp){
                sequence++;
                sequence&=idMeta.getSeqBitsMask();
                if (sequence==0){
                    timeStamp=TimeUtils.tillNextTimeUnit(oldVariant.lastTimestamp);
                }
            }else {
                sequence=0;
            }
            newVariant=new Variant();
            newVariant.lastTimestamp=timeStamp;
            newVariant.sequence=sequence;
            if(variant.compareAndSet(oldVariant,newVariant)){
                id.setSeq(sequence);
                id.setTime(timeStamp);
                break;
            }
        }
    }
}
