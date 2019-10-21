package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
  app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectCheckbox();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("ред test11", "ред test12", "ред test13"));
app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }}
