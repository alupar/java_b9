package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() {
    int before = app.getUserHelper().getUserCount();
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Иван", "Иванович", "Иванов", "ivanov", "work", "lolo@lol.ru", "test@test.ru", "https://ya.ru", "+79991112233", "test321"), true);
      app.getNavigationHelper().goHomePage();
    }
    app.getUserHelper().editUser(new UserData("Пётр", "Петрович", "Петров", "petrov", "home", "ololo@lolo.ru", "test@test.ru", "https://google.com", "+74441142434", null), false);
    app.getNavigationHelper().goHomePage();
    int after = app.getUserHelper().getUserCount();
    Assert.assertEquals(after, before);
  }
}