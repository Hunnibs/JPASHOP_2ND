package jpabook_2nd.jpashop_2nd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jpashop2ndApplication {

	public static void main(String[] args) {

		WelcomeMessage welcomeMessage = new WelcomeMessage();
		welcomeMessage.setData("My second project example");
		System.out.println("> welcomeMessage  :" + welcomeMessage.getData());

		SpringApplication.run(Jpashop2ndApplication.class, args);
	}
}
