package org.zhaoyangli.ravenote.service;

import org.springframework.stereotype.Service;
import org.zhaoyangli.ravenote.exception.CustomErrorCodeEnum;
import org.zhaoyangli.ravenote.exception.CustomException;
import org.zhaoyangli.ravenote.mapper.UnitMapper;
import org.zhaoyangli.ravenote.mapper.UserAccountMapper;
import org.zhaoyangli.ravenote.mapper.UserMapper;
import org.zhaoyangli.ravenote.mapper.UserUnitMapper;
import org.zhaoyangli.ravenote.model.Unit;
import org.zhaoyangli.ravenote.model.UserAccount;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {

    @Resource
    private UserUnitMapper userUnitMapper;

    @Resource
    private UnitMapper unitMapper;

    @Resource
    private  UserAccountMapper userAccountMapper;

    public List<String> getUnitIdsByUsername(String username){
        return userUnitMapper.getUnitIdByUsername(username);
    }

    // Check if current user has the access to visit the unit
    public void unitValidationCheck(String username, String unitId){
        List<String> validUnitIds = getUnitIdsByUsername(username);
        if(!validUnitIds.contains(unitId)){
            Unit unit = unitMapper.getUnitByUnitId(unitId);
            if (unit==null){
                throw new CustomException(CustomErrorCodeEnum.UNIT_NOT_FOUND);
            }else {
                throw new CustomException(CustomErrorCodeEnum.HAVE_NO_ACCESS);
            }
        }
    }

    // get the list of all of the unit objects of the units that a user is taking
    public List<Unit> getUnitList(String username){
        List<String> unitIds = getUnitIdsByUsername(username);
        List<Unit> units = new ArrayList<>();
        for (String unitId : unitIds) {
            Unit unit = unitMapper.getUnitByUnitId(unitId);
            units.add(unit);
        }
        return units;
    }

    public Unit getUnitById(String unitId) {
        return unitMapper.getUnitByUnitId(unitId);
    }
}
