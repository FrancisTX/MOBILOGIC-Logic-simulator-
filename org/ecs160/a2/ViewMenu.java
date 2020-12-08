package org.ecs160.a2;
import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Command;
import org.ecs160.a2.Utilities.WidgetFactory;
import org.ecs160.a2.Utilities.WorkspaceUtil;
import org.ecs160.a2.UI.EditButton;

import java.io.IOException;

public class ViewMenu extends Container {
    private ViewWorkspace workspace;

    public ViewMenu(ViewWorkspace workspace) {
        super();
        this.workspace = workspace;

        this.setLayout(new BoxLayout(BoxLayout.X_AXIS));

        Button SaveButton = new Button("Save");
        SaveButton.addActionListener((evt) -> {
            TextField circuitName = new TextField("", "Circuit Name", 20, TextArea.ANY);
            Command enter = new Command("Ok");
            if(Dialog.show("Save the Circuit", circuitName, enter, new Command("Cancel")) == enter){
                if(circuitName.getText() != "") {
                    workspace.mainCircuit.save(circuitName.getText());
                }
            }
        });
        this.add(SaveButton);

        Button LoadButton = new Button("Load");
        LoadButton.addActionListener((evt)->{
            String[] files = workspace.getFiles();
            Dialog circuitSelect = new Dialog("Load the Circuit", BoxLayout.y());
            Container fileSelectorContainer = new Container(BoxLayout.y());
            fileSelectorContainer.setScrollableY(true);
            Button cancel = new Button("Cancel");
            cancel.addActionListener(evt2->{
                circuitSelect.dispose();
            });
            for(int iter = 0; iter < files.length; iter++){
                MultiButton fileSelector = new MultiButton(files[iter]);
                String circuitName = files[iter];
                fileSelector.addActionListener((evt1)->{
                    workspace.loadMain(circuitName);
                    circuitSelect.dispose();
                });
                fileSelectorContainer.add(fileSelector);
            }
            circuitSelect.add(fileSelectorContainer);
            circuitSelect.add(cancel);
            circuitSelect.show();
        });
        this.add(LoadButton);

        Button Trash = new Button("Delete");
        Trash.addActionListener(evt -> {
            workspace.removeHighlighted();
            workspace.repaint();
        });this.add(Trash);

        //this button not actually operable yet
        Button setSub = new Button("setName");
        setSub.addActionListener((evt) -> {
            TextField circuitName = new TextField("", "Subcircuit Name", 20, TextArea.ANY);
            Command enter = new Command("Ok");
            if(Dialog.show("Set Subcircuit Name", circuitName, enter, new Command("Cancel")) == enter){
            }
        });
        this.add(setSub);
    }
}
