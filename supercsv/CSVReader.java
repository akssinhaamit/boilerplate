import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amits on 04/04/2016.
 */
public class CSVReader {
    public static <T> List<T> readWithCsvBeanReader(final String fileLoc, final String fileName, Class<T> klazz, CellProcessor[] processor) throws Exception {
        java.nio.file.Path outputPath = FileSystems.getDefault().getPath(fileLoc, fileName);
        CsvBeanReader beanReader = null;
        List<T> objects = new ArrayList<T>();
        String in = fileName;
        try {
            beanReader = new CsvBeanReader(new FileReader(outputPath.toFile()), CsvPreference.STANDARD_PREFERENCE);
            final String[] header = beanReader.getHeader(true);
            int count = 0;
            T obj;
            while ((obj = beanReader.read(klazz, header, processor)) != null) {
                count++;
                objects.add(obj);
            }
            return objects;
        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }
    }
}