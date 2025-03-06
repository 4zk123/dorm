package com.cduestc.dorm.Service.Impl;

import com.cduestc.dorm.Mapper.ChangeApplyMapper;
import com.cduestc.dorm.Service.ChangeApplyService;
import com.cduestc.dorm.entity.ChangeApply;
import com.cduestc.dorm.entity.ChangeApplyPage;
import com.cduestc.dorm.entity.ChangeApplyPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangeApplyServiceImpl implements ChangeApplyService {
    @Autowired
    private ChangeApplyMapper changeApplyMapper;
    @Override
    public void insertChangeApply(ChangeApply changeApply) {
        changeApply.setStatus(0);
        System.out.println(changeApply);
        changeApplyMapper.insertChangeApply(changeApply);
    }

    @Override
    public void delChangeApply(Integer id) {
        changeApplyMapper.delChangeApply(id);
    }

    @Override
    public void upChangeApply(ChangeApply changeApply) {
        changeApplyMapper.upChangeApply(changeApply);
    }

    @Override
    public void delBatchChangeApply(List<Integer> ids) {
        changeApplyMapper.delBatchChangeApply(ids);
    }

    @Override
    public ChangeApplyPaging getChangeApply(ChangeApplyPage changeApplyPage) {
        Integer pageNum = changeApplyPage.getPageNum();
        Integer pageSize = changeApplyPage.getPageSize();
        pageNum = (pageNum - 1) * pageSize;
        changeApplyPage.setPageNum(pageNum);
        List<ChangeApply> changeApplies = changeApplyMapper.getListChangeApply(changeApplyPage);
        Integer total = changeApplyMapper.getTotalChangeApply(changeApplyPage);
        ChangeApplyPaging changeApplyPaging = new ChangeApplyPaging(changeApplies,total);
        return changeApplyPaging;
    }
}
