package com.cduestc.dorm.Service;

import com.cduestc.dorm.entity.ListSchoolApplyPage;
import com.cduestc.dorm.entity.ListSchoolApplyPaging;
import com.cduestc.dorm.entity.SchoolApply;
import com.cduestc.dorm.entity.SchoolApplyDto;

import java.util.List;

public interface SchoolApplyService {
    void insertSchoolApply(SchoolApply schoolApply);

    List<SchoolApplyDto> getListSchoolApplyById(String studentId);

    ListSchoolApplyPaging getListSchoolApply(ListSchoolApplyPage page);

    void delSchoolApply(Integer id);

    void updateSchoolApplyById(SchoolApply schoolApply);

    void delBatchSchoolApply(List<Integer> ids);
}
