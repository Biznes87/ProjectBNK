/*
 Класс для загрузки данных из файла
 */
package com.projectbnk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.nio.charset.Charset;
import java.util.List;


public class LoadSettings {
 
   private String filePath;
   private List<String> list = new ArrayList();
   private static Map <String,String> mp = new HashMap();
   private String[] parts;
   private String key;
   private StringBuffer buffer = new StringBuffer(); 
   private int count=0;
   private Properties settingsFile;
    
   public LoadSettings(InputStream is){
       // this.filePath=pathToFile;
       settingsFile=getProperties(is);
       
    }

    public Properties getSettingsFile() {
        return settingsFile;
    }
    
    public static Map<String,String> getMp(){
        return mp;
    } 
    
    private Properties getProperties(InputStream configFileInput) {
        Properties property = new Properties();
        try {
            property.load(new InputStreamReader(configFileInput, Charset.forName("Windows-1251")));
            return property;
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл настроек");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void filePrint(){
        try (Stream<String> stream = 
                Files.lines(Paths.get(filePath), Charset.forName("cp1251"))) 
        {
            //list = stream.filter(line->line.contains("=")).collect(Collectors.toList());
             
            stream.forEach(line->{
                
                if(line.contains("=")){ 
                    parts=line.split("=");
                    key = parts[0];
                    mp.put(key, parts[1]);
                    count=0;
                    buffer.setLength(0);
                }else{
                   // System.out.println(key);
                    String value = (String) mp.get(key);
                    if(count == 0){
                        buffer.append(value + "\n");
                       String s ="";
                        buffer.append(line);
                        ++count;
                    }else{
                         buffer.append("\n" + line);
                    }
                   // System.out.println(buffer.toString());
                    mp.replace(key,buffer.toString());
              
                }
            }
            );
            for(String name: mp.keySet()){
                
              //  System.out.println("New entry");
                String keyName =name.toString();
                String valueName = mp.get(name).toString();  
              //  System.out.println(keyName + "\n" + valueName);  
            }
        }catch(IOException e){e.printStackTrace();}
    }
}
   
    
    

