package com.validity.models.services;

import com.csvreader.CsvReader;
import com.validity.models.domains.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    public List<Person> readFile(MultipartFile file, HttpServletRequest request){
        //if file is null, return null
        if(file.isEmpty()){
            return null;
        }
        String sourceName = file.getOriginalFilename();
        String fileType = sourceName.substring(sourceName.lastIndexOf("."));
        //if file is not .csv type, return null
        if (!fileType.toLowerCase().equals(".csv")) {
            return null;
        }
        List<Person> persons=new ArrayList<>();
        //create a tmp directory for uploaded file
        File saveFile = new File(request.getSession().getServletContext().getRealPath("/upload/") + sourceName);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        try {
            //write the uploaded file into tmp directory
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
            out.write(file.getBytes());
            out.flush();
            out.close();
            //Read .csv file
            CsvReader csvReader = new CsvReader(saveFile.getAbsolutePath());
            //skip headers
            csvReader.readHeaders();
            while (csvReader.readRecord()){
                Person person=new Person();
                person.setId(csvReader.get("id"));
                person.setFirstName(csvReader.get("first_name"));
                person.setLastName(csvReader.get("last_name"));
                person.setCompany(csvReader.get("company"));
                person.setEmail(csvReader.get("email"));
                person.setAddress1(csvReader.get("address1"));
                person.setAddress2(csvReader.get("address2"));
                person.setZip(csvReader.get("zip"));
                person.setCity(csvReader.get("city"));
                person.setState_long(csvReader.get("state_long"));
                person.setState(csvReader.get("state"));
                person.setPhone(csvReader.get("phone"));
                persons.add(person);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return persons;
    }
}
