CS102 Project CodeCheckpoint_01:
Group number: g2A
Group Name: Code Erat Demonstrandum
Project Name: Call-IDE


Status:
- Project is still in progress
- IDE part has minor bugs and it lacks the project system
- Submission System only has the Database Design and Moss checker but nothing else.
~~~~~~~~~Checkpoint 2~~~~~~~~~~~~
- Submission System is partially complete, the barebones are ready but some detailed functionalities are still on progress.
- IDE is nearly complete, we are constantly fixing bugs.


-Group Members-

Mahmud Sami Aydın
- File Browser [filebrowser]
- Method Summary [methodsummary]
- Moss Similarity Checker [helputils.MossDetector]
- Error Help GUI Part and console integration (not integrated with main frame) [helputils.ErrorHelper.java], [helputils.LinkingLabel.java] 
~~~~~~~~~Checkpoint 2~~~~~~~~~~~~
-found console bugs
-fix the navigator and method summary bugs

Abdullah Talayhan
- Build and execution system (Compile, Run) [runutils.BuildSys.java, runutils.Executor.java]
- Simulating System Console in a Text Component [runutils.ConsoleCore.java]
- Handling Input, Output and Error Streams [runutils.ConsoleBuilder.java]
- Compile and Run methods in MainFrame [userinterface.MainFrame]
- SQL Query that creates the Submission System Database [CallIDESubSys.sql]
- Draw the ToolBar Icons [userinterface.images]
~~~~~~~~~Checkpoint 2~~~~~~~~~~~~
- implemented the new Build system with dependencies
- wrote the ant scripts for certain build tasks
- fixed the new console bugs
- Started to implement Submission System [Sources inside Submission System folder]

Ataberk Gözkaya
- Help Utilities (IDE) [helputils.ExceptionHelper]
- Assisted the main frame and integration

Emin Bahadır Tülüce
- Wiring-up the components of the main frame. [userinterface.MainFrame]
- Integration of other works to the main frame. [userinterface.MainFrame]
- File tracing and handling operations on the active tabs of the editor. [userinterface.MainFrame]
- File loading/saving operations [fileoperations.ContentReader], [fileoperations.FileSaver], [fileoperations.AutoFileSaver]
- Loading/saving user configurations [fileoperations.configurers.FileConfigurer]
- Saving user preferences [fileoperations.configurers.Preferences]
- Configuring, loading and applying the user preferences [fileoperations.configurers.PreferencesConfigurer], [userinterface.MainFrame]
- Saving, configuring and inserting code templates [fileoperations.configurers.TemplateManager], [userinterface.MainFrame]
- Visiting the method summary tree’s methods [editor.PositionCalculator], [methodsummary.VisitableNode], [methodsummary.NodeVisitor]
- Implementation of simple operations on editor [editor.BasicOperations]
- Simple link redirecting feature [helputils.LinkOpener]
- Assisted some filebrowser and method summary package files.

Halil Şahiner
- Find and Replace functionality and integration to main frame [editor.FindAndReplace]
- Commenting and uncommenting functionality and integration to main frame [editor.CommentFunctionality]
- Assisted the main frame and integration

Ahmet Furkan Bıyık
- JDK checker ( not integrated yet)
- Integrating drag & drop and open a file to main frame [userinterface.MainFrame.java]
- Handle project and project properties [fileoperations.projecthandling.ProjectHandler.java], [fileoperations.projecthandling.ProjectProperties.java]
