package hnu.yhc.populator;

import hnu.yhc.entity.Id;
import hnu.yhc.entity.IdMeta;

public interface IdPopulator {
    void populateId(Id id, IdMeta idMeta);
}
