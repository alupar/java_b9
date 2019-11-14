package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() {
    app.goTo().userPage();
    Users before = app.user().all();
    UserData user = new UserData().withFirstname("Пётр").withMiddlename("Иванович").withLastname("Петров").withNickname("petrov").withCompany("work").withEmail("lol@lol.ru").withEmail2("test@test.ru").withHomepage("localhost").withMobile("+79991112233").withNew_group("test2");
    app.user().create(user, true);
    app.goTo().userPage();
    Users after = app.user().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((g) -> (g).getId()).max().getAsInt()))));
  }

}
