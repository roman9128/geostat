package rt.model.data_preparation;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class XLSXDataLoader {

    public final void loadData(String path, boolean titleIsNeeded) {
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(path))) {
            Sheet sheet = workbook.getSheetAt(0);
            if (titleIsNeeded) {
                sendTitle(makeArrayToSend(sheet.getRow(0)));
            }
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // 1 строка для заголовка
                sendData(makeArrayToSend(sheet.getRow(i)));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Object[] makeArrayToSend(Row row) {
        int size = row.getPhysicalNumberOfCells();
        Object[] data = new Object[size];
        for (int j = 0; j < size; j++) {
            switch (row.getCell(j).getCellType()) {
                case NUMERIC -> data[j] = Double.valueOf(row.getCell(j).getNumericCellValue()).longValue();
                default -> data[j] = row.getCell(j).toString();
            }
        }
        return data;
    }

    protected abstract void sendTitle(Object[] title);

    protected abstract void sendData(Object[] data);
}