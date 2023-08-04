package co.ptit.service;

import java.io.InputStream;

/**
 * project: library_springboot
 * author:  HieuDM
 * date:    8/4/2023
 */

public interface ExcelService {

    InputStream loadExcelTemplateFromResource(String filename);
}
