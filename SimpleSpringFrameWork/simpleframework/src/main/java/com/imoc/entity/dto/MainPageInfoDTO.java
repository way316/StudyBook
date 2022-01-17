package com.imoc.entity.dto;

import com.imoc.entity.bo.HeadLine;
import com.imoc.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

@Data
public class MainPageInfoDTO {
	private List<HeadLine> headLineList;
	private List<ShopCategory> shopCategoryList;
}
