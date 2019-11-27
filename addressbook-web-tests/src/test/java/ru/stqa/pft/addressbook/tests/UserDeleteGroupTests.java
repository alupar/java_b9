package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.Set;

public class UserDeleteGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().userPage();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test111").withFooter("footer1").withHeader("header1"));
    }
    if (app.db().users().size() == 0) {
      app.goTo().userPage();
      app.user().create(new UserData().withFirstname("Ivana").withLastname("Ivanova").withNickname("Ivanivan").withAddress("Moscow"));
    }
  }

  @Test
  public void testUserDeleteGroup() {
    Groups groups = app.db().groups();
    Users before = app.db().users();
    UserData deletedUser = before.iterator().next();
    UserData user = new UserData().withId(deletedUser.getId()).inGroup(groups.iterator().next());
    if (user.getGroups().size() == 0) {
      app.user().addGroup(user);
    }
    Set<GroupData> userGroups = (Set<GroupData>) user.getGroups();
    GroupData groupToDel = userGroups.iterator().next();
    GroupData group = new GroupData().withId(groupToDel.getId());
    app.user().delFromGroup(user, group);
  }
}
