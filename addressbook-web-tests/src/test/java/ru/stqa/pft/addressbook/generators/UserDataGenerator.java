package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {
  @Parameter(names = "-c", description = "Group count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;
  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    UserDataGenerator generator = new UserDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<UserData> users = generateUsers(count);
    if (format.equals("csv")) {
      saveAsCSV(users, new File(file));
    } else if (format.equals("xml")) {
      saveAsXML(users, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(users, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<UserData> users, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(users);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXML(List<UserData> users, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.alias("users", UserData.class);
    String xml = xstream.toXML(users);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private static List<UserData> generateUsers(int count) {
    List<UserData> users = new ArrayList<UserData>();
    for (int i = 0; i < count; i++) {
      users.add(new UserData().withFirstname(String.format("Имя %s", i)).withLastname(String.format("Фамилия %s", i)).withMobile(String.format("моб.телефон 8909%s", i)).withNew_group("test321").withAddress(String.format("г. Москва, ул. %s-ая", i)).withEmail(String.format("test%s@test%s.com", i, i)).withEmail2(String.format("lol%s@lol%s.com", i, i)));
    }
    return users;
  }

  private static void saveAsCSV(List<UserData> users, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (UserData user : users) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", user.getFirstname(), user.getLastname(), user.getMobile(), user.getNew_group(), user.getAddress(), user.getEmail(), user.getEmail2()));
      }
    }
  }
}
