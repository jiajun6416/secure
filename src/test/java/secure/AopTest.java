package secure;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jiajun.secure.service.UserService;

public class AopTest {
	
	@Test
	public void test() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"classpath:spring/applicationcontext-main.xml"});
		UserService userService = ctx.getBean(UserService.class);
		userService.delte();
	}
}
