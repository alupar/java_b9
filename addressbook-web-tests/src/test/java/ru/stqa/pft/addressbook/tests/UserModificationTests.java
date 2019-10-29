package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() {
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Иван", "Иванович", "Иванов", "ivanov", "work", "lolo@lol.ru", "test@test.ru", "https://ya.ru", "+79991112233", "test321"), true);
      app.getNavigationHelper().goHomePage();
    }
    app.getUserHelper().editUser(new UserData("Пётр", "Петрович", "Петров", "petrov", "home", "ololo@lolo.ru", "test@test.ru", "https://google.com", "+74441142434", null), false);

  }
}