package com.example.two.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.example.two.dao.TblInventoryDao;
import com.example.two.dao.TblTwoDao;
import com.example.two.entity.TblInventory;
import com.example.two.entity.TblTwo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author yueyi2019
 */
@Service
public class RmTwoService {
	
	@Autowired
	TblTwoDao mapper;

	@Autowired
	TblInventoryDao tblInventoryDao;

	@GlobalTransactional(rollbackFor = Exception.class)
	@Transactional
	public String reduce(int goodId) {
		TblInventory tblInventory = tblInventoryDao.selectByPrimaryKey(goodId);
		tblInventory.setNum(tblInventory.getNum()-1);
		try {
			TimeUnit.SECONDS.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tblInventoryDao.updateByPrimaryKey(tblInventory);

		return "success";
	}
	
	public String updateRm2() {
		TblTwo o = mapper.selectByPrimaryKey(2);
		o.setName(o.getName()+new Random().nextInt(2));
		mapper.updateByPrimaryKeySelective(o);
		return "";
	}
}