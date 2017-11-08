package com.cpf.ccshop.dao;

import com.cpf.ccshop.common.dto.Page;
import com.cpf.ccshop.pojo.po.TbItem;
import com.cpf.ccshop.pojo.vo.TbItemCustom;

import java.util.List;

public interface TbItemCustomMapper {
    /**
     * 查询商品表中所有商品的数量
     * @return
     */
    int countItems();

    List<TbItemCustom> listItemsByPage(Page page);
}
