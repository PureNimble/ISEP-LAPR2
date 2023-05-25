//package pt.ipp.isep.dei.esoft.project.repository;
//
//import com.opencsv.CSVReader;
//import pt.ipp.isep.dei.esoft.project.domain.FileReaderClass;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.net.URISyntaxException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FileReaderRepository {

//    private List<FileReaderClass> readFromFile = new ArrayList<>();
//
//
//    public List<FileReaderClass> getDataFromList() throws Exception {
//        return readFromFile;
//    }
//    public void readAndAddToList() throws Exception {
//        readAllLines("C:\\Users\\35193\\Desktop\\PII\\src\\legacyRealStateUSAMoodle.csv");
//        return;
//    }
//    public void readAllLines(String filePath) throws Exception {
//        String line = "";
//        String splitBy = ";";
//        try
//        {
//            BufferedReader br = new BufferedReader(new FileReader(filePath));
//            br.readLine();
//            while ((line = br.readLine()) != null)   //returns a Boolean value
//            {
//                String[] fromFile = line.split(splitBy);    // use comma as separator
//                FileReaderClass fileReaderClass = new FileReaderClass(fromFile[0].trim(),fromFile[1].trim(),fromFile[2].trim(),fromFile[3].trim(),fromFile[4].trim(),fromFile[5].trim(),fromFile[6].trim(),fromFile[7].trim(),fromFile[8].trim(),fromFile[9].trim(),fromFile[10].trim(),fromFile[11].trim(),fromFile[12].trim(),fromFile[13].trim(),fromFile[14].trim(),fromFile[15].trim(),fromFile[16].trim(),fromFile[17].trim(),fromFile[18].trim(),fromFile[19].trim(),fromFile[20].trim(),fromFile[21].trim(),fromFile[22].trim(),fromFile[23].trim(),fromFile[24].trim(),fromFile[25].trim(),fromFile[26].trim(),fromFile[27].trim(),fromFile[28].trim());
//
//                readFromFile.add(fileReaderClass);
//            }
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }
//}
