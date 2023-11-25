package cs3500.pa05.controller;

import static java.nio.file.FileVisitResult.CONTINUE;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.bujofile.BujoFileSaver;
import cs3500.pa05.model.themes.AbstractTheme;
import cs3500.pa05.model.themes.CustomTheme;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * A class to allow the user to select and create new bujo files to load the week planner.
 */
public class FileSelectorController implements FileVisitor<Path>, Controller {

  private final List<String> pathNames;
  private boolean hasBeenVisited;
  private String fileSelected;
  @FXML
  private HBox fileBox;
  @FXML
  private Button createNewFile;
  private final FullGuiController controller;

  /**
   * An instance of the FileSelectorController class.
   *
   * @param controller FullGuiController passed to the instance.
   */
  public FileSelectorController(FullGuiController controller) {
    pathNames = new ArrayList<>();
    hasBeenVisited = false;
    this.controller = controller;
    try {
      Files.walkFileTree(Path.of("src/test/resources/bujoResources"), this);
    } catch (IOException e) {
      System.err.println("An unexpected error occurred while preparing files.");
    }
  }

  @Override
  public void run() {
    createNewFile.setOnAction(e -> handleNewFile());
    initFiles();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Initializes the buttons to each of the available bujo files.
   */
  public void initFiles() {
    for (String path : pathNames) {
      String fileName;
      if (path.contains("/")) {
        fileName = path.substring(path.lastIndexOf("/") + 1);
      } else {
        fileName = path.substring(path.lastIndexOf("\\") + 1);
      }
      Button button = new Button(fileName);
      button.setOnAction(e -> selectFile(path));
      fileBox.getChildren().add(button);
    }
  }

  /**
   * Selects the file passed to this function.
   *
   * @param path the path of the file selected.
   */
  @FXML
  public void selectFile(String path) {
    fileSelected = path;
    controller.loadDiary(this);
  }

  /**
   * Gets the file selected.
   *
   * @return the path of the file selected in String format.
   */
  public String getFileSelected() {
    return fileSelected;
  }

  /**
   * Handles when the user selects to create a new week file.
   */
  @FXML
  public void handleNewFile() {
    int count = 0;
    while (true) {
      if (!(new File("src/test/resources/bujoResources/newWeek" + count + ".bujo"
      )).exists()) {
        try {
          fileSelected = String.valueOf(Files.createFile(Path.of(
              "src/test/resources/bujoResources/newWeek" + count + ".bujo")));
          BujoFileSaver fileSaver = new BujoFileSaver(fileSelected);
          List<Task> tasks = new ArrayList<>();
          List<Event> events = new ArrayList<>();
          AbstractTheme theme = new CustomTheme.ThemeBuilder().brown().build();
          List<String> quotes = new ArrayList<>();
          fileSaver.save("newWeek" + count, 0, tasks, events, theme, quotes);
          controller.loadDiary(this);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        break;
      }
      count++;
    }
  }

  /**
   * Called everytime the file-system walker encounters a file
   *
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   * @return directive on how to process current item's siblings and children.
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    hasBeenVisited = true;
    if (file.toString().endsWith(".bujo") && attrs.isRegularFile()) {
      pathNames.add(String.valueOf(file));
    }
    return CONTINUE;
  }

  /**
   * What to do at the beginning of processing a directory.
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return a directive to continue processing siblings and children of current item.
   * @throws IOException if an I/O occurs.
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
    hasBeenVisited = true;
    return CONTINUE;
  }

  /**
   * Called when a file cannot be visited for some undetermined reason (perhaps
   * locked by another process or an access permissions issue)
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return a directive to continue processing siblings and children of the current item.
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    if (hasBeenVisited) {
      System.out.println("File not accessible");
    } else {
      throw new NoSuchFileException("Directory does not exist or is unacceptable.");
    }
    hasBeenVisited = true;
    return CONTINUE;
  }

  /**
   * What to do after processing all items in a directory
   *
   * @param dir a reference to the directory
   * @param exc {@code null} if the iteration of the directory completes without
   *            an error; otherwise the I/O exception that caused the iteration
   *            of the directory to complete prematurely
   * @return in all cases, continue processing the sibling and child items of current item
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
    hasBeenVisited = true;
    return CONTINUE;
  }
}
