package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.persistence.NoResultException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Ivan", "Petrov", "petrov@gmail.com");
      User user2 = new User("Hariton", "Ivanov", "ivanov@outlook.com");
      User user3 = new User("Roman", "Sidorov", "sidorov@gmail.com");
      User user4 = new User("Serg", "Goreliy", "goreliy@outlook.com");

      Car car1 = new Car("Chevrolet", 2021);
      Car car2 = new Car("BMV", 5);
      Car car3 = new Car("Audi", 7);
      Car car4 = new Car("Hover", 290);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));


      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println("1. _____________________________________________");
      }


      System.out.println(userService.getUserByCar("BMV", 5));
      System.out.println("2. _____________________________________________");


      try {
         User notFoundUser = userService.getUserByCar("Broom", 90);
      } catch (NoResultException e) {
         System.out.println("User not found");
         System.out.println("3. _____________________________________________");
      }

      context.close();


   }
}