package com.cduestc.dorm.Service;

import com.cduestc.dorm.entity.ChangeApply;
import com.cduestc.dorm.entity.ChangeApplyPage;
import com.cduestc.dorm.entity.ChangeApplyPaging;

import java.util.List;

public interface ChangeApplyService {
    void insertChangeApply(ChangeApply changeApply);

    void delChangeApply(Integer id);

    void upChangeApply(ChangeApply changeApply);

    void delBatchChangeApply(List<Integer> ids);

    ChangeApplyPaging getChangeApply(ChangeApplyPage changeApplyPage);
}
