package com.huatec.service.impl;

import com.huatec.dao.PositionDao;
import com.huatec.domain.Position;
import com.huatec.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service("PositionService")
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDao positionDao;

    @Override
    public List<Position> findPositions(Map<String, Object> map) {

        return positionDao.findPositions(map);
    }
  @Override
    public Integer getCount(Map<String, Object> map) {

        return positionDao.getCount(map);
    }
  @Override
    public Integer addPosition(Position position) {

        return positionDao.addPosition(position);
    }
   @Override
    public Integer updatePosition(Position position) {

        return positionDao.updatePosition(position);
    }
    @Override
    public Integer deletePosition(Integer id) {
        Integer flag = null;
        try {
            flag = positionDao.deletePosition(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return flag;
    }

}
