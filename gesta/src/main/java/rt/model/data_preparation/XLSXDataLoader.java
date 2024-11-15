package rt.model.data_preparation;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class XLSXDataLoader {

    public void loadData(String path) {
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(path))) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // пропускаю первую строку, т.к. она для заголовков
                Row row = sheet.getRow(i);
                int size = row.getPhysicalNumberOfCells();
                String[] data = new String[size];
                for (int j = 0; j < size; j++) {
                    data[j] = row.getCell(j).getStringCellValue();
                }
                send(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected abstract void send(String[] data);
}