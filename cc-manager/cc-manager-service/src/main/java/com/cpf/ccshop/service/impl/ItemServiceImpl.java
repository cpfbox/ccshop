package com.cpf.ccshop.service.impl;

import com.cpf.ccshop.common.dto.Order;
import com.cpf.ccshop.common.dto.Page;
import com.cpf.ccshop.common.dto.Result;
import com.cpf.ccshop.common.util.IDUtils;
import com.cpf.ccshop.dao.TbItemCustomMapper;
import com.cpf.ccshop.dao.TbItemDescMapper;
import com.cpf.ccshop.dao.TbItemMapper;
import com.cpf.ccshop.pojo.po.TbItem;
import com.cpf.ccshop.pojo.po.TbItemDesc;
import com.cpf.ccshop.pojo.po.TbItemExample;
import com.cpf.ccshop.pojo.vo.TbItemCustom;
import com.cpf.ccshop.pojo.vo.TbItemQuery;
import com.cpf.ccshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemMapper itemDao;

    @Autowired
    private TbItemCustomMapper itemCustomDao;

    @Autowired
    private TbItemDescMapper itemDescDao;

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
    public int updateBatch(List<Long> ids, byte status) {
        int i=0;
        try {
            TbItem record = new TbItem();
            record.setStatus(status);
            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            i=itemDao.updateByExampleSelective(record,example);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @Transactional
    @Override
    public int saveItem(TbItem tbItem, String content) {
        int i = 0;
        try{
            Long itemId = IDUtils.getItemId();
            tbItem.setId(itemId);
            tbItem.setStatus((byte)2);
            tbItem.setCreated(new Date());
            tbItem.setUpdated(new Date());
            i=itemDao.insert(tbItem);

            TbItemDesc desc = new TbItemDesc();
            desc.setItemId(itemId);
            desc.setItemDesc(content);
            desc.setCreated(new Date());
            desc.setUpdated(new Date());
            i+=itemDescDao.insert(desc);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public Result<TbItemCustom> listItemByPage(Page page, Order order, TbItemQuery query) {
        Result<TbItemCustom> result = null;
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("page",page);
            map.put("order",order);
            map.put("query",query);
            result = new Result<TbItemCustom>();
            int total = itemCustomDao.countItems(map);
            result.setTotal(total);
            List<TbItemCustom> list = itemCustomDao.listItemsByPage(map);
            result.setRows(list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }
//    @Override
//    public Result<TbItemCustom> listItemByPage(Page page, Order order) {
//        Result<TbItemCustom> result = null;
//        try{
//            result = new Result<TbItemCustom>();
//            int total = itemCustomDao.countItems();
//            result.setTotal(total);
//            List<TbItemCustom> list = itemCustomDao.listItemsByPage(page,order);
//            result.setRows(list);
//        }catch (Exception e){
//            logger.error(e.getMessage(),e);
//            e.printStackTrace();
//        }
//        return result;
//    }
}
