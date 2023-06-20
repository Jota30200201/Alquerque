/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopratico;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author joaop
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private MediaView mv;
    @FXML
    private Button btn_play;
    @FXML
    private Button btn_stop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClick_btn_play(ActionEvent event) {
    }

    @FXML
    private void onClick_btn_stop(ActionEvent event) {
    }
    
}
