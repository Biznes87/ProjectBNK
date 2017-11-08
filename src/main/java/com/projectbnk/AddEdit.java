/*
    Класс форма для отображения окна добавления или редактирования строк
*/
package com.projectbnk;

import com.projectbnk.controllers.Controller;
import com.projectbnk.controllers.ControllerAdd;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class AddEdit extends javax.swing.JFrame {

    
    private ControllerAdd controllerAdd=null;
    private BNKSeekInterfacce mvc=null;
    private List<JTextField> textFileds; 
    private String controllerType="";  
   

    
    public AddEdit(BNKSeekInterfacce mvc, String controllerType) {
        
        this.mvc=mvc;
        initComponents();
        this.controllerType = controllerType;
        controllerAdd = new ControllerAdd(this);
       textFileds  = new ArrayList<>();
       textFileds.add(tfIND);
       textFileds.add(tfNNP);textFileds.add(tfADR);
       textFileds.add(tfRKC);textFileds.add(tfNAMEP);
       textFileds.add(tfNewNum);
       textFileds.add(tfTelef);textFileds.add(tfRegn);
       textFileds.add(tfOKPO);textFileds.add(tfDtIzm);
       textFileds.add(tfKsnp);textFileds.add(tfDateIn);
       textFileds.add(tfDateCh);  
   
    }

    public List<JTextField> getTextFileds() {
        return textFileds;
    }

    public ControllerAdd getControllerAdd() {
        return controllerAdd;
    }

    public BNKSeekInterfacce getMvc() {
        return mvc;
    }

    public JComboBox<String> getCbPzn() {
        return cbPzn;
    }

    public JComboBox<String> getCbRgn() {
        return cbRgn;
    }

    public JComboBox<String> getCbTnp() {
        return cbTnp;
    }

    public JComboBox<String> getCbUER() {
        return cbUer;
    }

    public JButton getjButton1() {
        return buttonAddOK;
    }

    public JButton getjButton2() {
        return buttonAddCancel;
    }

    public JTextField getTfADR() {
        return tfADR;
    }

    public JTextField getTfDateCh() {
        return tfDateCh;
    }

    public JTextField getTfDateIn() {
        return tfDateIn;
    }

    public JTextField getTfDtIzm() {
        return tfDtIzm;
    }

    public JTextField getTfIND() {
        return tfIND;
    }

    public JTextField getTfKsnp() {
        return tfKsnp;
    }

    public JTextField getTfNAMEP() {
        return tfNAMEP;
    }

    public JTextField getTfNNP() {
        return tfNNP;
    }

    public JTextField getTfNewNum() {
        return tfNewNum;
    }

    public JTextField getTfOKPO() {
        return tfOKPO;
    }

    

    public JTextField getTfRKC() {
        return tfRKC;
    }

    public JTextField getTfRegn() {
        return tfRegn;
    }

    public JTextField getTfTelef() {
        return tfTelef;
    }

    public JComboBox<String> getCbReal() {
        return cbReal;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        cbUer = new javax.swing.JComboBox<>();
        cbPzn = new javax.swing.JComboBox<>();
        cbTnp = new javax.swing.JComboBox<>();
        cbRgn = new javax.swing.JComboBox<>();
        tfNNP = new javax.swing.JTextField();
        tfIND = new javax.swing.JTextField();
        tfRKC = new javax.swing.JTextField();
        tfADR = new javax.swing.JTextField();
        tfNewNum = new javax.swing.JTextField();
        tfNAMEP = new javax.swing.JTextField();
        tfRegn = new javax.swing.JTextField();
        tfTelef = new javax.swing.JTextField();
        tfDtIzm = new javax.swing.JTextField();
        tfOKPO = new javax.swing.JTextField();
        tfDateIn = new javax.swing.JTextField();
        tfKsnp = new javax.swing.JTextField();
        tfDateCh = new javax.swing.JTextField();
        buttonAddOK = new javax.swing.JButton();
        buttonAddCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbReal = new javax.swing.JComboBox<>();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tfNNP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfNNPMouseClicked(evt);
            }
        });

        tfIND.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfINDMouseClicked(evt);
            }
        });
        tfIND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfINDActionPerformed(evt);
            }
        });

        tfRKC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfRKCMouseClicked(evt);
            }
        });

        tfADR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfADRMouseClicked(evt);
            }
        });

        if(controllerType.equals("Edit")){
            tfNewNum.setEditable(false);
        }
        tfNewNum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfNewNumMouseClicked(evt);
            }
        });

        tfNAMEP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfNAMEPMouseClicked(evt);
            }
        });

        tfRegn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfRegnMouseClicked(evt);
            }
        });

        tfTelef.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfTelefMouseClicked(evt);
            }
        });

        tfDtIzm.setToolTipText("");
        tfDtIzm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfDtIzmMouseClicked(evt);
            }
        });

        tfOKPO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfOKPOMouseClicked(evt);
            }
        });

        tfDateIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfDateInMouseClicked(evt);
            }
        });

        tfKsnp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfKsnpMouseClicked(evt);
            }
        });

        tfDateCh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfDateChMouseClicked(evt);
            }
        });

        buttonAddOK.setText("OK");
        buttonAddOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddOKActionPerformed(evt);
            }
        });

        buttonAddCancel.setText("Cancel");
        buttonAddCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddCancelActionPerformed(evt);
            }
        });

        jLabel1.setText("<html>Код контроля допустимости<br> проведения расчетных операций</html>");

        jLabel2.setText("Индекс");

        jLabel3.setText("Населенный пункт");

        jLabel4.setText("Адрес");

        jLabel6.setText("Код типа участника расчетов");

        jLabel7.setText("БИК РКЦ(ГРКЦ)");

        jLabel8.setText("Типа участника ситемы электронных расчетов");

        jLabel9.setText("Наименование участника расчетов");

        jLabel10.setText("Территория РФ");

        jLabel11.setText("БИК");

        jLabel12.setText("Тип населенного пункта");

        jLabel13.setText("Телефон");

        jLabel14.setText("Регистрационный номер");

        jLabel15.setText("Код ОКПО");

        jLabel16.setText("Номер счета");

        jLabel17.setText("Дата последнего изменения записи");

        jLabel18.setText("<html>Дата включения информации об участнике<br>расчтеов в ЭБД</html>");

        jLabel19.setText("Дата контроля");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonAddOK, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAddCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cbPzn, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbReal, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfIND, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                                    .addComponent(tfNNP, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17)
                                    .addComponent(tfOKPO, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                    .addComponent(tfDtIzm))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfDateIn)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addComponent(tfDateCh, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(257, 257, 257)
                                        .addComponent(tfKsnp))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(tfTelef))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addGap(180, 180, 180))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tfRegn)
                                                .addGap(18, 18, 18))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(463, 463, 463)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(tfNewNum, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cbTnp, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbRgn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbUer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(39, 39, 39))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7)
                                        .addComponent(tfNAMEP, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tfRKC, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tfADR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbReal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIND, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPzn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNNP, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbUer, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfADR, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbRgn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfRKC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTnp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNAMEP, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfTelef, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfNewNum, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfRegn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfOKPO, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfKsnp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDtIzm, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfDateCh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDateIn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAddOK, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfINDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfINDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfINDActionPerformed

    private void tfINDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfINDMouseClicked
       
    }//GEN-LAST:event_tfINDMouseClicked

    private void tfNNPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNNPMouseClicked
       
    }//GEN-LAST:event_tfNNPMouseClicked

    private void tfADRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfADRMouseClicked
        
    }//GEN-LAST:event_tfADRMouseClicked

    private void tfRKCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfRKCMouseClicked
        
    }//GEN-LAST:event_tfRKCMouseClicked

    private void tfNAMEPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNAMEPMouseClicked
        
    }//GEN-LAST:event_tfNAMEPMouseClicked

    private void tfNewNumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNewNumMouseClicked
        
    }//GEN-LAST:event_tfNewNumMouseClicked

    private void tfTelefMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfTelefMouseClicked
       
    }//GEN-LAST:event_tfTelefMouseClicked

    private void tfRegnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfRegnMouseClicked
      
    }//GEN-LAST:event_tfRegnMouseClicked

    private void tfOKPOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfOKPOMouseClicked
        
    }//GEN-LAST:event_tfOKPOMouseClicked

    private void tfDtIzmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfDtIzmMouseClicked
       
    }//GEN-LAST:event_tfDtIzmMouseClicked

    private void tfKsnpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfKsnpMouseClicked
     
    }//GEN-LAST:event_tfKsnpMouseClicked

    private void tfDateInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfDateInMouseClicked
        
    }//GEN-LAST:event_tfDateInMouseClicked

    private void tfDateChMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfDateChMouseClicked
       
    }//GEN-LAST:event_tfDateChMouseClicked

    private void buttonAddCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCancelActionPerformed
        controllerAdd.closeForm();
    }//GEN-LAST:event_buttonAddCancelActionPerformed

    private void buttonAddOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddOKActionPerformed
       if(controllerType.equals("Add")){
        controllerAdd.addOK();
       }else{ controllerAdd.OkEdit();}
    }//GEN-LAST:event_buttonAddOKActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddCancel;
    private javax.swing.JButton buttonAddOK;
    private javax.swing.JComboBox<String> cbPzn;
    private javax.swing.JComboBox<String> cbReal;
    private javax.swing.JComboBox<String> cbRgn;
    private javax.swing.JComboBox<String> cbTnp;
    private javax.swing.JComboBox<String> cbUer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField tfADR;
    private javax.swing.JTextField tfDateCh;
    private javax.swing.JTextField tfDateIn;
    private javax.swing.JTextField tfDtIzm;
    private javax.swing.JTextField tfIND;
    private javax.swing.JTextField tfKsnp;
    private javax.swing.JTextField tfNAMEP;
    private javax.swing.JTextField tfNNP;
    private javax.swing.JTextField tfNewNum;
    private javax.swing.JTextField tfOKPO;
    private javax.swing.JTextField tfRKC;
    private javax.swing.JTextField tfRegn;
    private javax.swing.JTextField tfTelef;
    // End of variables declaration//GEN-END:variables
}
