package org.zhaoyangli.ravenote.service;

import org.springframework.stereotype.Service;
import org.zhaoyangli.ravenote.DTO.CollectionDTO;
import org.zhaoyangli.ravenote.mapper.CollectionMapper;
import org.zhaoyangli.ravenote.model.Collection;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectionService {

    @Resource
    CollectionMapper collectionMapper;

    public void insertCollection(CollectionDTO collectionDTO){

        collectionMapper.insertCollection(collectionDTO);
    }

    public List<Collection> getCollectionsByUsername(String username){

        return collectionMapper.getCollectionsByUsername(username);

    }

    public void removeFromCollection(CollectionDTO collectionDTO) {

        collectionMapper.removeFromCollection(collectionDTO);
    }
}
