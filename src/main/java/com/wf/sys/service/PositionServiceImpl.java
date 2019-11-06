package com.wf.sys.service;

import com.wf.sys.bean.Position;
import com.wf.sys.bean.PositionExample;
import com.wf.sys.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionMapper positionMapper;

    public List<Position> getPositionList() {
        PositionExample example=new PositionExample();
        List<Position> positions = positionMapper.selectByExample(example);
        return  positions;

    }
}
