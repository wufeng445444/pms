package com.wf.sys.service;

import com.wf.sys.bean.Sources;
import com.wf.sys.bean.SourcesExample;
import com.wf.sys.mapper.SourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SourcesServicesImpl implements SourcesServices {

    @Resource
    private SourcesMapper sourcesMapper;

    public List<Sources> getSourcesByPid(int i) {
        SourcesExample sourcesExample=new SourcesExample();
        SourcesExample.Criteria criteria = sourcesExample.createCriteria();
        criteria.andPidEqualTo(i);
        List<Sources> sources = sourcesMapper.selectByExample(sourcesExample);
        return  sources;
    }

    public void saveInfo(Sources source) {
        sourcesMapper.insert(source);
    }

    public Sources getSourcesInfo(Integer sid) {
        return sourcesMapper.selectByPrimaryKey(sid);
    }

    public void updateInfo(Sources sources) {

         sourcesMapper.updateByPrimaryKeySelective(sources);
    }

    public void delete(Integer id) {
        sourcesMapper.deleteByPrimaryKey(id);
    }

    public List<Sources> getSourcesListByEid(Integer eid) {
        List<Sources> sources=sourcesMapper.getSourcesListByEid(eid,1);
        for (Sources source : sources) {
            Integer id = source.getId();
            List<Sources> list = sourcesMapper.getSourcesListByEid(eid, id);
            source.setChildren(list);

        }

    }


}
