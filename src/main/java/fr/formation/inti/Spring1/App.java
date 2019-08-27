package fr.formation.inti.Spring1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.formation.inti.beans.HelloBean;
import fr.formation.inti.beans.UserService;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("========================================================");

		HelloBean hb = context.getBean("hello", HelloBean.class);

		hb.Hello();

		UserService us = context.getBean("bonjour2", UserService.class);

		us.Service();

		context.close();
	}
}
