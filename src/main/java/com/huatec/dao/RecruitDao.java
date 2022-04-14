package com.huatec.dao;

import com.huatec.domain.Recruit;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2022-04-12 15:04
 */
@Repository
public interface RecruitDao {
    List<Recruit> findRecruits(Map<String, Object> map);

    Integer getCount(Map<String, Object> map);
}
