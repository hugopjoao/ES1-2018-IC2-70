import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Browser extends Region {

  public static final String SUCCESS_URL = "https://www.facebook.com/connect/login_success.html";
  final WebView browser = new WebView();
  final WebEngine webEngine = browser.getEngine();
  private String code;

  private final String appId;

  private final String appSecret;

  public Browser(String appId, String appSecret) {
    this.appId = appId;
    this.appSecret = appSecret;
    // add the web view to the scene
    getChildren().add(browser);
  }

  public void showLogin() {
    DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
    ScopeBuilder scopes = new ScopeBuilder();
    String loadUrl = facebookClient.getLoginDialogUrl(appId, SUCCESS_URL, scopes);
    webEngine.load(loadUrl + "&display=popup&response_type=code");
    webEngine.getLoadWorker().stateProperty().addListener(
      (ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) -> {
        if (newValue != Worker.State.SUCCEEDED) {
          return;
        }

        String myUrl = webEngine.getLocation();

        if ("https://www.facebook.com/dialog/close".equals(myUrl)) {
          System.out.println("dialog closed");
          System.exit(0);
        }

        if (myUrl.startsWith(SUCCESS_URL)) {
          System.out.println(myUrl);
          int pos = myUrl.indexOf("code=");
          code = myUrl.substring(pos + "code=".length());
          FacebookClient.AccessToken token = facebookClient.obtainUserAccessToken(appId,
                  appSecret, SUCCESS_URL, code);
          System.out.println("Accesstoken: " + token.getAccessToken());
          System.out.println("Expires: " + token.getExpires());
        }

      });
  }

  @Override
  protected void layoutChildren() {
    double w = getWidth();
    double h = getHeight();
    layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
  }

  @Override
  protected double computePrefWidth(double height) {
    return 900;
  }

  @Override
  protected double computePrefHeight(double width) {
    return 600;
  }

}