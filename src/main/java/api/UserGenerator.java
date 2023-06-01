package api;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
 static Faker faker = new Faker();
 public static User getUserData() {
  final String email = faker.name().firstName() + "@ya.ya";
  final String password = RandomStringUtils.randomAlphabetic(6,10);
  final String name = faker.name().firstName();
  return new User(email, password, name);
 }
}