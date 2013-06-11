package proj.green.javafx.modules.constraint;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import proj.green.javafx.modules.fxml.CustomFxmlTool;
import proj.green.javafx.modules.fxml.NodeAndController;


public class ConstaintTextApplication extends Application {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void init() {
        CustomFxmlTool.init(ResourceBundle.getBundle("SampleApplication"));
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        NodeAndController<AnchorPane, ConstraintPaneController> nac =
                CustomFxmlTool.loadNodeAndController(AnchorPane.class, ConstraintPaneController.class);
        
        primaryStage.setScene(new Scene(nac.getNode()));
        
        ConstraintPaneController controller = nac.getController();
        
        primaryStage.show();
        primaryStage.requestFocus();
        
    }
}
