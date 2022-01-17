package com.imoc.controller.frontend;

import com.imoc.entity.dto.MainPageInfoDTO;
import com.imoc.entity.dto.Result;
import com.imoc.service.combine.HeadLineShopCategoryCombineService;
import lombok.Getter;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Getter
public class MainPageController {
	@Autowired
	private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;
	public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp){
		return headLineShopCategoryCombineService.getMainPageInfo();
	}
}
