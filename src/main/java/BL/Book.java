/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author David
 */
public class Book {
    private String inputName;
    private String text;

    public Book(String inputName, String text) {
        this.inputName = inputName;
        this.text = text;
    }
    
    public HashMap<String,Integer> duplicateWords(){
        List<String> list = Arrays.asList(text.split(" "));
        Set<String> uniqueWords = new HashSet<String>(list);
        
        HashMap<String,Integer> words = new HashMap<String,Integer>();
        
        for (String word : uniqueWords) {
            if(Collections.frequency(list,word) > 1){
                words.put(word, Collections.frequency(list,word));
            }
        }
        return words;
    }
}
