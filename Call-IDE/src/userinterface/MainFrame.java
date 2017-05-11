package userinterface;

import editor.*;
import filebrowser.*;
import fileoperations.*;
import fileoperations.configurers.*;
import fileoperations.projecthandling.*;
import helputils.*;
import methodsummary.*;
import runutils.*;
import submissionsystem.userinterface.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;

import filedrop.*;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

import com.github.javaparser.*;
import com.github.javaparser.ast.*;

/**
 * The main frame of the IDE.
 * @author Emin Bahadir Tuluce, Halil Sahiner, Abdullah Talayhan
 */
public class MainFrame extends JFrame implements NavigationParent, AutosaveHandler, Attachable, NodeVisitor {
            
    /** Creates the main frame of the IDE. */
    public MainFrame( String openWith) throws IOException {
        initStreams();
        initProperties();
        loadSources();
        initComponents();
        initOtherComponents();
        loadProfile();
        enableFileDrop( this);
        newFile();
        initFrame();
        // checkJDK();
                
        if (openWith != null )
        {
           if ( openWith.endsWith(ProjectHandler.EXTENSION) )
              openProject( new File( openWith).getParentFile());
           else if( openWith.endsWith(".java") )
              openFile( new File( openWith) );
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * This code is generedated by NetBeans Form Editor.
     * WARNING: Do not modify the method.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        preferencesFrame = new javax.swing.JFrame();
        toolbarPreferencesPanel = new javax.swing.JPanel();
        newCheck = new javax.swing.JCheckBox();
        openCheck = new javax.swing.JCheckBox();
        saveCheck = new javax.swing.JCheckBox();
        undoCheck = new javax.swing.JCheckBox();
        redoCheck = new javax.swing.JCheckBox();
        compileCheck = new javax.swing.JCheckBox();
        runCheck = new javax.swing.JCheckBox();
        compileRunCheck = new javax.swing.JCheckBox();
        resetCheck = new javax.swing.JCheckBox();
        jarCheck = new javax.swing.JCheckBox();
        javadocCheck = new javax.swing.JCheckBox();
        apiCheck = new javax.swing.JCheckBox();
        helpCheck = new javax.swing.JCheckBox();
        loginCheck = new javax.swing.JCheckBox();
        idePreferencesPanel = new javax.swing.JPanel();
        themeLabel = new javax.swing.JLabel();
        editorFontSizeLabel = new javax.swing.JLabel();
        indentLabel = new javax.swing.JLabel();
        lineNumbersCheck = new javax.swing.JCheckBox();
        themeComboBox = new javax.swing.JComboBox<>();
        indentTextField = new javax.swing.JTextField();
        autosaveTextField = new javax.swing.JTextField();
        minsLabel = new javax.swing.JLabel();
        showHelpCheck = new javax.swing.JCheckBox();
        autosaveCheck = new javax.swing.JCheckBox();
        workspaceLabel = new javax.swing.JLabel();
        workspaceTextField = new javax.swing.JTextField();
        browseWorkspaceButton = new javax.swing.JButton();
        bracketMatchingCheck = new javax.swing.JCheckBox();
        editorFontLabel = new javax.swing.JLabel();
        editorFontSizeField = new javax.swing.JTextField();
        editorFontChooser = new javax.swing.JComboBox<>();
        outputFontSizeLabel = new javax.swing.JLabel();
        outputFontLabel = new javax.swing.JLabel();
        outputFontSizeField = new javax.swing.JTextField();
        outputFontChooser = new javax.swing.JComboBox<>();
        submissionSelectLabel = new javax.swing.JLabel();
        callideSubmissionRadio = new javax.swing.JRadioButton();
        externalSubmissionRadio = new javax.swing.JRadioButton();
        externalSubmissionField = new javax.swing.JTextField();
        detachConsoleCheck = new javax.swing.JCheckBox();
        preferecesButtonPanel = new javax.swing.JPanel();
        preferencesOk = new javax.swing.JButton();
        preferencesCancel = new javax.swing.JButton();
        preferencesReset = new javax.swing.JButton();
        findAndReplaceFrame = new javax.swing.JFrame();
        findReplacePanel = new javax.swing.JPanel();
        replaceButton = new javax.swing.JButton();
        replaceAllButton = new javax.swing.JButton();
        findLabel = new javax.swing.JLabel();
        replaceLabel = new javax.swing.JLabel();
        findTextField = new javax.swing.JTextField();
        replaceTextField = new javax.swing.JTextField();
        nextButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        matchCaseCheck = new javax.swing.JCheckBox();
        wholeWordCheck = new javax.swing.JCheckBox();
        projectFrame = new javax.swing.JFrame();
        projectPanel = new javax.swing.JPanel();
        projectNameLabel = new javax.swing.JLabel();
        projectLocationLabel = new javax.swing.JLabel();
        mainClassField = new javax.swing.JTextField();
        mainClassLabel = new javax.swing.JLabel();
        classPathLabel = new javax.swing.JLabel();
        projectOkButton = new javax.swing.JButton();
        projectCancelButton = new javax.swing.JButton();
        classPathScrollPane = new javax.swing.JScrollPane();
        classPathList = new javax.swing.JList<>(new DefaultListModel<String>());
        classPathButton = new javax.swing.JButton();
        projectNameField = new javax.swing.JTextField();
        projectLocationField = new javax.swing.JTextField();
        browseLocationButton = new javax.swing.JButton();
        browseMainButton = new javax.swing.JButton();
        projectRootLabel = new javax.swing.JLabel();
        projectRootField = new javax.swing.JTextField();
        submissionButtonGroup = new javax.swing.ButtonGroup();
        mainSelectionFrame = new javax.swing.JFrame();
        mainSelectionLabel = new javax.swing.JLabel();
        mainSelectionScrollPane = new javax.swing.JScrollPane();
        mainSelectionList = new javax.swing.JList();
        selectMainButton = new javax.swing.JButton();
        helpContentsFrame = new javax.swing.JFrame();
        helpSplitPane = new javax.swing.JSplitPane();
        helpListScrollPane = new javax.swing.JScrollPane();
        helpList = new javax.swing.JList();
        helpPaneScrollPane = new javax.swing.JScrollPane();
        helpPane = new javax.swing.JTextPane();
        aboutFrame = new javax.swing.JFrame();
        logoLabel = new javax.swing.JLabel();
        developerListLabel = new javax.swing.JLabel();
        aboutSubPanel = new javax.swing.JPanel();
        aboutSubLabel = new javax.swing.JLabel();
        licenseLabel = new javax.swing.JLabel();
        mainSplitPane = new javax.swing.JSplitPane();
        topSplitPane = new javax.swing.JSplitPane();
        editorAndMethodSummaryPanel = new javax.swing.JPanel();
        topLeftSplitPane = new javax.swing.JSplitPane();
        textTabs = new javax.swing.JTabbedPane();
        methodSummaryScrollPane = new javax.swing.JScrollPane();
        placeholderMethodSummary = new javax.swing.JTree();
        explorerLayeredPane = new javax.swing.JLayeredPane();
        explorerScrollPane = new javax.swing.JScrollPane();
        placeHolderFileExplorer = new javax.swing.JTree();
        outputsPanel = new javax.swing.JPanel();
        outputTabs = new javax.swing.JTabbedPane();
        statusPanel = new javax.swing.JPanel();
        statusScrollPane = new javax.swing.JScrollPane();
        statusArea = new javax.swing.JTextArea();
        compilerOutputPanel = new javax.swing.JPanel();
        compilerOutputScrollPane = new javax.swing.JScrollPane();
        compilerOutputArea = new javax.swing.JTextArea();
        consoleOutputPanel = new javax.swing.JPanel();
        consoleOutputScrollPane = new javax.swing.JScrollPane();
        placeholderOutputArea = new javax.swing.JTextArea();
        toolbarPanel = new javax.swing.JPanel();
        newTool = new javax.swing.JButton();
        openTool = new javax.swing.JButton();
        saveTool = new javax.swing.JButton();
        undoTool = new javax.swing.JButton();
        redoTool = new javax.swing.JButton();
        resetTool = new javax.swing.JButton();
        javadocTool = new javax.swing.JButton();
        jarTool = new javax.swing.JButton();
        compileTool = new javax.swing.JButton();
        runTool = new javax.swing.JButton();
        compileRunTool = new javax.swing.JButton();
        apiTool = new javax.swing.JButton();
        helpTool = new javax.swing.JButton();
        loginTool = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newFileButton = new javax.swing.JMenuItem();
        newProjectButton = new javax.swing.JMenuItem();
        separator1 = new javax.swing.JPopupMenu.Separator();
        openFileButton = new javax.swing.JMenuItem();
        openProjectButton = new javax.swing.JMenuItem();
        openFolderButton = new javax.swing.JMenuItem();
        separator2 = new javax.swing.JPopupMenu.Separator();
        saveButton = new javax.swing.JMenuItem();
        saveAsButton = new javax.swing.JMenuItem();
        separator3 = new javax.swing.JPopupMenu.Separator();
        quitButton = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        undoButton = new javax.swing.JMenuItem();
        redoButton = new javax.swing.JMenuItem();
        separator4 = new javax.swing.JPopupMenu.Separator();
        selectAllButton = new javax.swing.JMenuItem();
        cutButton = new javax.swing.JMenuItem();
        copyButton = new javax.swing.JMenuItem();
        pasteButton = new javax.swing.JMenuItem();
        separator5 = new javax.swing.JPopupMenu.Separator();
        findReplaceButton = new javax.swing.JMenuItem();
        toolsMenu = new javax.swing.JMenu();
        submissionMenu = new javax.swing.JMenu();
        registerButton = new javax.swing.JMenuItem();
        loginButton = new javax.swing.JMenuItem();
        separator6 = new javax.swing.JPopupMenu.Separator();
        templatesMenu = new javax.swing.JMenu();
        newTemplateButton = new javax.swing.JMenuItem();
        separator7 = new javax.swing.JPopupMenu.Separator();
        placeHolderMenu1 = new javax.swing.JMenuItem();
        placeHolderMenu2 = new javax.swing.JMenuItem();
        placeHolderMenu3 = new javax.swing.JMenuItem();
        insertJavadocButton = new javax.swing.JMenuItem();
        separator8 = new javax.swing.JPopupMenu.Separator();
        javadocButton = new javax.swing.JMenuItem();
        jarButton = new javax.swing.JMenuItem();
        separator9 = new javax.swing.JPopupMenu.Separator();
        compileButton = new javax.swing.JMenuItem();
        runButton = new javax.swing.JMenuItem();
        compileRunButton = new javax.swing.JMenuItem();
        resetButton = new javax.swing.JMenuItem();
        separator10 = new javax.swing.JPopupMenu.Separator();
        preferencesButton = new javax.swing.JMenuItem();
        windowMenu = new javax.swing.JMenu();
        fileTrackerButton = new javax.swing.JCheckBoxMenuItem();
        methodSummaryButton = new javax.swing.JCheckBoxMenuItem();
        outputsPaneButton = new javax.swing.JCheckBoxMenuItem();
        separator11 = new javax.swing.JPopupMenu.Separator();
        restoreButton = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        apiButton = new javax.swing.JMenuItem();
        tutorialsButton = new javax.swing.JMenuItem();
        separator12 = new javax.swing.JPopupMenu.Separator();
        helpContentsButton = new javax.swing.JMenuItem();
        aboutButton = new javax.swing.JMenuItem();

        preferencesFrame.setTitle("Preferences");
        preferencesFrame.setResizable(false);

        toolbarPreferencesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Toolbar"));
        toolbarPreferencesPanel.setLayout(new java.awt.GridLayout(4, 4));

        newCheck.setSelected(true);
        newCheck.setText("New");
        toolbarPreferencesPanel.add(newCheck);

        openCheck.setSelected(true);
        openCheck.setText("Open");
        toolbarPreferencesPanel.add(openCheck);

        saveCheck.setSelected(true);
        saveCheck.setText("Save");
        toolbarPreferencesPanel.add(saveCheck);

        undoCheck.setSelected(true);
        undoCheck.setText("Undo");
        toolbarPreferencesPanel.add(undoCheck);

        redoCheck.setSelected(true);
        redoCheck.setText("Redo");
        toolbarPreferencesPanel.add(redoCheck);

        compileCheck.setSelected(true);
        compileCheck.setText("Compile");
        toolbarPreferencesPanel.add(compileCheck);

        runCheck.setSelected(true);
        runCheck.setText("Run");
        toolbarPreferencesPanel.add(runCheck);

        compileRunCheck.setSelected(true);
        compileRunCheck.setText("Compile&Run");
        toolbarPreferencesPanel.add(compileRunCheck);

        resetCheck.setSelected(true);
        resetCheck.setText("Reset");
        toolbarPreferencesPanel.add(resetCheck);

        jarCheck.setSelected(true);
        jarCheck.setText("Export Jar");
        toolbarPreferencesPanel.add(jarCheck);

        javadocCheck.setSelected(true);
        javadocCheck.setText("Javadoc");
        toolbarPreferencesPanel.add(javadocCheck);

        apiCheck.setSelected(true);
        apiCheck.setText("API Link");
        toolbarPreferencesPanel.add(apiCheck);

        helpCheck.setSelected(true);
        helpCheck.setText("Help");
        toolbarPreferencesPanel.add(helpCheck);

        loginCheck.setSelected(true);
        loginCheck.setText("Login");
        toolbarPreferencesPanel.add(loginCheck);

        idePreferencesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("IDE"));

        themeLabel.setText("Editor Theme");

        editorFontSizeLabel.setText("Size");

        indentLabel.setText("Indent Level:");

        lineNumbersCheck.setSelected(true);
        lineNumbersCheck.setText("Display line numbers");
        lineNumbersCheck.setVerifyInputWhenFocusTarget(false);

        themeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "default.xml", "dark.xml", "eclipse.xml", "idea.xml", "monokai.xml", "vs.xml" }));

        indentTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        indentTextField.setText("4");

        autosaveTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        autosaveTextField.setText("5");
        autosaveTextField.setEnabled(false);

        minsLabel.setText("mins");

        showHelpCheck.setText("Show help for errors");

        autosaveCheck.setText("Autosave in");
        autosaveCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autosaveCheckActionPerformed(evt);
            }
        });

        workspaceLabel.setText("Browser Path:");

        workspaceTextField.setEditable(false);
        workspaceTextField.setText("C:\\Users\\User\\Documents");

        browseWorkspaceButton.setText("Browse");
        browseWorkspaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseWorkspaceButtonActionPerformed(evt);
            }
        });

        bracketMatchingCheck.setText("Enable bracketmatching");

        editorFontLabel.setText("Editor Font");

        editorFontSizeField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editorFontSizeField.setText("16");

        editorFontChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arial", "Calibri", "Consolas", "Courier New", "Lucida Console", "Times New Roman", "Tahoma" }));

        outputFontSizeLabel.setText("Size");

        outputFontLabel.setText("Outputs Font");

        outputFontSizeField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        outputFontSizeField.setText("16");

        outputFontChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arial", "Calibri", "Consolas", "Courier New", "Lucida Console", "Times New Roman", "Tahoma" }));

        submissionSelectLabel.setText("Submission System:");

        submissionButtonGroup.add(callideSubmissionRadio);
        callideSubmissionRadio.setSelected(true);
        callideSubmissionRadio.setText("Default Call-IDE Submission System");
        callideSubmissionRadio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                callideSubmissionRadioStateChanged(evt);
            }
        });

        submissionButtonGroup.add(externalSubmissionRadio);
        externalSubmissionRadio.setText("External System:");
        externalSubmissionRadio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                externalSubmissionRadioStateChanged(evt);
            }
        });

        externalSubmissionField.setEnabled(false);

        detachConsoleCheck.setText("Detach console on run");

        javax.swing.GroupLayout idePreferencesPanelLayout = new javax.swing.GroupLayout(idePreferencesPanel);
        idePreferencesPanel.setLayout(idePreferencesPanelLayout);
        idePreferencesPanelLayout.setHorizontalGroup(
            idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                        .addComponent(submissionSelectLabel)
                        .addGap(18, 18, 18)
                        .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                .addComponent(externalSubmissionRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(externalSubmissionField)
                                .addContainerGap())
                            .addComponent(callideSubmissionRadio)))
                    .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                        .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                .addComponent(workspaceLabel)
                                .addGap(9, 9, 9)
                                .addComponent(workspaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(browseWorkspaceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(indentLabel)
                                        .addGap(15, 15, 15)
                                        .addComponent(indentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lineNumbersCheck)
                                    .addComponent(bracketMatchingCheck)
                                    .addComponent(showHelpCheck)
                                    .addComponent(detachConsoleCheck)
                                    .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                        .addComponent(autosaveCheck)
                                        .addGap(5, 5, 5)
                                        .addComponent(autosaveTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(minsLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(editorFontLabel)
                                        .addGap(162, 162, 162)
                                        .addComponent(editorFontSizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                        .addComponent(editorFontChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(editorFontSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                        .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(outputFontLabel))
                                            .addComponent(outputFontChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(6, 6, 6)
                                        .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(outputFontSizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(outputFontSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(themeLabel))
                                    .addComponent(themeComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        idePreferencesPanelLayout.setVerticalGroup(
            idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                        .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(indentLabel))
                            .addComponent(indentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(lineNumbersCheck)
                        .addGap(8, 8, 8)
                        .addComponent(showHelpCheck)
                        .addGap(8, 8, 8)
                        .addComponent(bracketMatchingCheck)
                        .addGap(8, 8, 8)
                        .addComponent(detachConsoleCheck)
                        .addGap(8, 8, 8)
                        .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(autosaveCheck)
                            .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(autosaveTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(minsLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, idePreferencesPanelLayout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addComponent(themeLabel)
                        .addGap(0, 0, 0)
                        .addComponent(themeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editorFontLabel)
                            .addComponent(editorFontSizeLabel))
                        .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editorFontChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editorFontSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                .addComponent(outputFontLabel)
                                .addGap(0, 0, 0)
                                .addComponent(outputFontChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(idePreferencesPanelLayout.createSequentialGroup()
                                .addComponent(outputFontSizeLabel)
                                .addGap(0, 0, 0)
                                .addComponent(outputFontSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38)))
                .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(workspaceLabel)
                    .addComponent(workspaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseWorkspaceButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submissionSelectLabel)
                    .addComponent(callideSubmissionRadio))
                .addGap(11, 11, 11)
                .addGroup(idePreferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(externalSubmissionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(externalSubmissionRadio))
                .addContainerGap())
        );

        preferencesOk.setText("OK");
        preferencesOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preferencesOkActionPerformed(evt);
            }
        });

        preferencesCancel.setText("Cancel");
        preferencesCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preferencesCancelActionPerformed(evt);
            }
        });

        preferencesReset.setText("Reset to Default");
        preferencesReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preferencesResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout preferecesButtonPanelLayout = new javax.swing.GroupLayout(preferecesButtonPanel);
        preferecesButtonPanel.setLayout(preferecesButtonPanelLayout);
        preferecesButtonPanelLayout.setHorizontalGroup(
            preferecesButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, preferecesButtonPanelLayout.createSequentialGroup()
                .addComponent(preferencesReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(preferencesOk, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(preferencesCancel))
        );
        preferecesButtonPanelLayout.setVerticalGroup(
            preferecesButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(preferecesButtonPanelLayout.createSequentialGroup()
                .addGroup(preferecesButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(preferencesCancel)
                    .addComponent(preferencesOk)
                    .addComponent(preferencesReset))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout preferencesFrameLayout = new javax.swing.GroupLayout(preferencesFrame.getContentPane());
        preferencesFrame.getContentPane().setLayout(preferencesFrameLayout);
        preferencesFrameLayout.setHorizontalGroup(
            preferencesFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(preferencesFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(preferencesFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idePreferencesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toolbarPreferencesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(preferecesButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        preferencesFrameLayout.setVerticalGroup(
            preferencesFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(preferencesFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idePreferencesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toolbarPreferencesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(preferecesButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        findAndReplaceFrame.setTitle("Find & Replace");
        findAndReplaceFrame.setResizable(false);

        replaceButton.setText("Replace");

        replaceAllButton.setText("Replace All");

        findLabel.setText("Find what:");

        replaceLabel.setText("Replace with:");

        findTextField.setText(" ");

        replaceTextField.setText(" ");

        nextButton.setText("Next");

        previousButton.setText("Previous");

        matchCaseCheck.setText("Match Case");

        wholeWordCheck.setText("Whole word");

        javax.swing.GroupLayout findReplacePanelLayout = new javax.swing.GroupLayout(findReplacePanel);
        findReplacePanel.setLayout(findReplacePanelLayout);
        findReplacePanelLayout.setHorizontalGroup(
            findReplacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findReplacePanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(findReplacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(findLabel)
                    .addComponent(replaceLabel)
                    .addComponent(matchCaseCheck)
                    .addComponent(wholeWordCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(findReplacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(replaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, findReplacePanelLayout.createSequentialGroup()
                        .addGroup(findReplacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(replaceAllButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(previousButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(findReplacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(findReplacePanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, findReplacePanelLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(replaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        findReplacePanelLayout.setVerticalGroup(
            findReplacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findReplacePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(findReplacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findLabel))
                .addGap(18, 18, 18)
                .addGroup(findReplacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(replaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(replaceLabel))
                .addGap(18, 18, 18)
                .addGroup(findReplacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(findReplacePanelLayout.createSequentialGroup()
                        .addGroup(findReplacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nextButton)
                            .addComponent(previousButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(findReplacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(replaceAllButton)
                            .addComponent(replaceButton)))
                    .addGroup(findReplacePanelLayout.createSequentialGroup()
                        .addComponent(wholeWordCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(matchCaseCheck)))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout findAndReplaceFrameLayout = new javax.swing.GroupLayout(findAndReplaceFrame.getContentPane());
        findAndReplaceFrame.getContentPane().setLayout(findAndReplaceFrameLayout);
        findAndReplaceFrameLayout.setHorizontalGroup(
            findAndReplaceFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(findReplacePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        findAndReplaceFrameLayout.setVerticalGroup(
            findAndReplaceFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(findReplacePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        projectFrame.setTitle("Project Properties");
        projectFrame.setResizable(false);

        projectNameLabel.setText("Project Name:");

        projectLocationLabel.setText("Project Location:");

        mainClassField.setEditable(false);

        mainClassLabel.setText("Main Class:");

        classPathLabel.setText("External Class Paths");

        projectOkButton.setText("OK");
        projectOkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectOkButtonActionPerformed(evt);
            }
        });

        projectCancelButton.setText("Cancel");
        projectCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectCancelButtonActionPerformed(evt);
            }
        });

        classPathScrollPane.setViewportView(classPathList);

        classPathButton.setText("Choose Path");
        classPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classPathButtonActionPerformed(evt);
            }
        });

        projectNameField.setText("MyProject");

        browseLocationButton.setText("Browse");
        browseLocationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseLocationButtonActionPerformed(evt);
            }
        });

        browseMainButton.setText("Select");
        browseMainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseMainButtonActionPerformed(evt);
            }
        });

        projectRootLabel.setText("Project Root:");

        projectRootField.setEditable(false);
        projectRootField.setText("/MyProject");

        javax.swing.GroupLayout projectPanelLayout = new javax.swing.GroupLayout(projectPanel);
        projectPanel.setLayout(projectPanelLayout);
        projectPanelLayout.setHorizontalGroup(
            projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, projectPanelLayout.createSequentialGroup()
                        .addComponent(classPathLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(classPathButton))
                    .addComponent(classPathScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, projectPanelLayout.createSequentialGroup()
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mainClassLabel)
                            .addComponent(projectNameLabel))
                        .addGap(33, 33, 33)
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(projectPanelLayout.createSequentialGroup()
                                .addComponent(mainClassField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseMainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(projectNameField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, projectPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(projectOkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(projectCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(projectPanelLayout.createSequentialGroup()
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(projectRootLabel)
                            .addComponent(projectLocationLabel))
                        .addGap(20, 20, 20)
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(projectPanelLayout.createSequentialGroup()
                                .addComponent(projectLocationField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseLocationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(projectRootField))))
                .addContainerGap())
        );
        projectPanelLayout.setVerticalGroup(
            projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectNameLabel)
                    .addComponent(projectNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectRootLabel)
                    .addComponent(projectRootField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectLocationLabel)
                    .addComponent(projectLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseLocationButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainClassLabel)
                    .addComponent(mainClassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseMainButton))
                .addGap(18, 18, 18)
                .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classPathLabel)
                    .addComponent(classPathButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classPathScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectCancelButton)
                    .addComponent(projectOkButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout projectFrameLayout = new javax.swing.GroupLayout(projectFrame.getContentPane());
        projectFrame.getContentPane().setLayout(projectFrameLayout);
        projectFrameLayout.setHorizontalGroup(
            projectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(projectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        projectFrameLayout.setVerticalGroup(
            projectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(projectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        mainSelectionFrame.setTitle("Select Main Class");

        mainSelectionLabel.setText("Please select the main class of your project.");

        mainSelectionList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "<no main method found>" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        mainSelectionList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        mainSelectionScrollPane.setViewportView(mainSelectionList);

        selectMainButton.setText("OK");
        selectMainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMainButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainSelectionFrameLayout = new javax.swing.GroupLayout(mainSelectionFrame.getContentPane());
        mainSelectionFrame.getContentPane().setLayout(mainSelectionFrameLayout);
        mainSelectionFrameLayout.setHorizontalGroup(
            mainSelectionFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainSelectionFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainSelectionFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainSelectionLabel)
                    .addComponent(mainSelectionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainSelectionFrameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(selectMainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        mainSelectionFrameLayout.setVerticalGroup(
            mainSelectionFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainSelectionFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainSelectionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainSelectionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectMainButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        helpContentsFrame.setTitle("Call-IDE Help");

        helpSplitPane.setDividerLocation(90);

        helpList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "General", "Editing", "Building", "Window", "Projects", "Options" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        helpListScrollPane.setViewportView(helpList);

        helpSplitPane.setLeftComponent(helpListScrollPane);

        helpPane.setEditable(false);
        helpPaneScrollPane.setViewportView(helpPane);

        helpSplitPane.setRightComponent(helpPaneScrollPane);

        javax.swing.GroupLayout helpContentsFrameLayout = new javax.swing.GroupLayout(helpContentsFrame.getContentPane());
        helpContentsFrame.getContentPane().setLayout(helpContentsFrameLayout);
        helpContentsFrameLayout.setHorizontalGroup(
            helpContentsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(helpSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        helpContentsFrameLayout.setVerticalGroup(
            helpContentsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(helpSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        aboutFrame.setTitle("About Call-IDE");
        aboutFrame.setResizable(false);

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/splash.png"))); // NOI18N

        developerListLabel.setText("<html>\n<u><b>Developer Team</b></u><br>\n%developers%\n</html>");

        aboutSubPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        aboutSubLabel.setText("<html>\n<u><b>Product Version:</b></u>\n%version%<br>\n<br>\n<u><b>System:</b></u>\n%system%<br>\n<br>\n<u><b>Working Path:</b></u>\n%userpath%<br>\n<br>");

        javax.swing.GroupLayout aboutSubPanelLayout = new javax.swing.GroupLayout(aboutSubPanel);
        aboutSubPanel.setLayout(aboutSubPanelLayout);
        aboutSubPanelLayout.setHorizontalGroup(
            aboutSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aboutSubLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        aboutSubPanelLayout.setVerticalGroup(
            aboutSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aboutSubLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        licenseLabel.setText("This product has not licensed yet.");

        javax.swing.GroupLayout aboutFrameLayout = new javax.swing.GroupLayout(aboutFrame.getContentPane());
        aboutFrame.getContentPane().setLayout(aboutFrameLayout);
        aboutFrameLayout.setHorizontalGroup(
            aboutFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(developerListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(aboutFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aboutFrameLayout.createSequentialGroup()
                        .addComponent(licenseLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(aboutSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(aboutFrameLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(logoLabel)
                .addGap(80, 80, 80))
        );
        aboutFrameLayout.setVerticalGroup(
            aboutFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoLabel)
                .addGap(18, 18, 18)
                .addComponent(licenseLabel)
                .addGap(18, 18, 18)
                .addGroup(aboutFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(developerListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aboutSubPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainSplitPane.setDividerLocation(300);
        mainSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        topSplitPane.setDividerLocation(200);
        topSplitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                topSplitPanePropertyChange(evt);
            }
        });

        topLeftSplitPane.setDividerLocation(700);
        topLeftSplitPane.setLeftComponent(textTabs);
        textTabs.getAccessibleContext().setAccessibleName("");

        placeholderMethodSummary.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("HelloWorld.java");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("main( String[]) : void");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("greeting() : String");
        treeNode1.add(treeNode2);
        placeholderMethodSummary.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        methodSummaryScrollPane.setViewportView(placeholderMethodSummary);

        topLeftSplitPane.setRightComponent(methodSummaryScrollPane);

        javax.swing.GroupLayout editorAndMethodSummaryPanelLayout = new javax.swing.GroupLayout(editorAndMethodSummaryPanel);
        editorAndMethodSummaryPanel.setLayout(editorAndMethodSummaryPanelLayout);
        editorAndMethodSummaryPanelLayout.setHorizontalGroup(
            editorAndMethodSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topLeftSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
        );
        editorAndMethodSummaryPanelLayout.setVerticalGroup(
            editorAndMethodSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topLeftSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
        );

        topSplitPane.setRightComponent(editorAndMethodSummaryPanel);

        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("workspace");
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("lab01");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("src");
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("HelloWorld.java");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("classes");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("HelloWorld.class");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("doc");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Files");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("lab02");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Files");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("lab03");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("src");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Person.java");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Student.java");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Test.java");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("classes");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Person.class");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Student.class");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Test.class");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("doc");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Files");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("lab04");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Files");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("lab05");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Files");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("lab06");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Files");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("lab07");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Files");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("lab08");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Files");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("lab09");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Files");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("lab10");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Files");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        placeHolderFileExplorer.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        explorerScrollPane.setViewportView(placeHolderFileExplorer);

        explorerLayeredPane.setLayer(explorerScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout explorerLayeredPaneLayout = new javax.swing.GroupLayout(explorerLayeredPane);
        explorerLayeredPane.setLayout(explorerLayeredPaneLayout);
        explorerLayeredPaneLayout.setHorizontalGroup(
            explorerLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(explorerLayeredPaneLayout.createSequentialGroup()
                .addComponent(explorerScrollPane)
                .addGap(1, 1, 1))
        );
        explorerLayeredPaneLayout.setVerticalGroup(
            explorerLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(explorerScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
        );

        topSplitPane.setLeftComponent(explorerLayeredPane);

        mainSplitPane.setTopComponent(topSplitPane);

        statusArea.setColumns(20);
        statusArea.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        statusArea.setRows(5);
        statusScrollPane.setViewportView(statusArea);

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
        );

        outputTabs.addTab("Status", statusPanel);

        compilerOutputArea.setColumns(20);
        compilerOutputArea.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        compilerOutputArea.setRows(5);
        compilerOutputScrollPane.setViewportView(compilerOutputArea);

        javax.swing.GroupLayout compilerOutputPanelLayout = new javax.swing.GroupLayout(compilerOutputPanel);
        compilerOutputPanel.setLayout(compilerOutputPanelLayout);
        compilerOutputPanelLayout.setHorizontalGroup(
            compilerOutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(compilerOutputScrollPane)
        );
        compilerOutputPanelLayout.setVerticalGroup(
            compilerOutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(compilerOutputScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
        );

        outputTabs.addTab("Compiler Output", compilerOutputPanel);

        consoleOutputPanel.setPreferredSize(new java.awt.Dimension(682, 200));

        placeholderOutputArea.setColumns(20);
        placeholderOutputArea.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        placeholderOutputArea.setRows(5);
        consoleOutputScrollPane.setViewportView(placeholderOutputArea);

        javax.swing.GroupLayout consoleOutputPanelLayout = new javax.swing.GroupLayout(consoleOutputPanel);
        consoleOutputPanel.setLayout(consoleOutputPanelLayout);
        consoleOutputPanelLayout.setHorizontalGroup(
            consoleOutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(consoleOutputScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
        );
        consoleOutputPanelLayout.setVerticalGroup(
            consoleOutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(consoleOutputScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
        );

        outputTabs.addTab("Console Output", consoleOutputPanel);

        javax.swing.GroupLayout outputsPanelLayout = new javax.swing.GroupLayout(outputsPanel);
        outputsPanel.setLayout(outputsPanelLayout);
        outputsPanelLayout.setHorizontalGroup(
            outputsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outputTabs)
        );
        outputsPanelLayout.setVerticalGroup(
            outputsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outputTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
        );

        outputTabs.getAccessibleContext().setAccessibleName("t3");

        mainSplitPane.setRightComponent(outputsPanel);

        newTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/new.png"))); // NOI18N
        newTool.setToolTipText("New File");
        newTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newToolActionPerformed(evt);
            }
        });

        openTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/open.png"))); // NOI18N
        openTool.setToolTipText("Open File");
        openTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openToolActionPerformed(evt);
            }
        });

        saveTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/save.png"))); // NOI18N
        saveTool.setToolTipText("Save");
        saveTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToolActionPerformed(evt);
            }
        });

        undoTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/undo.png"))); // NOI18N
        undoTool.setToolTipText("Undo");
        undoTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoToolActionPerformed(evt);
            }
        });

        redoTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/redo.png"))); // NOI18N
        redoTool.setToolTipText("Redo");
        redoTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoToolActionPerformed(evt);
            }
        });

        resetTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/reset.png"))); // NOI18N
        resetTool.setToolTipText("Stop Program");
        resetTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetToolActionPerformed(evt);
            }
        });

        javadocTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/doc.png"))); // NOI18N
        javadocTool.setToolTipText("Generate Javadoc");
        javadocTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javadocToolActionPerformed(evt);
            }
        });

        jarTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/jar.png"))); // NOI18N
        jarTool.setToolTipText("Generate Jar File");
        jarTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jarToolActionPerformed(evt);
            }
        });

        compileTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/compile.png"))); // NOI18N
        compileTool.setToolTipText("Compile");
        compileTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileToolActionPerformed(evt);
            }
        });

        runTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/run.png"))); // NOI18N
        runTool.setToolTipText("Run");
        runTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runToolActionPerformed(evt);
            }
        });

        compileRunTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/comprun.png"))); // NOI18N
        compileRunTool.setToolTipText("Compile & Run");
        compileRunTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileRunToolActionPerformed(evt);
            }
        });

        apiTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/api.png"))); // NOI18N
        apiTool.setToolTipText("Java API");
        apiTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apiToolActionPerformed(evt);
            }
        });

        helpTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/help.png"))); // NOI18N
        helpTool.setToolTipText("Help Contents");
        helpTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpToolActionPerformed(evt);
            }
        });

        loginTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/login.png"))); // NOI18N
        loginTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginToolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout toolbarPanelLayout = new javax.swing.GroupLayout(toolbarPanel);
        toolbarPanel.setLayout(toolbarPanelLayout);
        toolbarPanelLayout.setHorizontalGroup(
            toolbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolbarPanelLayout.createSequentialGroup()
                .addComponent(newTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(openTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(saveTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(undoTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(redoTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(compileTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(runTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(compileRunTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(resetTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jarTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(javadocTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(apiTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(helpTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loginTool, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        toolbarPanelLayout.setVerticalGroup(
            toolbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(openTool, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(saveTool, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(undoTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(redoTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(resetTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(compileTool, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jarTool, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(javadocTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(compileRunTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(apiTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(runTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(helpTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(loginTool, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(newTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        fileMenu.setText("File");

        newFileButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newFileButton.setText("New File");
        newFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileButtonActionPerformed(evt);
            }
        });
        fileMenu.add(newFileButton);

        newProjectButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        newProjectButton.setText("New Project");
        newProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newProjectButtonActionPerformed(evt);
            }
        });
        fileMenu.add(newProjectButton);
        fileMenu.add(separator1);

        openFileButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openFileButton.setText("Open File");
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });
        fileMenu.add(openFileButton);

        openProjectButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        openProjectButton.setText("Open Project");
        openProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openProjectButtonActionPerformed(evt);
            }
        });
        fileMenu.add(openProjectButton);

        openFolderButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        openFolderButton.setText("Open Folder");
        openFolderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFolderButtonActionPerformed(evt);
            }
        });
        fileMenu.add(openFolderButton);
        fileMenu.add(separator2);

        saveButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        fileMenu.add(saveButton);

        saveAsButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsButton.setText("Save As...");
        saveAsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsButtonActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsButton);
        fileMenu.add(separator3);

        quitButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        quitButton.setText("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });
        fileMenu.add(quitButton);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        undoButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoButton.setText("Undo");
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });
        editMenu.add(undoButton);

        redoButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoButton.setText("Redo");
        redoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoButtonActionPerformed(evt);
            }
        });
        editMenu.add(redoButton);
        editMenu.add(separator4);

        selectAllButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        selectAllButton.setText("Select all");
        selectAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllButtonActionPerformed(evt);
            }
        });
        editMenu.add(selectAllButton);

        cutButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        cutButton.setText("Cut");
        cutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutButtonActionPerformed(evt);
            }
        });
        editMenu.add(cutButton);

        copyButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyButton.setText("Copy");
        copyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyButtonActionPerformed(evt);
            }
        });
        editMenu.add(copyButton);

        pasteButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        pasteButton.setText("Paste");
        pasteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteButtonActionPerformed(evt);
            }
        });
        editMenu.add(pasteButton);
        editMenu.add(separator5);

        findReplaceButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        findReplaceButton.setText("Find/Replace");
        findReplaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findReplaceButtonActionPerformed(evt);
            }
        });
        editMenu.add(findReplaceButton);

        menuBar.add(editMenu);

        toolsMenu.setText("Tools");

        submissionMenu.setText("Submission System");

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        submissionMenu.add(registerButton);

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        submissionMenu.add(loginButton);

        toolsMenu.add(submissionMenu);
        toolsMenu.add(separator6);

        templatesMenu.setText("Code Templates");

        newTemplateButton.setText("New Template");
        newTemplateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTemplateButtonActionPerformed(evt);
            }
        });
        templatesMenu.add(newTemplateButton);
        templatesMenu.add(separator7);

        placeHolderMenu1.setText("CS101 - Console");
        templatesMenu.add(placeHolderMenu1);

        placeHolderMenu2.setText("CS102 - Frame");
        templatesMenu.add(placeHolderMenu2);

        placeHolderMenu3.setText("CustomTemp1");
        templatesMenu.add(placeHolderMenu3);

        toolsMenu.add(templatesMenu);

        insertJavadocButton.setText("Insert Javadoc Comment");
        insertJavadocButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertJavadocButtonActionPerformed(evt);
            }
        });
        toolsMenu.add(insertJavadocButton);
        toolsMenu.add(separator8);

        javadocButton.setText("Generate Javadoc");
        javadocButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javadocButtonActionPerformed(evt);
            }
        });
        toolsMenu.add(javadocButton);

        jarButton.setText("Generate Jar File");
        jarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jarButtonActionPerformed(evt);
            }
        });
        toolsMenu.add(jarButton);
        toolsMenu.add(separator9);

        compileButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.CTRL_MASK));
        compileButton.setText("Compile");
        compileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileButtonActionPerformed(evt);
            }
        });
        toolsMenu.add(compileButton);

        runButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_MASK));
        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });
        toolsMenu.add(runButton);

        compileRunButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        compileRunButton.setText("Compile & Run");
        compileRunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileRunButtonActionPerformed(evt);
            }
        });
        toolsMenu.add(compileRunButton);

        resetButton.setText("Stop Program");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        toolsMenu.add(resetButton);
        toolsMenu.add(separator10);

        preferencesButton.setText("Preferences");
        preferencesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preferencesButtonActionPerformed(evt);
            }
        });
        toolsMenu.add(preferencesButton);

        menuBar.add(toolsMenu);

        windowMenu.setText("Window");

        fileTrackerButton.setSelected(true);
        fileTrackerButton.setText("File Tracker");
        fileTrackerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileTrackerButtonActionPerformed(evt);
            }
        });
        windowMenu.add(fileTrackerButton);

        methodSummaryButton.setSelected(true);
        methodSummaryButton.setText("Method Summary");
        methodSummaryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                methodSummaryButtonActionPerformed(evt);
            }
        });
        windowMenu.add(methodSummaryButton);

        outputsPaneButton.setSelected(true);
        outputsPaneButton.setText("Outputs Pane");
        outputsPaneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputsPaneButtonActionPerformed(evt);
            }
        });
        windowMenu.add(outputsPaneButton);
        windowMenu.add(separator11);

        restoreButton.setText("Restore to default");
        restoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreButtonActionPerformed(evt);
            }
        });
        windowMenu.add(restoreButton);

        menuBar.add(windowMenu);

        helpMenu.setText("Help");

        apiButton.setText("Java API");
        apiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apiButtonActionPerformed(evt);
            }
        });
        helpMenu.add(apiButton);

        tutorialsButton.setText("Java Tutorials");
        tutorialsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutorialsButtonActionPerformed(evt);
            }
        });
        helpMenu.add(tutorialsButton);
        helpMenu.add(separator12);

        helpContentsButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        helpContentsButton.setText("Help Contents");
        helpContentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpContentsButtonActionPerformed(evt);
            }
        });
        helpMenu.add(helpContentsButton);

        aboutButton.setText("About");
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });
        helpMenu.add(aboutButton);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainSplitPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(toolbarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(mainSplitPane)
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void preferencesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preferencesButtonActionPerformed
        showPreferences();
    }//GEN-LAST:event_preferencesButtonActionPerformed

    private void topSplitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_topSplitPanePropertyChange
        dividerLocation = topSplitPane.getDividerLocation();
    }//GEN-LAST:event_topSplitPanePropertyChange

    private void findReplaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findReplaceButtonActionPerformed
        if (isEditing()) showFindAndReplace();
    }//GEN-LAST:event_findReplaceButtonActionPerformed

    private void saveToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveToolActionPerformed
        saveButtonActionPerformed(evt);
    }//GEN-LAST:event_saveToolActionPerformed

    private void openToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openToolActionPerformed
        openFileButtonActionPerformed(evt);
    }//GEN-LAST:event_openToolActionPerformed

    private void newToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newToolActionPerformed
        newFileButtonActionPerformed(evt);
    }//GEN-LAST:event_newToolActionPerformed

    private void newFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFileButtonActionPerformed
        newFile();
    }//GEN-LAST:event_newFileButtonActionPerformed

    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
        showFileOpenDialog();
    }//GEN-LAST:event_openFileButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (isEditing()) saveFile( getActiveFile(), getActiveContent());
    }//GEN-LAST:event_saveButtonActionPerformed

    private void saveAsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsButtonActionPerformed
        if (isEditing()) saveAsFile( getActiveFile(), getActiveContent());
    }//GEN-LAST:event_saveAsButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        quit();
    }//GEN-LAST:event_quitButtonActionPerformed

    private void undoToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoToolActionPerformed
        undoButtonActionPerformed(evt);
    }//GEN-LAST:event_undoToolActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        if (isEditing()) BasicOperations.undo( getActiveTextArea());
    }//GEN-LAST:event_undoButtonActionPerformed

    private void redoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoButtonActionPerformed
        if (isEditing()) BasicOperations.redo( getActiveTextArea());
    }//GEN-LAST:event_redoButtonActionPerformed

    private void redoToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoToolActionPerformed
        redoButtonActionPerformed(evt);
    }//GEN-LAST:event_redoToolActionPerformed

    private void selectAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllButtonActionPerformed
        if (isEditing()) BasicOperations.selectAll( getActiveTextArea());
    }//GEN-LAST:event_selectAllButtonActionPerformed

    private void cutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutButtonActionPerformed
        if (isEditing()) BasicOperations.cut( getActiveTextArea());
    }//GEN-LAST:event_cutButtonActionPerformed

    private void copyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyButtonActionPerformed
        if (isEditing()) BasicOperations.copy( getActiveTextArea());
    }//GEN-LAST:event_copyButtonActionPerformed

    private void pasteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteButtonActionPerformed
        if (isEditing()) BasicOperations.paste( getActiveTextArea());
    }//GEN-LAST:event_pasteButtonActionPerformed

    private void apiToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apiToolActionPerformed
        apiButtonActionPerformed(evt);
    }//GEN-LAST:event_apiToolActionPerformed

    private void apiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apiButtonActionPerformed
        try{ LinkOpener.openLink( LinkOpener.API_LINK);
        } catch (Exception e) {}
    }//GEN-LAST:event_apiButtonActionPerformed

    private void tutorialsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutorialsButtonActionPerformed
        try {LinkOpener.openLink( LinkOpener.TUTORIALS_LINK);
        } catch (Exception e) {}
    }//GEN-LAST:event_tutorialsButtonActionPerformed

    private void preferencesOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preferencesOkActionPerformed
        savePreferences();
        preferencesFrame.setVisible( false);
    }//GEN-LAST:event_preferencesOkActionPerformed

    private void preferencesCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preferencesCancelActionPerformed
        preferencesFrame.setVisible( false);
    }//GEN-LAST:event_preferencesCancelActionPerformed

    private void browseWorkspaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseWorkspaceButtonActionPerformed
        choosePreferredWorkspace();
    }//GEN-LAST:event_browseWorkspaceButtonActionPerformed

    private void autosaveCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autosaveCheckActionPerformed
        autosaveTextField.setEnabled( autosaveCheck.isSelected());
    }//GEN-LAST:event_autosaveCheckActionPerformed

    private void insertJavadocButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertJavadocButtonActionPerformed
        if (isEditing()) insertJavadocComment();
    }//GEN-LAST:event_insertJavadocButtonActionPerformed

    private void fileTrackerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileTrackerButtonActionPerformed
        arrangeWindow();
    }//GEN-LAST:event_fileTrackerButtonActionPerformed

    private void methodSummaryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_methodSummaryButtonActionPerformed
        arrangeWindow();
    }//GEN-LAST:event_methodSummaryButtonActionPerformed

    private void outputsPaneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputsPaneButtonActionPerformed
        arrangeWindow();
    }//GEN-LAST:event_outputsPaneButtonActionPerformed

    private void restoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreButtonActionPerformed
        arrangeWindowDefault();
    }//GEN-LAST:event_restoreButtonActionPerformed

    private void preferencesResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preferencesResetActionPerformed
        resetPreferencesWindow();
    }//GEN-LAST:event_preferencesResetActionPerformed

    private void newTemplateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newTemplateButtonActionPerformed
        addNewTemplate();
    }//GEN-LAST:event_newTemplateButtonActionPerformed

    private void openFolderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFolderButtonActionPerformed
        showFolderOpenDialog();
    }//GEN-LAST:event_openFolderButtonActionPerformed

    private void compileToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compileToolActionPerformed
        compileButtonActionPerformed(evt);
    }//GEN-LAST:event_compileToolActionPerformed

    private void runToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runToolActionPerformed
        runButtonActionPerformed(evt);
    }//GEN-LAST:event_runToolActionPerformed
        
    private void resetToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetToolActionPerformed
        resetButtonActionPerformed(evt);
    }//GEN-LAST:event_resetToolActionPerformed

    private void jarToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jarToolActionPerformed
        jarButtonActionPerformed(evt);
    }//GEN-LAST:event_jarToolActionPerformed

    private void javadocToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_javadocToolActionPerformed
        javadocButtonActionPerformed(evt);
    }//GEN-LAST:event_javadocToolActionPerformed

    private void compileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compileButtonActionPerformed
        compileAction();
    }//GEN-LAST:event_compileButtonActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        runAction();
    }//GEN-LAST:event_runButtonActionPerformed

    private void javadocButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_javadocButtonActionPerformed
        javadocCurrentProject();
    }//GEN-LAST:event_javadocButtonActionPerformed

    private void jarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jarButtonActionPerformed
        jarCurrentProject();
    }//GEN-LAST:event_jarButtonActionPerformed

    private void compileRunToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compileRunToolActionPerformed
        compileRunButtonActionPerformed(evt);
    }//GEN-LAST:event_compileRunToolActionPerformed

    private void compileRunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compileRunButtonActionPerformed
        compileRunAction();
    }//GEN-LAST:event_compileRunButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        resetInteractions();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void newProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectButtonActionPerformed
        showCreateProject();
    }//GEN-LAST:event_newProjectButtonActionPerformed

    private void projectOkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectOkButtonActionPerformed
        projectOkAction();
    }//GEN-LAST:event_projectOkButtonActionPerformed

    private void projectCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectCancelButtonActionPerformed
        projectFrame.setVisible(false);
    }//GEN-LAST:event_projectCancelButtonActionPerformed

    private void openProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openProjectButtonActionPerformed
        showProjectOpenDialog();
    }//GEN-LAST:event_openProjectButtonActionPerformed

    private void browseLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseLocationButtonActionPerformed
        browseProjectLocation();
    }//GEN-LAST:event_browseLocationButtonActionPerformed

    private void browseMainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseMainButtonActionPerformed
        showMainSelection();
    }//GEN-LAST:event_browseMainButtonActionPerformed

    private void classPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classPathButtonActionPerformed
        browseClassPath();
    }//GEN-LAST:event_classPathButtonActionPerformed

    private void loginToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginToolActionPerformed
       loginButtonActionPerformed( evt);
    }//GEN-LAST:event_loginToolActionPerformed

    private void callideSubmissionRadioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_callideSubmissionRadioStateChanged
        updateLinkField();
    }//GEN-LAST:event_callideSubmissionRadioStateChanged

    private void externalSubmissionRadioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_externalSubmissionRadioStateChanged
        updateLinkField();
    }//GEN-LAST:event_externalSubmissionRadioStateChanged

    private void selectMainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMainButtonActionPerformed
        selectMainClass();
    }//GEN-LAST:event_selectMainButtonActionPerformed

    private void helpToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpToolActionPerformed
        helpContentsButtonActionPerformed( evt);
    }//GEN-LAST:event_helpToolActionPerformed

    private void helpContentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpContentsButtonActionPerformed
        showHelpContents();
    }//GEN-LAST:event_helpContentsButtonActionPerformed

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        showAbout();
    }//GEN-LAST:event_aboutButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        loginAction();
    }//GEN-LAST:event_loginButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        registerAction();
    }//GEN-LAST:event_registerButtonActionPerformed

    /**
     * Sets LookAndFeel to the given name.
     * @param lookAndFeel "Metal", "Nimbus", "CDE/Motif", "Windows", or "Windows Classic"
     */
    public static void setLookAndFeel (String lookAndFeel) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :
                    javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (lookAndFeel.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                 |javax.swing.UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null,
                    "An error occured while loading the Look and Feel settings.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }        
    }

    /** This class is used for listening resize events of the window to arrange components with each. */
    class ResizeListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            arrangeComponents();
        }
    }

    /** Checks each tab if they are unsaved and closes the IDE after the processes. */
    private void quit() {
        boolean cancelled = false;
        while (textAreas.size() > 0 && !cancelled) {
            textTabs.setSelectedIndex(0);
            cancelled = (checkTab(0) == JOptionPane.CANCEL_OPTION);
        }
        if (!cancelled) {
            resetInteractions();
            System.exit(0);
        }
    }

    /** Loads the icon files. */
    private void loadSources() {
        try {
            closeIcon = new ImageIcon( (new ImageIcon( getClass().getResource(
                "/userinterface/images/close.png"))).getImage().getScaledInstance(
                12, 12, java.awt.Image.SCALE_SMOOTH));
            detachIcon = new ImageIcon( (new ImageIcon( getClass().getResource(
                "/userinterface/images/detach.png"))).getImage().getScaledInstance(
                16, 16, java.awt.Image.SCALE_SMOOTH));
            frameIcon = ImageIO.read(getClass().getResource( "/userinterface/images/icon.jpg"));
            refreshIcon = new ImageIcon( (new ImageIcon( getClass().getResource(
                "/userinterface/images/refresh.png"))).getImage().getScaledInstance(
                16, 16, java.awt.Image.SCALE_SMOOTH));
            for (int i = 1; i <= 6; i++) {
                Scanner helpReader = new Scanner(getClass().getResourceAsStream("/helputils/helpcontents/page" + i +".html"));
                String helpString = "";
                while (helpReader.hasNext())
                    helpString = helpString + helpReader.nextLine();
                helpStrings.add(helpString);
                helpReader.close();
            }
            
            BufferedReader developerReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/helputils/developers.html"), "ISO-8859-9"));
            developerList = "";
            String line = developerReader.readLine();
            while (line != null) {
                developerList = developerList + line;
                line = developerReader.readLine();
            }
            developerReader.close();
            
        } catch (Exception e) {
            showError( "An error occured while loading the sources.");
        }
    }

    /** Initializes the properties. */
    private void initProperties() {
        textAreas = new ArrayList<RSyntaxTextArea>();
        files = new ArrayList<File>();
        savedContents = new ArrayList<String>();
        tabTitles = new ArrayList<JLabel>();
        autosavers = new ArrayList<AutoFileSaver>();
        openProjects = new ArrayList<ProjectHandler>();
        folderWatchers = new ArrayList<RealTimeFolderWatcher>();
        helpStrings = new ArrayList<String>();
        untitledCount = 1;
    }

    /** Initializes the components other than the auto-generated ones. */
    private void initOtherComponents() {
        clearPlaceHolders();
        initFileExplorer();
        initConsole();
        initMethodSummary();
        initRefreshButton();
        initHelpContents();
        initAbout();
    }
    
    /** Initializes the file explorer. */
    private void initFileExplorer() {
        noWorkspacePanel = new JPanel();
        workspaceButton = new JButton( "Choose Path");
        workspaceButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e) {
                chooseWorkspace();
                if (workspace != null) {
                    addExplorer();
                }
            }
        });
        noWorkspacePanel.add( workspaceButton);
        
        projectLocationField.addKeyListener( new KeyAdapter() {
            @Override
            public void keyReleased (KeyEvent e) {
                updateProjectRootField();
            }
        });
        projectNameField.addKeyListener( new KeyAdapter() {
            @Override
            public void keyReleased( KeyEvent e) {
                updateProjectRootField();
            }
        });
    }
    
    /** Initializes the console pane. */
    private void initConsole() {
        JPanel consoleTabPanel = new JPanel();
        JLabel titleLabel = new JLabel( "Console Output");
        JButton detachButton = new JButton();
        detachButton.setIcon( detachIcon);
        detachButton.setPreferredSize( new Dimension(24, 24));
        detachButton.addActionListener( new DetachConsoleListener());
        consoleTabPanel.setOpaque( false);
        consoleTabPanel.add( titleLabel);
        consoleTabPanel.add( detachButton);
        outputTabs.setTabComponentAt( 2, consoleTabPanel);
        tabComp = consoleTabPanel;
        outputTabs.addChangeListener( new ChangeListener() {
            @Override
            public void stateChanged( ChangeEvent evt) {
                lastOutputTabIndex = outputTabs.getSelectedIndex();
            }
        });
        consoleOutputArea = new JTextPane();
        consoleOutputScrollPane.setViewportView( consoleOutputArea);
        consoleFrame = new JFrame( "Console Output");
        detachScroll = new JScrollPane();
        consoleOut = false;
        
        statusArea.setLineWrap(true);
        statusArea.setWrapStyleWord(true);
    }
    
    /** Initializes the refresh button of file explorer. */
    private void initRefreshButton() {
        JButton refreshButton = new JButton();
        refreshButton.setPreferredSize( new Dimension(25, 25));
        refreshButton.setIcon( refreshIcon);
        explorerLayeredPane.add( refreshButton);
        explorerLayeredPane.setLayer( refreshButton, JLayeredPane.DRAG_LAYER);
        explorerLayeredPane.addComponentListener( new ComponentAdapter() {
            @Override
            public void componentResized( ComponentEvent e) {
                refreshButton.setBounds( explorerLayeredPane.getWidth() - 30, 3, 25, 25);
            }
        });
        refreshButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e) {
                refreshExplorer();
            }
        });
    }
    
    /** Initializes the help contents. */
    private void initHelpContents() {
        helpPane.setContentType("text/html");
        helpList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                helpPane.setText(helpStrings.get(helpList.getSelectedIndex()));
            }
        });
    }
    
    /** Initializes the about frame. */
    private void initAbout() {
        String aboutContent = aboutSubLabel.getText();
        aboutContent = aboutContent.replace("%version%", "Call-IDE <i>Alpha</i>");
        aboutContent = aboutContent.replace("%system%", System.getProperty("os.name") +
                " version: " + System.getProperty("os.version") + "  architecture: " + System.getProperty("os.arch"));
        aboutContent = aboutContent.replace("%userpath%", System.getProperty("user.home") + File.separator + "Call-IDE" + File.separator);
        aboutSubLabel.setText(aboutContent);
        developerListLabel.setText( developerListLabel.getText().replace( "%developers%", developerList));
    }

    /** Listener for the console's detach button. */
    private class DetachConsoleListener implements ActionListener {
        /** Calls the detaching method of the console. */
        @Override
        public void actionPerformed( ActionEvent e) {
            detachConsole();
        }
    }

    /** Detaches the console from the frame. */
    private void detachConsole() {
        ConsoleCore.dispatch(detachScroll , consoleOutputArea,
                             outputTabs, tabComp, consoleFrame, consoleOut, MainFrame.this);
        outputTabs.getComponentAt(2).setVisible(false);
        JPanel emptyPanel = new JPanel();
        JButton fakeDetachButton = new JButton();
        fakeDetachButton.setIcon( detachIcon);
        fakeDetachButton.setPreferredSize( new Dimension(0, 24));
        emptyPanel.add(fakeDetachButton);
        emptyPanel.setVisible(false);
        outputTabs.setTabComponentAt( 2, emptyPanel);
        if (outputTabs.getSelectedIndex() == 2)
            outputTabs.setSelectedIndex(1);
        else
            outputTabs.setSelectedIndex(outputTabs.getSelectedIndex());
        outputTabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged( ChangeEvent evt) {
                if (outputTabs.getSelectedIndex() == 2)
                    outputTabs.setSelectedIndex( lastOutputTabIndex);
            }
        });
        consoleOut = true;
    }

    /** Clears placeholder components which were used for a better designing experience. */
    private void clearPlaceHolders() {
        placeHolderFileExplorer.setVisible( false);
        placeHolderMenu1.setVisible( false);
        placeHolderMenu2.setVisible( false);
        placeHolderMenu3.setVisible( false);
        placeholderMethodSummary.setVisible( false);
    }

    /** Initializes the main frame of the IDE. */
    private void initFrame() throws IOException {
        dividerLocation = 200;
        setTitle( "Call-IDE");
        setIconImage( frameIcon); 
        setSize( new Dimension(1400, 700));
        setLocationRelativeTo( null);
        arrangeComponents();
        addComponentListener( new ResizeListener());
        setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e) {
                quit();
            }
        });
        arrangeWindow();
        setVisible(true);
    }

    /**
     * Adds a tab to the text editor with given properties.
     * @param title the title of the new tab
     * @param content the content of the new tab
     */
    private void addTab( String title, String content, String tooltip) throws IOException {
        JPanel panel = new JPanel( new BorderLayout());
        RSyntaxTextArea textArea = new RSyntaxTextArea();
        new CommentFunctionality( textArea);
        enableFileDrop( textArea);
        textArea.setSyntaxEditingStyle( SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled( true);
        Theme theme = Theme.load( getClass().getResourceAsStream( preferences.getTheme()));
        theme.apply(textArea);
        textArea.setFont( preferences.getEditorFont());
        textArea.setBracketMatchingEnabled( preferences.getBracketMatching());
        textArea.setText( content);
        textArea.setTabsEmulated( true);
        textArea.setTabSize( preferences.getIndentLevel());
        textArea.discardAllEdits();
        textAreas.add( textArea);
        RTextScrollPane sp = new RTextScrollPane( textArea);
        sp.setLineNumbersEnabled( preferences.getDisplayLineNumbers());
        panel.add( sp);
        textTabs.add(title, panel);
        int index = textTabs.getTabCount() - 1;
        JPanel tabPanel = new JPanel();
        JLabel titleLabel = new JLabel( title);
        tabTitles.add( titleLabel);
        JButton closeTabButton = new JButton();
        closeTabButton.setIcon( closeIcon);
        closeTabButton.setPreferredSize( new Dimension(24, 24));
        closeTabButton.addActionListener( new CloseTabListener( titleLabel));
        tabPanel.setOpaque( false);
        tabPanel.add( titleLabel);
        tabPanel.add( closeTabButton);
        textTabs.setTabComponentAt( index, tabPanel);
        textTabs.setToolTipTextAt( index, tooltip);
    }

    /** The listener for close buttons of the tabs. */
    private class CloseTabListener implements ActionListener {
        private JLabel titleLabel;
        /** Creates a new CloseTabListener with the given label. */
        public CloseTabListener( JLabel titleLabel) {
            this.titleLabel = titleLabel;
        }
        /** Makes an attempt to close the tab. */
        @Override
        public void actionPerformed( ActionEvent e) {
            int index = tabTitles.indexOf( titleLabel);
            checkTab( index);
        }
    }

    /** Checks if the tab's content is unsaved on the given index, if not closes it. */
    private int checkTab( int index) {
        if (files.get(index) == null && !(textAreas.get(index).getText().isEmpty())) {
            String fileName = tabTitles.get(index).getText();
            int command = JOptionPane.showConfirmDialog( this, fileName + 
                " is not saved. Do you want to save it before closing?",
                "Call-IDE", JOptionPane.YES_NO_CANCEL_OPTION);
            if (command == JOptionPane.YES_OPTION) {
                if (saveFile( files.get(index), textAreas.get(index).getText()))
                    closeTab( index);
            }
            else if (command == JOptionPane.NO_OPTION) {
                closeTab( index);
            }
            else if (command == JOptionPane.CANCEL_OPTION ||
                     command == JOptionPane.CLOSED_OPTION) {
                return JOptionPane.CANCEL_OPTION;
            }
        }
        else if ( !textAreas.get(index).getText().equals(savedContents.get(index))) {
            String fileName = tabTitles.get(index).getText();
            int command = JOptionPane.showConfirmDialog( this, fileName + 
                " is changed. Do you want to save the changes before closing?",
                "Call-IDE", JOptionPane.YES_NO_CANCEL_OPTION);
            if (command == JOptionPane.YES_OPTION) {
                if (saveFile( files.get(index), textAreas.get(index).getText()));
                closeTab( index);
            }
            else if (command == JOptionPane.NO_OPTION) {
                closeTab( index);
            }
            else if (command == JOptionPane.CANCEL_OPTION ||
                     command == JOptionPane.CLOSED_OPTION) {
                return JOptionPane.CANCEL_OPTION;
            }
        }
        else {
            closeTab( index);
        }
        return JOptionPane.DEFAULT_OPTION;
    }

    /** Closes the tab on the given index. */
    private void closeTab( int index) {
        methodParser.removeNode( files.remove( index));
        methodSummary.updateUI();
        savedContents.remove( index);
        tabTitles.remove( index);
        textAreas.remove( index);
        textTabs.remove( index);
        if (autosavers.get( index) == null)
            autosavers.remove( index);
        else
            autosavers.remove( index).stop();
    }

    /** Arranges the split pane divider locations for a better look. */
    private void arrangeComponents() {
        topSplitPane.setDividerLocation( dividerLocation);
        mainSplitPane.setDividerLocation( mainSplitPane.getHeight() - 200);
        topLeftSplitPane.setDividerLocation( topLeftSplitPane.getWidth() - 250);
    }

    /** Opens the find and replace tool for the current tab. */
    private void showFindAndReplace() {
        new FindAndReplace(findAndReplaceFrame, this, getActiveTextArea(),
            nextButton, previousButton, replaceButton, replaceAllButton,
            findTextField, replaceTextField, matchCaseCheck, wholeWordCheck );
    }

    /** Opens the preferences frame of the IDE. */
    private void showPreferences() {
        try {
            boolean[] checkBoxValues = preferences.getToolbar();
            JCheckBox[] checkBoxes = {newCheck, openCheck, saveCheck, undoCheck,
                redoCheck, compileCheck, runCheck, compileRunCheck, resetCheck,
                jarCheck, javadocCheck, apiCheck, helpCheck, loginCheck};
            for (int i = 0; i < checkBoxes.length; i++) {
                checkBoxes[i].setSelected( checkBoxValues[i]);
            }

            int autosaveIn = preferences.getAutosaveIn();
            if (autosaveIn == -1) {
                autosaveCheck.setSelected( false);
                autosaveTextField.setEnabled( false);
            }
            else {
                autosaveCheck.setSelected( true);
                autosaveTextField.setEnabled( true);
                autosaveTextField.setText( "" + (autosaveIn / 60000));
            }
            lineNumbersCheck.setSelected( preferences.getDisplayLineNumbers());
            showHelpCheck.setSelected( preferences.getShowHelpForErrors());
            bracketMatchingCheck.setSelected( preferences.getBracketMatching());
            detachConsoleCheck.setSelected( preferences.getDispatchOnRun());
            editorFontSizeField.setText( "" + preferences.getEditorFont().getSize());
            editorFontChooser.setSelectedItem( preferences.getEditorFont().getName());
            outputFontSizeField.setText( "" + preferences.getOutputFont().getSize());
            outputFontChooser.setSelectedItem( preferences.getOutputFont().getName());
            workspaceTextField.setText( config.getWorkspace());
            workspaceChanged = false;
            for (int i = 0; i < themeComboBox.getItemCount(); i++) {
                String theme = themeComboBox.getItemAt(i);
                if (preferences.getTheme().endsWith(theme))
                    themeComboBox.setSelectedIndex(i);
            }
            if (preferences.getSubmissionLink() != null) {
                externalSubmissionRadio.setSelected( true);
                externalSubmissionField.setText(preferences.getSubmissionLink());
            }
            preferencesFrame.pack();
            preferencesFrame.setLocationRelativeTo( this);
            preferencesFrame.setVisible( true);
        } catch( IOException e) {
            showError( "An error occured while loading the preferences.");
        }
    }

    /**
     * Attempts to save the given content to the given file location.
     * @param file the file to save on
     * @param content the content of the file to save
     */
    private boolean saveFile( File file, String content) {
        try {
            if (file == null) {
                return saveAsFile( file, content);
            }
            else {
                FileSaver saver = new FileSaver( file);
                saver.save( content);
                savedContents.set( files.indexOf( file), content);
                printStatus( "File updated: " + file.getName());
                updateAutoComplete( file);
                updateMethodSummary( file);
                return true;
            }
        } catch (IOException e) {
            showError( "An error occured while saving the file.");
            return false;
        }
    }

    /**
     * Saves the given content to another file location.
     * @param file the old file to save as
     * @param content the content of the file to save as
     */
    private boolean saveAsFile( File file, String content) {
        try {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File selected = chooser.getSelectedFile();
                methodParser.removeNode( file);
                if (selected.getName().contains("."))
                    file = new File(selected.getParent() + "/" + selected.getName());
                else
                    file = new File(selected.getParent() + "/" + selected.getName() + ".java");
                files.set( textTabs.getSelectedIndex(), file);
                FileSaver saver = new FileSaver( file);
                saver.save( getActiveContent());
                savedContents.set( files.indexOf( file), content);
                printStatus( "File saved as: " + file.getAbsolutePath());
                JLabel titleLabel = tabTitles.get( textTabs.getSelectedIndex());
                titleLabel.setText( file.getName());
                textTabs.setToolTipTextAt( textTabs.getSelectedIndex(), file.getAbsolutePath());
                if (preferences.getAutosaveIn() != -1)
                    autosavers.set( textTabs.getSelectedIndex(), 
                                   new AutoFileSaver( new FileSaver(file),
                                                     textAreas.get( textAreas.size() - 1),
                                                     this, preferences.getAutosaveIn()));
                if (fileExplorer != null) {
                    try {
                        fileExplorer.updateDirectory(selected.getParent());
                    } catch (NullPointerException ex) {}
                }
                updateAutoComplete( file);
                updateMethodSummary( file);
                methodSummary.updateUI();

                return true;
            }
        } catch (IOException e) {
            showError( "An error occured while loading the icon sources.");
            return false;
        }
        return false;
    }

    /** Shows a dialog to open a file. */
    private void showFileOpenDialog() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog( this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            openFile( file);
        }
    }

    /** Opens a file in the editor. */
    @Override
    public void openFile( File file) {
        try {
            if (!ContentReader.isSupported(file)) {
                printStatus( "This file format is not supported for editing.");
                return;
            }
            for (int i = 0; i < files.size(); i++) {
                File checkFile = files.get(i);
                if (checkFile != null &&
                    file.getAbsolutePath().equals(checkFile.getAbsolutePath())) {
                    textTabs.setSelectedIndex(i);
                    return;
                }
            }
            String content = ContentReader.read( file);
            addTab( file.getName(), content, file.getAbsolutePath());
            files.add( file);
            if (preferences.getAutosaveIn() == -1)
                autosavers.add( null);
            else
                autosavers.add ( new AutoFileSaver( new FileSaver(file),
                                                   textAreas.get( textAreas.size() - 1),
                                                   this, preferences.getAutosaveIn()));
            savedContents.add( content);
            textTabs.setSelectedIndex( textAreas.size() - 1);
            updateMethodSummary( file);
            printStatus( "File opened: " + file.getName());
        } catch (IOException e) {
            showError( "An error occured while opening the file.");
        }
    }

    /** Creates a new opened file on the IDE. */
    private void newFile() {
        try {
            files.add( null);
            savedContents.add( "");
            autosavers.add( null);
            addTab( "Untitled " + untitledCount, "", "<unsaved>");
            untitledCount++;
            textTabs.setSelectedIndex( textAreas.size() - 1);
        } catch(IOException e) {
            showError( "An error occured while creating a new file.");
        }
    }

    /** Supplies access to the active file on the editor. */
    private File getActiveFile() {
        int index = textTabs.getSelectedIndex();
        if (index >= files.size())
            return null;
        return files.get(index);
    }

    /** Supplies access to the active content on the editor. */
    private String getActiveContent() {
        int index = textTabs.getSelectedIndex();
        RSyntaxTextArea textArea = textAreas.get( index);
        return textArea.getText();
    }

    /** Supplies access to the active text area component. */
    private RSyntaxTextArea getActiveTextArea() {
        int index = textTabs.getSelectedIndex();
        return textAreas.get( index);
    }

    /** Prints a message to the status tab and makes that tab active. */
    public void printStatus( String status) {
        statusArea.setText( statusArea.getText() + "> " + status + "\n");
        outputTabs.setSelectedIndex(0);
    }

    /** Adds an empty explorer to the frame which asks for a path from the user. */
    private void addEmptyExplorer() {
        explorerScrollPane.setViewportView( noWorkspacePanel);
    }

    /** Adds the file explorer to the frame. */
    private void addExplorer() {
        addExplorerWith( workspace);
    }

    /** Adds the file explorer to the frame wih the given root. */
    private void addExplorerWith( String rootDir) {
        noWorkspacePanel.setVisible( false);
        ArrayList<String> projects = new ArrayList<String>();
        projects.add( rootDir);
        fileExplorer = new FileExplorer( projects, this);
        fileExplorer.setBackground( Color.WHITE);
        explorerScrollPane.setViewportView( fileExplorer);
        fileExplorer.updateDirectory( rootDir);
        fileExplorer.expandRoot();
        fileExplorer.getTree().addMouseListener( new MouseAdapter() {
            @Override
            public void mousePressed( MouseEvent e) {
                explorerLayeredPane.repaint();
            }
            @Override
            public void mouseReleased( MouseEvent e) {
                explorerLayeredPane.repaint();
            }
        });
    }

    /** Loads the profile settings of the user. */
    private void loadProfile() throws IOException {
        config = new FileConfigurer();
        if (!config.configExists())
            config.configureDefault();
        config.readConfigs();
        config.checkFolders();
        userPath = config.getUserPath();
        preferences = PreferencesConfigurer.load( userPath);
        applyPreferences();
        loadTemplates();
        workspace = config.getWorkspace();
        loadWorkspace( workspace);
    }

    /** Loads the file explorer's contents if exists. */
    private void loadWorkspace( String workspace) {
        if (workspace == null)
            addEmptyExplorer();
        else
            addExplorer();
    }

    /** Asks user to choose a path for the file explorer. */
    private void chooseWorkspace() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                if (file.isDirectory()) {
                    workspace = file.getAbsolutePath();
                    config.setWorkspace( workspace);
                }
            }
        } catch (IOException e) {
            showError( "An error occured while setting the workspace.");
        }
    }

    /** Applies the preference settings to the IDE. */
    private void applyPreferences() throws IOException {
        boolean[] toolbarPrefs = preferences.getToolbar();
        JButton[] currentToolbar = {newTool, openTool, saveTool,
            undoTool, redoTool, compileTool, runTool, compileRunTool,
            resetTool, jarTool, javadocTool, apiTool, helpTool, loginTool};
        for (int i = 0; i < currentToolbar.length; i++) {
            currentToolbar[i].setVisible( toolbarPrefs[i]);
        }

        int tabSize = preferences.getIndentLevel();
        boolean bracketMatching = preferences.getBracketMatching();
        boolean lineNumbersEnabled = preferences.getDisplayLineNumbers();
        Font editorFont = preferences.getEditorFont();
        String themeName = preferences.getTheme();
        for (RSyntaxTextArea textArea : textAreas) {
            Theme theme = Theme.load(getClass().getResourceAsStream( themeName));
            theme.apply(textArea);
            textArea.setFont( editorFont);
            textArea.setBracketMatchingEnabled( bracketMatching);
            textArea.setTabSize( tabSize);
            ((RTextScrollPane) (textArea.getParent().getParent())).
                setLineNumbersEnabled( lineNumbersEnabled);
        }

        Font outputFont = preferences.getOutputFont();
        statusArea.setFont( outputFont);
        consoleOutputArea.setFont( outputFont);
        compilerOutputArea.setFont( outputFont);

        if (workspaceChanged)
            if (workspace != null && (new File(workspace)).isDirectory()) {
            ArrayList<String> projects = new ArrayList<String>();
            projects.add(workspace);
            fileExplorer = new FileExplorer( projects, this);
            fileExplorer.setBackground(Color.WHITE);
            explorerScrollPane.setViewportView( fileExplorer);
        }
    }

    /** The listener for inserting template button. */
    private class TemplateButtonListener implements ActionListener {
        int templateIndex;
        public TemplateButtonListener( int templateIndex) {
            this.templateIndex = templateIndex;
        }
        @Override
        public void actionPerformed( ActionEvent e) {
            insertTemplate( templateIndex);
        }
    }

    /** Loads the templates from the user's home location. */
    private void loadTemplates() throws IOException {
        TemplateManager manager = new TemplateManager( userPath);
        templates = manager.getTemplates();
        templateNames = manager.getTemplateNames();
        clearTemplatesMenu();
        templatesMenu.add( new JPopupMenu.Separator());
        for (int i = 0; i < templates.length; i++) {
            JMenuItem templateButton = new JMenuItem( templateNames[i]);
            templatesMenu.add(templateButton);
            templateButton.addActionListener( new TemplateButtonListener(i));
        }
    }

    /** Clears the template menu for refreshing. */
    private void clearTemplatesMenu() {
        while (templatesMenu.getItemCount() > 1)
            templatesMenu.remove(1);
    }

    /** Inserts the template on the given index to the active empty text area,
      If the active text area is not empty, opens a new tab to insert the template. */
    private void insertTemplate( int templateIndex) {
        if (!getActiveTextArea().getText().isEmpty())
            newFile();
        getActiveTextArea().insert(templates[templateIndex], getActiveTextArea().getCaretPosition());
    }

    /** Saves the preferences of the user which they specified on the preferences frame. */
    private void savePreferences() {
        try {
            boolean[] toolbarPrefs = {newCheck.isSelected(), openCheck.isSelected(),
                saveCheck.isSelected(), undoCheck.isSelected(), redoCheck.isSelected(),
                compileCheck.isSelected(), runCheck.isSelected(), compileRunCheck.isSelected(),
                resetCheck.isSelected(), jarCheck.isSelected(), javadocCheck.isSelected(),
                apiCheck.isSelected(), helpCheck.isSelected(), loginCheck.isSelected()};
            String theme = "/org/fife/ui/rsyntaxtextarea/themes/" + themeComboBox.getSelectedItem();
            int autosaveIn = -1;
            int indentLevel = 3;
            int editorFontSize = 16;
            int outputFontSize = 16;
            if (autosaveCheck.isSelected()) {
                try {
                    int minutes = Integer.parseInt(autosaveTextField.getText());
                    minutes = Math.min(minutes, 60);
                    autosaveIn = 60000 * minutes;
                } catch( NumberFormatException e) {}
            }
            try {
                indentLevel = Integer.parseInt(indentTextField.getText());
            } catch( NumberFormatException e) {}
            try {
                editorFontSize = Integer.parseInt(editorFontSizeField.getText());
            } catch( NumberFormatException e) {}
            try {
                outputFontSize = Integer.parseInt(outputFontSizeField.getText());
            } catch( NumberFormatException e) {}
            if (indentLevel > 10)
                indentLevel = 10;
            if (editorFontSize > 72)
                editorFontSize = 72;
            if (outputFontSize > 72)
                outputFontSize = 72;
            
            String editorFontName = editorFontChooser.getSelectedItem().toString();
            String outputFontName = outputFontChooser.getSelectedItem().toString();
            Font editorFont = new Font(editorFontName, Font.PLAIN, editorFontSize);
            Font outputFont = new Font(outputFontName, Font.PLAIN, outputFontSize);

            String submissionOption = null;
            if (externalSubmissionRadio.isSelected())
                submissionOption = externalSubmissionField.getText();

            preferences = new Preferences(detachConsoleCheck.isSelected(),
                bracketMatchingCheck.isSelected(), lineNumbersCheck.isSelected(),
                showHelpCheck.isSelected(), toolbarPrefs, editorFont, outputFont,
                autosaveIn, indentLevel, theme, submissionOption);

            PreferencesConfigurer.save( config.getUserPath(), preferences);
            config.setWorkspace(workspace);
            applyPreferences();

        } catch (IOException e) {
            showError( "An error occured while saving the preferences.");
        }
    }

    /** Opens a file chooser for user to choose its file explorer's path. */
    public void choosePreferredWorkspace() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog( preferencesFrame) == JFileChooser.APPROVE_OPTION) {
            workspaceChanged = true;
            File file = chooser.getSelectedFile();
            if (file.isDirectory()) {
                workspace = file.getAbsolutePath();
                workspaceTextField.setText(workspace);
            }
        }
    }

    /** Restores all the default preferences to the preferences frame. */
    public void resetPreferencesWindow() {
        final Preferences DEFAULT = Preferences.DEFAULT;

        boolean[] checkBoxValues = DEFAULT.getToolbar();
        JCheckBox[] checkBoxes = {newCheck, openCheck, saveCheck, undoCheck,
            redoCheck, compileCheck, runCheck, compileRunCheck, resetCheck,
            jarCheck, javadocCheck, apiCheck, helpCheck, loginCheck};
        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i].setSelected(checkBoxValues[i]);
        }

        autosaveCheck.setSelected( DEFAULT.getAutosaveIn() != -1);
        autosaveTextField.setEnabled( DEFAULT.getAutosaveIn() != -1);
        if (DEFAULT.getAutosaveIn() != -1)
            autosaveTextField.setText( "" + DEFAULT.getAutosaveIn());

        lineNumbersCheck.setSelected( DEFAULT.getDisplayLineNumbers());
        showHelpCheck.setSelected( DEFAULT.getShowHelpForErrors());
        bracketMatchingCheck.setSelected( DEFAULT.getBracketMatching());
        detachConsoleCheck.setSelected( DEFAULT.getDispatchOnRun());
        editorFontSizeField.setText( "" + DEFAULT.getEditorFont().getSize());
        editorFontChooser.setSelectedItem( DEFAULT.getEditorFont().getName());
        outputFontSizeField.setText( "" + DEFAULT.getOutputFont().getSize());
        outputFontChooser.setSelectedItem( DEFAULT.getOutputFont().getName());
        for (int i = 0; i < themeComboBox.getItemCount(); i++) {
            String theme = themeComboBox.getItemAt(i);
            if (DEFAULT.getTheme().endsWith(theme))
                themeComboBox.setSelectedIndex(i);
        }
        if (DEFAULT.getSubmissionLink() != null) {
            externalSubmissionRadio.setSelected( true);
            externalSubmissionField.setText(DEFAULT.getSubmissionLink());
        }
        else {
            callideSubmissionRadio.setSelected( true);
            externalSubmissionField.setText("");
        }
    }

    /** Prints the status message about the autosaved file. */
    @Override
    public void report( File file, String content) {
        printStatus( "File saved automatically: " + file.getAbsolutePath());
        savedContents.set(files.indexOf(file), content);
    }

    /** Inserts a sample javadoc comment to the active text area. */
    private void insertJavadocComment() {
        final String COMMENT = "/**\n" +
            " * __description__\n" +
            " * @param param1 __\n" +
            " * @param param2 __\n" +
            " * @return __\n" + " */";
        getActiveTextArea().insert( COMMENT, getActiveTextArea().getCaretPosition());
    }

    /** Arranges the window divider locations. */
    private void arrangeWindow() {
        explorerScrollPane.setVisible( fileTrackerButton.getState());
        explorerLayeredPane.setVisible( fileTrackerButton.getState());
        methodSummaryScrollPane.setVisible( methodSummaryButton.getState());
        outputsPanel.setVisible( outputsPaneButton.getState());
        revalidateProperly();
        if (fileTrackerButton.getState()) {
            dividerLocation = 200;
            arrangeComponents();
        }
    }

    /** Arranges the window to its default state. */
    private void arrangeWindowDefault() {
        fileTrackerButton.setState( true);
        methodSummaryButton.setState( true);
        outputsPaneButton.setState( true);
        arrangeWindow();
        dividerLocation = 200;
        arrangeComponents();
    }

    /** This method is for the cases which JFrame.revalidate() does not works properly. */
    private void revalidateProperly() {
        boolean fullScreen = false;
        if (getExtendedState() == JFrame.MAXIMIZED_BOTH)
            fullScreen = true;
        setSize( getWidth() + 1, getHeight() + 1);
        setSize( getWidth() - 1, getHeight() - 1);
        if (fullScreen)
            setExtendedState( JFrame.MAXIMIZED_BOTH);
    }

    /** Opens a file chooser and adds a new template to the user's template archive. */
    private void addNewTemplate() {
        try {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog( this) == JFileChooser.APPROVE_OPTION) {
                File sourceFile = chooser.getSelectedFile();
                if (sourceFile.isFile()) {
                    String targetName = sourceFile.getName().
                        substring( 0, sourceFile.getName().lastIndexOf("."));
                    File targetFile = new File( config.getUserPath() +
                                                "/Templates/" + targetName);
                    TemplateManager.importTemplate( sourceFile, targetFile);
                    printStatus( "Template imported: " + targetName);
                    loadTemplates();
                }
            }
        } catch (IOException e) {
            showError( "An error occured while loading the template.");
        }
    }

    /** Opens a dialog to open a folder into the editor. */
    private void showFolderOpenDialog() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File folder = chooser.getSelectedFile();
            File[] folderContent = folder.listFiles();
            printStatus( "Opening folder: " + folder.getAbsolutePath());
            for (int i = 0; i < folderContent.length; i++) {
                if (folderContent[i].getName().endsWith(".java"))
                    openFile(folderContent[i]);
            }
        }
    }

    /** Compiles the active file on the editor. */
    private JTextPane compileCurrentFile() {
        int index = textTabs.getSelectedIndex();
        if (getActiveFile() == null) {
            printStatus( "The file should be saved before compiling.");
            return null;
        }
        else if ( !textAreas.get(index).getText().equals(savedContents.get(index)) ) {
            printStatus( "The file should be saved to its modified version before compiling.");
            return null;
        }
        else {
            printStatus( "Compiling file: " + getActiveFile().getName());
            return compileFile(getActiveFile().getAbsolutePath());
        }
    }

    /** Compiles a given folder to a given path. */
    private JTextPane compileFolderTo( String srcFolder, String buildFolder,
                                       ArrayList<File> dependencies) {
        if (builder == null)
            builder = new ConsoleBuilder();

        // clean up buffers
        builder.destroy();
        ConsoleCore.free();
        builder.init();
        
        JTextPane insertedPane = builder.getOutErrConsole();
        insertedPane.setFont( preferences.getOutputFont());
        compilerOutputScrollPane.setViewportView( insertedPane);
        BuildSys.setPropsForCompileWithDepend(userPath + "/BuildConfigs/buildDeps.xml",
                                              buildFolder, srcFolder, dependencies);
        BuildSys.compile(userPath + "/BuildConfigs/buildDeps.xml", stdOut, stdErr );
        outputTabs.setSelectedIndex(1);

        fileExplorer.updateDirectory( (new File(buildFolder)).getParent());
        return insertedPane;
    }

    /** Compiles a given file right next to it. */
    private JTextPane compileFile( String filePath) {
        if (builder == null)
            builder = new ConsoleBuilder();

        // clean up buffers
        builder.destroy();
        ConsoleCore.free();
        builder.init();
        
        JTextPane insertedPane = builder.getOutErrConsole();
        insertedPane.setFont( preferences.getOutputFont());
        compilerOutputScrollPane.setViewportView( insertedPane);
        BuildSys.setPropsForCompileFile(userPath + "/BuildConfigs/buildFile.xml", filePath);

        BuildSys.compile(userPath + "/BuildConfigs/buildFile.xml", stdOut, stdErr);
        outputTabs.setSelectedIndex(1);
        fileExplorer.updateDirectory( (new File(filePath)).getParent());
        return insertedPane;
    }

    /** Runs the current active file on the console. */
    private void runCurrentFile() {
        runFileDefault( getActiveFile());
    }

    private void runFileDefault( File file) {
        if (file == null)
            printStatus( "The file should be saved before running.");
        else if (!file.exists())
            printStatus( "This file could not be found on the disk.");
        else {
            File build = new File(file.getParent());
            String packageName = "";
            String classFileName;
            CompilationUnit cu;

            // control package name
            try {
                cu = JavaParser.parse( file);
                if (cu.getPackageDeclaration().isPresent())
                    packageName = cu.getPackageDeclaration().get().getName().toString();
            } catch (Exception ex) {}
            if(packageName.equals("")) {
                classFileName = file.getName().substring(0,
                                file.getName().length() - 5) + ".class";
            }
            else {
                classFileName = packageName + "/" +file.getName().substring(0,
                                file.getName().length() - 5) + ".class";
            }
            if (!(new File(file.getParent() + "/" + classFileName)).exists()) {
                printStatus( "The file should be compiled before running.");
                return;
            }
            if ((new File(file.getParent() + "/" + classFileName)).lastModified() < file.lastModified()){
                printStatus( "The file should be compiled to its lastest version before running.");
                return;
            }
            printStatus( "Running " + file.getName() + " ...");
            runFile( file, build);
        }
    }

    private void runFile( File file, File build) {
        runFile( file, build, null);
    }

    /** Runs a given file on the console. */
    private void runFile( File file, File build, ArrayList<File> dependencies) {
        resetInteractions();
        
        if (!hasMainMethod( file))
            return;
        
        String packageName = "";
        CompilationUnit cu;
        
        try {
            cu = JavaParser.parse( file);
            if (cu.getPackageDeclaration().isPresent())
                packageName = cu.getPackageDeclaration().get().getName().toString();
        } catch (IOException ex) {}
        
        String packageDest = packageName.replace('.', '/');
        String className = file.getName().substring(0, file.getName().length() - 5);
        String classDest = build + "/" + packageDest + "/" + className + ".class";
        if (!(new File( classDest)).exists()) {
            printStatus( "The source packages should be compiled before running.");
            return;
        }

        if (consoleOut) {
            consoleOutputArea = new JTextPane();
            consoleOutputArea.setFont( preferences.getOutputFont());
            // dispatch again if the console is out already.
            ConsoleCore.dispatch(detachScroll, consoleOutputArea, outputTabs,
                                 tabComp, consoleFrame, consoleOut, this);
        }
        else {
            consoleOutputArea = new JTextPane();
            consoleOutputArea.setFont( preferences.getOutputFont());
            consoleOutputScrollPane.setViewportView(consoleOutputArea);
        }

        if (dependencies == null)
            executor = new Executor(build.getAbsolutePath());
        else
            executor = new Executor(build.getAbsolutePath(), dependencies);

        if(packageName.equals(""))
            executor.execute(consoleOutputArea,className);
        else
            executor.execute(consoleOutputArea, packageName + "." + className);

        outputTabs.setSelectedIndex(2);
    }

    /** Generates javadoc for the current project. */
    private void javadocCurrentProject() {    
        ProjectHandler currentProject = getProjectHandler(getActiveFile());

        if (!projectMode || currentProject == null) {
            printStatus( "You should first create a project to generate javadoc.");
            return;
        }

        printStatus( "Generating javadoc: " + currentProject.getName());

        if (builder == null)
            builder = new ConsoleBuilder();

        builder.destroy();
        ConsoleCore.free();
        builder.init();
        JTextPane insertedPane = builder.getOutErrConsole();
        insertedPane.setFont( preferences.getOutputFont());
        compilerOutputScrollPane.setViewportView( insertedPane);

        // INPUTS: JAR XML FILE, SRC DIR, TARGET DIR
        BuildSys.setPropsForJavaDoc(userPath + "/BuildConfigs/buildJavadoc.xml", 
                                    currentProject.getSrc().getAbsolutePath(),
                                    currentProject.getPath() + "/doc");
        BuildSys.compile(userPath + "/BuildConfigs/buildJavadoc.xml", stdOut, stdErr);
        outputTabs.setSelectedIndex(1);
        fileExplorer.updateDirectory(currentProject.getPath());
    }

    /** Generates an executable jar file for the current project. */
    private void jarCurrentProject() {
        ProjectHandler currentProject = getProjectHandler(getActiveFile());

        if (!projectMode || currentProject == null) {
            printStatus( "You should first create a project to generate jar file.");
            return;
        }

        printStatus( "Generating jar: " + currentProject.getName());

        if (builder == null)
            builder = new ConsoleBuilder();

        builder.destroy();
        ConsoleCore.free();
        builder.init();
        JTextPane insertedPane = builder.getOutErrConsole();
        insertedPane.setFont( preferences.getOutputFont());
        compilerOutputScrollPane.setViewportView( insertedPane);

        String mainFileName = currentProject.getMainClass().getName();
        String mainClassName = mainFileName.substring(0, mainFileName.length() - 5);

        String packageName = "";
        CompilationUnit cu;
        try {
            cu = JavaParser.parse( currentProject.getMainClass());
            if (cu.getPackageDeclaration().isPresent())
                packageName = cu.getPackageDeclaration().get().getName().toString();
        } catch (IOException e) {}
        if (!packageName.equals(""))
            mainClassName = packageName + "." + mainClassName;

        String buildConfigPath;
        if (currentProject.getJarFiles().isEmpty())
            buildConfigPath = "/BuildConfigs/buildJar.xml";
        else
            buildConfigPath = "/BuildConfigs/buildDepJar.xml";

        BuildSys.setPropsForJar(userPath + buildConfigPath,
                                currentProject.getBuild().getAbsolutePath(),
                                currentProject.getPath() + "/dist",
                                mainClassName, currentProject.getName() +".jar");
        BuildSys.compile(userPath + buildConfigPath, stdOut, stdErr);

        outputTabs.setSelectedIndex(1);
        fileExplorer.updateDirectory(currentProject.getPath());
    }

    /** Resets the console interactions. */
    private void resetInteractions() {
        if (executor != null)
            executor.stop();
    }

    /** Compiles the file and runs it when compiling is done. */
    private void compileRunCurrentFile() {
        int index = textTabs.getSelectedIndex();
        if ( !textAreas.get(index).getText().equals(savedContents.get(index)) ) {
            printStatus( "The file should be saved to its modified version before compiling.");
            return;
        }
        if (getActiveFile() == null)
            return;
        if ( !hasMainMethod(getActiveFile())) {
            return;
        }
        JTextPane insertedPane = compileCurrentFile();
        if (insertedPane != null) {
            insertedPane.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {}
                public void removeUpdate(DocumentEvent e) {}
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (insertedPane.getText().contains( "BUILD SUCCESSFUL")) {
                        runCurrentFile();
                        insertedPane.getDocument().removeDocumentListener(this);
                    }
                }
            });
        }
    }

    /** Re-attaches the detached console to the main frame. */
    @Override
    public void attachConsole() {
        consoleOut = false;
        consoleOutputScrollPane.setViewportView(consoleOutputArea);
        outputTabs.setTabComponentAt( 2, tabComp);
        outputTabs.removeChangeListener( outputTabs.getChangeListeners()[0]);
        outputTabs.setSelectedIndex(2);
    }

    /** Enables drop-to-open feature. */
    private void enableFileDrop( Component dropPane) {
        new FileDrop( dropPane, new FileDrop.Listener() {
            @Override
            public void filesDropped( java.io.File[] files ){   
                for( int i = 0; i < files.length; i++ ){   
                    openFile( files[i]);
                }  
            }
        });
    }

    /** Checks if there is an active editor. */
    private boolean isEditing() {
        return textTabs.getSelectedIndex() != -1;
    }
    
    /**
    * A method to update the auto-completion list for class
    * @param file a parameter to take the active file to parse
    */
    public void updateAutoComplete( File file)
    {
        autoComplete = new AutoComplete( getActiveTextArea(), file);

        try {
            if (file.getName().endsWith(".java")) {

            autoComplete.updateAutoComplete();
            }
        } catch (IOException | ParseProblemException ex ) {}
    }

    /** Inserts the method summary tree to the main frame. */
    private void initMethodSummary() {
        try {
            methodParser = new Parser();
            methodSummary = new SummaryTree( methodParser, this);
            methodSummary.setFont(Preferences.DEF_FONT);
            ToolTipManager.sharedInstance().registerComponent( methodSummary ) ;
        } catch (MalformedURLException ex) {} catch (IOException ex) {}
        methodSummaryScrollPane.setViewportView(methodSummary);
    }

    /** Updates the method summary tree's contents according to the given file. */
    private void updateMethodSummary( File file) {
        try {
            if (file.getName().endsWith(".java")) {
                if (!methodParser.contains(file))
                    methodParser.addNode( file);
                else
                    methodParser.refreshNode( file);
            }
        } catch (ParseException | IOException | ParseProblemException ex ) {}
        methodSummary.configureTree();
        methodSummary.expandRow(0);
        methodSummary.updateUI();
    }

    /** Visits the given file's position on the editor. */
    @Override
    public void visitNode( File file, Position position) {
        int index = -1;
        for (int i = 0; i < files.size(); i++) {
            if (file == files.get(i))
                index = i;
        }
        if (index == -1) {
            openFile(file);
            index = files.size() - 1;
        }
        textTabs.setSelectedIndex(index);
        int distance = PositionCalculator.calculate(textAreas.get(index).getText(), position);
        textAreas.get(index).requestFocus();
        textAreas.get(index).setCaretPosition(distance);
    }

    /** Shows the frame for creating projects. */
    private void showCreateProject() {
        creatingProject = true;
        projectNameField.setEditable(true);
        projectLocationField.setEditable(true);
        browseLocationButton.setEnabled(true);
        browseMainButton.setEnabled( false);
        mainClassField.setText("");
        projectFrame.setTitle( "Create New Project");
        projectFrame.pack();
        projectFrame.setLocationRelativeTo( this);
        projectFrame.setVisible(true);
    }

    /** Creates a project with the specified properties on the frame. */
    private void createProject() {
        String projectName = projectNameField.getText();
        String projectLocation = projectLocationField.getText();
        String projectRoot = projectLocation + "/" + projectName + "/";

        File buildFolder = new File( projectRoot + "/build");
        File srcFolder = new File( projectRoot + "/src");
        File rootFolder = new File( projectRoot);
        ListModel<String> paths = classPathList.getModel();
        try {
            rootFolder.mkdir();
            ProjectHandler handler = new ProjectHandler( buildFolder,
                                     srcFolder, null, projectRoot);
            for ( int i = 0; i < paths.getSize(); i++ ) 
                handler.addJar( new File( paths.getElementAt(i) ) );
            handler.saveProject( projectRoot, projectName);
            handler.copyLibs();
        } catch (IOException e) {
            showError( "An error occured while creating the project.");
        }

        openProject( new File( projectRoot));
        projectFrame.setVisible(false);
    }

    /** Opens a folder chooser to open a project. */
    private void showProjectOpenDialog() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES);
        if (chooser.showOpenDialog( this) == JFileChooser.APPROVE_OPTION) {
            File projectSelection = chooser.getSelectedFile();
            if (projectSelection.isDirectory())
                openProject( projectSelection);
            else if (projectSelection.isFile()) {
                if (projectSelection.getName().endsWith(ProjectHandler.EXTENSION))
                    openProject( projectSelection.getParentFile());
                else
                    printStatus( "This project file is not supported.");
            }
        }
    }

    /** Opens the given project folder on the IDE. */
    private void openProject( File projectFolder) {
        boolean open = true;
        ProjectHandler handler = new ProjectHandler();
        File projectFile = null;
        try {
            File[] projectFiles = projectFolder.listFiles();
            for (File file : projectFiles)
                if (file.getName().endsWith(ProjectHandler.EXTENSION))
                projectFile = file;
            if (projectFile == null)
                printStatus( "No Call-IDE project file found in the given directory.");
            else {
                boolean alreadyOpen = false;
                for (ProjectHandler ph : openProjects) {
                    if (new File(projectFile.getParent()).equals(new File(ph.getPath())))
                        alreadyOpen = true;
                }
                if (alreadyOpen) {
                    printStatus( "This project is already opened.");
                    open = false;
                }
                else
                    handler.openProject( projectFile);
            }
        } catch (IOException | ClassNotFoundException ex) {
            open = false;
            showError( "An error occured while opening the project.");
        } catch ( ProjectHandler.FilesMismatchException ex) {
            // printStatus( "Some files might be missing in your src folder.");
        }
        if (open) {
            if (fileExplorer == null || !fileExplorer.isProjectBrowser()) {
                if (fileExplorer != null)
                    explorerScrollPane.remove(fileExplorer);
                addExplorerWith( handler.getPath());
                fileExplorer.setIsProjectBrowser(true);
                projectMode = true;
                openProjects.add( handler);
            }
            else {
                openProjects.add( handler);
                fileExplorer.openProject(handler.getPath());
            }
            try {
                RealTimeFolderWatcher fw = new RealTimeFolderWatcher(handler.getSrc().toPath(), this);
                folderWatchers.add(fw);
                fw.registerAll( handler.getSrc().toPath());
                fw.start();
            } catch (IOException e) {}
            printStatus( "Project opened: " + handler.getName());
        }
    }

    ArrayList<RealTimeFolderWatcher> folderWatchers;

    /** Supplies access to the ProjectHandler object of the given file. */
    private ProjectHandler getProjectHandler( File file) {
        for (ProjectHandler ph : openProjects) {
            ArrayList<File> javaFiles = ph.getAllJavaFiles();
            for (File javaFile : javaFiles) {
                if (file.equals(javaFile))
                    return ph;
            }
        }
        return null;
    }

    /** Updates the project files on the disk. */
    @Override
    public void updateProjects() {
        for (ProjectHandler handler : openProjects) {
            try {
                File projectFile = null;
                File[] projectFiles = (new File(handler.getPath())).listFiles();
                for (File file : projectFiles)
                    if (file.getName().endsWith(ProjectHandler.EXTENSION))
                    projectFile = file;

                ArrayList<File> javaFiles = new ArrayList<File>();
                searchJavaFiles( handler.getSrc(), javaFiles);
                handler.setAllJavaFiles( javaFiles);
                handler.saveProject( projectFile);
            } catch (IOException ex) {
                showError( "An error occured while synchronizing the projects.");
            }
        }
    }

    /** Adds all of the java source files in the given folder location to the given ArrayList. */
    private void searchJavaFiles( File src, ArrayList<File> javaFiles) {
        ArrayList<File> srcFiles = new ArrayList<File>( Arrays.asList(src.listFiles()));
        for (File f : srcFiles) {
            if (f.isDirectory())
                searchJavaFiles( f, javaFiles);
            else if (f.getName().endsWith(".java"))
                javaFiles.add(f);
        }
    }

    /** Determines what to do with the compile button on the frame. */
    private void compileAction() {
        if (isEditing()) {
            if (builder == null)
                builder = new ConsoleBuilder();
            if (projectMode)
                compileCurrentProject();
            else
                compileCurrentFile();
        }
    }

    /** Compiles the active project on the editor. */
    private JTextPane compileCurrentProject() {
        updateProjects();
        if (getActiveFile() == null) {
            printStatus( "The file should be saved before compiling.");
            return null;
        }
        ProjectHandler activeProject = getProjectHandler( getActiveFile());
        if (activeProject == null) {
            printStatus( "This file is not in a project.");
            return null;
        }
        printStatus( "Compiling project: " + activeProject.getName());
        JTextPane insertedPane = compileFolderTo( activeProject.getSrc().getAbsolutePath(),
                                                  activeProject.getBuild().getAbsolutePath(),
                                                  activeProject.getJarFiles());
        fileExplorer.updateDirectory(activeProject.getPath());
        return insertedPane;
    }

    /** Checks if a file has a proper main method in it. */
    private boolean hasMainMethod( File file) {
        Parser mainChecker = new Parser();
        try {
            mainChecker.addNode(file);
        } catch (Exception ex) {
            return true; // Should return true for errorenous source files.
        }
        if (!mainChecker.hasMain(file)) {
            printStatus( "The class " + file.getName() + " does not have a proper main method.");
            return false;
        }
        return true;
    }

    /** Checks and arranges the state (detached/attached) of the console. */
    private void checkConsoleState() {
        if (preferences.getDispatchOnRun()) {
            if (consoleOut) {
                consoleFrame.setExtendedState( JFrame.NORMAL);
                consoleFrame.toFront();
                consoleFrame.requestFocus();
            }
            else
                detachConsole();
        }
    }

    /** Determines what to do with the run button on the frame. */
    private void runAction() {
        if (isEditing()) {
            if (projectMode)
                runCurrentProject();
            else
                runCurrentFile();
            checkConsoleState();
        }
    }

    /** Runs the active project on the editor. */
    private void runCurrentProject() {
        updateProjects();
        if (getActiveFile() == null) {
            printStatus( "The file should be saved before running.");
            return;
        }
        ProjectHandler activeProject = getProjectHandler( getActiveFile());
        if (activeProject == null) {
            printStatus( "This file is not in a project.");
            return;
        }
        File mainClassFile = activeProject.getMainClass();
        if (mainClassFile == null) {
            printStatus( "Please select the main class of your project first.");
            showPropertiesOf(activeProject);
            showMainSelection();
        }
        else if (!mainClassFile.exists() || !mainClassFile.isFile()) {
            printStatus( "Can't find the compiled main class: " + mainClassFile.getAbsolutePath());
        }
        else if (!compiledToLast(activeProject)) {
            printStatus( "The project should be compiled to its latest version before running.");
        }
        else {
            printStatus( "Running " + activeProject.getMainClass().getName() +
                         " in project " + activeProject.getName());
            runFile( activeProject.getMainClass(), activeProject.getBuild(),
                    activeProject.getJarFiles());
        }
    }

    /** Determines what to do with the compile & run button on the frame. */
    private void compileRunAction() {
        if (isEditing()) {
            if (builder == null)
                builder = new ConsoleBuilder();
            if (projectMode)
                compileRunCurrentProject();
            else
                compileRunCurrentFile();
            checkConsoleState();
        }
    }
    
    private String getPathDifference( String shorter, String longer) {
        int i = 0;
        while (i < shorter.length() && shorter.charAt(i) == longer.charAt(i)) i++;
        return longer.substring(i);
    }
    private boolean compiledToLast( ProjectHandler project) {
        ArrayList<File> sourceFiles = project.getAllJavaFiles();
        String srcLocation = project.getSrc().getAbsolutePath();
        String buildLocation = project.getBuild().getAbsolutePath();

        for (File sourceFile : sourceFiles) {
            String sourcePath = sourceFile.getParent();            
            String difference = getPathDifference(srcLocation, sourcePath);
            String sourceName = sourceFile.getName();
            String buildPath = buildLocation + "/" + difference + "/" + sourceName.substring(0, sourceName.length()-5) + ".class";
            File buildFile = new File(buildPath);
            if (buildFile.lastModified() < sourceFile.lastModified())
                return false;
        }
        
        return true;
    }

    /** Opens a folder chooser dialog for the user to choose its project location. */
    private void browseProjectLocation() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(projectFrame) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file.isDirectory())
                projectLocationField.setText( file.getAbsolutePath());
        }
        updateProjectRootField();
    }

    /** Opens a file chooser dialog for user to choose its main class location. */
    private void showMainSelection() {
        mainSelectionList.setModel(new AbstractListModel() {
            ArrayList files = (new Parser()).searchMain( modifyingProject.getSrc());
            public int getSize() { return files.size(); }
            public Object getElementAt(int i) { return files.get(i); }
        });
        mainSelectionFrame.pack();
        mainSelectionFrame.setLocationRelativeTo(this);
        mainSelectionFrame.setVisible(true);
    }

    /** Sets the main class of the project as selected in the frame. */
    private void selectMainClass() {
        if (mainSelectionList.getSelectedValue() != null)
            mainClassField.setText( mainSelectionList.getSelectedValue().toString());
        mainSelectionFrame.setVisible(false);
    }

    /** Opens a file chooser dialog to browse class paths of the project. */
    private void browseClassPath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        if (chooser.showOpenDialog(projectFrame) == JFileChooser.APPROVE_OPTION) {
            //File file = chooser.getSelectedFile();
            File[] files = chooser.getSelectedFiles();
            for(File file : files) {
                String pathToAdd = file.getAbsolutePath();
                ListModel<String> currentPaths = classPathList.getModel();
                boolean alreadyExists = false;
                for (int i = 0; i < currentPaths.getSize(); i++)
                    if (currentPaths.getElementAt(i).equals(pathToAdd))
                    alreadyExists = true;
                if (!alreadyExists && pathToAdd.endsWith(".jar"))
                    ((DefaultListModel) classPathList.getModel()).addElement(pathToAdd);
            }
        }
    }

    /** Shows the project properties of the project which is located on the given folder. */
    @Override
    public void showProjectProperties( File projectRoot) {
        ProjectHandler selectedProject = null;
        for (ProjectHandler project : openProjects) {
            if (new File(projectRoot.getAbsolutePath()).equals(new File(project.getPath()))) {
                selectedProject = project;
            }
        }
        if (selectedProject != null)
            showPropertiesOf( selectedProject);
    }

    /** Closes the project which is located on the given folder. */
    @Override
    public void closeProject( File projectRoot) {
        ProjectHandler selectedProject = null;
        for (ProjectHandler project : openProjects) {
            if (new File(projectRoot.getAbsolutePath()).equals(new File(project.getPath())))
                selectedProject = project;
        }
        openProjects.remove(selectedProject);
        if (openProjects.isEmpty()) {
            explorerScrollPane.remove(fileExplorer);
            if (workspace != null)
                addExplorer();
            else {
                addEmptyExplorer();
                noWorkspacePanel.setVisible(true);
            }
            fileExplorer.setIsProjectBrowser(false);
            projectMode = false;
        }
        printStatus( "Project closed: " + selectedProject.getName());
    }

    /** Shows properties frame of the given project. */
    private void showPropertiesOf( ProjectHandler project) {
        creatingProject = false;
        modifyingProject = project;
        projectNameField.setEditable(false);
        projectLocationField.setEditable(false);
        browseLocationButton.setEnabled(false);
        projectNameField.setText( project.getName());
        projectRootField.setText( project.getPath());
        projectLocationField.setText( (new File(project.getPath()).getParent()));
        if (project.getMainClass() != null)
            mainClassField.setText( project.getMainClass().getAbsolutePath());
        ArrayList<File> externalPaths = project.getJarFiles();
        ((DefaultListModel) classPathList.getModel()).removeAllElements();
        for (File jarFile : externalPaths)
            ((DefaultListModel) classPathList.getModel()).addElement(jarFile.getAbsolutePath());
        projectFrame.setTitle( "Project Properties");
        browseMainButton.setEnabled( true);
        projectFrame.pack();
        projectFrame.setLocationRelativeTo( this);
        projectFrame.setVisible(true);
    }

    /** Updates the project root text field. */
    private void updateProjectRootField() {
        if (projectLocationField.getText().indexOf('\\') != -1)
            projectRootField.setText( projectLocationField.getText() + "\\" + projectNameField.getText());
        else
            projectRootField.setText( projectLocationField.getText() + "/" + projectNameField.getText());
    }

    /** Modifies the project according to the given info on the project properties frame. */
    private void modifyProject() {
        String projectName = projectNameField.getText();
        String projectLocation = projectLocationField.getText();
        String mainClass = mainClassField.getText();
        String projectRoot = projectLocation + "/" + projectName + "/";

        File buildFolder = new File( projectRoot + "/build");
        File srcFolder = new File( projectRoot + "/src");
        File mainClassFile;
        if (mainClass.trim().equals(""))
            mainClassFile = null;
        else
            mainClassFile = new File( mainClass);
        File rootFolder = new File( projectRoot);
        ListModel<String> paths = classPathList.getModel();
        try {
            if (!(new File(modifyingProject.getPath())).equals(rootFolder))
                rootFolder.mkdir();
            modifyingProject.setPath( projectRoot);
            modifyingProject.setBuild(buildFolder);
            modifyingProject.setSrc(srcFolder);
            modifyingProject.setMainClass(mainClassFile);
            modifyingProject.removeAllJars();
            ArrayList<File> existingJars = modifyingProject.getJarFiles();
            for (int i = 0; i < paths.getSize(); i++) {
                File jarFile = new File( paths.getElementAt(i));
                boolean alreadyIn = false;
                for (int j = 0; j < existingJars.size(); j++) {
                    File existingJar = existingJars.get(j);
                    if (existingJar.equals( jarFile)) {
                        j = existingJars.size();
                        alreadyIn = true;
                    }
                }
                if (!alreadyIn)
                    modifyingProject.addJar( jarFile);
            }
            modifyingProject.saveProject( projectRoot, projectName);
            modifyingProject.copyLibs();
        } catch (IOException e) {
            showError( "An error occured while saving the project properties.");
        }
        projectFrame.setVisible(false);
    }

    /** Compiles the selected file on the file explorer. */
    @Override
    public void compileSelectedFile( File selectedFile) {
        if (projectMode) {
            updateProjects();
            ProjectHandler selectedProject = getProjectHandler( selectedFile);
            compileFolderTo( selectedProject.getSrc().getAbsolutePath(),
                            selectedProject.getBuild().getAbsolutePath(),
                            selectedProject.getJarFiles());
            fileExplorer.updateDirectory(selectedProject.getPath());
        }
        else
            compileFile( selectedFile.getAbsolutePath());
    }

    /** Runs the selected file on the file explorer. */
    @Override
    public void runSelectedFile( File selectedFile) {
        if (projectMode) {
            updateProjects();
            ProjectHandler selectedProject = getProjectHandler( selectedFile);
            runFile( selectedFile, selectedProject.getBuild());
        }
        else
            runFile( selectedFile, selectedFile.getParentFile());
    }

    /** Compiles and runs the active project on the editor. */
    private void compileRunCurrentProject() {
        if (getActiveFile() == null) {
            printStatus( "The file should be saved before running.");
            return;
        }
        ProjectHandler activeProject = getProjectHandler( getActiveFile());
        if (activeProject == null) {
            printStatus( "This file is not in a project.");
            return;
        }
        if (!currentIsRunnable())
            return;
        JTextPane insertedPane = compileCurrentProject();
        if (insertedPane != null) {
            insertedPane.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {}
                public void removeUpdate(DocumentEvent e) {}
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (insertedPane.getText().contains( "BUILD SUCCESSFUL"))
                        runCurrentProject();
                }
            });
        }
    }

    /** Checks if the current project has runnable properties. */
    private boolean currentIsRunnable()  {
        updateProjects();
        ProjectHandler activeProject = getProjectHandler( getActiveFile());
        if (!activeProject.getMainClass().exists()) {
            printStatus( "Please set a main class to the project.");
            return false;
        }
        return hasMainMethod( activeProject.getMainClass());
    }

    /** Determines what to do with the ok button of the project properties frame. */
    private void projectOkAction() {
        if (creatingProject)
            createProject();
        else
            modifyProject();
    }

    /** Updates the state of the external submission link field according to the radio buttons. */
    private void updateLinkField() {
        externalSubmissionField.setEnabled( externalSubmissionRadio.isSelected());
        if (!externalSubmissionRadio.isSelected())
            externalSubmissionField.setText("");
    }

    /** Initializes the input output streams of the debug console. */
    private void initStreams() {
        // Use StdOut.println() instead of System.out.println for debugging.
        stdOut = System.out;
        stdErr = System.err;
    }

    /** Checks the JDK and redirects the user if not any found. */
    private void checkJDK() {
        JDKChecker checker = new JDKChecker();
        String homeJdk = checker.checkJAVA_HOME();
        String pathJdk = checker.checkPathVar();
        ArrayList<String> allJdk = checker.checkDefaultPath();
        if (homeJdk == null && pathJdk == null && allJdk.isEmpty())
            printStatus( "The system JDK can't be found. Visit this address " +
                         "to download and install it from the official website: " +
                         JDKChecker.JDK_LINK);
    }

    /** Refreshes the file list of the explorer. */
    private void refreshExplorer() {
        if (fileExplorer != null)
            fileExplorer.refreshAll();
    }

    /** Pops up an error message. */
    private void showError( String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /** Shows the help about the IDE. */
    private void showHelpContents() {
        helpContentsFrame.pack();
        helpContentsFrame.setLocationRelativeTo(this);
        helpContentsFrame.setVisible(true);
        helpList.setSelectedIndex(0);
    }
    
    /** Shows the about page of the software. */
    private void showAbout() {
        aboutFrame.pack();
        aboutFrame.setLocationRelativeTo( this);
        aboutFrame.setVisible( true);
    }
    
    /** Opens the login screen according to the login option of the user. */
    private void loginAction() {
        String submissionLink = preferences.getSubmissionLink();
        if (submissionLink != null) {
            try {
                LinkOpener.openLink( submissionLink);
            } catch( Exception e) {
                printStatus( "Your submission link is invalid. Please check the preferences.");
            }
        }
        else {
            JFrame loginFrame = new LoginFrame();
            loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            loginFrame.setLocationRelativeTo(this);
            loginFrame.setVisible(true);
        }
    }
    
    /** Opens the registeration screen according to the login option of the user. */
    private void registerAction() {
        String submissionLink = preferences.getSubmissionLink();
        if (submissionLink != null) {
            try {
                LinkOpener.openLink( submissionLink);
            } catch( Exception e) {
                printStatus( "Your submission link is invalid. Please check the preferences.");
            }
        }
        else {
            VerifyEmail verifyEmail = new VerifyEmail();
            verifyEmail.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            verifyEmail.setLocationRelativeTo(this);
            verifyEmail.setVisible(true);
        }
    }

    // Other Variables
    private ArrayList<RSyntaxTextArea> textAreas;
    private ArrayList<File> files;
    private ArrayList<JLabel> tabTitles;
    private ArrayList<String> savedContents;
    private ArrayList<AutoFileSaver> autosavers;
    private ArrayList<ProjectHandler> openProjects;
    private ArrayList<String> helpStrings;
    private boolean workspaceChanged;
    private boolean consoleOut;
    private boolean projectMode;
    private boolean creatingProject;
    private int dividerLocation;
    private int untitledCount;
    private int lastOutputTabIndex;
    private String userPath;
    private String workspace;
    private String developerList;
    private String[] templates;
    private String[] templateNames;
    private JButton workspaceButton;
    private JPanel noWorkspacePanel;
    private JTextPane consoleOutputArea;
    private JScrollPane detachScroll;
    private JFrame consoleFrame;
    private Component tabComp;
    private BufferedImage frameIcon;
    private ImageIcon closeIcon;
    private ImageIcon detachIcon;
    private ImageIcon refreshIcon;
    private FileConfigurer config;
    private Preferences preferences;
    private FileExplorer fileExplorer;
    private Executor executor;
    private SummaryTree methodSummary;
    private Parser methodParser;
    private ProjectHandler modifyingProject;
    private PrintStream stdOut;
    private PrintStream stdErr;
    private ConsoleBuilder builder;
    private AutoComplete autoComplete;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutButton;
    private javax.swing.JFrame aboutFrame;
    private javax.swing.JLabel aboutSubLabel;
    private javax.swing.JPanel aboutSubPanel;
    private javax.swing.JMenuItem apiButton;
    private javax.swing.JCheckBox apiCheck;
    private javax.swing.JButton apiTool;
    private javax.swing.JCheckBox autosaveCheck;
    private javax.swing.JTextField autosaveTextField;
    private javax.swing.JCheckBox bracketMatchingCheck;
    private javax.swing.JButton browseLocationButton;
    private javax.swing.JButton browseMainButton;
    private javax.swing.JButton browseWorkspaceButton;
    private javax.swing.JRadioButton callideSubmissionRadio;
    private javax.swing.JButton classPathButton;
    private javax.swing.JLabel classPathLabel;
    private javax.swing.JList<String> classPathList;
    private javax.swing.JScrollPane classPathScrollPane;
    private javax.swing.JMenuItem compileButton;
    private javax.swing.JCheckBox compileCheck;
    private javax.swing.JMenuItem compileRunButton;
    private javax.swing.JCheckBox compileRunCheck;
    private javax.swing.JButton compileRunTool;
    private javax.swing.JButton compileTool;
    private javax.swing.JTextArea compilerOutputArea;
    private javax.swing.JPanel compilerOutputPanel;
    private javax.swing.JScrollPane compilerOutputScrollPane;
    private javax.swing.JPanel consoleOutputPanel;
    private javax.swing.JScrollPane consoleOutputScrollPane;
    private javax.swing.JMenuItem copyButton;
    private javax.swing.JMenuItem cutButton;
    private javax.swing.JCheckBox detachConsoleCheck;
    private javax.swing.JLabel developerListLabel;
    private javax.swing.JMenu editMenu;
    private javax.swing.JPanel editorAndMethodSummaryPanel;
    private javax.swing.JComboBox<String> editorFontChooser;
    private javax.swing.JLabel editorFontLabel;
    private javax.swing.JTextField editorFontSizeField;
    private javax.swing.JLabel editorFontSizeLabel;
    private javax.swing.JLayeredPane explorerLayeredPane;
    private javax.swing.JScrollPane explorerScrollPane;
    private javax.swing.JTextField externalSubmissionField;
    private javax.swing.JRadioButton externalSubmissionRadio;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JCheckBoxMenuItem fileTrackerButton;
    private javax.swing.JFrame findAndReplaceFrame;
    private javax.swing.JLabel findLabel;
    private javax.swing.JMenuItem findReplaceButton;
    private javax.swing.JPanel findReplacePanel;
    private javax.swing.JTextField findTextField;
    private javax.swing.JCheckBox helpCheck;
    private javax.swing.JMenuItem helpContentsButton;
    private javax.swing.JFrame helpContentsFrame;
    private javax.swing.JList helpList;
    private javax.swing.JScrollPane helpListScrollPane;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JTextPane helpPane;
    private javax.swing.JScrollPane helpPaneScrollPane;
    private javax.swing.JSplitPane helpSplitPane;
    private javax.swing.JButton helpTool;
    private javax.swing.JPanel idePreferencesPanel;
    private javax.swing.JLabel indentLabel;
    private javax.swing.JTextField indentTextField;
    private javax.swing.JMenuItem insertJavadocButton;
    private javax.swing.JMenuItem jarButton;
    private javax.swing.JCheckBox jarCheck;
    private javax.swing.JButton jarTool;
    private javax.swing.JMenuItem javadocButton;
    private javax.swing.JCheckBox javadocCheck;
    private javax.swing.JButton javadocTool;
    private javax.swing.JLabel licenseLabel;
    private javax.swing.JCheckBox lineNumbersCheck;
    private javax.swing.JMenuItem loginButton;
    private javax.swing.JCheckBox loginCheck;
    private javax.swing.JButton loginTool;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JTextField mainClassField;
    private javax.swing.JLabel mainClassLabel;
    private javax.swing.JFrame mainSelectionFrame;
    private javax.swing.JLabel mainSelectionLabel;
    private javax.swing.JList mainSelectionList;
    private javax.swing.JScrollPane mainSelectionScrollPane;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JCheckBox matchCaseCheck;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JCheckBoxMenuItem methodSummaryButton;
    private javax.swing.JScrollPane methodSummaryScrollPane;
    private javax.swing.JLabel minsLabel;
    private javax.swing.JCheckBox newCheck;
    private javax.swing.JMenuItem newFileButton;
    private javax.swing.JMenuItem newProjectButton;
    private javax.swing.JMenuItem newTemplateButton;
    private javax.swing.JButton newTool;
    private javax.swing.JButton nextButton;
    private javax.swing.JCheckBox openCheck;
    private javax.swing.JMenuItem openFileButton;
    private javax.swing.JMenuItem openFolderButton;
    private javax.swing.JMenuItem openProjectButton;
    private javax.swing.JButton openTool;
    private javax.swing.JComboBox<String> outputFontChooser;
    private javax.swing.JLabel outputFontLabel;
    private javax.swing.JTextField outputFontSizeField;
    private javax.swing.JLabel outputFontSizeLabel;
    private javax.swing.JTabbedPane outputTabs;
    private javax.swing.JCheckBoxMenuItem outputsPaneButton;
    private javax.swing.JPanel outputsPanel;
    private javax.swing.JMenuItem pasteButton;
    private javax.swing.JTree placeHolderFileExplorer;
    private javax.swing.JMenuItem placeHolderMenu1;
    private javax.swing.JMenuItem placeHolderMenu2;
    private javax.swing.JMenuItem placeHolderMenu3;
    private javax.swing.JTree placeholderMethodSummary;
    private javax.swing.JTextArea placeholderOutputArea;
    private javax.swing.JPanel preferecesButtonPanel;
    private javax.swing.JMenuItem preferencesButton;
    private javax.swing.JButton preferencesCancel;
    private javax.swing.JFrame preferencesFrame;
    private javax.swing.JButton preferencesOk;
    private javax.swing.JButton preferencesReset;
    private javax.swing.JButton previousButton;
    private javax.swing.JButton projectCancelButton;
    private javax.swing.JFrame projectFrame;
    private javax.swing.JTextField projectLocationField;
    private javax.swing.JLabel projectLocationLabel;
    private javax.swing.JTextField projectNameField;
    private javax.swing.JLabel projectNameLabel;
    private javax.swing.JButton projectOkButton;
    private javax.swing.JPanel projectPanel;
    private javax.swing.JTextField projectRootField;
    private javax.swing.JLabel projectRootLabel;
    private javax.swing.JMenuItem quitButton;
    private javax.swing.JMenuItem redoButton;
    private javax.swing.JCheckBox redoCheck;
    private javax.swing.JButton redoTool;
    private javax.swing.JMenuItem registerButton;
    private javax.swing.JButton replaceAllButton;
    private javax.swing.JButton replaceButton;
    private javax.swing.JLabel replaceLabel;
    private javax.swing.JTextField replaceTextField;
    private javax.swing.JMenuItem resetButton;
    private javax.swing.JCheckBox resetCheck;
    private javax.swing.JButton resetTool;
    private javax.swing.JMenuItem restoreButton;
    private javax.swing.JMenuItem runButton;
    private javax.swing.JCheckBox runCheck;
    private javax.swing.JButton runTool;
    private javax.swing.JMenuItem saveAsButton;
    private javax.swing.JMenuItem saveButton;
    private javax.swing.JCheckBox saveCheck;
    private javax.swing.JButton saveTool;
    private javax.swing.JMenuItem selectAllButton;
    private javax.swing.JButton selectMainButton;
    private javax.swing.JPopupMenu.Separator separator1;
    private javax.swing.JPopupMenu.Separator separator10;
    private javax.swing.JPopupMenu.Separator separator11;
    private javax.swing.JPopupMenu.Separator separator12;
    private javax.swing.JPopupMenu.Separator separator2;
    private javax.swing.JPopupMenu.Separator separator3;
    private javax.swing.JPopupMenu.Separator separator4;
    private javax.swing.JPopupMenu.Separator separator5;
    private javax.swing.JPopupMenu.Separator separator6;
    private javax.swing.JPopupMenu.Separator separator7;
    private javax.swing.JPopupMenu.Separator separator8;
    private javax.swing.JPopupMenu.Separator separator9;
    private javax.swing.JCheckBox showHelpCheck;
    private javax.swing.JTextArea statusArea;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JScrollPane statusScrollPane;
    private javax.swing.ButtonGroup submissionButtonGroup;
    private javax.swing.JMenu submissionMenu;
    private javax.swing.JLabel submissionSelectLabel;
    private javax.swing.JMenu templatesMenu;
    private javax.swing.JTabbedPane textTabs;
    private javax.swing.JComboBox<String> themeComboBox;
    private javax.swing.JLabel themeLabel;
    private javax.swing.JPanel toolbarPanel;
    private javax.swing.JPanel toolbarPreferencesPanel;
    private javax.swing.JMenu toolsMenu;
    private javax.swing.JSplitPane topLeftSplitPane;
    private javax.swing.JSplitPane topSplitPane;
    private javax.swing.JMenuItem tutorialsButton;
    private javax.swing.JMenuItem undoButton;
    private javax.swing.JCheckBox undoCheck;
    private javax.swing.JButton undoTool;
    private javax.swing.JCheckBox wholeWordCheck;
    private javax.swing.JMenu windowMenu;
    private javax.swing.JLabel workspaceLabel;
    private javax.swing.JTextField workspaceTextField;
    // End of variables declaration//GEN-END:variables

}
