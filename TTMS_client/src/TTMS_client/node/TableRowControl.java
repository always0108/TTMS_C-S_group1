package node;

import javafx.event.EventHandler;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class TableRowControl<T> extends TableRow<T> {

    public TableRowControl() {
        super();
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)
                        && event.getClickCount() == 2
//                        && TableRowControl.this.getIndex() < tableView.getItems().size()
                    ) {
                    System.out.println("ok");
                    //doSomething
                }
            }
        });
    }
}
