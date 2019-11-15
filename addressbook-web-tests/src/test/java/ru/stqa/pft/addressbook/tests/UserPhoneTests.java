package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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

    assertThat(user.getHomePhone(), equalTo(cleaned(userInfoFromEditForm.getHomePhone())));
    assertThat(user.getWorkPhone(), equalTo(cleaned(userInfoFromEditForm.getWorkPhone())));
    assertThat(user.getMobile(), equalTo(cleaned(userInfoFromEditForm.getMobile())));
  }

  public String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}