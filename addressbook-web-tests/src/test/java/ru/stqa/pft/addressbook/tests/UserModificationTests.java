package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() {
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Иван", "Иванович", "Иванов", "ivanov", "work", "lolo@lol.ru", "test@test.ru", "https://ya.ru", "+79991112233", "test321"), true);
      app.getNavigationHelper().goHomePage();
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().initUserModification();
    UserData user = new UserData(before.get(before.size() - 1).getId(),"Пётр", "Петрович", "Петров", "petrov", "home", "ololo@lolo.ru", "test@test.ru", "https://google.com", "+74441142434",null);
    app.getUserHelper().fillUserForm(user, false);
    app.getUserHelper().submitUserModification();
    app.getNavigationHelper().goHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(user);
    Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}