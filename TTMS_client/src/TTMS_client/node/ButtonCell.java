package node;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import model.EmployeeProperty;

public class ButtonCell extends TableCell<EmployeeProperty, Boolean> {

    Button cellButton = new Button("显示详情");

    public ButtonCell(){
        //Action when the button is pressed
        cellButton.setOnAction(e->{
                // get Selected Item
                EmployeeProperty currentPerson = (EmployeeProperty) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                currentPerson.print();
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(cellButton);
        }
    }
}

