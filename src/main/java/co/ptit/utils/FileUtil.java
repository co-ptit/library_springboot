package co.ptit.utils;

import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * project: library_springboot
 * date:    5/16/2023
 */

@NoArgsConstructor
public class FileUtil {

    public static final String ATTACHMENT_FILE = "attachment;filename=";
    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";

    public static String upload(MultipartFile data, String typePath) {
        try {
            String url = Constant.ROOT_PATH + typePath + data.getOriginalFilename();
            Path path = Paths.get(url);
            Files.write(path, data.getBytes());
            return url;
        } catch (Exception e) {
            throw new IllegalArgumentException(MsgUtil.getMessage("upload.file.error", e.getMessage()));
        }
    }

    public static byte[] download(String url) {
        try {
            Path path = Paths.get(url);
            return Files.readAllBytes(path);
        } catch (Exception e) {
            throw new IllegalArgumentException(MsgUtil.getMessage("download.file.error", e.getMessage()));
        }
    }

    public static String sanitizeFilename(String input) {
        String[] invalidSymbols = {"\\", "/", ":", "*", "?", "\"", "<", ">", "|", "(", ")", "&"};

        for (String currentSymbol : invalidSymbols) {
            input = input.replaceAll("[\\" + currentSymbol + "]", "_");
        }

        return input;
    }

}
