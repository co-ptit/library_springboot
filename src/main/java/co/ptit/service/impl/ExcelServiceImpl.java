package co.ptit.service.impl;

import co.ptit.service.ExcelService;
import co.ptit.utils.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * project: library_springboot
 * author:  HieuDM
 * date:    8/4/2023
 */

@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {

    @Value("${templates.excel.folder}")
    private String folder;

    @Override
    public InputStream loadExcelTemplateFromResource(String filename) {
        try {
            return new ClassPathResource(folder + StringPool.FORWARD_SLASH + filename).getInputStream();
        } catch (IOException e) {
            log.error("Error when load file {} in folder {}", filename, this.folder);
        }
        return null;
    }
}
