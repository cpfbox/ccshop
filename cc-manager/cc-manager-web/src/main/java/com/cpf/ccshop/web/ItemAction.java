package com.cpf.ccshop.web;

import com.cpf.ccshop.common.dto.Order;
import com.cpf.ccshop.common.dto.Page;
import com.cpf.ccshop.common.dto.Result;
import com.cpf.ccshop.pojo.po.TbItem;
import com.cpf.ccshop.pojo.vo.TbItemCustom;
import com.cpf.ccshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Scope("prototype")
public class ItemAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/{id}",method = RequestMethod.GET)
    @ResponseBody
    public TbItem getById(@PathVariable("id") Long id){
//        System.out.println(id);
        TbItem byId = itemService.getById(id);
        return byId;
    }

//    @RequestMapping("/items")
//    @ResponseBody
//    public List<TbItem> listItems(){
//        List<TbItem> list = null;
//        try{
//            list = itemService.listItems();
//        }catch (Exception e){
//            logger.error(e.getMessage(),e);
//            e.printStackTrace();
//        }
//        return list;
//
//    }

    @RequestMapping("/items")
    @ResponseBody
    public Result<TbItemCustom> listItemByPage(Page page,Order order){
        Result<TbItemCustom> list = null;
        try{
            list = itemService.listItemByPage(page,order);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return list;
    }

    @ResponseBody
    @RequestMapping("/items/batch")
    public int updateBatch(@RequestParam("ids[]")List<Long> ids,@RequestParam("status")byte status){
        int i=0;
        try {
            i=itemService.updateBatch(ids,status);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }
}
