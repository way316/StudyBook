package com.imoc.service.solo.impl;

import com.imoc.entity.bo.ShopCategory;
import com.imoc.entity.dto.Result;
import com.imoc.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;

import java.util.List;
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
	@Override
	public Result<Boolean> addShopCategory(ShopCategory shopCategory) {
		return null;
	}

	@Override
	public Result<Boolean> removeShopCategory(int shopCategoryId) {
		return null;
	}

	@Override
	public Result<Boolean> modifyShopCategory(ShopCategory shopCategory) {
		return null;
	}

	@Override
	public Result<ShopCategory> queryShopCategoryById(int shopCategoryId) {
		return null;
	}

	@Override
	public Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize) {
		return null;
	}
}
