package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() {
    int before = app.getUserHelper().getUserCount();
    app.getUserHelper().createUser(new UserData("Сергей1", "Иванович", "Иванов", "ivanov", "work", "lol@lol.ru", "test@test.ru", "localhost", "+79991112233", "test321"), true);
    app.getNavigationHelper().goHomePage();
    int after = app.getUserHelper().getUserCount();
    Assert.assertEquals(after, before + 1);
  }

}
