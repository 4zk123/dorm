package com.cduestc.dorm.Service.Impl;

import com.cduestc.dorm.Mapper.OtherApplyMapper;
import com.cduestc.dorm.Service.OtherApplyService;
import com.cduestc.dorm.entity.OtherApply;
import com.cduestc.dorm.entity.OtherApplyPage;
import com.cduestc.dorm.entity.OtherApplyPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherApplyServiceImpl implements OtherApplyService {
    @Autowired
    private OtherApplyMapper otherApplyMapper;

    @Override
    public void insertOtherApply(OtherApply otherApply) {
        otherApplyMapper.insertOtherApply(otherApply);
    }

    @Override
    public OtherApplyPaging getListOtherApply(OtherApplyPage otherApplyPage) {
        Integer pageNum = otherApplyPage.getPageNum();
        Integer pageSize = otherApplyPage.getPageSize();
        pageNum = (pageNum - 1) * pageSize;
        otherApplyPage.setPageNum(pageNum);
        List<OtherApply> otherApplies = otherApplyMapper.getListOtherApply(otherApplyPage);
        int total = otherApplyMapper.getListOtherApplyTotal(otherApplyPage);
        OtherApplyPaging otherApplyPaging = new OtherApplyPaging(otherApplies,total);
        return otherApplyPaging;
    }

    @Override
    public void updateOtherApply(OtherApply otherApply) {
        otherApplyMapper.updateOtherApply(otherApply);
    }

    @Override
    public void delOtherApply(Integer id) {
        otherApplyMapper.delOtherApply(id);
    }

    @Override
    public void delBatchOtherApply(List<Integer> ids) {
        otherApplyMapper.delBatchOtherApply(ids);
    }
}
