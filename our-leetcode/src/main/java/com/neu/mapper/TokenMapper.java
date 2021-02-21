package com.neu.mapper;

import org.springframework.stereotype.Repository;
/**
 * @author: treblez
 * @className: TokenMapper
 * @description: 处理token验证
 * @data: 2020-04-05
 **/

@Repository
public interface TokenMapper {
    boolean checkToken(String token);
}
