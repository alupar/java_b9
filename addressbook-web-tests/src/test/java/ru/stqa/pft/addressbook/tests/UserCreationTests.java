package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsers() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new UserData().withFirstname(split[0]).withLastname(split[1]).withMobile(split[2]).withNew_group(split[3])});
      line = reader.readLine();
    }
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
