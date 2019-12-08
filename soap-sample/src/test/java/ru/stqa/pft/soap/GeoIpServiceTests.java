package ru.stqa.pft.soap;

import com.lavasoft.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {
  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("91.227.191.159");
    assertEquals(ipLocation.isEmpty(), false);
  }

  @Test
  public void testInvalidIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("194.28.29.xxx");
    assertEquals(ipLocation.endsWith("152"), false);
  }
}