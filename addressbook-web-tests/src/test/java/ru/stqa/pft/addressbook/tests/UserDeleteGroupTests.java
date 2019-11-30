package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

public class UserDeleteGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().users().size() == 0) {
      app.user().create(new UserData().withFirstname("Ivan").withLastname("Ivan2").withNickname("Ivan3").withCompany("Home").withAddress("Moscow").withMobile("89078922456").withHomePhone("89078922457").withWorkPhone("89078922458").withEmail("w@ww.ww").withEmail2("e@e.e").withEmail3("q@q.q"));
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test111").withFooter("footer1").withHeader("header1"));
    }
  }

  @Test
  public void testUserDeleteGroup() {
    app.goTo().userPage();
    Groups groupsbefore = app.db().groups();
    Users usersbefore = app.db().users();
    UserData removedUser = usersbefore.iterator().next();

    for (UserData removedUser1 : usersbefore) {
      Groups userGroups = removedUser1.getGroups();
      if (userGroups.size() != 0) {
        removedUser = removedUser1;
        break;
      }
    }

    if (removedUser.getGroups().size() == 0) {
      GroupData groupToAdd = groupsbefore.iterator().next();
      app.user().addToGroup(removedUser, groupToAdd);
    }

    int beforeid = removedUser.getId();
    Users allusersbefore = app.db().users();
    UserData userbefore = null;

    for (UserData userbefore1 : allusersbefore) {
      if (userbefore1.getId() == beforeid) {
        userbefore = userbefore1;
        break;
      }
    }
    Groups before = userbefore.getGroups();
    GroupData grouptoremove = before.iterator().next();
    app.user().delFromGroup(removedUser, grouptoremove);

    Users allusersafter = app.db().users();
    UserData userafter = null;

    for (UserData userafter1 : allusersafter) {
      if (userafter1.getId() == beforeid) {
        userafter = userafter1;
        break;
      }
    }

    assert (userbefore.equals(userafter));
  }
}