package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Сергей1", "Иванович", "Иванов", "ivanov", "work", "lol@lol.ru", "test@test.ru", "localhost", "+79991112233", "test321"), true);
      app.getNavigationHelper().goHomePage();
    }
    app.getUserHelper().selectCheckbox();
    app.getUserHelper().deleteSelectedUsers();
    app.getUserHelper().CloseAlert();

  }
}
