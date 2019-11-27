package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().userPage();
    if (app.db().users().size() == 0) {
      app.user().create(new UserData().withFirstname("Сергей1").withMiddlename("Иванович").withLastname("Иванов").withNickname("ivanov").withCompany("Home").withEmail("lol@lol.ru").withEmail2("test@test.ru").withHomepage("localhost").withMobile("+7 999 1112234").withHomePhone("+7(999)1112234").withWorkPhone("8999-111-2234"));
      app.goTo().userPage();
    }
  }

  @Test
  public void testUserDeletion() {
    Users before = app.db().users();
    UserData deletedUser = before.iterator().next();
    app.user().delete(deletedUser);
    app.goTo().userPage();
    Users after = app.db().users();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(deletedUser);
    Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(deletedUser)));
    Assert.assertEquals(before, after);

  }


}
