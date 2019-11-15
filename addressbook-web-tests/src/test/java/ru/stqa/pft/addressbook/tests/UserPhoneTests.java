package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;


public class UserPhoneTests extends TestBase{

  @Test
          public void testUserPhones(){
  app.goTo().userPage();
  UserData user = app.user().all().iterator().next();
  UserData userInfoFromEditForm = app.user().infoFromEditForm(user);
}}