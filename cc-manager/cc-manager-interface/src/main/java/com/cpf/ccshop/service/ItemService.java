package com.cpf.ccshop.service;

import com.cpf.ccshop.common.dto.Order;
import com.cpf.ccshop.common.dto.Page;
import com.cpf.ccshop.common.dto.Result;
import com.cpf.ccshop.pojo.po.TbItem;
import com.cpf.ccshop.pojo.vo.TbItemCustom;
import com.cpf.ccshop.pojo.vo.TbItemQuery;

import java.util.List;

public interface ItemService {

    TbItem getById(Long id);

    //List<TbItem> listItems();

    Result<TbItemCustom> listItemByPage(Page page, Order order, TbItemQuery query);

    int updateBatch(List<Long> ids, byte status);

    int saveItem(TbItem tbItem, String content);
}
