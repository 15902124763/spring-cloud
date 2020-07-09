package com.eureka;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommonTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("k1","v1");
        System.out.println(map);
        map.put("k1","v1");
        map.put(null,null);
        UUID uuid = UUID.randomUUID();
        System.out.println(map);
    }
}
