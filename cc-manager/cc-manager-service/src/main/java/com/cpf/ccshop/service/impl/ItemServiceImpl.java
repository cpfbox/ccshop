package com.cpf.ccshop.service.impl;

import com.cpf.ccshop.common.dto.Page;
import com.cpf.ccshop.common.dto.Result;
import com.cpf.ccshop.dao.TbItemCustomMapper;
import com.cpf.ccshop.dao.TbItemMapper;
import com.cpf.ccshop.pojo.po.TbItem;
import com.cpf.ccshop.pojo.vo.TbItemCustom;
import com.cpf.ccshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemMapper itemDao;

    @Autowired
    private TbItemCustomMapper itemCustomDao;

    @Override
    public TbItem getById(Long id) {
        TbItem tbItem = itemDao.selectByPrimaryKey(id);
        return tbItem;
    }

//    @Override
//    public List<TbItem> listItems() {
//        List<TbItem> list = null;
//        try{
//            list = itemDao.selectByExample(null);
//        }catch (Exception e){
//            logger.error(e.getMessage(),e);
//            e.printStackTrace();
//        }
//        return list;
//    }

    @Override
    public Result<TbItemCustom> listItemByPage(Page page) {
        Result<TbItemCustom> result = null;
        try{
            result = new Result<TbItemCustom>();
            int total = itemCustomDao.countItems();
            result.setTotal(total);
            List<TbItemCustom> list = itemCustomDao.listItemsByPage(page);
            result.setRows(list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }
}
