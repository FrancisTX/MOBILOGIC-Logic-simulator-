package org.ecs160.a2;

import com.codename1.components.MultiButton;
import com.codename1.io.Storage;
import com.codename1.ui.layouts.GridLayout;

import java.util.*;
import static com.codename1.ui.CN.*;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.GridLayout;
import org.ecs160.a2.Utilities.WidgetFactory;
import org.ecs160.a2.Utilities.WorkspaceUtil;

public class ViewTaskbar extends Container {
    private ViewWorkspace workspace;

    private String[] buttonNames =
            {       "OR", "AND", "XOR", "SWI", "NOT",
                    "NOR","NAND","XNOR", "LED", "SUB"};
    private String[] WFnameCases =
            {       "GateOR", "GateAND", "GateXOR", "Switch", "GateNOT",
                    "GateNOR","GateNAND","GateXNOR", "Led", "Circuit"};

    private Hashtable<String, Button> buttons = new Hashtable<String, Button>();

    public ViewTaskbar(ViewWorkspace workspace) {
        super();
        this.workspace = workspace;
        this.setLayout(new GridLayout(2, 5));

        for (String buttonName : buttonNames) {
            Button button = new Button(buttonName);
            buttons.put(buttonName, button);
            this.add(button);
//            if (buttonName != "LOAD"){
//                button.addActionListener((evt) -> {
//                    WorkspaceUtil.getInstance().setWidgetAddingStrategy(button.getText());
//                });
//            }
        }

        Button addANDButton = getButton("AND");
        addANDButton.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateAND");
        });

        Button addORButton = getButton("OR");
        addORButton.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateOR");
        });

        Button addNANDButton = getButton("NAND");
        addNANDButton.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateNAND");
        });

        Button addNORButton = getButton("NOR");
        addNORButton.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateNOR");
        });

        Button addNOTButton = getButton("NOT");
        addNOTButton.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateNOT");
        });

        Button addXORButton = getButton("XOR");
        addXORButton.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateXOR");
        });

        Button addXNORButton = getButton("XNOR");
        addXNORButton.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateXNOR");
        });

        Button addSWITCHButton = getButton("SWI");
        addSWITCHButton.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().setWidgetAddingStrategy("Switch");
        });

        Button addLEDButton = getButton("LED");
        addLEDButton.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().setWidgetAddingStrategy("Led");
        });


        Button loadSubCircuitButton = getButton("SUB");
        String[] fileNames = Storage.getInstance().listEntries();
        loadSubCircuitButton.addActionListener((evt) ->{
            Dialog d = new Dialog();
            d.setLayout(BoxLayout.y());
            d.getContentPane().setScrollableY(true);
            for(int i=0; i < fileNames.length; i++){
                System.out.print(fileNames[i]);
                Button mb = new Button(fileNames[i]);
                d.add(mb);
                mb.addActionListener(ee->{
                    //loadSubCircuitButton.setText(mb.getText()); //Change button to name of button
                    d.dispose();
                    WidgetFactory.getInstance().setCircuitName(mb.getText());
                    WorkspaceUtil.getInstance().setWidgetAddingStrategy("Circuit");
                });
            }
            d.showPopupDialog(loadSubCircuitButton);
        });


    }

    public Button getButton(String buttonName) { return buttons.get(buttonName);}

}