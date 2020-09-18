package org.zhaoyangli.ravenote.service;

import org.springframework.stereotype.Service;
import org.zhaoyangli.ravenote.DTO.PageDTO;
import org.zhaoyangli.ravenote.exception.CustomErrorCodeEnum;
import org.zhaoyangli.ravenote.exception.CustomException;
import org.zhaoyangli.ravenote.mapper.NoteMapper;
import org.zhaoyangli.ravenote.mapper.PageMapper;
import org.zhaoyangli.ravenote.model.Page;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PageService {

    @Resource
    private PageMapper pageMapper;

    public Page getPageById(int pageId){
        Page page = pageMapper.getPageById(pageId);
        if (page == null){
            throw new CustomException(CustomErrorCodeEnum.PAGE_NOT_FOUND);
        }
        return page;
    }
}
