package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;

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
}
