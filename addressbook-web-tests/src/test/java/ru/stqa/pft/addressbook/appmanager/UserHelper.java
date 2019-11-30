package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.List;

public class UserHelper extends BaseHelper {

  public UserHelper(WebDriver wd) {
    super(wd);
  }


  public void submitUserCreation() {
    click(By.name("submit"));
  }

  public void fillUserForm(UserData userData) {
    type(By.name("firstname"), userData.getFirstname());
    type(By.name("middlename"), userData.getMiddlename());
    type(By.name("lastname"), userData.getLastname());
    type(By.name("nickname"), userData.getNickname());
    type(By.name("company"), userData.getCompany());
    type(By.name("address"), userData.getAddress());
    type(By.name("email"), userData.getEmail());
    type(By.name("email2"), userData.getEmail2());
    type(By.name("email3"), userData.getEmail3());
    type(By.name("homepage"), userData.getHomepage());
    type(By.name("mobile"), userData.getMobile());
    type(By.name("work"), userData.getWorkPhone());
    type(By.name("home"), userData.getHomePhone());
    //attach(By.name("photo"), userData.getPhoto());

    //  if (creation) {
    //    if (userData.getGroups().size() > 0) {
    //      Assert.assertTrue(userData.getGroups().size() == 1);
    //      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroups().iterator().next().getName());
    //    }
    //  } else {
    //    Assert.assertFalse(isElementPresent(By.name("new_group")));
    //  }
  }


  public void initUserCreation() {
    click(By.linkText("add new"));
  }

  public void initUserModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
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

  public void selectUserById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void create(UserData user) {
    initUserCreation();
    fillUserForm(user);
    submitUserCreation();
  }

  public void delete(UserData userData) {
    selectUserById(userData.getId());
    deleteSelectedUsers();
    CloseAlert();
  }

  public void modify(UserData user) {
    initUserModificationById(user.getId());
    fillUserForm(user);
    submitUserModification();
  }

  public int getUserCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Users all() {
    Users users = new Users();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      users.add(new UserData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
    }
    return users;
  }

  public UserData infoFromEditForm(UserData user) {
    initUserModificationById(user.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new UserData().withId(user.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address).withHomePhone(home).withMobile(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public void addToGroup(UserData user, GroupData groupToAdd) {
    selectUserById(user.getId());
    addSelectedUserToGroup(user, groupToAdd);
  }

  public void addSelectedUserToGroup(UserData user, GroupData group) {
    String myvalue = String.valueOf(group.getId());
    new Select(wd.findElement(By.name("to_group"))).selectByValue(myvalue);
    click(By.xpath("//input[@value='Add to']"));
    System.out.println("Added user " + user.getId() + " to a group " + group.getId() + " " + group.getName());
  }

  public void delFromGroup(UserData user, GroupData group) {
    selectUserById(user.getId());
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    deleteSelectedUserFromGroup(user, group);
    System.out.println("Removed user " + user.getId() + " from a group " + group.getId() + " " + group.getName());
  }

  private void deleteSelectedUserFromGroup(UserData user, GroupData group) {
    click(By.xpath("//input[@name='remove']"));
    wd.findElement(By.cssSelector("div.msgbox"));
  }
}
