package node;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

public class NavigationButton extends Button {

    public NavigationButton() { super(); init();}

    public NavigationButton(String name) { super(name); init();}

    public void init(){
        this.getStyleClass().add("firstMenu");
        this.addEventFilter(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            this.setEffect(new DropShadow());
        });
        this.addEventFilter(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            this.setEffect(null);
        });
    }
}
