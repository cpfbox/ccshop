package com.cpf.ccshop.service;

import com.cpf.ccshop.common.dto.Page;
import com.cpf.ccshop.common.dto.Result;
import com.cpf.ccshop.pojo.po.TbItem;
import com.cpf.ccshop.pojo.vo.TbItemCustom;

import java.util.List;

public interface ItemService {

    TbItem getById(Long id);

    //List<TbItem> listItems();

    Result<TbItemCustom> listItemByPage(Page page);
}
