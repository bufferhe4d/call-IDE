package submissionsystem.userinterface;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import submissionsystem.*;
/**
 * A class to create a frame for providing the functionality for the students in the submission system
 * @author Abdullah Talayhan
 */
public class StudentMain extends javax.swing.JFrame {
    
    Client client;
    String courseCode;
    int currentAsgnIndex;
    File subPathToSend;
    String downloadPath;
    Assignment toDown;
    ArrayList<Assignment> curAssignments;
    ArrayList<Assignment> pastAssignments;
    ArrayList<Assignment> allSubmissions;
    ArrayList<String> curAssignmentNames;
    ArrayList<String> pastAssignmentNames;
    public ArrayList<String> courseArrList;
    DefaultListModel curModel;
    DefaultListModel pastModel;
    FileInputStream fis;
    FileOutputStream fos;
    
    
    /**
     * Creates new form InsMain
     */
    public StudentMain(Client pclient) {
        client = pclient;
        
        initComponents();
        userNameLabel.setText("Welcome: " + client.getName() );
        curList.setModel(new DefaultListModel());
        pastList.setModel(new DefaultListModel());
        
        
        this.setSize(new Dimension(600, this.getHeight())); //-
        curPanel.setVisible(false);
        pastPanel.setVisible(false);
        
        initModels();
        initCourses();
        initCurAssignments();
        initPastAssignments();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pastList = new javax.swing.JList();
        curAssLabel = new javax.swing.JLabel();
        selectLabel = new javax.swing.JLabel();
        courseCombo = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        curList = new javax.swing.JList();
        pastAssLabel = new javax.swing.JLabel();
        enrollBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pastPanel = new javax.swing.JPanel();
        asgnLabel = new javax.swing.JLabel();
        pastAsName = new javax.swing.JLabel();
        dueLabel = new javax.swing.JLabel();
        pastAsDueDate = new javax.swing.JLabel();
        downSubBtn = new javax.swing.JButton();
        pastStateLabel = new javax.swing.JLabel();
        pastAsSubDate = new javax.swing.JLabel();
        pastGradeLabel = new javax.swing.JLabel();
        downAsgnFromSubBtn = new javax.swing.JButton();
        gradeLabel = new javax.swing.JLabel();
        curPanel = new javax.swing.JPanel();
        curAsgnLabel = new javax.swing.JLabel();
        curAsName = new javax.swing.JLabel();
        curDueaLabel = new javax.swing.JLabel();
        curAsDate = new javax.swing.JLabel();
        downAsgnBtn = new javax.swing.JButton();
        chooseSubPathBtn = new javax.swing.JButton();
        subPathField = new javax.swing.JTextField();
        curStateLabel = new javax.swing.JLabel();
        curSubLabel = new javax.swing.JLabel();
        submitBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        userNameLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("My Assignments");

        pastList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Lab01", "Lab02", "Lab03", "Lab04", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(pastList);

        curAssLabel.setText("Current Assignments");

        selectLabel.setText("Select Course: ");

        courseCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CS101-03", "CS223-01", "CS319-16" }));
        courseCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseComboActionPerformed(evt);
            }
        });

        curList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Lab05" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(curList);

        pastAssLabel.setText("Past Assignments");

        enrollBtn.setText("Enroll");
        enrollBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollBtnActionPerformed(evt);
            }
        });

        pastPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pastPanel.setPreferredSize(new java.awt.Dimension(327, 163));

        asgnLabel.setText("Assignment: ");

        pastAsName.setText("Lab01");

        dueLabel.setText("Due Date: ");

        pastAsDueDate.setText("21.03.2017");

        downSubBtn.setText("Downlaod Submission");
        downSubBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downSubBtnActionPerformed(evt);
            }
        });

        pastStateLabel.setText("State: ");

        pastAsSubDate.setText("Submitted(21.03.2017)");

        pastGradeLabel.setText("Grade: ");

        downAsgnFromSubBtn.setText("Downlaod Assignment");
        downAsgnFromSubBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downAsgnFromSubBtnActionPerformed(evt);
            }
        });

        gradeLabel.setText("100");
        gradeLabel.setToolTipText("");

        javax.swing.GroupLayout pastPanelLayout = new javax.swing.GroupLayout(pastPanel);
        pastPanel.setLayout(pastPanelLayout);
        pastPanelLayout.setHorizontalGroup(
            pastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pastPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pastPanelLayout.createSequentialGroup()
                        .addComponent(pastGradeLabel)
                        .addGap(18, 18, 18)
                        .addComponent(gradeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(downSubBtn))
                    .addGroup(pastPanelLayout.createSequentialGroup()
                        .addGroup(pastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(asgnLabel)
                            .addComponent(dueLabel))
                        .addGap(18, 18, 18)
                        .addGroup(pastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pastAsName)
                            .addComponent(pastAsDueDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(downAsgnFromSubBtn))
                    .addGroup(pastPanelLayout.createSequentialGroup()
                        .addComponent(pastStateLabel)
                        .addGap(21, 21, 21)
                        .addComponent(pastAsSubDate)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pastPanelLayout.setVerticalGroup(
            pastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pastPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pastPanelLayout.createSequentialGroup()
                        .addGroup(pastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(asgnLabel)
                            .addComponent(pastAsName))
                        .addGap(5, 5, 5)
                        .addGroup(pastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dueLabel)
                            .addComponent(pastAsDueDate)))
                    .addComponent(downAsgnFromSubBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(pastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pastAsSubDate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pastStateLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(pastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pastGradeLabel)
                    .addComponent(gradeLabel)
                    .addComponent(downSubBtn))
                .addGap(15, 15, 15))
        );

        jPanel1.add(pastPanel);

        curPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        curPanel.setPreferredSize(new java.awt.Dimension(327, 163));

        curAsgnLabel.setText("Assignment: ");

        curAsName.setText("Lab01");

        curDueaLabel.setText("Due Date: ");

        curAsDate.setText("21.03.2017");

        downAsgnBtn.setText("Downlaod Assignment");
        downAsgnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downAsgnBtnActionPerformed(evt);
            }
        });

        chooseSubPathBtn.setText("Choose File");
        chooseSubPathBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseSubPathBtnActionPerformed(evt);
            }
        });

        curStateLabel.setText("State: ");

        curSubLabel.setText("Not Submitted / Submitted");

        submitBtn.setText("Submit Assignment");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout curPanelLayout = new javax.swing.GroupLayout(curPanel);
        curPanel.setLayout(curPanelLayout);
        curPanelLayout.setHorizontalGroup(
            curPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(curPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(curPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(curPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(curPanelLayout.createSequentialGroup()
                                .addComponent(curDueaLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(curAsDate))
                            .addGroup(curPanelLayout.createSequentialGroup()
                                .addComponent(curAsgnLabel)
                                .addGap(18, 18, 18)
                                .addComponent(curAsName)
                                .addGap(28, 28, 28)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(downAsgnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, curPanelLayout.createSequentialGroup()
                        .addGroup(curPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(curPanelLayout.createSequentialGroup()
                                .addComponent(curStateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addComponent(curSubLabel))
                            .addComponent(subPathField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                        .addGroup(curPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(submitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chooseSubPathBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        curPanelLayout.setVerticalGroup(
            curPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(curPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(curPanelLayout.createSequentialGroup()
                        .addGroup(curPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(curAsgnLabel)
                            .addComponent(curAsName))
                        .addGap(5, 5, 5)
                        .addGroup(curPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(curDueaLabel)
                            .addComponent(curAsDate)))
                    .addComponent(downAsgnBtn))
                .addGap(25, 25, 25)
                .addGroup(curPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(curPanelLayout.createSequentialGroup()
                        .addGroup(curPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chooseSubPathBtn)
                            .addComponent(subPathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(submitBtn))
                    .addGroup(curPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(curSubLabel)
                        .addComponent(curStateLabel)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel1.add(curPanel);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        userNameLabel.setText("Welcome");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pastAssLabel)
                    .addComponent(curAssLabel)
                    .addComponent(userNameLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(selectLabel)
                        .addGap(6, 6, 6)
                        .addComponent(courseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enrollBtn)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectLabel)
                    .addComponent(courseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enrollBtn)
                    .addComponent(jButton1)
                    .addComponent(userNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(curAssLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pastAssLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void enrollBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollBtnActionPerformed
        // TODO add your handling code here:
        EnrollCourse enrollFrame = new EnrollCourse(client);
        enrollFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        enrollFrame.setLocationRelativeTo(this);
        enrollFrame.setVisible(true);
    }//GEN-LAST:event_enrollBtnActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        // TODO add your handling code here:
        Assignment asgn = curAssignments.get(currentAsgnIndex);
        //subPathToSend = subPathField.getText();
        
        long length = subPathToSend.length();
        
        if(length > Integer.MAX_VALUE) {
            JOptionPane.showMessageDialog(this, "Assignment file is more than 2 GB", "Call-IDE Error!", JOptionPane.ERROR);

        }
        else {
            client.sendUTFDataToServer("SEND_SUBMISSION");
            long millis=System.currentTimeMillis();
            String section = (String)courseCombo.getSelectedItem();
            section = section.substring(section.lastIndexOf("-") + 1);
            String subFileName = section + "_" + client.getName() + "_" + asgn.getName();
            Assignment subToSend = new Assignment(subFileName, asgn.getAuthor(), asgn.getDuedate(), new Date(millis), subPathToSend.getAbsolutePath(), -1);
            client.sendObjectToServer(subToSend);
            client.sendUTFDataToServer((String)courseCombo.getSelectedItem());
            try {
                ZipParameters zipParams = new ZipParameters();
                zipParams.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
                zipParams.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
                
                
                
                String compressedPath = subPathToSend.getParent() + "/" + section + "_" + client.getName() + "_" + subToSend.getName()  + ".zip";
                System.out.println(compressedPath);
                ZipFile compressedFile;
            
                compressedFile = new ZipFile(compressedPath);
            
                
                compressedFile.addFolder(subPathToSend, zipParams);
                File toSendZip = new File(compressedPath);
                
                fis = new FileInputStream(toSendZip);
                byte[] data = new byte[(int) toSendZip.length()];
                
                fis.read(data);
                System.out.println(java.util.Arrays.toString(data));
                fis.close();
               
                client.sendObjectToServer(data);
                
                String result = client.getUTFDataFromServer();
                if(result.equals("DONE")) {
                    JOptionPane.showMessageDialog(this, "Submission Complete", "Call-IDE Submission System!", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(this, "Submisson Failed", "Call-IDE Error!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ZipException ex) {
                Logger.getLogger(StudentMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StudentMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StudentMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_submitBtnActionPerformed

    private void chooseSubPathBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseSubPathBtnActionPerformed
        // TODO add your handling code here:
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showOpenDialog(this);
        
        subPathToSend = f.getSelectedFile();
        subPathField.setText(subPathToSend.getAbsolutePath());
        
    }//GEN-LAST:event_chooseSubPathBtnActionPerformed

    private void downAsgnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downAsgnBtnActionPerformed
        // TODO add your handling code here:
        toDown = curAssignments.get(currentAsgnIndex);
        downloadFrom(toDown);
        
    }//GEN-LAST:event_downAsgnBtnActionPerformed

    private void downAsgnFromSubBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downAsgnFromSubBtnActionPerformed
        // TODO add your handling code here:
        toDown = pastAssignments.get(currentAsgnIndex);
        downloadFrom(toDown);
    }//GEN-LAST:event_downAsgnFromSubBtnActionPerformed
    /**
     * A method to download the assignment from the submission system server
     * @param toDown a parameter to take the assignment in Assignment type
     */
    private void downloadFrom(Assignment toDown ) {
        client.sendUTFDataToServer("DOWNLOAD_ASGN");
        client.sendObjectToServer(toDown);
        
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(this);
        File downFolder = chooser.getSelectedFile();
        
        
        byte[] data = (byte[]) client.getObjectFromServer();
        try {
            fos = new FileOutputStream(downFolder.getAbsolutePath() + "/" + toDown.getName() + ".zip");
            fos.write(data);
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(StudentMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    private void courseComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseComboActionPerformed
        // TODO add your handling code here:
        //initCourses();
        initCurAssignments();
        initPastAssignments();
    }//GEN-LAST:event_courseComboActionPerformed

    private void downSubBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downSubBtnActionPerformed
        // TODO add your handling code here:
        toDown = allSubmissions.get(currentAsgnIndex);
        downloadFrom(toDown);
    }//GEN-LAST:event_downSubBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        initCourses();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * Initializes the JTable models
     */
    public void initModels() {
        curModel = (DefaultListModel) curList.getModel();
        pastModel = (DefaultListModel) pastList.getModel();
        //curModel = new DefaultListModel();
        //pastModel = new DefaultListModel();
        curModel.clear();
        pastModel.clear();
        
          MouseListener curMouseListener = new MouseAdapter() {
          public void mouseClicked(MouseEvent mouseEvent) {
            JList theList = (JList) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 2) {
              int index = theList.locationToIndex(mouseEvent.getPoint());
              if (index >= 0) {
                currentAsgnIndex = index;
                Assignment temp = curAssignments.get(index);
                if(getSubmitted(temp) != null) {
                    constructPastPanel(index, temp);
                    if(!pastPanel.isVisible()) {
                    curPanel.setVisible(false);
                    pastPanel.setVisible(true);
                    }
                }
                else {
                    Object o = theList.getModel().getElementAt(index);
                    curAsName.setText(temp.getName());
                    curAsDate.setText(temp.getDuedate().toString());
                    if(!curPanel.isVisible()) {
                        pastPanel.setVisible(false);
                        curPanel.setVisible(true);
                    }
                    System.out.println("Double-clicked on: " + o.toString());
                }
              }
            }
          }
        };
          MouseListener pastMouseListener = new MouseAdapter() {
          public void mouseClicked(MouseEvent mouseEvent) {
            JList theList = (JList) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 2) {
              int index = theList.locationToIndex(mouseEvent.getPoint());
              if (index >= 0) {
                currentAsgnIndex = index;
                
                
                constructPastPanel(index);
                
                if(!pastPanel.isVisible()) {
                    curPanel.setVisible(false);
                    pastPanel.setVisible(true);
                }
                
              }
            }
          }
        };
        curList.addMouseListener(curMouseListener);
        pastList.addMouseListener(pastMouseListener);
    }
    /**
     * A method to construct the panel for the past assignments
     * @param index a parameter to take the index of the assignments
     */
    public void constructPastPanel(int index) {
        Assignment temp = pastAssignments.get(index);
        pastAsName.setText(temp.getName());
        pastAsDueDate.setText(temp.getDuedate().toString());
        Assignment tempSub = getSubmitted(temp);
        System.out.println(temp.getName());
        if(tempSub != null) {
            pastAsSubDate.setText("Submitted(" + tempSub.getSubdate() + ")");
            downSubBtn.setEnabled(true);
            if(tempSub.getGrade() != -1) {
                gradeLabel.setText("" + tempSub.getGrade());
            }
            else {
                gradeLabel.setText("-");
            }
        }
        else {
            pastAsSubDate.setText("Not Submitted");
            downSubBtn.setEnabled(false);
            gradeLabel.setText("-");
        }
    }
    /**
     * A method to construct the panel for the past assignments
     * @param index a parameter to take the index of the assignments
     * @param temp a parameter to take the past assignments
     */
    public void constructPastPanel(int index, Assignment temp) {
        //Assignment temp = pastAssignments.get(index);
        pastAsName.setText(temp.getName());
        pastAsDueDate.setText(temp.getDuedate().toString());
        Assignment tempSub = getSubmitted(temp);
        System.out.println(temp.getName());
        if(tempSub != null) {
            pastAsSubDate.setText("Submitted(" + tempSub.getSubdate() + ")");
            downSubBtn.setEnabled(true);
            if(tempSub.getGrade() != -1) {
                gradeLabel.setText("" + tempSub.getGrade());
            }
            else {
                gradeLabel.setText("-");
            }
        }
        else {
            pastAsSubDate.setText("Not Submitted");
            downSubBtn.setEnabled(false);
            gradeLabel.setText("-");
        }
    }
    /**
     * A method to get the submitted files from server
     * @param asgn a parameter to take the assignment from the server
     * @return the submitted assignment, if it exists; null, if not
     */
    public Assignment getSubmitted(Assignment asgn) {
        
        for( Assignment temp : allSubmissions) {
            String asgnName = temp.getName().substring(temp.getName().lastIndexOf("_") + 1);
            if(asgn.getName().equals(asgnName)) {
                return temp;
            }
        }
        return null;
    }
    /**
     * A method to get the current assignments from server and update the table of the current assignments
     */
    public void initCurAssignments() {
        client.sendUTFDataToServer("GET_CUR_ASSIGNMENTS");
        courseCode = (String)courseCombo.getSelectedItem();
        curModel.clear();
        if(courseCode != null) {
            client.sendUTFDataToServer(courseCode);
            System.out.println((String)courseCombo.getSelectedItem());
            curAssignments = (ArrayList<Assignment>) client.getObjectFromServer();
            curAssignmentNames = new ArrayList<String>();

            for(int i = 0; i < curAssignments.size(); i++) {
                curAssignmentNames.add(curAssignments.get(i).getName());
            }

            for(int i = 0; i < curAssignmentNames.size(); i++) {
                curModel.addElement(curAssignmentNames.get(i));
            }
        }
        else {
            client.sendUTFDataToServer("NO_COURSE");
        }
    }
    
    /**
     * A method to get the past assignments from the server and update the table according to that
     */
    public void initPastAssignments() {
        client.sendUTFDataToServer("GET_PAST_ASSIGNMENTS");
        courseCode = (String)courseCombo.getSelectedItem();
        pastModel.clear();
        if(courseCode != null) {
            client.sendUTFDataToServer(courseCode);
            pastAssignments = (ArrayList<Assignment>) client.getObjectFromServer();
            allSubmissions = (ArrayList<Assignment>) client.getObjectFromServer();
            pastAssignmentNames = new ArrayList<String>();

            for(int i = 0; i < pastAssignments.size(); i++) {
                pastAssignmentNames.add(pastAssignments.get(i).getName());
            }

            for(int i = 0; i < pastAssignmentNames.size(); i++) {
                pastModel.addElement(pastAssignmentNames.get(i));
            }
        }
        else {
            client.sendUTFDataToServer("NO_COURSE");
        }
        
    }
    /**
     * A method to get the current courses from server and update the courses
     */
    public void initCourses() {
        client.sendUTFDataToServer("GET_COURSES");
        courseArrList = (ArrayList)client.getObjectFromServer();
        
        courseCombo.setModel(new DefaultComboBoxModel(courseArrList.toArray()));
    }
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asgnLabel;
    private javax.swing.JButton chooseSubPathBtn;
    private javax.swing.JComboBox courseCombo;
    private javax.swing.JLabel curAsDate;
    private javax.swing.JLabel curAsName;
    private javax.swing.JLabel curAsgnLabel;
    private javax.swing.JLabel curAssLabel;
    private javax.swing.JLabel curDueaLabel;
    private javax.swing.JList curList;
    private javax.swing.JPanel curPanel;
    private javax.swing.JLabel curStateLabel;
    private javax.swing.JLabel curSubLabel;
    private javax.swing.JButton downAsgnBtn;
    private javax.swing.JButton downAsgnFromSubBtn;
    private javax.swing.JButton downSubBtn;
    private javax.swing.JLabel dueLabel;
    private javax.swing.JButton enrollBtn;
    private javax.swing.JLabel gradeLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel pastAsDueDate;
    private javax.swing.JLabel pastAsName;
    private javax.swing.JLabel pastAsSubDate;
    private javax.swing.JLabel pastAssLabel;
    private javax.swing.JLabel pastGradeLabel;
    private javax.swing.JList pastList;
    private javax.swing.JPanel pastPanel;
    private javax.swing.JLabel pastStateLabel;
    private javax.swing.JLabel selectLabel;
    private javax.swing.JTextField subPathField;
    private javax.swing.JButton submitBtn;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}
