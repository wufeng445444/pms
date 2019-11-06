package com.wf.compare.service;

import com.wf.compare.bean.BenchMarking;

import java.util.List;

public interface BenchMarkingService {
    void saveInfo(BenchMarking benchMarking);

    List<BenchMarking> getAmountList(Integer year);
}
