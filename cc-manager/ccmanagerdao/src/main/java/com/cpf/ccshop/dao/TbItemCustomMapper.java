package com.cpf.ccshop.dao;

import com.cpf.ccshop.common.dto.Order;
import com.cpf.ccshop.common.dto.Page;
import com.cpf.ccshop.pojo.po.TbItem;
import com.cpf.ccshop.pojo.vo.TbItemCustom;
import com.cpf.ccshop.pojo.vo.TbItemQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbItemCustomMapper {
    /**
     * 查询商品表中所有商品的数量
     * @return
     */
    int countItems(Map<String,Object> map);

    List<TbItemCustom> listItemsByPage(Map<String,Object> map);
}
