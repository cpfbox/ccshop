package com.cpf.ccshop.service;

import com.cpf.ccshop.pojo.po.TbItem;

import java.util.List;

public interface ItemService {

    TbItem getById(Long id);

    List<TbItem> listItems();
}
