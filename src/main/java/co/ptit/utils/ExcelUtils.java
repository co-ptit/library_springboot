package co.ptit.utils;

import org.apache.poi.ss.usermodel.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * project: library_springboot
 * author:  HieuDM
 * date:    8/4/2023
 */

public class ExcelUtils {
    public static final int FONT_SIZE = 12;
    public static final String NAME_CELL = "nameCell";
    public static final String FONT_FAMILY = "Times New Roman";

    private ExcelUtils() {
    }

    public static String readCellContent(Cell cell) {
        String content;

        switch (cell.getCellType()) {
            case STRING:
                content = StrUtil.getString(cell.getStringCellValue());

                break;
            case NUMERIC:
                content = BigDecimal.valueOf(cell.getNumericCellValue()).toString();

                break;
            case BOOLEAN:
                content = cell.getBooleanCellValue() + StringPool.BLANK;

                break;
            case FORMULA:
                content = cell.getCellFormula() + StringPool.BLANK;

                break;
            default:
                content = StringPool.BLANK;
        }

        return content;
    }

    public static void createCell(Row row, int index, CellStyle style, String message) {
        Cell cell = row.createCell(index, CellType.STRING);
        cell.setCellValue(message);

        cell.setCellStyle(style);

        row.setRowStyle(style);
    }

    public static void createNormalCell(Row row, int index, CellStyle style, String message) {
        Cell cell = row.createCell(index, CellType.STRING);
        cell.setCellValue(message);
        cell.setCellStyle(style);
    }

    public static void modifyValue(Cell cell, String message) {
        CellStyle cellStyle = cell.getCellStyle();
        cell.setCellValue(message.toUpperCase());
        cell.setCellStyle(cellStyle);
    }

    public static CellStyle createBoldBlackWithRotateCellStyle(Workbook workbook) {
        CellStyle cellStyle = createCellStyle(workbook, IndexedColors.BLACK.getIndex(), NAME_CELL);
        cellStyle.setRotation((short) 90);
        return cellStyle;
    }

    public static CellStyle createCellStyle(Workbook workbook, short fontIndex, String type) {
        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();

        font.setColor(fontIndex);

        // set font bold neu la ten cot
        if (Objects.nonNull(type) && type.equals(NAME_CELL)) {
            font.setBold(true);
        } else {
            font.setItalic(true);
        }
        font.setFontHeight((short) (FONT_SIZE * 20));

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        font.setFontName(FONT_FAMILY);
        style.setFont(font);
        style.setWrapText(true);

        return style;
    }

    public static CellStyle createRedCellStyle(Workbook workbook, Boolean isBold) {
        String type = Boolean.TRUE.equals(isBold) ? NAME_CELL : null;
        return createCellStyle(workbook, IndexedColors.RED.getIndex(), type);
    }

    public static CellStyle createBoldBlackCellStyle(Workbook workbook) {
        return createCellStyle(workbook, IndexedColors.BLACK.getIndex(), NAME_CELL);
    }

    public static CellStyle createValueCellStyle(Workbook workbook) {
        return createCellStyle(workbook, IndexedColors.BLACK1.getIndex(), null);
    }

    public static CellStyle createSuccessCellStyle(Workbook workbook) {
        return createCellStyle(workbook, IndexedColors.BLUE.getIndex(), null);
    }

    public static CellStyle createBorderBoldBlackCellStyle(Workbook workbook) {
        return createBorderCellStyle(workbook, IndexedColors.BLACK.getIndex(), BorderStyle.THIN);
    }

    public static CellStyle createBorderCellStyle(
            Workbook workbook, short fontIndex, BorderStyle borderStyle) {
        CellStyle style = workbook.createCellStyle();
        DataFormat fmt = workbook.createDataFormat();
        style.setDataFormat(fmt.getFormat("@")); // set format cell is text
        Font font = workbook.createFont();

        font.setColor(fontIndex);
        font.setItalic(false);

        style.setBorderBottom(borderStyle);
        style.setBorderTop(borderStyle);
        style.setBorderRight(borderStyle);
        style.setBorderLeft(borderStyle);

        font.setFontHeight((short) (FONT_SIZE * 20));

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        font.setFontName(FONT_FAMILY);
        style.setFont(font);
        style.setWrapText(true);

        return style;
    }
}

class StrUtil {

    public static final String EMPTY = "";
    public static final String RETURN_NEW_LINE = "\r\n";
    public static final String NEW_LINE = "\n";
    private StrUtil() {
    }

    public static String getString(String value) {
        return getString(value, EMPTY);
    }

    public static String getString(String value, String defaultValue) {
        return get(value, defaultValue);
    }

    public static String get(String value, String defaultValue) {
        if (value != null) {
            value = value.trim();
            value = replace(value, RETURN_NEW_LINE, NEW_LINE);

            return value;
        }

        return defaultValue;
    }

    /**
     * Replace.
     *
     * @param s      the s
     * @param oldSub the old sub
     * @param newSub the new sub
     * @return the string
     */
    public static String replace(String s, String oldSub, String newSub) {
        return replace(s, oldSub, newSub, 0);
    }

    public static String replace(String s, String oldSub, String newSub, int fromIndex) {

        if (s == null) {
            return null;
        }

        if (oldSub == null || oldSub.equals(EMPTY)) {
            return s;
        }

        if (newSub == null) {
            newSub = EMPTY;
        }

        int y = s.indexOf(oldSub, fromIndex);

        if (y >= 0) {
            StringBuilder sb = new StringBuilder();

            int length = oldSub.length();
            int x = 0;

            while (x <= y) {
                sb.append(s, x, y);
                sb.append(newSub);

                x = y + length;
                y = s.indexOf(oldSub, x);
            }

            sb.append(s.substring(x));

            return sb.toString();
        } else {
            return s;
        }
    }
}