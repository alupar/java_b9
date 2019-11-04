package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Сергей1", "Иванович", "Иванов", "ivanov", "work", "lol@lol.ru", "test@test.ru", "localhost", "+79991112233", "test321"), true);
      app.getNavigationHelper().goHomePage();
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().selectUser(before.size() - 1);
    app.getUserHelper().deleteSelectedUsers();
    app.getUserHelper().CloseAlert();
    app.getNavigationHelper().goHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
