/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pkg105;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import static test.pkg105.TEST105.MAINFILE;
import static test.pkg105.TEST105.MYLIST;
import static test.pkg105.TEST105.SELECTED_ROW;

public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
        initComponents();
        fileload();
        update();
        EDITButton.setVisible(false);
        REMOVEButoon.setVisible(false);
    }
    
    public static void fileload() {
        if (MAINFILE.isFile()) {
            try {
                FileInputStream inputstream = new FileInputStream(MAINFILE);
                ObjectInputStream objectinputstream = new ObjectInputStream(inputstream);
                MYLIST = (ArrayList<Student>) objectinputstream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                
            }
        }
    }
    
    public void update() {
        String colomname [] = {"Number" , "Name" , "Sirname" , "Age" , "Average" , "ID"};
        Object rowdata [][] = new Object [TEST105.MYLIST.size()][6];
        for (int i = 0 ; i < TEST105.MYLIST.size(); i++ ) {
            rowdata[i][0] = String.valueOf(i+1);
            rowdata[i][1] = TEST105.MYLIST.get(i).get_name();
            rowdata[i][2] = TEST105.MYLIST.get(i).get_Sirname();
            rowdata[i][3] = TEST105.MYLIST.get(i).get_age();
            rowdata[i][4] = TEST105.MYLIST.get(i).get_Average();
            rowdata[i][5] = TEST105.MYLIST.get(i).get_ID();
            
        }
        DefaultTableModel tablemodle = new DefaultTableModel(rowdata, colomname);
        MainTable.setModel(tablemodle);
    }
    
    public void sort () {
        int n = MYLIST.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (MYLIST.get(j).get_Average() > MYLIST.get(j+1).get_Average()) 
                { 
                    
                    Student temp = MYLIST.get(j); 
                    Student temp2 = MYLIST.get(j+1);
                    MYLIST.set(j, temp2); 
                    MYLIST.set(j+1, temp); 
                } 
    }
    
    public void add () {
        try {
            String name = String.valueOf(NameTF.getText());
            String Sirname = String.valueOf(SirnameTF.getText());
            float Average = Float.valueOf(AverageTF.getText());
            int age = Integer.parseInt(AgeTF.getText());
            String id = String.valueOf(IDTF.getText());
            TEST105.MYLIST.add(new Student(name, Sirname, age, Average, id));
        } catch (NumberFormatException ex1) {
        }
        update();
    }
    
    public void put_text() {
        try {
            clear();
            NameTF.setText(String.valueOf(TEST105.MYLIST.get(SELECTED_ROW).get_name()));
            AgeTF.setText(String.valueOf(TEST105.MYLIST.get(SELECTED_ROW).get_age()));
            IDTF.setText(String.valueOf(TEST105.MYLIST.get(SELECTED_ROW).get_ID()));
            SirnameTF.setText(String.valueOf(TEST105.MYLIST.get(SELECTED_ROW).get_Sirname()));
            AverageTF.setText(String.valueOf(TEST105.MYLIST.get(SELECTED_ROW).get_Average()));

        } catch (Exception ex1) {
            
        }
    }
    
    public void clear() {
        NameTF.setText("");
        AgeTF.setText("");
        IDTF.setText("");
        SirnameTF.setText("");
        AverageTF.setText("");
        SearchTF.setText("Search by ID");
    }
    
    public void Edit() {
        try {

            String name = String.valueOf(NameTF.getText());
            int age = Integer.parseInt(AgeTF.getText());
            String id = String.valueOf(IDTF.getText());
            String sirname = String.valueOf(SirnameTF.getText());
            float Average = Float.parseFloat(AverageTF.getText());

            TEST105.MYLIST.get(SELECTED_ROW).set_Name(name);
            TEST105.MYLIST.get(SELECTED_ROW).set_Sirname(sirname);
            TEST105.MYLIST.get(SELECTED_ROW).set_Average(Average);
            TEST105.MYLIST.get(SELECTED_ROW).set_ID(id);
            TEST105.MYLIST.get(SELECTED_ROW).set_age(age);

        } catch (NumberFormatException ex2) {
            
        }
        update();
    }
    
    public void Save() {
        try {
            ObjectOutputStream objectoutputstream;
            try (FileOutputStream outputstream = new FileOutputStream(TEST105.MAINFILE)) {
                objectoutputstream = new ObjectOutputStream(outputstream);
                objectoutputstream.writeObject(TEST105.MYLIST);
                outputstream.flush();
            }
            objectoutputstream.close();
        }catch (IOException ex) {

        }
    }
    
    public void search() {
        String searchitem = SearchTF.getText();
        ArrayList<Student> searchlist = new ArrayList<>();
        for (int  j=0; j < MYLIST.size();j++) {
            if (searchitem == null ? MYLIST.get(j).get_ID() == null : searchitem.equals(MYLIST.get(j).get_ID())) {
                searchlist.add(MYLIST.get(j));
            }
        }
        
        String colomname [] = {"Number" , "Name" , "Sirname" , "Age" , "Average" , "ID"};
        Object rowdata [][] = new Object [searchlist.size()][6];
        
        for (int i = 0 ; i < searchlist.size(); i++ ) {
            rowdata[i][0] = String.valueOf(i+1);
            rowdata[i][1] = searchlist.get(i).get_name();
            rowdata[i][2] = searchlist.get(i).get_Sirname();
            rowdata[i][3] = searchlist.get(i).get_age();
            rowdata[i][4] = searchlist.get(i).get_Average();
            rowdata[i][5] = searchlist.get(i).get_ID();
            
        }
        DefaultTableModel tablemodle = new DefaultTableModel(rowdata, colomname);
        MainTable.setModel(tablemodle);
       
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MainTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        ADDButton = new javax.swing.JButton();
        CLEARButton = new javax.swing.JButton();
        EDITButton = new javax.swing.JButton();
        REMOVEButoon = new javax.swing.JButton();
        SAVEButton = new javax.swing.JButton();
        SearchTF = new javax.swing.JTextField();
        SORTButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        NameTF = new javax.swing.JTextField();
        SirnameTF = new javax.swing.JTextField();
        AgeTF = new javax.swing.JTextField();
        AverageTF = new javax.swing.JTextField();
        IDTF = new javax.swing.JTextField();

        jButton1.setText("jButton1");

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 126, 234));

        jPanel1.setBackground(new java.awt.Color(42, 82, 152));
        jPanel1.setForeground(new java.awt.Color(102, 126, 234));

        MainTable.setBackground(new java.awt.Color(161, 196, 253));
        MainTable.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
        MainTable.setForeground(new java.awt.Color(0, 0, 0));
        MainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        MainTable.setColumnSelectionAllowed(true);
        MainTable.setGridColor(new java.awt.Color(102, 126, 234));
        MainTable.setSurrendersFocusOnKeystroke(true);
        MainTable.getTableHeader().setReorderingAllowed(false);
        MainTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MainTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(MainTable);
        MainTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel2.setBackground(new java.awt.Color(183, 248, 219));

        ADDButton.setBackground(new java.awt.Color(161, 196, 253));
        ADDButton.setForeground(new java.awt.Color(0, 0, 0));
        ADDButton.setText("Add");
        ADDButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDButtonActionPerformed(evt);
            }
        });

        CLEARButton.setBackground(new java.awt.Color(161, 196, 253));
        CLEARButton.setForeground(new java.awt.Color(0, 0, 0));
        CLEARButton.setText("Clear");
        CLEARButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARButtonActionPerformed(evt);
            }
        });

        EDITButton.setBackground(new java.awt.Color(161, 196, 253));
        EDITButton.setForeground(new java.awt.Color(0, 0, 0));
        EDITButton.setText("Edit");
        EDITButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EDITButtonActionPerformed(evt);
            }
        });

        REMOVEButoon.setBackground(new java.awt.Color(161, 196, 253));
        REMOVEButoon.setForeground(new java.awt.Color(0, 0, 0));
        REMOVEButoon.setText("Remove");
        REMOVEButoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REMOVEButoonActionPerformed(evt);
            }
        });

        SAVEButton.setBackground(new java.awt.Color(161, 196, 253));
        SAVEButton.setForeground(new java.awt.Color(0, 0, 0));
        SAVEButton.setText("Save");
        SAVEButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SAVEButtonActionPerformed(evt);
            }
        });

        SearchTF.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        SearchTF.setText("Search by ID");
        SearchTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchTFMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SearchTFMouseExited(evt);
            }
        });
        SearchTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchTFActionPerformed(evt);
            }
        });

        SORTButton.setBackground(new java.awt.Color(161, 196, 253));
        SORTButton.setForeground(new java.awt.Color(0, 0, 0));
        SORTButton.setText("Sort");
        SORTButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SORTButtonActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(183, 248, 219));

        jLabel1.setBackground(new java.awt.Color(183, 248, 219));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("NAME : ");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("SirName : ");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Age  : ");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Average : ");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("ID : ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AverageTF, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AgeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SirnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IDTF, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SirnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AgeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AverageTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SearchTF)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ADDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SORTButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SAVEButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CLEARButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(REMOVEButoon, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EDITButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(SearchTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ADDButton)
                    .addComponent(CLEARButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SAVEButton)
                    .addComponent(SORTButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(REMOVEButoon)
                    .addComponent(EDITButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MainTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MainTableMouseClicked
        REMOVEButoon.setVisible(true);
        EDITButton.setVisible(true);
        SELECTED_ROW = MainTable.getSelectedRow();
        put_text();
    }//GEN-LAST:event_MainTableMouseClicked

    private void SORTButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SORTButtonActionPerformed
        sort();
        update();
    }//GEN-LAST:event_SORTButtonActionPerformed

    private void SearchTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchTFActionPerformed
        search();
    }//GEN-LAST:event_SearchTFActionPerformed

    private void SearchTFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchTFMouseExited

    }//GEN-LAST:event_SearchTFMouseExited

    private void SearchTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchTFMouseClicked
        SearchTF.setText("");
    }//GEN-LAST:event_SearchTFMouseClicked

    private void SAVEButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SAVEButtonActionPerformed
        Save();
    }//GEN-LAST:event_SAVEButtonActionPerformed

    private void REMOVEButoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REMOVEButoonActionPerformed
        try {
            TEST105.MYLIST.remove(SELECTED_ROW);
            update();
        } catch (Exception ex2) {

        }
    }//GEN-LAST:event_REMOVEButoonActionPerformed

    private void EDITButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EDITButtonActionPerformed
        Edit();
        
    }//GEN-LAST:event_EDITButtonActionPerformed

    private void CLEARButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARButtonActionPerformed
        clear();
        update();
    }//GEN-LAST:event_CLEARButtonActionPerformed

    private void ADDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDButtonActionPerformed
       add();
    }//GEN-LAST:event_ADDButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADDButton;
    private javax.swing.JTextField AgeTF;
    private javax.swing.JTextField AverageTF;
    private javax.swing.JButton CLEARButton;
    private javax.swing.JButton EDITButton;
    private javax.swing.JTextField IDTF;
    private javax.swing.JTable MainTable;
    private javax.swing.JTextField NameTF;
    private javax.swing.JButton REMOVEButoon;
    private javax.swing.JButton SAVEButton;
    private javax.swing.JButton SORTButton;
    private javax.swing.JTextField SearchTF;
    private javax.swing.JTextField SirnameTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
