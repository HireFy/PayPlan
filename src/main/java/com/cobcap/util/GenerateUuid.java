package com.cobcap.util;

import java.util.UUID;

public class GenerateUuid {
    public static String getUuid(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
