
package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() {
    app.getUserHelper().initUserModification();
    app.getUserHelper().fillUserForm(new UserData("Сергей2", "Сергеевич", "Петров", "petrov", "work1", "lol1@lol1.ru", "test1@test.ru", "localhost", "+79991112234", null), false);
    app.getUserHelper().submitUserModification();
    app.getNavigationHelper().goHomePage();
  }
}