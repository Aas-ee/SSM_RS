package com.huatec.service.impl;

import com.huatec.dao.RecruitDao;
import com.huatec.domain.Recruit;
import com.huatec.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2022-04-12 15:02
 */
@Transactional
@Service("RecruitService")
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    private RecruitDao recruitDao;

    @Override
    public List<Recruit> findRecruits(Map<String, Object> map) {
        return recruitDao.findRecruits(map);
    }

    @Override
    public Integer getCount(Map<String, Object> map) {
        return recruitDao.getCount(map);
    }
}
