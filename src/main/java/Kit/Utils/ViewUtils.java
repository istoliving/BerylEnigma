package Kit.Utils;

import Init.Init;
import com.jfoenix.controls.*;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class ViewUtils {

    public static String comboxSplitConvert(String splitString){
        switch (splitString){
            case "空格分隔" : return " ";
            case "Space_separation" : return " ";
            case "换行分隔" : return "\n";
            case "Line_break" : return "\n";
            case "分号分隔" : return ";";
            case "Semicolon_separated" : return ";";
            case "冒号分隔" : return ":";
            case "Colon_separated" : return ":";
            case "逗号分隔" : return ",";
            case "Comma_separated" : return ",";
            case "0x分隔" : return "0x";
            case "0x_seperated" : return "0x";
            case "%分隔" : return "%";
            case "%_Separation" : return "%";
            case "$分隔" : return "$";
            case "$_separation" : return "$";
            case "双空格分隔" : return "  ";
            case "Double_space_separation" : return "  ";
            case "双换行分隔" : return "\n\n";
            case "Double_line_break" : return "\n\n";
            case "双分号分隔" : return ";;";
            case "Double_semicolon_separated" : return ";;";
            case "双冒号分隔" : return "::";
            case "Double_colon_separated" : return "::";
            case "双逗号分隔" : return ",,";
            case "Double_comma_separated" : return ",,";
            default : return splitString;
        }


    }//combobox根据输入返回分隔符

    public static File getFile(){
        Stage primaryStage = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Init.languageResourceBundle.getString("File"));
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        return fileChooser.showOpenDialog(primaryStage);
    } //获取文件类

    public static File getFile(FileChooser.ExtensionFilter[] extFilter){
        Stage primaryStage = null;
        FileChooser fileChooser = new FileChooser();
        for (FileChooser.ExtensionFilter filter:extFilter) {
            fileChooser.getExtensionFilters().add(filter);
        }
        fileChooser.setTitle(Init.languageResourceBundle.getString("File"));
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        return fileChooser.showOpenDialog(primaryStage);
    } //获取文件类,携带文件后缀过滤器

    public static File saveFileFilter(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.gif"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
        Stage stage = new Stage();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        fileChooser.setInitialFileName("Result");
        return fileChooser.showSaveDialog(stage);
    }//保存文件选择器

    public static void AlertPane(Stage stage,String heading,String body){
        JFXAlert alert = new JFXAlert(stage);
        alert.initModality(Modality.NONE);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        javafx.scene.control.Label HeadingLable = new javafx.scene.control.Label(heading);
        HeadingLable.setStyle("-fx-font-size: 20.0px;");
        layout.setHeading(HeadingLable);
        layout.setBody(new Label(body));
        JFXButton closeButton = new JFXButton(Init.languageResourceBundle.getString("Accept"));
        closeButton.setPrefSize(120,60);
        closeButton.setStyle("    -fx-background-color: WHITE;\n" +
                "    -fx-font-size: 14.0px;");
        closeButton.setOnAction(event -> alert.hideWithAnimation());
        layout.setActions(closeButton);
        alert.setContent(layout);
        alert.show();
    }//Alert弹窗
}
