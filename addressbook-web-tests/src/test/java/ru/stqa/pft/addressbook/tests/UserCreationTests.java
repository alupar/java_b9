package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() {
    app.initUserCreation();
    app.fillUserForm(new UserData("Спиридон", "Иванович", "Иванов", "ivanov", "work", "lol@lol.ru", "test@test.ru", "localhost", "+79991112233", "test11"));
    app.submitUserCreation();
    // wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
  }

}
