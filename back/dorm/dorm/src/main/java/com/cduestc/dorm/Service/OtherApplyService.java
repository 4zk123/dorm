package com.cduestc.dorm.Service;

import com.cduestc.dorm.entity.OtherApply;
import com.cduestc.dorm.entity.OtherApplyPage;
import com.cduestc.dorm.entity.OtherApplyPaging;

import java.util.List;

public interface OtherApplyService {
    void insertOtherApply(OtherApply otherApply);

    OtherApplyPaging getListOtherApply(OtherApplyPage otherApplyPage);

    void updateOtherApply(OtherApply otherApply);

    void delOtherApply(Integer id);

    void delBatchOtherApply(List<Integer> ids);
}
