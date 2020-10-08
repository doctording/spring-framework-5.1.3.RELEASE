# SpringBoot

<a href="https://docs.spring.io/spring/docs/5.2.8.RELEASE/spring-framework-reference/web.html#spring-web">spring web 官方文档</a>

* 内嵌容器原理

* 零配置

## Spring MVC

Spring Web MVC is the original web framework built on the Servlet API and has been included in the Spring Framework from the very beginning.

* spring mvc处理流程

![](../imgs/springmvc.png)

* 第一步：发起请求到前端控制器(DispatcherServlet)
* 第二步：前端控制器请求HandlerMapping查找 Handler （可以根据xml配置、注解进行查找）
* 第三步：处理器映射器HandlerMapping向前端控制器返回Handler，HandlerMapping会把请求映射为HandlerExecutionChain对象（包含一个Handler处理器（页面控制器）对象，多个HandlerInterceptor拦截器对象），通过这种策略模式，很容易添加新的映射策略
* 第四步：前端控制器调用处理器适配器去执行Handler
* 第五步：处理器适配器HandlerAdapter将会根据适配的结果去执行Handler
* 第六步：Handler执行完成给适配器返回ModelAndView
* 第七步：处理器适配器向前端控制器返回ModelAndView （ModelAndView是springmvc框架的一个底层对象，包括 Model和view）
* 第八步：前端控制器请求视图解析器去进行视图解析 （根据逻辑视图名解析成真正的视图(jsp)），通过这种策略很容易更换其他视图技术，只需要更改视图解析器即可
* 第九步：视图解析器向前端控制器返回View
* 第十步：前端控制器进行视图渲染 （视图渲染将模型数据(在ModelAndView对象中)填充到request域）
* 第十一步：前端控制器向用户响应结果


## 请求是怎么由@Controller的方法处理的?

### @Controller处理架构图

![](../imgs/controller.png)

### debug打点,访问`http://localhost:8098/boot/json.do`

```java
@Controller
public class TestController {

	private static final Log logger = LogFactory.getLog(TestController.class);

	@GetMapping("/test.do")
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response){
		String uri = request.getRequestURI();
		logger.info("TestController test");
		return uri;
	}

	@GetMapping("/json.do")
	@ResponseBody
	public Map<String, String> getJson(HttpServletRequest request, HttpServletResponse response){
		Map<String,String> mp = new HashMap<>(2);
		mp.put("key", "val");
		return mp;
	}
}
```

#### 请求统一交给`DispatchServlet`分发处理

![](../imgs/doDispatch.png)

#### 从Spring加载好的`HandlerExecutionChain`找到对应的Handler的适配器`HandlerAdapter`处理请求

![](../imgs/handler.png)

spring mvc中，在 web.xml 文件中配置 url 映射配置，通俗来讲就是请求的 URL 怎么能被 SpringMVC 识别，从而去执行我们编写好的 Handler；Spring boot中加载了`@Controller`下各个Mapping注解bean

#### `invokeHandlerMethod(request, response, handlerMethod)`完成处理

![](../imgs/invoke.png)

#### 仍然是利用`反射`实现`@RequestMapping`方法的调用

debug会走到如下的一行代码,即一个常见jdk的`InvocationHandler`反射调用代码

```java
getBridgedMethod().invoke(getBean(), args);
```

![](../imgs/invoke2.png)

返回结果交给了`returnValueHandlers`

最后仍然是`HTTPServletResponse`处理返回，并应用`Converters`操作
