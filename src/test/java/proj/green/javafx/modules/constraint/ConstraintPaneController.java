/*
 * ConstraintPaneController.java
 * created in 2013/07/13
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.constraint;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


/**
 *
 *
 * @author T.TSUCHIE
 *
 */
public class ConstraintPaneController
    implements Initializable {

        @FXML //  fx:id="lengthField"
        private TextField lengthField; // Value injected by FXMLLoader

        @FXML //  fx:id="patternNumField"
        private TextField patternNumField; // Value injected by FXMLLoader
        
        @FXML //  fx:id="convertField"
        private TextField convertField; // Value injected by FXMLLoader

        @Override // This method is called by the FXMLLoader when initialization is complete
        public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
            // initialize your logic here: all @FXML variables will have been injected
            
            lengthField.textProperty().addListener(new MaxLengthListener(5));
            
            convertField.textProperty().addListener(new Full2HalfListener());
        }

}
