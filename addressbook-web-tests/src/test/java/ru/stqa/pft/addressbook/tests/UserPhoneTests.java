package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;


public class UserPhoneTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().userPage();
    if (app.user().all().size() == 0) {
      app.user().create(new UserData().withFirstname("Сергей3").withMiddlename("Иванович").withLastname("Иванов3").withNickname("ivanov").withCompany("Home").withEmail("lol@lol.ru").withEmail2("test@test.ru").withHomepage("localhost").withMobile("+7 999 1112234").withHomePhone("+7(999)1112234").withWorkPhone("8999-111-2234").withNew_group("test321"), true);
      app.goTo().userPage();
    }
  }

  @Test
  public void testUserPhones() {
    app.goTo().userPage();
    UserData user = app.user().all().iterator().next();
    UserData userInfoFromEditForm = app.user().infoFromEditForm(user);
  }

  private String mergePhones(UserData user) {
    return Arrays.asList(user.getHomePhone(), user.getMobile(), user.getWorkPhone()).stream().filter((s) -> !s.equals("")).map(UserPhoneTests::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}