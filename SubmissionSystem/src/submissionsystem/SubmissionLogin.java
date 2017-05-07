/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package submissionsystem;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author abdullah.talayhan-ug
 */
public class SubmissionLogin extends javax.swing.JFrame {
    
    Client client = null;
    RegFrame regFrame;
    /**
     * Creates new form Submission_Login
     */
    public SubmissionLogin(Client pclient) {
        client = pclient;
		client.sendUTFDataToServer("REGISTER_OPEN");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				client.closeConnection();
			}
		});
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mailField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        verifCodeField = new javax.swing.JTextField();
        confirmVerifCodeButton = new javax.swing.JButton();
        sendVerifCodeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("E-mail:");

        mailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mailFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Verification Code: ");

        confirmVerifCodeButton.setText("Confirm");
        confirmVerifCodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmVerifCodeButtonActionPerformed(evt);
            }
        });

        sendVerifCodeButton.setText("Send Code:");
        sendVerifCodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendVerifCodeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sendVerifCodeButton)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(mailField, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                        .addComponent(verifCodeField))
                    .addComponent(confirmVerifCodeButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(verifCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmVerifCodeButton)
                    .addComponent(sendVerifCodeButton))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mailFieldActionPerformed

    private void sendVerifCodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendVerifCodeButtonActionPerformed
        // TODO add your handling code here:
        // send e-mail
        client.sendUTFDataToServer(mailField.getText());
    }//GEN-LAST:event_sendVerifCodeButtonActionPerformed

    private void confirmVerifCodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmVerifCodeButtonActionPerformed
        // TODO add your handling code here:
        String ans = client.getUTFDataFromServer();
        
        // not valid email
        if(ans.equals("IS_EMAIL")) {
                client.sendUTFDataToServer(verifCodeField.getText());
                if(client.getUTFDataFromServer().equals("ACCEPT_USER")) {
                        //System.out.println(client.getUTFDataFromServer());

                        regFrame = new RegFrame(client, mailField.getText());
                        //regFrame = new RegFrame();
                        regFrame.setVisible(true);


                        dispose();
                }

        }
        else {
                System.out.println(ans);
        }
    }//GEN-LAST:event_confirmVerifCodeButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmVerifCodeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField mailField;
    private javax.swing.JButton sendVerifCodeButton;
    private javax.swing.JTextField verifCodeField;
    // End of variables declaration//GEN-END:variables
}
