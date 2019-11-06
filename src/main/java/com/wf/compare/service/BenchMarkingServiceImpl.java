package com.wf.compare.service;

import com.wf.compare.bean.BenchMarking;
import com.wf.compare.mapper.BenchMarkingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BenchMarkingServiceImpl implements BenchMarkingService {

    @Resource
    private BenchMarkingMapper benchMarkingMapper;

    public void saveInfo(BenchMarking benchMarking) {
        benchMarkingMapper.saveInfo(benchMarking);
    }

    public List<BenchMarking> getAmountList(Integer year) {
       return benchMarkingMapper.getAmountList(year);
    }
}
