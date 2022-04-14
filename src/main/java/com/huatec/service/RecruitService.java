package com.huatec.service;

import com.huatec.domain.Recruit;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2022-04-12 15:02
 */
public interface RecruitService {
    List<Recruit> findRecruits(Map<String, Object> map);

    Integer getCount(Map<String, Object> map);
}
