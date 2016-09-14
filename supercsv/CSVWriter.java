import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.util.List;

/**
 * Created by amits on 05/04/2016.
 */
public class CSVWriter {
    public static <T> void writeWithCsvBeanReader(final String fileLoc, final String fileName, final String[] header, List<T> object, CellProcessor[] processor) throws Exception{
        java.nio.file.Path outputPath = FileSystems.getDefault().getPath(fileLoc, fileName);
        CsvBeanWriter beanWriter = null;
        try
        {
            beanWriter = new CsvBeanWriter(new FileWriter(outputPath.toFile()), CsvPreference.STANDARD_PREFERENCE);
            beanWriter.writeHeader(header);
            for(Object obj: object){
                beanWriter.write(obj, header, processor);
            }
        }
        finally
        {
            if( beanWriter != null )
            {
                beanWriter.close();
            }
        }
    }
}