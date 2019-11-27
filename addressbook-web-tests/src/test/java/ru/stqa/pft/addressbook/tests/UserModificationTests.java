package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().userPage();
    if (app.db().users().size() == 0) {
      app.user().create(new UserData().withFirstname("Сергей1").withMiddlename("Иванович").withLastname("Иванов").withNickname("ivanov").withCompany("Home").withEmail("lol@lol.ru").withEmail2("test@test.ru").withHomepage("localhost").withMobile("+79991112234").withWorkPhone("+7(999)1112234").withHomePhone("8999-111-2234"));
      app.goTo().userPage();
    }
  }

  @Test
  public void testUserModification() {
    Users before = app.db().users();
    UserData modifiedUser = before.iterator().next();
    UserData user = new UserData().withId(modifiedUser.getId()).withFirstname("Сергей2").withMiddlename("Иванович").withLastname("Иванов").withNickname("ivanov").withCompany("Home").withEmail("lol@lol.ru").withEmail2("test@test.ru").withHomepage("localhost").withMobile("+79991112234");

    app.user().modify(user);
    app.goTo().userPage();
    Users after = app.db().users();
    Assert.assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
  }


}