package hnu.yhc.populator;

import hnu.yhc.entity.Id;
import hnu.yhc.entity.IdMeta;
import hnu.yhc.utils.TimeUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockIdPopulator implements IdPopulator {
    private long sequence=0;
    private long lastTimestamp=-1;
    private Lock lock=new ReentrantLock();

    public LockIdPopulator() {
        super();
    }

    @Override
    public void populateId(Id id, IdMeta idMeta) {
        lock.lock();
        try {
            long timeStamp= TimeUtils.genTime();
            TimeUtils.validateTime(lastTimestamp,timeStamp);
            if(timeStamp==lastTimestamp){
                sequence++;
                sequence &=idMeta.getSeqBitsMask();
                if(sequence==0)
                    timeStamp=TimeUtils.tillNextTimeUnit(lastTimestamp);
            }
            else{
                lastTimestamp=timeStamp;
                sequence=0;
            }
            id.setSeq(sequence);
            id.setTime(timeStamp);
        }finally {
            lock.unlock();
        }
    }
}
