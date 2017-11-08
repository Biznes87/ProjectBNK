/*
    Класс контроллер для главного окна

*/
package com.projectbnk.controllers;


import com.projectbnk.AddEdit;
import com.projectbnk.BNKSeekInterfacce;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class Controller {     
    private BNKSeekInterfacce mvc;

    public Controller(BNKSeekInterfacce mv){
       this.mvc=mv; 
    }

    public void loadBTN(){

        String getPath=""; //в эту переменную получаем путь к папке где лежат DBF файлы
        JFileChooser fc = new JFileChooser();

        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
        int returnValue = fc.showOpenDialog(null);
        if(returnValue==JFileChooser.APPROVE_OPTION){
           getPath = fc.getSelectedFile().getPath().concat("\\");
        }

       //Сброс всех таблиц перед заполнением
       mvc.getModel().truncateAllTables();
       //Обращение к функции загрузки из DBF в таблицы
        mvc.getModel().loadPzn(getPath);
        mvc.getModel().loadRgn(getPath);
        mvc.getModel().loadUer(getPath);
        mvc.getModel().loadTnp(getPath);
        mvc.getModel().loadReal(getPath);
        mvc.getModel().loadBnkSeek(getPath); 
        //заполняем JTable данными из базы. Пустые строки на входе функции
        //говорят о том что загружены будут все данные
        mvc.getModel().selectJtableResult("", "", "");
    }

    public void addBTN(){
      //создаем форму для добавления строки
      AddEdit fr = new AddEdit(mvc,"Add");
      fr.setVisible(true);
      fr.setTitle("Adding row frame");
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
      Date date = new Date(); 
      //заполняем значения полей с датами текущей датой
      fr.getTfDtIzm().setText(dateFormat.format(date));
      fr.getTfDateIn().setText(dateFormat.format(date));
      fr.getTfDateCh().setText(dateFormat.format(date));
    }

    public void delBTN(){
        //Диалог подтверждающий удаление
        int answer =  JOptionPane.showConfirmDialog(mvc,"Удалить выбранную строку?","Удаление...",JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.YES_OPTION){ //если выбрали ДА
          try{ 
            int row = mvc.getjTable2().getSelectedRow();
            String newNum  = mvc.getjTable2().getModel().getValueAt(row, 10).toString();
            mvc.getModel().deleteRow(newNum);
            DefaultTableModel model = (DefaultTableModel) mvc.getjTable2().getModel();
            model.removeRow(row);
         }catch(ArrayIndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(mvc, "Не выбрана строка для удаления");
        }
        }
    }

    public void filterBTN(){
        //элементы фильтра становятся видимы
        mvc.getTfBik().setVisible(true);
        mvc.getCbPzn().setVisible(true);
        mvc.getCbRgn().setVisible(true);
        mvc.getlBik().setVisible(true);
        mvc.getlRgn().setVisible(true);
        mvc.getlPzn().setVisible(true);
        mvc.getButtonFilterClose().setVisible(true);
        mvc.getButtonFilterOK().setVisible(true); 
        mvc.getCbRgn().setSelectedItem("");
        mvc.getCbPzn().setSelectedItem("");
    }
    
    public void filterOkBTN(){
        //считываем значения полей фильтра  
        String NN = mvc.getTfBik().getText();
        String pzn = mvc.getCbPzn().getSelectedItem().toString();
        String rgn = mvc.getCbRgn().getSelectedItem().toString();
        //заполняем выбранными данными JTable
        mvc.getModel().selectJtableResult(NN, pzn, rgn);
 
    }
    
    public void filterCloseBTN(){
        //элементы фильтра становятся невидимы
        mvc.getTfBik().setVisible(false);
        mvc.getCbPzn().setVisible(false);
        mvc.getCbRgn().setVisible(false);
        mvc.getlBik().setVisible(false);
        mvc.getlRgn().setVisible(false);
        mvc.getlPzn().setVisible(false);
        mvc.getButtonFilterClose().setVisible(false);
        mvc.getButtonFilterOK().setVisible(false); 
        
        //загрузить таблицу по умолчанию
       mvc.getModel().selectJtableResult("", "", "");
    }

    public void  editBTN(){
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
        Date date = new Date(); 
        AddEdit fr = new AddEdit(mvc,"Edit");
        try{    
         fr.setTitle("Edit row frame");
         int row = mvc.getjTable2().getSelectedRow();
         fr.getTfNewNum().setEnabled(false);
        
            //заполняем форму данными из таблицы
            fr.getCbReal().setSelectedItem(mvc.getjTable2().getModel().getValueAt(row, 0).toString());  
            fr.getCbPzn().setSelectedItem(mvc.getjTable2().getModel().getValueAt(row, 1));
            fr.getCbUER().setSelectedItem(mvc.getjTable2().getModel().getValueAt(row, 2));
            fr.getCbRgn().setSelectedItem(mvc.getjTable2().getModel().getValueAt(row, 3));
            fr.getCbTnp().setSelectedItem(mvc.getjTable2().getModel().getValueAt(row, 5));
            fr.getTfIND().setText(mvc.getjTable2().getModel().getValueAt(row, 4).toString());
            fr.getTfNNP().setText(mvc.getjTable2().getModel().getValueAt(row, 6).toString());
            fr.getTfADR().setText(mvc.getjTable2().getModel().getValueAt(row, 7).toString());
            fr.getTfRKC().setText(mvc.getjTable2().getModel().getValueAt(row, 8).toString());
            fr.getTfNAMEP().setText(mvc.getjTable2().getModel().getValueAt(row, 9).toString());
            fr.getTfNewNum().setText(mvc.getjTable2().getModel().getValueAt(row, 10).toString());
            fr.getTfTelef().setText(mvc.getjTable2().getModel().getValueAt(row, 11).toString());
            fr.getTfRegn().setText(mvc.getjTable2().getModel().getValueAt(row, 12).toString());
            fr.getTfOKPO().setText(mvc.getjTable2().getModel().getValueAt(row, 13).toString());
            //если значение равно null то заменяем на текущую дату
            if(mvc.getjTable2().getModel().getValueAt(row, 14)==null){fr.getTfDtIzm().setText(dateFormat.format(date));}
            else {fr.getTfDtIzm().setText(mvc.getjTable2().getModel().getValueAt(row, 14).toString());}
            fr.getTfKsnp().setText(mvc.getjTable2().getModel().getValueAt(row, 15).toString());
            if(mvc.getjTable2().getModel().getValueAt(row, 16).toString()==null){ fr.getTfDateIn().setText(dateFormat.format(date));}
            else{fr.getTfDateIn().setText(mvc.getjTable2().getModel().getValueAt(row, 16).toString());}
            if(mvc.getjTable2().getModel().getValueAt(row, 17)==null){ fr.getTfDateCh().setText(dateFormat.format(date));}
            else{fr.getTfDateCh().setText(mvc.getjTable2().getModel().getValueAt(row, 17).toString());}
            fr.setVisible(true);
        }catch(ArrayIndexOutOfBoundsException ex){
           fr.dispatchEvent(new WindowEvent(fr, WindowEvent.WINDOW_CLOSING)); 
            JOptionPane.showMessageDialog(mvc, "Не выбрана строка для редактирования");
        }
    }
    
     public DefaultComboBoxModel setCbModel(List listFromDB, JComboBox<String> comboBox)  {
        List<String> list = new ArrayList<>(listFromDB);
       
         DefaultComboBoxModel model = (DefaultComboBoxModel) comboBox.getModel();
         for(int i=0; i<list.size();i++){
              model.addElement(list.get(i));
         }
      return model;
    }  //задаем модель по умолчанию для компонента ComboBox
}
