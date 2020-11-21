package View.Encryption.Modern.AES;

import Init.ViewInit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * @author RyuZU
 */
public class AESController {

    private Integer AP_OPTION_STATES = 0;

    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXButton JBT_option;

    @FXML private AnchorPane AP_option;
    @FXML private JFXComboBox JCB_encryptMode;
    @FXML private JFXComboBox JCB_paddingMode;
    @FXML private JFXTextArea JTA_AESKey;
    @FXML private JFXTextArea JTA_AESIV;
    @FXML private JFXComboBox JCB_outputFormat;
    @FXML private JFXComboBox JCB_textEncoding;

    @FXML private void initialize(){
        AP_option.setVisible(false);
        initButtonOption();
        initComboBoxes();
    }

    @FXML
    public void ONClick_JBT_enCode(){
        JTA_dst.setText(JTA_src.getText());
    }

    @FXML
    public void ONClick_JBT_deCode(){

    }

    @FXML
    public void ONClick_JBT_option(){
        optionPaneAnime(AP_OPTION_STATES);
    }

    public void initButtonOption(){
        JBT_option.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/img/settings-5-fill.png"))));
    }

    public void initComboBoxes(){
        JCB_encryptMode.getItems().addAll("ECB","CBC");
        JCB_encryptMode.setValue("ECB");

        JCB_paddingMode.getItems().addAll("PKCS5","PKCS7","NoPadding");
        JCB_paddingMode.setValue("PKCS5");

        JCB_outputFormat.getItems().addAll("Base64","HEX");
        JCB_outputFormat.setValue("HEX");

        ViewInit.comboBoxCharset(JCB_textEncoding);

    }

    public void optionPaneAnime(Integer states) {
        Timeline timeLine = new Timeline();
        KeyFrame kf_AP_Start;
        KeyFrame kf_AP_Stop;
        KeyFrame kf_BT_Start;
        KeyFrame kf_BT_Stop;
        if(states == 0){
            kf_AP_Start = new KeyFrame(Duration.seconds(0), "Start", event -> AP_option.setVisible(true),
                    new KeyValue(AP_option.layoutXProperty(), 610));
            kf_AP_Stop = new KeyFrame(Duration.seconds(0.4), "Stop", event -> { },
                    new KeyValue(AP_option.layoutXProperty(), 455));

            kf_BT_Start = new KeyFrame(Duration.seconds(0), "Start", event -> { },
                    new KeyValue(JBT_option.layoutXProperty(), 570));
            kf_BT_Stop = new KeyFrame(Duration.seconds(0.4), "Stop", event -> { },
                    new KeyValue(JBT_option.layoutXProperty(), 410));

            timeLine.getKeyFrames().addAll(kf_AP_Start,kf_AP_Stop,kf_BT_Start,kf_BT_Stop);
            AP_OPTION_STATES = 1;
        }else{
            kf_AP_Start = new KeyFrame(Duration.seconds(0), "Start", event -> { },
                    new KeyValue(AP_option.layoutXProperty(), 455));
            kf_AP_Stop = new KeyFrame(Duration.seconds(0.4), "Stop", event -> { },
                    new KeyValue(AP_option.layoutXProperty(), 610));

            kf_BT_Start = new KeyFrame(Duration.seconds(0), "Start", event -> { },
                    new KeyValue(JBT_option.layoutXProperty(), 410));
            kf_BT_Stop = new KeyFrame(Duration.seconds(0.4), "Stop", event -> AP_option.setVisible(false),
                    new KeyValue(JBT_option.layoutXProperty(), 570));

            timeLine.getKeyFrames().addAll(kf_AP_Start,kf_AP_Stop,kf_BT_Start,kf_BT_Stop);
            AP_OPTION_STATES = 0;
        }
        timeLine.play();
    }


}
