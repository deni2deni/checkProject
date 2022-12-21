package com.checkproject.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CheckSaverUtil {

    @Value("${check.folder}")
    private String path;

    public void saveToTxt(String check) throws IOException {
        var file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        try (var out = new PrintWriter(new File(path, "check" + System.currentTimeMillis() + ".txt"))) {
            out.println(check);
        }
    }
}
