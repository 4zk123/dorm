package com.cduestc.dorm.Service;

import com.cduestc.dorm.entity.*;

import java.util.List;

public interface PreService {
    List<Dorm> take(PreDorms dorms);

    boolean testToStu(String college);

    List<String> getToCollege(String college);

    List<String> getByBuilding(String college, String building);

    Boolean testRoomIn(TestRoomIn testRoomIn);

    int saveRoom(SaveRoomIn saveRoomIn);

    PrePaging getListPre(PrePage prePage);

    void del(String college, String building);
}
