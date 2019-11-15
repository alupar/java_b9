package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserFieldsTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().userPage();
    if (app.user().all().size() == 0) {
      app.user().create(new UserData().withFirstname("Сергей3").withMiddlename("Иванович").withLastname("Иванов3").withNickname("ivanov").withCompany("Home").withEmail("lol@lol.ru").withEmail2("test@test.ru").withEmail3("test3@test3.ru").withHomepage("localhost").withMobile("+7 999 1112234").withHomePhone("+7(999)1112234").withWorkPhone("8999-111-2234").withNew_group("test321"), true);
      app.goTo().userPage();
    }
  }

  @Test
  public void testUserPhones() {
    app.goTo().userPage();
    UserData user = app.user().all().iterator().next();
    UserData userInfoFromEditForm = app.user().infoFromEditForm(user);

    assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEditForm)));

  }

  @Test
  public void testUserEmails() {
    app.goTo().userPage();
    UserData user = app.user().all().iterator().next();
    UserData userInfoFromEditForm = app.user().infoFromEditForm(user);

    assertThat(user.getAllEmails(), equalTo(mergeEmails(userInfoFromEditForm)));
  }

  @Test
  public void testUserAddress() {
    app.goTo().userPage();
    UserData user = app.user().all().iterator().next();
    UserData userInfoFromEditForm = app.user().infoFromEditForm(user);

    assertThat(user.getAddress(), equalTo(userInfoFromEditForm.getAddress()));
  }

  private String mergePhones(UserData user) {
    return Arrays.asList(user.getHomePhone(), user.getMobile(), user.getWorkPhone()).stream().filter((s) -> !s.equals("")).map(UserFieldsTests::cleaned).collect(Collectors.joining("\n"));
  }

  private String mergeEmails(UserData user) {
    return Arrays.asList(user.getEmail(), user.getEmail2(), user.getEmail3()).stream().filter((s) -> !s.equals("")).map(UserFieldsTests::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}