package org.simpleframework.core;

import com.imoc.controller.DispatcherServlet;
import com.imoc.controller.frontend.MainPageController;
import com.imoc.service.solo.HeadLineService;
import com.imoc.service.solo.impl.HeadLineServiceImpl;
import org.junit.jupiter.api.*;
import org.simpleframework.core.annotation.Controller;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeanContainerTest {
	private static BeanContainer beanContainer;
	@BeforeAll
	static void init() {
		beanContainer = BeanContainer.getInstance();
	}

	@DisplayName("Test Load Beans Function for beanContainer")
	@Order(1)
	@Test
	public void loadBeansTest() {
		Assertions.assertEquals(false,beanContainer.isLoaded());
		beanContainer.loadBeans("com.imoc");
		Assertions.assertEquals(6,beanContainer.size());
		Assertions.assertEquals(true,beanContainer.isLoaded());
	}

	@DisplayName("根据类获取其实例：getBeanTest")
	@Order(2)
	@Test
	public void getBeanTest(){
		MainPageController controller = (MainPageController)beanContainer.getBean(MainPageController.class);
		Assertions.assertEquals(true, controller instanceof MainPageController);
		DispatcherServlet dispatcherServlet = (DispatcherServlet) beanContainer.getBean(DispatcherServlet.class);
		Assertions.assertEquals(null, dispatcherServlet);
	}
	@DisplayName("根据注解获取对应的实例：getClassesByAnnotationTest")
	@Order(3)
	@Test
	public void getClassesByAnnotationTest(){
		Assertions.assertEquals(true, beanContainer.isLoaded());
		Assertions.assertEquals(3, beanContainer.getClassesByAnnotation(Controller.class).size());
	}
	@DisplayName("根据接口获取实现类：getClassesBySuperTest")
	@Order(4)
	@Test
	public void getClassesBySuperTest(){
		Assertions.assertEquals(true, beanContainer.isLoaded());
		Assertions.assertEquals(true, beanContainer.getClassesBySuper(HeadLineService.class).contains(HeadLineServiceImpl.class));
	}
}
