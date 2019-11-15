package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserPhoneTests extends TestBase {

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