package rt.model.data_preparation;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * Класс для загрузки данных из XLSX-файлов
 */
public abstract class XLSXDataLoader {
/**
 * Метод для чтения первого листа таблицы XLSX-файла и собирания его строк (горизонтальных рядов ячеек).
 * Данный метод работает в связке с методом makeArrayToSend, предназначенным для преобразования прочитанных строк в массивы типа String[].
 * Размер массива равен количеству заполненных ячеек соответствующей строки XLSX-файла.
 * Прочитанная строка передаётся в метод формирования массива типа String[] и далее получившийся массив отправляется в класс-наследник данного класса для последующей обработки.
 * Для формирования заголовка отправляется первая строка таблицы.
 * @param path путь к XLSX-файлу
 * @param titleIsNeeded маркер необходимости чтения заголовка таблицы
 */
    protected final void loadData(String path, boolean titleIsNeeded) {
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(path))) {
            Sheet sheet = workbook.getSheetAt(0);
            if (titleIsNeeded) {
                setTitle(makeArrayToSend(sheet.getRow(0)));
            }
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // 1 строка для заголовка
                setData(makeArrayToSend(sheet.getRow(i)));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод для преобразования строки таблицы XLSX-файла в массивы типа String[].
     * В связи с особенностями используемой библиотеки для чтения XLSX-файлов числовые значения преобразуются в String из Long, который формируется из Double.
     * @param row ряд заполненных ячеек
     * @return массив строк String[]
     */

    private String[] makeArrayToSend(Row row) {
        int size = row.getPhysicalNumberOfCells();
        String[] data = new String[size];
        for (int j = 0; j < size; j++) {
            switch (row.getCell(j).getCellType()) {
                case NUMERIC -> data[j] = String.valueOf(Double.valueOf(row.getCell(j).getNumericCellValue()).longValue());
                default -> data[j] = row.getCell(j).toString();
            }
        }
        return data;
    }
/**
 * Метод для установления заголовка, наименования передаваемых данных
 * @param title массив типа String[], содержащий заголовки
 */
    protected abstract void setTitle(String[] title);
/**
 * Метод для передачи считанных данных в класс-наследник данного абстрактного класса.
 * @param data массив типа String[], содержащий данные из строки XLSX-файла.
 */
    protected abstract void setData(String[] data);
}