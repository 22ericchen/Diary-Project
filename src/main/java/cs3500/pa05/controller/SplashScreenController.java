package cs3500.pa05.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A class to control a splash scren for the bullet jounral.
 */
public class SplashScreenController implements Controller {

  @FXML
  ImageView view;

  private int countDown;

  /**
   * An instance of the SplashScreenController class.
   */
  public SplashScreenController() {
    countDown = 7;
    view.setImage(
        new Image(getClass().getResourceAsStream("src/main/resources/splashScreen.fxml")));
  }

  @Override
  public void run() {
    while (countDown != 0) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      countDown--;
    }
  }

}
