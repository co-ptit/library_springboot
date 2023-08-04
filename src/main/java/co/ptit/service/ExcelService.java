package co.ptit.service;

import co.ptit.domain.dto.response.UsersResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * project: library_springboot
 * author:  HieuDM
 * date:    8/4/2023
 */

public interface ExcelService {

    InputStream loadExcelTemplateFromResource(String filename);

    List<UsersResponseDto> readUsersDataFromExcelFile(MultipartFile file, Set<String> phoneNumberList, Set<String> emailList, Set<String> userNameList);
}
