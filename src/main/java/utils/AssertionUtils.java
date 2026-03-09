package utils;

import org.testng.Assert;

public class AssertionUtils {
    public static void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    public static void assertNotNull(Object object, String message) {
        Assert.assertNotNull(object, message);
    }

}
