package com.wf.compare.mapper;

import com.wf.compare.bean.BenchMarking;

import java.util.List;

public interface BenchMarkingMapper {
    void saveInfo(BenchMarking benchMarking);

    List<BenchMarking> getAmountList(Integer year);
}
