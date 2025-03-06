package com.cduestc.dorm.Service.Impl;

import com.cduestc.dorm.Mapper.BackApplyMapper;
import com.cduestc.dorm.Service.BackApplyService;
import com.cduestc.dorm.entity.BackApply;
import com.cduestc.dorm.entity.BackApplyPage;
import com.cduestc.dorm.entity.BackApplyPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackApplyServiceImpl implements BackApplyService {
    @Autowired
    private BackApplyMapper backApplyMapper;
    @Override
    public BackApplyPaging getBackApplyPage(BackApplyPage backApply) {
        Integer pageNum = backApply.getPageNum();
        Integer pageSize = backApply.getPageSize();
        pageNum = (pageNum - 1) * pageSize;
        backApply.setPageNum(pageNum);
        List<BackApply> backApplies = backApplyMapper.getListBackApply(backApply);
        Integer total = backApplyMapper.getBackApplyTotal(backApply);
        BackApplyPaging backApplyPaging = new BackApplyPaging(backApplies,total);
        return backApplyPaging;
    }

    @Override
    public void insertBackApply(BackApply backApply) {
        backApplyMapper.insertBackApply(backApply);
    }

    @Override
    public void updateBackApply(BackApply backApply) {
        backApplyMapper.updateBackApply(backApply);
    }

    @Override
    public void delBackApplyById(Integer id) {
        backApplyMapper.delBackApplyById(id);
    }

    @Override
    public void delBatchBackApply(List<Integer> ids) {
        backApplyMapper.delBatchBackApply(ids);
    }
}
