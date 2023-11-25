package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * A class to load FXML files.
 */
public class GuiViewImpl implements GuiView {

  private final FXMLLoader loader;

  /**
   * An instance of the GuiViewImpl class.
   *
   * @param controller the controller for the FXML
   * @param root       the root of the FXML file.
   */
  public GuiViewImpl(Controller controller, String root) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource(root));
    this.loader.setController(controller);
  }

  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
