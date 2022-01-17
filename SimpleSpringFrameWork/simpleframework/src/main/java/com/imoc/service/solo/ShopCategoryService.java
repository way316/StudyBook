package com.imoc.service.solo;

import com.imoc.entity.bo.ShopCategory;
import com.imoc.entity.dto.Result;

import java.util.List;

public interface ShopCategoryService {
	Result<Boolean> addShopCategory(ShopCategory shopCategory);
	Result<Boolean> removeShopCategory(int shopCategoryId);
	Result<Boolean> modifyShopCategory(ShopCategory shopCategory);
	Result<ShopCategory> queryShopCategoryById(int shopCategoryId);
	Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize);
}
