package org.simpleframework.inject;

import com.imoc.controller.frontend.MainPageController;
import com.imoc.service.combine.impl.HeadLineShopCategoryCombineServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.BeanContainer;

public class DependencyInjectorTest {
	@DisplayName("依赖注入doIoc")
	@Test
	public void doIocTest(){
		BeanContainer beanContainer = BeanContainer.getInstance();
		beanContainer.loadBeans("com.imoc");
		Assertions.assertEquals(true, beanContainer.isLoaded());
		MainPageController mainPageController = (MainPageController)beanContainer.getBean(MainPageController.class);
		Assertions.assertEquals(true, mainPageController instanceof MainPageController);
		Assertions.assertEquals(null, mainPageController.getHeadLineShopCategoryCombineService());
		new DependencyInjector().doIoc();
		Assertions.assertNotEquals(null, mainPageController.getHeadLineShopCategoryCombineService());
		Assertions.assertEquals(true, mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl);
		//Assertions.assertEquals(false, mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl2);
	}
}
