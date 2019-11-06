package com.wf.pro.service;

import com.wf.pro.bean.Attachment;
import com.wf.pro.mapper.AttachmentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Resource
    private AttachmentMapper attachmentMapper;

    public void saveInfo(Attachment atta) {
        attachmentMapper.insert(atta);
    }
}
