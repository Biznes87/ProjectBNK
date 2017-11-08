/*
    Класс для работы с базой данных
 */
package com.projectbnk;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Model {
    
    private BNKSeekInterfacce   mvc;
  //  private WorkWithDB base = new WorkWithDB("bnkbase", "bnkseek");;
    
    private PreparedStatement stmt;
    private Connection connection;
    private LoadSettings loadSettings;
    private String expectedPattern = "EEE MMM d kk:mm:ss yyyy" ;
    private String expectedPattern1 = "yyyy-MM-dd" ;
    private DateFormat format = new SimpleDateFormat(expectedPattern,Locale.ENGLISH);
    private DateFormat format1 = new SimpleDateFormat(expectedPattern1,Locale.ENGLISH);
   
     
    public Model(BNKSeekInterfacce mv){
        
        loadSettings = new LoadSettings(getClass().getClassLoader().getResourceAsStream("Settings.txt" )); 
        System.out.println(this.getClass().getClassLoader().getResource( "Settings.txt" ).getPath());
      //  loadSettings.filePrint(); //загрузка параметров подключения к базе из файла настроек
        this.mvc=mv;
        connToDB(); 
    }

    public void setStmt(PreparedStatement stmt) {
        this.stmt = stmt;
    }

    public PreparedStatement getStmt() {
        return stmt;
    }

    public Connection getConnection() {
        return connection;
    }

    public void defaultTableModel(){ //задает колонки по умолчанию для элемента JTable на главной форме
        
        DefaultTableModel model = (DefaultTableModel) mvc.getjTable2().getModel();
        
        Object[] names = {"real","pzn","uer","rgn","ind","tnp","nnp","adr","rkc","namep","newnum","telef","regn","okpo","dt_izm","ksnp","date_in","date_ch"};
          for(int i =0; i<names.length; i++){
           model.addColumn(names[i]);
         }
           mvc.getjTable2().setModel(model);
    
    }
    
    public void selectJtableResult(String newnumFilter, String pznFilter, String rgnFilter){ //загрузка данных в Jtable из базы данных
              DefaultTableModel model = (DefaultTableModel) mvc.getjTable2().getModel();
                String rgnF = changeNameToDigit("RGN","tb_rgn","NAME",rgnFilter);
                String pznF = changeNameToDigit("PZN","tb_pzn","NAME",pznFilter);
                model.setRowCount(0);
                mvc.getjTable2().setModel(model);
      try{
          stmt=connection.prepareStatement("SELECT bnkseek.REAL, tb_pzn.NAME, tb_uer.UERNAME, tb_rgn.NAME AS 'RGNNAME', bnkseek.IND," + 
                                                "tb_tnp.FULLNAME, bnkseek.NNP, bnkseek.ADR, bnkseek.RKC, bnkseek.NAMEP,"+
                                                "bnkseek.NEWNUM,bnkseek.TELEF, bnkseek.REGN,bnkseek.OKPO, bnkseek.DT_IZM,"+
                                                "bnkseek.KSNP, bnkseek.DATE_IN,bnkseek.DATE_CH "+
                                           "FROM bnkseek "+
                                           "LEFT JOIN tb_pzn ON bnkseek.PZN = tb_pzn.PZN "+
                                           "LEFT JOIN tb_uer ON bnkseek.UER = tb_uer.UER "+
                                           "LEFT JOIN tb_rgn ON bnkseek.RGN = tb_rgn.RGN "+
                                           "LEFT JOIN tb_tnp ON bnkseek.TNP = tb_tnp.TNP"+
                                           " WHERE bnkseek.NEWNUM LIKE'%" +newnumFilter + "%' AND bnkseek.RGN LIKE '%"+rgnF+"%' AND bnkseek.PZN LIKE '%"+pznF+"%';");
        //  System.out.println(stmt.toString());
          ResultSet rs = stmt.executeQuery();
          while(rs.next()){
              String real = rs.getString("REAL");
              String pzn = rs.getString("NAME");
              String uer = rs.getString("UERNAME");
              String rgn = rs.getString("RGNNAME");
              String ind = rs.getString("IND");
              String tnp = rs.getString("FULLNAME");
              String nnp = rs.getString("NNP");
              String adr = rs.getString("ADR");
              String rkc = rs.getString("RKC");
              String namep = rs.getString("NAMEP");
              String newnum = rs.getString("NEWNUM");
              String telef = rs.getString("TELEF");
              String regn = rs.getString("REGN");
              String okpo = rs.getString("OKPO");
              String dt_izm = rs.getString("DT_IZM");
              String ksnp = rs.getString("KSNP");
              String date_in = rs.getString("DATE_IN");
              String date_ch = rs.getString("DATE_CH");
          
              Object[] row = {real,pzn,uer,rgn,ind,tnp,nnp,adr,rkc,namep,newnum,telef,regn,okpo,dt_izm,ksnp,date_in,date_ch};
              model.addRow(row);
              
          }
      
       mvc.getjTable2().setModel(model);
      
      }catch(SQLException ex){ex.printStackTrace();}
  
    }

    public LoadSettings getLoadSettings() {
        return loadSettings;
    }
    
    public List<String> selectPZN(){
     List<String> list = new ArrayList<String>();
      
         try{
          stmt=connection.prepareStatement("SELECT tb_pzn.Name FROM tb_pzn" );
          ResultSet rs = stmt.executeQuery();
        
          while(rs.next()){
             String res = rs.getString("NAME");
            list.add(res);
           
          }
      }catch(SQLException ex){ex.printStackTrace();}
        return list;
    } //выборка Имен для использования в выпадающих списках
    
    public List<String> selectTNP(){
     List<String> list = new ArrayList<String>();
      
         try{
          stmt=connection.prepareStatement("SELECT fullname FROM bnkbase.tb_tnp" );
          ResultSet rs = stmt.executeQuery();
        
          while(rs.next()){
             String res = rs.getString("FULLNAME");
            list.add(res);
           
          }
      }catch(SQLException ex){ex.printStackTrace();}
        return list;
    } //выборка Имен для использования в выпадающих списках
    
    public List<String> selectUER(){
     List<String> list = new ArrayList<String>();
      
         try{
          stmt=connection.prepareStatement("SELECT uername FROM bnkbase.tb_uer" );
          ResultSet rs = stmt.executeQuery();   
          while(rs.next()){
             String res = rs.getString("UERNAME");
            list.add(res);      
          }
      }catch(SQLException ex){ex.printStackTrace();}
        return list;
    } //выборка Имен для использования в выпадающих списках
     public List<String> selectReal(){
     List<String> list = new ArrayList<String>();   
         try{
          stmt=connection.prepareStatement("SELECT tb_real.REAL FROM bnkbase.tb_real" );
          ResultSet rs = stmt.executeQuery();   
          while(rs.next()){
             String res = rs.getString("REAL");
            list.add(res);      
          }
      }catch(SQLException ex){ex.printStackTrace();}
        return list;
    } //выборка Имен для использования в выпадающих списках
    
    public List<String> selectRGN(){ 
     List<String> list = new ArrayList<String>();
      
         try{
          stmt=connection.prepareStatement("SELECT NAME FROM bnkbase.tb_rgn;" );
          ResultSet rs = stmt.executeQuery();   
          while(rs.next()){
             String res = rs.getString("NAME");
            list.add(res);      
          }
      }catch(SQLException ex){ex.printStackTrace();}
        return list;
    } //выборка Имен для использования в выпадающих списках
    
    public void connToDB (){
            String url = loadSettings.getSettingsFile().getProperty("PathToDB");
        //    System.out.println(loadSettings.getMp().get("PathToDB"));     
            
            String username = loadSettings.getSettingsFile().getProperty("User");
        //    System.out.println(loadSettings.getMp().get("User"));
           
            String password = loadSettings.getSettingsFile().getProperty("Password");
        //    System.out.println(loadSettings.getMp().get("Password"));
        try {
       
            connection = DriverManager.getConnection(url,username,password);
            connection.setAutoCommit(false);
        }catch (SQLException e){e.printStackTrace();}
    } //подключение к базе данных
    
    public boolean ifTableIsEmpty(){
      int d=1;
        try{
        PreparedStatement st = connection.prepareStatement("SELECT COUNT(*) FROM bnkseek");
        ResultSet rs = st.executeQuery();
         while(rs.next()){
           d =  rs.getInt(1);
          }
       }catch(SQLException e){e.printStackTrace();}
        
        if (d==0) return true;
        else return false;      
    } // проверяет пуста ли таблица
     
    private String changeNameToDigit(String what, String tableName, String valueName ,String valueToCompare){
         String result="";
         try{
             stmt=connection.prepareStatement("SELECT " + what +" FROM bnkbase." + tableName + " WHERE " + valueName + " = '" + valueToCompare+"'" );
            // System.out.println(stmt.toString());
                ResultSet rs = stmt.executeQuery(); 
                  while(rs.next()){
                result = rs.getString(what);      
          }     
         }catch (SQLException e){e.printStackTrace();}         
         return result;
     } //замена текстовых значений на чиловые
     
    public java.sql.Date convertToSQLDate(String date) throws ParseException{
       
        java.sql.Date  dtSQL;
        if(date.contains("GST")){
        String[] str = date.split("GST ");
        str[0]=str[0].concat(str[1]); 
        dtSQL= new java.sql.Date(format.parse(str[0]).getTime()); 
        str[0]="";str[1]="";
        return dtSQL;
        }else{ 
            dtSQL= new java.sql.Date(format1.parse(date).getTime());
            return dtSQL;
        }  
    }
     
    public void insertRow(Map<String,String> rowValues){
     
        try{
                String rgn = changeNameToDigit("RGN","tb_rgn","NAME",rowValues.get("rgn"));
                String pzn = changeNameToDigit("PZN","tb_pzn","NAME",rowValues.get("pzn"));
                String tnp = changeNameToDigit("TNP","tb_tnp","FULLNAME",rowValues.get("tnp"));
                String uer = changeNameToDigit("UER","tb_uer","UERNAME",rowValues.get("uer"));        

                   stmt = connection.prepareStatement("INSERT INTO bnkseek "
                            + "(bnkseek.REAL,PZN,UER,RGN,IND,TNP,NNP,ADR,RKC,NAMEP,NEWNUM,TELEF,REGN,OKPO,DT_IZM,KSNP,DATE_IN,DATE_CH) "
                            + "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
               
                stmt.setString(1, rowValues.get("real"));                
                stmt.setString(2, pzn);              
                stmt.setString(3, uer);                
                stmt.setString(4, rgn);              
                stmt.setString(5, rowValues.get("ind"));               
                stmt.setString(6, tnp);              
                stmt.setString(7, rowValues.get("nnp"));
                stmt.setString(8, rowValues.get("adr"));
                stmt.setString(9, rowValues.get("rkc"));
                stmt.setString(10, rowValues.get("namep"));
                stmt.setString(11, rowValues.get("newnum"));
                stmt.setString(12, rowValues.get("telef"));
                stmt.setString(13, rowValues.get("regn"));
                stmt.setString(14, rowValues.get("okpo"));
                stmt.setDate(15, convertToSQLDate(rowValues.get("dtizm")));
                stmt.setString(16, rowValues.get("ksnp"));
                stmt.setDate(17, convertToSQLDate(rowValues.get("datein")));
                stmt.setDate(18, convertToSQLDate(rowValues.get("datech")));
                stmt.addBatch();
                stmt.executeBatch();
                connection.commit();
         } catch (SQLException e){e.printStackTrace();}
           catch (ParseException ex){
               ex.printStackTrace();         
           }             
     }
    
    public void updateRow(Map<String,String> rowValues){
        try{
            String rgn = changeNameToDigit("RGN","tb_rgn","NAME",rowValues.get("rgn"));
            String pzn = changeNameToDigit("PZN","tb_pzn","NAME",rowValues.get("pzn"));
            String tnp = changeNameToDigit("TNP","tb_tnp","FULLNAME",rowValues.get("tnp"));
            String uer = changeNameToDigit("UER","tb_uer","UERNAME",rowValues.get("uer"));        

               stmt = connection.prepareStatement("UPDATE bnkseek  SET "
                        + "bnkseek.REAL = ?,PZN = ?,UER = ?,RGN = ?,IND = ?,TNP = ?,NNP = ?,ADR = ?,RKC = ?,NAMEP = ?," + 
                          "TELEF = ?,REGN = ?,OKPO = ?,DT_IZM = ?,KSNP = ?,DATE_IN = ?,DATE_CH = ?" + " WHERE NEWNUM = " +rowValues.get("newnum"));
                    
            stmt.setString(1, rowValues.get("real"));               
            stmt.setString(2, pzn);             
            stmt.setString(3, uer);             
            stmt.setString(4, rgn);             
            stmt.setString(5, rowValues.get("ind"));  
            if(tnp.equals("")){
                stmt.setNull(6, java.sql.Types.CHAR);
            }else{ stmt.setString(6, tnp);      }
                 
            stmt.setString(7, rowValues.get("nnp"));
            stmt.setString(8, rowValues.get("adr"));
            stmt.setString(9, rowValues.get("rkc"));
            stmt.setString(10, rowValues.get("namep"));    
            stmt.setString(11, rowValues.get("telef"));
            stmt.setString(12, rowValues.get("regn"));
            stmt.setString(13, rowValues.get("okpo"));
            stmt.setDate(14, convertToSQLDate(rowValues.get("dtizm")));
            stmt.setString(15, rowValues.get("ksnp"));
            stmt.setDate(16, convertToSQLDate(rowValues.get("datein")));
            stmt.setDate(17, convertToSQLDate(rowValues.get("datech")));
            stmt.addBatch();
            stmt.executeBatch();
            connection.commit();
         } catch (SQLException e){e.printStackTrace();}
           catch (ParseException ex){
               ex.printStackTrace();         
           }
    }
     
    public boolean checkUnique(String nN, String propertyName){ 
      
           boolean flag=false;
            try{
                stmt=connection.prepareStatement("Select "+ propertyName +" FROM bnkseek where " + propertyName + " = '" + nN + "'");
                  ResultSet rs = stmt.executeQuery();   
                  if (!rs.isBeforeFirst() ){
                    flag=true;}
          
            }catch(SQLException e){e.printStackTrace();}
         return flag;
      }//проверка NewNum на уникальность
      
    public void loadTnp(String pathTODBFs){
    
         try{
            stmt = connection.prepareStatement("INSERT INTO tb_tnp "
                    + "(VKEY,TNP,FULLNAME,SHORTNAME) "
                    + "VALUES" + "(?,?,?,?)");
            
            InputStream inputStreamTNP  = new FileInputStream(pathTODBFs.concat("TNP.DBF")); 
            DBFReader readerTNP = new DBFReader( inputStreamTNP, Charset.forName("cp866")); 

            int numberOfFieldsTNP = readerTNP.getFieldCount();
            Object []rowObjectsTNP;

            while( (rowObjectsTNP = readerTNP.nextRecord()) != null) {
              for (int i = 0; i < rowObjectsTNP.length; i++) {
                        stmt.setString(i+1,rowObjectsTNP[i].toString());   
                }            
               stmt.addBatch();
            }  
             stmt.executeBatch();         
            }catch(DBFException e){e.printStackTrace();}
             catch(FileNotFoundException ex){ex.printStackTrace();System.out.println("Не задан путь к *.dbf");}
             catch (SQLException e){e.printStackTrace();}
    } //загрузка TNP.DBF в базу
    
    public void loadBnkSeek(String pathTODBFs){
         try{

            stmt = connection.prepareStatement("INSERT INTO bnkseek "
                        + "(bnkseek.REAL,PZN,UER,RGN,IND,TNP,NNP,ADR,RKC,NAMEP,NEWNUM,TELEF,REGN,OKPO,DT_IZM,KSNP,DATE_IN,DATE_CH) "
                        + "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            InputStream inputStreamBNK  = new FileInputStream(pathTODBFs.concat("BNKSEEK.DBF")); 
            DBFReader readerBNK = new DBFReader( inputStreamBNK, Charset.forName("cp866")); 

            int numberOfFieldsBNK = readerBNK.getFieldCount();
            Object []rowObjectsBNK;

            while( (rowObjectsBNK = readerBNK.nextRecord()) != null) {
             
                 stmt.setString(1,rowObjectsBNK[1].toString());
                 stmt.setString(2,rowObjectsBNK[2].toString());
                 stmt.setString(3,rowObjectsBNK[3].toString());
                 stmt.setString(4,rowObjectsBNK[4].toString());
                 stmt.setString(5,rowObjectsBNK[5].toString());
                if((rowObjectsBNK[6].equals("    "))|| (rowObjectsBNK[6].equals(" "))){
                    stmt.setNull(6, java.sql.Types.CHAR);  
                }else {//System.out.println(LoadBNKSeekfromDBF.baseBNK.get(i).data.get(j));
                             stmt.setString(6,rowObjectsBNK[6].toString());
                        }  
                 stmt.setString(7,rowObjectsBNK[7].toString());
                 stmt.setString(8,rowObjectsBNK[8].toString());
                 stmt.setString(9,rowObjectsBNK[9].toString());
                 stmt.setString(10,rowObjectsBNK[10].toString());
                 stmt.setString(11,rowObjectsBNK[12].toString());
                 stmt.setString(12,rowObjectsBNK[18].toString());
                 stmt.setString(13,rowObjectsBNK[19].toString());
                 stmt.setString(14,rowObjectsBNK[20].toString());
                  if (rowObjectsBNK[21].toString().equals(" ")){
                         stmt.setNull(15, java.sql.Types.DATE);  
                    }else{
                      stmt.setDate(15,convertToSQLDate(rowObjectsBNK[21].toString()));}       
                 stmt.setString(16,rowObjectsBNK[23].toString());
                 
                 if (rowObjectsBNK[24].toString().equals(" ")){
                         stmt.setNull(17, java.sql.Types.DATE);  
                    }else{
                       stmt.setDate(17,convertToSQLDate(rowObjectsBNK[24].toString()));}
                 if (rowObjectsBNK[25]==null){
                         stmt.setNull(18, java.sql.Types.DATE);  
                    }else{
                       stmt.setDate(18,convertToSQLDate(rowObjectsBNK[25].toString()));}
  
               stmt.addBatch();
            }
              stmt.executeBatch();
              connection.commit();
         }catch (ParseException ex){ex.printStackTrace();}
          catch( DBFException e) {
            System.out.println( e.getMessage());
          }
          catch( IOException e) {
            System.out.println( e.getMessage());
          }
          catch(SQLException ex){ex.printStackTrace();}
    }  //загрузка BNKSEEK.DBF в базу
     
    public void loadPzn(String pathTODBFs){
    
         try{
            stmt = connection.prepareStatement("INSERT INTO tb_pzn "
                    + "(VKEY,PZN,IMY,NAME,CB_DATE,CE_DATE) "
                    + "VALUES" + "(?,?,?,?,?,?)");
            InputStream inputStreamPZN  = new FileInputStream(pathTODBFs.concat("PZN.DBF")); 
            DBFReader readerPZN = new DBFReader( inputStreamPZN, Charset.forName("cp866")); 

            int numberOfFieldsPZN = readerPZN.getFieldCount();
            Object []rowObjectsPZN;

            while( (rowObjectsPZN = readerPZN.nextRecord()) != null) {
              for (int i = 0; i < rowObjectsPZN.length; i++) {
                 
                 //   System.out.println(LoadPZNSeekfromDBF.basePZN.get(i).data.size());
                    if((i==5)||(i==4)){
                   //     System.out.println(LoadPZNSeekfromDBF.basePZN.get(i).data.get(j));
                        if (rowObjectsPZN[i] == null){
                             stmt.setNull(i+1, java.sql.Types.DATE);  
                        }else{
                         //   System.out.println(LoadPZNSeekfromDBF.basePZN.get(i).data.get(j));
                             stmt.setDate(i+1,convertToSQLDate(rowObjectsPZN[i].toString()));}
                    }else{
                      //  System.out.println(LoadPZNSeekfromDBF.basePZN.get(i).data.get(j));
                         stmt.setString(i+1,rowObjectsPZN[i].toString());
                    }
                }            
                 stmt.addBatch();
            }     
              stmt.executeBatch();
        //       connection.commit();
     
            }catch(DBFException e){e.printStackTrace();}
             catch(FileNotFoundException ex){ex.printStackTrace();System.out.println("Не задан путь к *.dbf");}
             catch (SQLException e){e.printStackTrace();}
             catch(ParseException e){e.printStackTrace(); System.out.println("что то не верно с форматом даты");}   
    } //загрузка PZN.DBF в базу
    
    public void loadUer(String pathTODBFs){
    
         try{
             stmt = connection.prepareStatement("INSERT INTO tb_uer "
                    + "(VKEY,UER,UERNAME) "
                    + "VALUES" + "(?,?,?)");
            
            InputStream inputStreamUER  = new FileInputStream(pathTODBFs.concat("UER.DBF")); 
            DBFReader readerUER = new DBFReader( inputStreamUER, Charset.forName("cp866")); 

            int numberOfFieldsUER = readerUER.getFieldCount();
            Object []rowObjectsUER;

            while( (rowObjectsUER = readerUER.nextRecord()) != null) {
              for (int i = 0; i < rowObjectsUER.length; i++) {
                         stmt.setString(i+1,rowObjectsUER[i].toString());     
                }            
                stmt.addBatch();
            }   
              stmt.executeBatch();
       //        connection.commit();
            }catch(DBFException e){e.printStackTrace();}
             catch(FileNotFoundException ex){ex.printStackTrace();System.out.println("Не задан путь к *.dbf");}
             catch (SQLException e){e.printStackTrace();}
    }//загрузка UER.DBF в базу
    
    public void loadRgn(String pathTODBFs){
    
         try{
            stmt=connection.prepareStatement("INSERT INTO tb_rgn "
                    + "(VKEY,RGN,NAME,CENTER,NAMET) "
                    + "VALUES" + "(?,?,?,?,?)");
           InputStream inputStreamRGN  = new FileInputStream(pathTODBFs.concat("REG.DBF")); 
           DBFReader readerRGN = new DBFReader( inputStreamRGN, Charset.forName("cp866")); 

            int numberOfFieldsRGN = readerRGN.getFieldCount();
            Object []rowObjectsRGN;   
            
          while( (rowObjectsRGN = readerRGN.nextRecord()) != null) {
              for (int i = 0; i < rowObjectsRGN.length; i++) {
                        stmt.setString(i+1,rowObjectsRGN[i].toString());     
                }            
                stmt.addBatch();
            }   
             stmt.executeBatch();
         //    connection.commit();
            }catch(DBFException e){e.printStackTrace();}
             catch(FileNotFoundException ex){ex.printStackTrace();System.out.println("Не задан путь к *.dbf");}
             catch (SQLException e){e.printStackTrace();}
    }//загрузка REG.DBF в базу
     
    public void loadReal(String pathTODBFs){
    
         try{
            stmt=connection.prepareStatement("INSERT INTO tb_real "
                    + "(VKEY,tb_real.REAL,NAME_OGR) "
                    + "VALUES" + "(?,?,?)");
           InputStream inputStreamREAL  = new FileInputStream(pathTODBFs.concat("REAL.DBF")); 
           DBFReader readerREAL = new DBFReader( inputStreamREAL, Charset.forName("cp866")); 

            int numberOfFieldsREAL = readerREAL.getFieldCount();
            Object []rowObjectsREAL;   
            
          while( (rowObjectsREAL = readerREAL.nextRecord()) != null) {
              for (int i = 0; i < rowObjectsREAL.length; i++) {
                        stmt.setString(i+1,rowObjectsREAL[i].toString());     
                }            
                stmt.addBatch();
            }    
             stmt.executeBatch();
             //  connection.commit();
            }catch(DBFException e){e.printStackTrace();}
             catch(FileNotFoundException ex){ex.printStackTrace();System.out.println("Не задан путь к *.dbf");}
             catch (SQLException e){e.printStackTrace();}
    }//загрузка REAL.DBF в базу
     
    public void closeDBconn(){

        try {
            if(stmt != null) {
                stmt.executeBatch();
                stmt.close();
            }
            connection.commit();

            connection.close();
        }catch(SQLException e){e.printStackTrace();}
    }
     
    public void truncateAllTables(){
         List<String> list = new ArrayList<String>();
            try{           
                stmt=connection.prepareStatement("use bnkbase;");
                stmt.addBatch();
    
                stmt.addBatch("TRUNCATE TABLE bnkbase.bnkseek;");
                stmt.executeBatch();            
         
                stmt.addBatch("ALTER TABLE `bnkbase`.`bnkseek` "+
                                                "DROP FOREIGN KEY `FK_UER`," +
                                                "DROP FOREIGN KEY `FK_TNP`," +
                                                "DROP FOREIGN KEY `FK_RGN`," + 
                                                "DROP FOREIGN KEY `FK_REAL`," +
                                                "DROP FOREIGN KEY `FK_PZN`;");
                stmt.addBatch("ALTER TABLE `bnkbase`.`bnkseek`" + 
                                                "DROP INDEX `FK_UER_idx` ," +
                                                "DROP INDEX `FK_TNP_idx` ," +
                                                "DROP INDEX `FK_PZN_idx` ," +
                                                "DROP INDEX `FK_REAL_idx` ," +
                                                "DROP INDEX `FK_RGN_idx` ;");
                stmt.executeBatch();
           //     connection.commit();
                
                stmt.addBatch("TRUNCATE TABLE bnkbase.tb_pzn;");
                stmt.addBatch("TRUNCATE TABLE bnkbase.tb_uer;");
                stmt.addBatch("TRUNCATE TABLE bnkbase.tb_rgn;");
                stmt.addBatch("TRUNCATE TABLE bnkbase.tb_tnp;");
                stmt.addBatch("TRUNCATE TABLE bnkbase.tb_real;");
                stmt.executeBatch();
               // connection.commit();
                
                stmt.addBatch("ALTER TABLE `bnkbase`.`bnkseek`" + 
                              "ADD INDEX `FK_PZN_idx` (`PZN` ASC)," +
                              "ADD INDEX `FK_RGN_idx` (`RGN` ASC)," +
                              "ADD INDEX `FK_UER_idx` (`UER` ASC)," +
                              "ADD INDEX `FK_TNP_idx` (`TNP` ASC)," +
                              "ADD INDEX `FK_REAL_idx` (`REAL` ASC);");
                stmt.executeBatch();
                stmt.addBatch("ALTER TABLE `bnkbase`.`bnkseek`" +
                              "ADD CONSTRAINT `FK_PZN`" +
                                "FOREIGN KEY (`PZN`)" +
                                "REFERENCES `bnkbase`.`tb_pzn` (`PZN`)" +
                                "ON DELETE NO ACTION " +
                                "ON UPDATE NO ACTION, " +
                             "ADD CONSTRAINT `FK_RGN` " +
                                "FOREIGN KEY (`RGN`) " +
                                "REFERENCES `bnkbase`.`tb_rgn` (`RGN`) " +
                                "ON DELETE NO ACTION " +
                                "ON UPDATE NO ACTION, " +
                             "ADD CONSTRAINT `FK_UER` " +
                                "FOREIGN KEY (`UER`) " +
                                "REFERENCES `bnkbase`.`tb_uer` (`UER`) " +
                                "ON DELETE NO ACTION " +
                                "ON UPDATE NO ACTION, " +
                             "ADD CONSTRAINT `FK_TNP` " +
                                "FOREIGN KEY (`TNP`) " +
                                "REFERENCES `bnkbase`.`tb_tnp` (`TNP`) " +
                                "ON DELETE NO ACTION " +
                                "ON UPDATE NO ACTION, "  +
                             "ADD CONSTRAINT `FK_REAL` " +
                                "FOREIGN KEY (`REAL`) " +
                                "REFERENCES `bnkbase`.`tb_real` (`REAL`) " +
                                "ON DELETE NO ACTION " +
                                "ON UPDATE NO ACTION;");
                stmt.executeBatch();
                connection.commit();
                
            }catch (SQLException e){e.printStackTrace();}
     } //очистка таблиц перед загрузкой новых данных из DBF
    
    public void deleteRow(String newNum){
    
         try{   
            stmt=connection.prepareStatement("DELETE FROM bnkbase.bnkseek WHERE NEWNUM = '" + newNum + "';");
            stmt.addBatch();
            stmt.executeBatch();
            connection.commit(); 
         }catch(SQLException ex){ex.printStackTrace();}
    }
}
