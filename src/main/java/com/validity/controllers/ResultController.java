package com.validity.controllers;

import com.validity.models.domains.Person;
import com.validity.models.services.FileService;
import com.validity.models.services.FindDuplicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
public class ResultController {
    @Autowired
    private FileService fileService;

    @Autowired
    private FindDuplicateService findDuplicateService;

    @RequestMapping(value = "/Upload",method = RequestMethod.POST)
    public ModelAndView getResults(@RequestParam(value = "uploadedFile")MultipartFile file, HttpServletRequest request){
        List<Person> persons=fileService.readFile(file,request);
        if(persons==null){
            return new ModelAndView("redirect:","message","Please have a valid file input.");
        }
        Set<Integer> indexs=findDuplicateService.findDuplicates(persons);
        List<Person> duplicates=new ArrayList<>();
        List<Person> nonDuplicates=new ArrayList<>();
        for(int i=0;i<persons.size();i++){
            if(indexs.contains(i)){
                duplicates.add(persons.get(i));
            }
            else {
                nonDuplicates.add(persons.get(i));
            }
        }
        Collections.sort(duplicates);
        Collections.sort(nonDuplicates);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result");
        modelAndView.addObject("duplicates", duplicates);
        modelAndView.addObject("nonDuplicates", nonDuplicates);
        return modelAndView;
    }
}
