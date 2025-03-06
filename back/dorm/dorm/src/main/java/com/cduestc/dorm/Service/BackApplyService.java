package com.cduestc.dorm.Service;

import com.cduestc.dorm.entity.BackApply;
import com.cduestc.dorm.entity.BackApplyPage;
import com.cduestc.dorm.entity.BackApplyPaging;

import java.util.List;

public interface BackApplyService {
    BackApplyPaging getBackApplyPage(BackApplyPage backApply);

    void insertBackApply(BackApply backApply);

    void updateBackApply(BackApply backApply);

    void delBackApplyById(Integer id);

    void delBatchBackApply(List<Integer> ids);
}
