package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsers() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new UserData().withFirstname("Игорь").withLastname("Кукушкин").withMobile("89090525011").withNew_group("test321")});
    list.add(new Object[]{new UserData().withFirstname("Игорь2").withLastname("Кукушкин 2").withMobile("89090525012").withNew_group("test321")});
    list.add(new Object[]{new UserData().withFirstname("Игорь3").withLastname("Кукушкин 3").withMobile("89090525013").withNew_group("test321")});
    return list.iterator();
  }

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().userPage();
  }

  @Test(dataProvider = "validUsers")
  public void testUserCreation(UserData user) {
    Users before = app.user().all();
    //File photo=new File("src/test/resources/murmur.jpg");
    app.user().create(user, true);
    app.goTo().userPage();
    Users after = app.user().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((g) -> (g).getId()).max().getAsInt()))));
  }

}
