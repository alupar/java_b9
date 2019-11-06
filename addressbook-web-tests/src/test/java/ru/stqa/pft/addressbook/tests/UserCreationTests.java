package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() {
    List<UserData> before = app.getUserHelper().getUserList();
    UserData user = new UserData("Пётр", "Иванович", "Петров", "petrov", "work", "lol@lol.ru", "test@test.ru", "localhost", "+79991112233", "test321");
    app.getUserHelper().createUser(user, true);
    app.getNavigationHelper().goHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(user);
    Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
