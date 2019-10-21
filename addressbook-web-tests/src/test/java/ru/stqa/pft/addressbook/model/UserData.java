package ru.stqa.pft.addressbook.model;

public class UserData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String company;
  private final String email;
  private final String email2;
  private final String homepage;
  private final String mobile;
  private final String new_group;

  public UserData(String firstname, String middlename, String lastname, String nickname, String company, String email, String email2, String homepage, String mobile, String new_group) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.company = company;
    this.email = email;
    this.email2 = email2;
    this.homepage = homepage;
    this.mobile = mobile;
    this.new_group = new_group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getCompany() {
    return company;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getMobile() {
    return mobile;
  }

  public String getNew_group() {
    return new_group;
  }
}
