package co.ptit.service.impl;

import co.ptit.domain.dto.response.UsersResponseDto;
import co.ptit.exception.ValidateCommonException;
import co.ptit.service.ExcelService;
import co.ptit.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @Override
    public List<UsersResponseDto> readUsersDataFromExcelFile(MultipartFile file, Set<String> phoneNumberList, Set<String> emailList, Set<String> userNameList) {
        List<UsersResponseDto> result = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            if (Objects.isNull(sheet)) {
                throw new IllegalArgumentException(MsgUtil.getMessage("import.users.fail"));
            }

            // trong sheet cột đầu là stt nên bỏ qua
            Set<Integer> excludeColumnIndexes = Set.of(0);
            for (Row row : sheet) {
                if (row.getRowNum() < 3) {       // 3 hàng đầu tiên là tiêu đề nên bỏ qua
                    continue;
                }

                if (Boolean.FALSE.equals(this.isEmptyAllCell(row, excludeColumnIndexes))) {
                    UsersResponseDto usersDto = new UsersResponseDto();
                    for (Cell cell : row) {
                        String value = ExcelUtils.readCellContent(cell);
                        switch (cell.getColumnIndex()) {
                            case 1: // Họ tên
                                InputValidateUtil.validateNotNull("Họ tên", value);
                                usersDto.setFullName(value);
                                break;
                            case 2: // Số điện thoại
                                InputValidateUtil.validatePhoneNumber(value);
                                if (phoneNumberList.contains(value)) {
                                    throw new ValidateCommonException(MsgUtil.getMessage("phone.number.exists", value));
                                }
                                usersDto.setPhoneNumber(value);
                                break;
                            case 3: // Email
                                InputValidateUtil.validateEmail(value);
                                if (emailList.contains(value)) {
                                    throw new ValidateCommonException(MsgUtil.getMessage("email.exists", value));
                                }
                                usersDto.setEmail(value);
                                break;
                            case 4: // Địa chỉ
                                InputValidateUtil.validateNotNull("Địa chỉ", value);
                                usersDto.setAddress(value);
                                break;
                            case 5: // Tên đăng nhập
                                InputValidateUtil.validateNotNull("Tên đăng nhập", value);
                                if (userNameList.contains(value)) {
                                    throw new ValidateCommonException(MsgUtil.getMessage("user.name.exists", value));
                                }
                                usersDto.setUserName(value);
                                break;
                            case 6: // Ngày hoạt động
                                InputValidateUtil.validateNotNull("Ngày hoạt động", value);
                                if (!DateUtil.isValidLocalDate(value)) {
                                    throw new ValidateCommonException(MsgUtil.getMessage("invalid.date.format", value));
                                }
                                usersDto.setCreateDate(DateUtil.parseToLocalDate(value));
                                break;
                            default:
                                break;
                        }
                    }

                    result.add(usersDto);
                }
            }
        } catch (IOException e) {
            log.error("Error import user {}", e.getMessage());
        }
        return result;
    }

    private boolean isEmptyAllCell(Row checkRow, Set<Integer> excludeColumnIndexes) {
        for (Cell cell : checkRow) {
            int columnIndex = cell.getColumnIndex();
            String value = ExcelUtils.readCellContent(cell);
            if (!StringUtil.isBlank(value) && !excludeColumnIndexes.contains(columnIndex)) {
                return false;
            }
        }
        return true;
    }
}
