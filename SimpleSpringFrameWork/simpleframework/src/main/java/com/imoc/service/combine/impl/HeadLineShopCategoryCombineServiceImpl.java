package com.imoc.service.combine.impl;

import com.imoc.entity.bo.HeadLine;
import com.imoc.entity.bo.ShopCategory;
import com.imoc.entity.dto.MainPageInfoDTO;
import com.imoc.entity.dto.Result;
import com.imoc.service.combine.HeadLineShopCategoryCombineService;
import com.imoc.service.solo.HeadLineService;
import com.imoc.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.inject.annotation.Autowired;

import java.util.List;

@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {
	@Autowired
	private HeadLineService headLineService;
	@Autowired
	private ShopCategoryService shopCategoryService;

	@Override
	public Result<MainPageInfoDTO> getMainPageInfo() {
		// 1. get headline list
		HeadLine headLineCondition = new HeadLine();
		headLineCondition.setEnableStatus(1);
		Result<List<HeadLine>> headLineResult = headLineService.queryHeadLine(headLineCondition,1,4);
		// 2. get shop Category List
		ShopCategory shopCategoryCondition = new ShopCategory();
		Result<List<ShopCategory>> listResult = shopCategoryService.queryShopCategory(shopCategoryCondition, 1, 100);
		//3. Combine headline List and Category List
		Result<MainPageInfoDTO> result = mergeMainInfoResult(headLineResult,listResult);
		return result;
	}

	private Result<MainPageInfoDTO> mergeMainInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> listResult) {
		MainPageInfoDTO mainPageInfoDto = new MainPageInfoDTO();
		return null;
	}
}
