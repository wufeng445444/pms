package com.wf.info.service;

import com.wf.info.bean.Email;

import com.wf.info.mapper.EmailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailMapper emailMapper;
    public void saveInfo(Email email) {
        emailMapper.insert(email);
    }
}
