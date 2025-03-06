package com.cduestc.dorm.Service.Impl;

import com.cduestc.dorm.Mapper.SchoolApplyMapper;
import com.cduestc.dorm.Service.SchoolApplyService;
import com.cduestc.dorm.entity.ListSchoolApplyPage;
import com.cduestc.dorm.entity.ListSchoolApplyPaging;
import com.cduestc.dorm.entity.SchoolApply;
import com.cduestc.dorm.entity.SchoolApplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchoolApplyServiceImpl implements SchoolApplyService {
    @Autowired
    private SchoolApplyMapper schoolApplyMapper;
    @Override
    public void insertSchoolApply(SchoolApply schoolApply) {
        schoolApply.setCreateTime(LocalDateTime.now());
        schoolApply.setStatus(0);
        schoolApplyMapper.insertSchoolApply(schoolApply);
    }

    @Override
    public List<SchoolApplyDto> getListSchoolApplyById(String studentId) {
        List<SchoolApplyDto> schoolApplyDtos = schoolApplyMapper.getListSchoolApplyById(studentId);
        return schoolApplyDtos;
    }

    @Override
    public ListSchoolApplyPaging getListSchoolApply(ListSchoolApplyPage page) {
        int pageNum = page.getPageNum();
        page.setPageNum((pageNum-1)*page.getPageSize());
        List<SchoolApply> schoolApplies = schoolApplyMapper.getListSchoolApply(page);
        int total = schoolApplyMapper.countSchoolApply(page);
        ListSchoolApplyPaging paging = new ListSchoolApplyPaging(total,schoolApplies);
        return paging;
    }

    @Override
    public void delSchoolApply(Integer id) {
        schoolApplyMapper.delSchoolApply(id);
    }

    @Override
    public void updateSchoolApplyById(SchoolApply schoolApply) {
        schoolApplyMapper.updateSchoolApplyById(schoolApply);
    }

    @Override
    public void delBatchSchoolApply(List<Integer> ids) {
        schoolApplyMapper.delBatchSchoolApply(ids);
    }
}
