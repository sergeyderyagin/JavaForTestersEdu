//package ru.stqa.pft.soap;
//
//import org.testng.annotations.Test;
//
//public class GeoIpServiceTest {
//    @Test
//    public void testMyIP(){
//        GeoIP ip = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("93.92.196.22");
//        assertEquals(ip.getCountryCode(),"RUS ");
//    }
//
//    @Test
//    public void testInvalidIP(){
//        GeoIP ip = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("93.92.196.xxx");
//        assertEquals(ip.getReturnCodeDetails(),"Invalid IP address");
//
//    }
//}