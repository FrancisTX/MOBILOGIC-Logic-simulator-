package org.ecs160.a2;

import com.codename1.components.MultiButton;
import com.codename1.io.Storage;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.Command;
import org.ecs160.a2.UI.EditButton;
import org.ecs160.a2.Utilities.Config;
import org.ecs160.a2.Utilities.WorkspaceUtil;

public class ViewMenu extends Container {
    public ViewMenu(ViewWorkspace workspace) {
        super();
        this.setLayout(new GridLayout(1, 4));
        this.getAllStyles().setBgColor(0xffffff);

        Button SaveButton = new Button("Save");
        SaveButton.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
        SaveButton.getAllStyles().setBorder(RoundRectBorder.create());
        SaveButton.addActionListener((evt) -> {
            TextField circuitName = new TextField("", "Circuit Name", 20, TextArea.ANY);
            Command enter = new Command("Ok");
            if(Dialog.show("Save the Circuit", circuitName, enter, new Command("Cancel")) == enter){
                if(!circuitName.getText().isEmpty()) {
                    workspace.mainCircuit.save(circuitName.getText());
                }
            }
        });
        this.add(SaveButton);

        Button LoadButton = new Button("Load");
        LoadButton.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
        LoadButton.getAllStyles().setBorder(RoundRectBorder.create());
        LoadButton.addActionListener((evt)->{
            String[] files = Storage.getInstance().listEntries();
            Dialog circuitSelect = new Dialog("Load the Circuit", BoxLayout.y());
            Container fileSelectorContainer = new Container(BoxLayout.y());
            fileSelectorContainer.setScrollableY(true);
            Button cancel = new Button("Cancel");
            cancel.getAllStyles().setBgColor(Config.getInstance().cancelButtonColor);
            cancel.getAllStyles().setBorder(RoundRectBorder.create());
            cancel.addActionListener(evt2-> circuitSelect.dispose());
            for (String file : files) {
                MultiButton fileSelector = new MultiButton(file);
                fileSelector.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
                fileSelector.getAllStyles().setBorder(RoundRectBorder.create());
                fileSelector.addActionListener((evt1) -> {
                    workspace.loadMain(file);
                    circuitSelect.dispose();
                });
                fileSelectorContainer.add(fileSelector);
            }
            fileSelectorContainer.add(cancel);
            circuitSelect.add(fileSelectorContainer);
            circuitSelect.show();
        });
        this.add(LoadButton);

        Button Trash = new Button("Remove");
        Trash.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
        Trash.getAllStyles().setBorder(RoundRectBorder.create());
        Trash.addActionListener(evt -> {
            workspace.removeHighlighted();
            workspace.repaint();
        });
        this.add(Trash);

        EditButton edit = new EditButton();
        edit.button.addActionListener(evt -> {
            if (WorkspaceUtil.getInstance().getHighlightedWidget() == null){
                return;
            }
            edit.showEditPopup(workspace);
        });
        this.add(edit.button);
    }
}
