package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {
    int before = app.getUserHelper().getUserCount();
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Сергей1", "Иванович", "Иванов", "ivanov", "work", "lol@lol.ru", "test@test.ru", "localhost", "+79991112233", "test321"), true);
      app.getNavigationHelper().goHomePage();
    }
    app.getUserHelper().selectUser(before - 1);
    app.getUserHelper().deleteSelectedUsers();
    app.getUserHelper().CloseAlert();
    app.getNavigationHelper().goHomePage();
    int after = app.getUserHelper().getUserCount();
    Assert.assertEquals(after, before - 1);
  }
}
