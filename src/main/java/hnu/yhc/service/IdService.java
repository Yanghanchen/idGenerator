package hnu.yhc.service;

import hnu.yhc.entity.Id;
import java.util.Date;

public interface IdService {
    long getOtherId();
    Id expId(long id);
    long makeId(long time,long seq);
    long makeId(long time,long seq,long machine);
    Date transTime(long time);
}
