package com.cpf.ccshop.service.impl;

import com.cpf.ccshop.common.dto.TreeNode;
import com.cpf.ccshop.dao.TbItemCatMapper;
import com.cpf.ccshop.pojo.po.TbItemCat;
import com.cpf.ccshop.pojo.po.TbItemCatExample;
import com.cpf.ccshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemCatMapper itemCatDao;

    @Override
    public List<TreeNode> listItemCatsByPid(Long parentId) {
        List<TreeNode> treeNodeList = null;
        try{
            TbItemCatExample example = new TbItemCatExample();
            TbItemCatExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            List<TbItemCat> itemCatList = itemCatDao.selectByExample(example);
            treeNodeList = new ArrayList<TreeNode>();
            for(int i=0;i<itemCatList.size();i++){
                TbItemCat itemCat = itemCatList.get(i);
                TreeNode treeNode = new TreeNode();
                treeNode.setId(itemCat.getId());
                treeNode.setText(itemCat.getName());
                treeNode.setState(itemCat.getIsParent()?"closed":"open");
                treeNodeList.add(treeNode);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return treeNodeList;
    }
}
