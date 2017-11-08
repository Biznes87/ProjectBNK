/*
 Класс контроллер окна для добавления и редактирования строк
 */
package com.projectbnk.controllers;

import com.projectbnk.AddEdit;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControllerAdd {
    
    private AddEdit addEdit=null;
    private Map<String,String> rowValues;
    
    
    public ControllerAdd(AddEdit addEdit){
        this.addEdit=addEdit;
        //задаем модель для выпадающих списков на форме
        addEdit.getMvc().getController().setCbModel(addEdit.getMvc().getModel().selectPZN(),addEdit.getCbPzn());
        addEdit.getMvc().getController().setCbModel(addEdit.getMvc().getModel().selectTNP(),addEdit.getCbTnp());
        addEdit.getMvc().getController().setCbModel(addEdit.getMvc().getModel().selectUER(),addEdit.getCbUER());
        addEdit.getMvc().getController().setCbModel(addEdit.getMvc().getModel().selectRGN(),addEdit.getCbRgn());
        addEdit.getMvc().getController().setCbModel(addEdit.getMvc().getModel().selectReal(),addEdit.getCbReal());
        rowValues = new HashMap<String, String>();
    }
     
    public void closeForm(){
        addEdit.dispatchEvent(new WindowEvent(addEdit.getMvc(), WindowEvent.WINDOW_ACTIVATED));
        addEdit.dispatchEvent(new WindowEvent(addEdit, WindowEvent.WINDOW_CLOSING));
    }  
    
    public void addOK(){
        
        // если все поля заполнены
       if (!ifTextFieldsIsEmpty()){                                                                                                 
        String getReal = addEdit.getCbReal().getSelectedItem().toString();
        String getPzn = addEdit.getCbPzn().getSelectedItem().toString();
        String getUer = addEdit.getCbUER().getSelectedItem().toString();
        String getRgn = addEdit.getCbRgn().getSelectedItem().toString();
        String getTnp = addEdit.getCbTnp().getSelectedItem().toString();
        String getInd = addEdit.getTfIND().getText();
        String getNNP = addEdit.getTfNNP().getText();
        String getADR = addEdit.getTfADR().getText();
        String getRKC = addEdit.getTfRKC().getText();
        String getNAMEP = addEdit.getTfNAMEP().getText();
        String getNewNum = addEdit.getTfNewNum().getText();
        String getTelef = addEdit.getTfTelef().getText();
        String getRegn = addEdit.getTfRegn().getText();
        String getOKPO = addEdit.getTfOKPO().getText();
        String getDtIzm = addEdit.getTfDtIzm().getText();
        String getKSNP = addEdit.getTfKsnp().getText();
        String getDateIn = addEdit.getTfDateIn().getText();
        String getDateCh = addEdit.getTfDateCh().getText();
        
       // если БИК уникален
        if ((addEdit.getMvc().getModel().checkUnique(getNewNum,"NEWNUM")) ){
            rowValues.clear();
            rowValues.put("real", getReal);
            rowValues.put("pzn", getPzn);
            rowValues.put("uer", getUer);
            rowValues.put("rgn", getRgn);
            rowValues.put("tnp", getTnp);
            rowValues.put("ind", getInd);
            rowValues.put("nnp", getNNP);
            rowValues.put("adr", getADR);
            rowValues.put("rkc", getRKC);
            rowValues.put("namep", getNAMEP);
            rowValues.put("newnum", getNewNum);
            rowValues.put("telef", getTelef);
            rowValues.put("regn", getRegn);
            rowValues.put("okpo", getOKPO);
            rowValues.put("dtizm", getDtIzm);
            rowValues.put("ksnp", getKSNP);
            rowValues.put("datein", getDateIn);
            rowValues.put("datech", getDateCh);
            
            //формируем и добавляем строку в модель JTable
            Object [] rowData = {getReal,getPzn,getUer,getRgn,getInd,getTnp,getNNP,getADR,getRKC,getNAMEP,getNewNum,getTelef,getRegn,getOKPO,getDtIzm,getKSNP,getDateIn,getDateCh};  
            DefaultTableModel model = (DefaultTableModel) addEdit.getMvc().getjTable2().getModel();
            model.addRow(rowData);
            //добавляем строку в базу
            addEdit.getMvc().getModel().insertRow(rowValues);
            addEdit.dispatchEvent(new WindowEvent(addEdit, WindowEvent.WINDOW_CLOSING));
        }else{
             JOptionPane.showMessageDialog(addEdit, "NewNum должно быть уникально");
        }
       }
    }
    
    public void OkEdit(){
        
        int row = addEdit.getMvc().getjTable2().getSelectedRow();
        //считываем значения из таблицы и заполняем ими поля формы
        String getReal = addEdit.getCbReal().getSelectedItem().toString();
        String getPzn = addEdit.getCbPzn().getSelectedItem().toString();
        String getUer = addEdit.getCbUER().getSelectedItem().toString();
        String getRgn = addEdit.getCbRgn().getSelectedItem().toString();
        String getTnp="";
        if(addEdit.getCbTnp().getSelectedItem() == null){   
            getTnp = " ";
        }else getTnp = addEdit.getCbTnp().getSelectedItem().toString();
        
       
        
        String getInd = addEdit.getTfIND().getText();
        String getNNP = addEdit.getTfNNP().getText();
        String getADR = addEdit.getTfADR().getText();
        String getRKC = addEdit.getTfRKC().getText();
        String getNAMEP = addEdit.getTfNAMEP().getText();
        String getNewNum = addEdit.getTfNewNum().getText();
        String getTelef = addEdit.getTfTelef().getText();
        String getRegn = addEdit.getTfRegn().getText();
        String getOKPO = addEdit.getTfOKPO().getText();
        String getDtIzm = addEdit.getTfDtIzm().getText();
        String getKSNP = addEdit.getTfKsnp().getText();
        String getDateIn = addEdit.getTfDateIn().getText();
        String getDateCh = addEdit.getTfDateCh().getText();      
       
        rowValues.clear();

        rowValues.put("real", getReal);
        rowValues.put("pzn", getPzn);
        rowValues.put("uer", getUer);
        rowValues.put("rgn", getRgn);
        rowValues.put("tnp", getTnp);
        rowValues.put("ind", getInd);
        rowValues.put("nnp", getNNP);
        rowValues.put("adr", getADR);
        rowValues.put("rkc", getRKC);
        rowValues.put("namep", getNAMEP);
        rowValues.put("newnum", getNewNum);
        rowValues.put("telef", getTelef);
        rowValues.put("regn", getRegn);
        rowValues.put("okpo", getOKPO);
        rowValues.put("dtizm", getDtIzm);
        rowValues.put("ksnp", getKSNP);
        rowValues.put("datein", getDateIn);
        rowValues.put("datech", getDateCh);
         //формируем и добавляем строку в модель JTable          
        Object [] rowData = {getReal,getPzn,getUer,getRgn,getInd,getTnp,getNNP,getADR,getRKC,getNAMEP,getNewNum,getTelef,getRegn,getOKPO,getDtIzm,getKSNP,getDateIn,getDateCh};         
        DefaultTableModel model = (DefaultTableModel) addEdit.getMvc().getjTable2().getModel();
        model.removeRow(row); //удаляем старую строку
        model.insertRow(row,rowData );
        //изменяем строку в базе данных
        addEdit.getMvc().getModel().updateRow(rowValues);
        addEdit.dispatchEvent(new WindowEvent(addEdit, WindowEvent.WINDOW_CLOSING)); 
         
    }
    
    private boolean ifTextFieldsIsEmpty(){
    
        boolean flag = false;
            for (int i = 0; i < addEdit.getTextFileds().size(); i++) {
                if(addEdit.getTextFileds().get(i) == null || addEdit.getTextFileds().get(i).getText().trim().isEmpty()){
                    flag=true;
                    JOptionPane.showMessageDialog(addEdit, "Необходимо заполнить все текстовые поля");
                    break;
                }
        }
        return flag;
    } //проверка заполнения всех текстовых полей формы
}
