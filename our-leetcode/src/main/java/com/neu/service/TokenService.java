package com.neu.service;

import com.neu.mapper.DiscussionQueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author: treblez
 * @className: TokenService
 * @description: 处理token验证
 * @data: 2020-04-05
 **/
@Service
public class TokenService {
    @Autowired
    private TokenService tokenService;

    public boolean checkToken(String token){
        return false;
    }
}
