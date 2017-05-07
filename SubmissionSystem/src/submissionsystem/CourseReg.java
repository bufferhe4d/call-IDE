/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package submissionsystem;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author abdullah.talayhan-ug
 */
public class CourseReg extends javax.swing.JFrame {

    Client client;
    String cKey;
    InsMain insMain;
    /**
     * Creates new form CourseReg
     */
    public CourseReg( Client pclient, InsMain prev) {
        insMain =  prev;
        client = pclient;
        initComponents();
        promptLabel.setVisible(false);
        copyToClipBtn.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        courseCodeField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        createCourseBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        courseSectionField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        promptLabel = new javax.swing.JLabel();
        copyToClipBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register Course");

        jLabel1.setText("Course Code:");

        courseCodeField.setText("jTextField1");
        courseCodeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseCodeFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Course Title:");

        titleField.setText("jTextField2");

        createCourseBtn.setText("Create Class");
        createCourseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCourseBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Section No:");

        courseSectionField.setText("jTextField3");

        jLabel4.setText("Institude Code: ");

        jLabel5.setText("bilkent.edu.tr");

        promptLabel.setText("Class Key for CS101-03 :   QX7V6695D");

        copyToClipBtn.setText("Copy ");
        copyToClipBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyToClipBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(promptLabel)
                        .addGap(18, 18, 18)
                        .addComponent(copyToClipBtn))
                    .addComponent(createCourseBtn)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1))
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(courseCodeField, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(courseSectionField)
                            .addComponent(titleField))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(courseCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(courseSectionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(30, 30, 30)
                .addComponent(createCourseBtn)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(promptLabel)
                    .addComponent(copyToClipBtn))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createCourseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCourseBtnActionPerformed
        // TODO add your handling code here:
        
        String finalCode = courseCodeField.getText() + "-" + courseSectionField.getText();
        String title = titleField.getText();
        client.sendUTFDataToServer("CREATE_COURSE");
        client.sendUTFDataToServer(finalCode);
        client.sendUTFDataToServer(title);
        String ans = client.getUTFDataFromServer();
        if(ans.equals("COURSE_CREATED")) {
            cKey = client.getUTFDataFromServer();
            insMain.updateCourseList();
            promptLabel.setText("Class Key for " + finalCode +  ": " +  cKey);
            promptLabel.setVisible(true);
            copyToClipBtn.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(this, "Class Already Exists", "Call-IDE Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_createCourseBtnActionPerformed

    private void courseCodeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseCodeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_courseCodeFieldActionPerformed

    private void copyToClipBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyToClipBtnActionPerformed
        // TODO add your handling code here:
        StringSelection selection = new StringSelection(cKey);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        if(cKey != null) {
            clipboard.setContents(selection, selection);
        }
    }//GEN-LAST:event_copyToClipBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton copyToClipBtn;
    private javax.swing.JTextField courseCodeField;
    private javax.swing.JTextField courseSectionField;
    private javax.swing.JButton createCourseBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel promptLabel;
    private javax.swing.JTextField titleField;
    // End of variables declaration//GEN-END:variables
}
