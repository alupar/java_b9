package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.testng.Assert.assertEquals;

public class UserAddInGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test321").withFooter("footer1").withHeader("header1"));
    }
    if (app.db().users().size() == 0) {
      app.goTo().userPage();
      app.user().create(new UserData().withFirstname("Ivan").withLastname("Ivanov").withNickname("IvanIvan").withAddress("Moscow"));
    }
  }

  @Test
  public void testsAddGroupInUser() {
    Groups groups = app.db().groups();
    Users before = app.db().users();
    app.goTo().userPage();
    UserData addedUser = before.iterator().next();
    UserData user = new UserData().withId(addedUser.getId()).inGroup(groups.iterator().next());
    if (user.getGroups().size() != app.db().groups().size()) {
      app.user().addGroup(user);
      Users after = app.db().users();
      assertEquals(after.size(), before.size());
    }
  }
}