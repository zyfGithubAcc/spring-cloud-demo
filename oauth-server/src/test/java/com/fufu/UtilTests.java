package com.fufu;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

public class UtilTests {
    @Test
    public void jasyptTest() {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        // application.yml配置的jasypt.encryptor.password
        encryptor.setPassword("123456");
        // 对username进行加密操作
        System.out.println(encryptor.encrypt("root"));
        // 对password进行加密操作
        System.out.println(encryptor.encrypt("orcl"));
        // 进行解密操作
        System.out.println(encryptor.decrypt("CqUJIaDsOoGpSHmmsbkagA=="));
    }
}
