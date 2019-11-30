package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

public class UserAddInGroupTests extends TestBase {

  private GroupData groupToAdd;
  private UserData userToAdd;

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().userPage();
    if (app.db().users().size() == 0) {
      app.user().create(new UserData().withFirstname("Ivan").withLastname("Ivan2").withNickname("Ivan3").withCompany("HomeWork").withAddress("Abakan").withMobile("89078922450").withHomePhone("89078922451").withWorkPhone("89078922452").withEmail("w@ww.ww").withEmail2("e@e.e").withEmail3("q@q.q"));
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test111").withFooter("footer1").withHeader("header1"));

    }

    Users allusers = app.db().users();
    Groups allgroups = app.db().groups();
    groupToAdd = null;

    for (UserData userToAdd1 : allusers) {
      Groups userGroups = userToAdd1.getGroups();
      if (userGroups.size() != allgroups.size()) {
        allgroups.removeAll(userGroups);
        groupToAdd = allgroups.iterator().next();
        userToAdd = userToAdd1;
        break;
      }
    }

    if (groupToAdd == null) {
      UserData newuser = new UserData().withFirstname("Ivan7").withLastname("Ivan8").withNickname("Ivan9").withCompany("Company").withAddress("Omsk").withMobile("89078922455").withHomePhone("89078922454").withWorkPhone("89078922453").withEmail("w@ww.ww").withEmail2("e@e.e").withEmail3("q@q.q");
      app.user().create(newuser);
      Users after = app.db().users();
      newuser.withId(after.stream().mapToInt((g) -> (g).getId()).max().getAsInt());
      userToAdd = newuser;
      groupToAdd = allgroups.iterator().next();
    }
  }

  @Test
  public void testUserAddingToGroup() {
    app.goTo().userPage();
    UserData userbefore = userToAdd;
    Groups usergroupsbefore = userToAdd.getGroups();
    int beforeid = userToAdd.getId();

    app.user().addToGroup(userToAdd, groupToAdd);

    Users allusersafter = app.db().users();
    UserData userafter = null;

    for (UserData userafter1 : allusersafter) {
      if (userafter1.getId() == beforeid) {
        userafter = userafter1;
        break;
      }
    }

    assert (usergroupsbefore.equals(userafter.getGroups().without(groupToAdd)));
    assert (userbefore.equals(userafter));
  }
}