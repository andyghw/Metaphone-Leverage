package com.validity.models.services;

import com.validity.models.domains.Person;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FindDuplicateService {
    private DoubleMetaphone dm;

    public FindDuplicateService(){
        this.dm=new DoubleMetaphone();
        dm.setMaxCodeLen(1000);
    }

    //return the index of duplicate records
    public Set<Integer> findDuplicates(List<Person> persons){
        Set<Integer> set=new HashSet<>();
        //find duplicates one by one
        for(int i=0;i<persons.size();i++){
            for(int j=0;j<persons.size();j++){
                if(i==j){
                    continue;
                }
                //set the boundary as 0.35
                if(compare(persons.get(i),persons.get(j))<0.35){
                    set.add(i);
                    set.add(j);
                }
            }
        }
        return set;
    }

    /*get the diff rate
    * if one attribute is empty, ignore it, and compare others
    * */
    public double compare(Person p1,Person p2){
        double sum=0;
        int count=11;
        //Totally numbers
        if(p1.getZip().equals("")||p2.getZip().equals("")){
            count--;
        }
        else {
            sum += (double)minDistance(p1.getZip(),p2.getZip())/Math.max(p1.getZip().length(),p2.getZip().length());
        }
        if(p1.getPhone().equals("")||p2.getPhone().equals("")){
            count--;
        }
        else {
            sum += (double)minDistance(p1.getPhone(),p2.getPhone())/Math.max(p1.getPhone().length(),p2.getPhone().length());
        }

        //English without spaces
        if(p1.getFirstName().equals("")||p2.getFirstName().equals("")){
            count--;
        }
        else {
            sum+=calculateDistance(p1.getFirstName(),p2.getFirstName());
        }
        if(p1.getLastName().equals("")||p2.getLastName().equals("")){
            count--;
        }
        else {
            sum += calculateDistance(p1.getLastName(), p2.getLastName());
        }
        if(p1.getEmail().equals("")||p2.getEmail().equals("")){
            count--;
        }
        else {
            sum += calculateDistance(p1.getEmail(), p2.getEmail());
        }
        if(p1.getState().equals("")||p2.getState().equals("")){
            count--;
        }
        else {
            sum += calculateDistance(p1.getState(), p2.getState());
        }

        //English with spaces
        if(p1.getCompany().equals("")||p2.getCompany().equals("")){
            count--;
        }
        else {
            sum += calculateDistanceWithSpace(p1.getCompany(), p2.getCompany());
        }
        if(p1.getAddress1().equals("")||p2.getAddress1().equals("")){
            count--;
        }
        else {
            sum += calculateDistanceWithSpace(p1.getAddress1(), p2.getAddress1());
        }
        if(p1.getAddress2().equals("")||p2.getAddress2().equals("")){
            count--;
        }
        else {
            sum += calculateDistanceWithSpace(p1.getAddress2(), p2.getAddress2());
        }
        if(p1.getCity().equals("")||p2.getCity().equals("")){
            count--;
        }
        else {
            sum += calculateDistanceWithSpace(p1.getCity(), p2.getCity());
        }
        if(p1.getState_long().equals("")||p2.getState_long().equals("")){
            count--;
        }
        else {
            sum += calculateDistanceWithSpace(p1.getState_long(), p2.getState_long());
        }
        return sum/count;
    }

    //distance(difference) without subsets
    private double calculateDistance(String s1,String s2){
        //get metaphone values
        String ss1=dm.doubleMetaphone(s1);
        String ss2=dm.doubleMetaphone(s2);
        double dividend =(double)minDistance(ss1,ss2);
        int divisor = Math.max(ss1.length(),ss2.length());
        if(divisor==0){
            return 0;
        }
        return dividend/divisor;
    }

    //Attributes need to consider spaces and subsets
    private double calculateDistanceWithSpace(String s1,String s2){
        List<String> subsets=getSubsets(s1);
        double d=Double.MAX_VALUE;
        for(String s:subsets){
            d=Math.min(d,calculateDistance(s,s2));
        }
        return d;
    }

    //Get all the subsets
    private List<String> getSubsets(String s){
        List<String> list=new ArrayList<>();
        String[] strs=s.split(" ");
        for(int len=1;len<=s.length();len++){
            int start=0;
            int end=start+len;
            while(end<=strs.length){
                StringBuilder tmp=new StringBuilder();
                for(int i=start;i<end;i++){
                    tmp.append(i==end-1?strs[i]:strs[i]+" ");
                }
                list.add(tmp.toString());
                start++;
                end=start+len;
            }
        }
        return list;
    }

    //edit distance
    private int minDistance(String word1, String word2){
        int m=word1.length();
        int n=word2.length();
        int[][] dp=new int[m+1][n+1];
        for(int i=0;i<m+1;i++) dp[i][0]=i;
        for(int i=0;i<n+1;i++) dp[0][i]=i;
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)) dp[i][j]=dp[i-1][j-1];
                else{
                    int min=Math.min(dp[i-1][j]+1,dp[i][j-1]+1);
                    dp[i][j]=Math.min(min,dp[i-1][j-1]+1);
                }
            }
        }
        return dp[m][n];
    }
}
