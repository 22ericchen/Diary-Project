package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * represents a GUI view for a Diary
 */
public interface GuiView {

  /**
   * loads a scene for a diary week
   *
   * @return Scene loaded
   */
  Scene load() throws IllegalStateException;
}
