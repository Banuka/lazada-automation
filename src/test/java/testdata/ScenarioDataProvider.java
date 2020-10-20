package testdata;

import org.testng.annotations.DataProvider;

public class ScenarioDataProvider {

    @DataProvider(name = "data_test_case_01")
    public static Object[][] test01_data_provider() {
        return new Object[][]{
                {new String[]{"Electronic Devices","Laptops"},"2-in-1s","2-in-1s"},
                {new String[]{"Electronic Accessories","Audio"},"Portable Speakers","Wireless and Bluetooth Speakers"}
        };
    }



    @DataProvider(name = "data_test_case_02")
    public static Object[][] test02_data_provider() {
        return new Object[][]{
                {new String[]{"Electronic Devices"},new String[]{"Mobiles","Tablets", "Laptops","Desktops Computers","Gaming Consoles","Action/Video Cameras","Security Cameras","Digital Cameras","Gadgets & Drones"}},
                {new String[]{"Electronic Devices","Desktops Computers"},new String[]{"All-In-One","Gaming Desktops","DIY"}},
                {new String[]{"Watches & Bags","Travel"},new String[]{"Luggage","Travel Accessories","Travel Bags"}}
        };
    }

}
