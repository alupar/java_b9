package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserHelper extends BaseHelper {

  public UserHelper(WebDriver wd) {
    super(wd);
  }


  public void submitUserCreation() {
    click(By.name("submit"));
  }

  public void fillUserForm(UserData userData, boolean creation) {
    type(By.name("firstname"), userData.getFirstname());
    type(By.name("middlename"), userData.getMiddlename());
    type(By.name("lastname"), userData.getLastname());
    type(By.name("nickname"), userData.getNickname());
    type(By.name("company"), userData.getCompany());
    type(By.name("email"), userData.getEmail());
    type(By.name("email2"), userData.getEmail2());
    type(By.name("homepage"), userData.getHomepage());
    type(By.name("mobile"), userData.getMobile());
    click(By.name("work"));
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getNew_group());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void initUserCreation() {
    click(By.linkText("add new"));
  }

  public void initUserModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitUserModification() {
    click(By.name("update"));
  }

  public void CloseAlert() {
    wd.switchTo().alert().accept();
  }

  public void deleteSelectedUsers() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public boolean isThereAUser() {
    return isElementPresent(By.name("selected[]"));
  }

  public void selectUser(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void createUser(UserData user, boolean creation) {
    initUserCreation();
    fillUserForm(user, creation);
    submitUserCreation();
  }

  public void editUser(UserData user, boolean creation) {
    initUserModification();
    fillUserForm(user, creation);
    submitUserModification();
  }

  public int getUserCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<UserData> getUserList() {
    List<UserData> users = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String name = element.getText();
      UserData user = new UserData("Сергей1", "Иванович", "Иванов", "ivanov", "work", "lol@lol.ru", "test@test.ru", "localhost", "+79991112233", null);
      users.add(user);
    }
    return users;
  }
}
