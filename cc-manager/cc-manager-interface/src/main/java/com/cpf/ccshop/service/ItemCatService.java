package com.cpf.ccshop.service;

import com.cpf.ccshop.common.dto.TreeNode;

import java.util.List;

public interface ItemCatService {
    List<TreeNode> listItemCatsByPid(Long parentId);
}
