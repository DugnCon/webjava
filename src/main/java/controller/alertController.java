package main.java.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

public class alertController {
	/**Tạo thêm một khai báo class mới*/
	public static alertController setNew() {
		return new alertController();
	}
	/**Thông báo khi đăg kí thành công*/
    public void AlertComplete(String notify) {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Thông báo");
    	alert.setHeaderText(notify);
    	alert.setContentText("Chọn lựa chọn của bạn");
    	
    	ButtonType buttonTypeOk = new ButtonType("OK",ButtonData.YES);
    	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    	
    	alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
    	alert.show();
    }
    /**Thông báo khi không đăng kí được*/
    public void AlertUnComplete(String notify) {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Thông báo");
    	alert.setHeaderText(notify);
    	alert.setContentText("Chọn lựa chọn của bạn");
    	
    	ButtonType buttonTypeOk = new ButtonType("OK",ButtonData.YES);
    	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    	
    	alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
    	alert.show();
    }
    
    /**Thông báo khi bị ban acc*/
    public void AlertBan(String notify) {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Thông báo");
    	alert.setHeaderText(notify);
    	alert.setContentText("Chọn lựa chọn của bạn");
    	
    	ButtonType buttonTypeOk = new ButtonType("OK",ButtonData.YES);
    	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    	
    	alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
    	alert.show();
    }
}
