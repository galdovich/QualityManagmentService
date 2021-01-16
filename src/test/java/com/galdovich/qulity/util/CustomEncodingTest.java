package com.galdovich.qulity.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CustomEncodingTest {

    @Test
    public void testEncodingTest() {
        String expected = "21232f297a57a5a743894a0e4a801fc3";
        String actual = CustomEncoding.encodePassword("admin");
        assertEquals(expected, actual);
    }
}