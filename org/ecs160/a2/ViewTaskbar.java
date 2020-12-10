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
import com.codename1.ui.plaf.RoundRectBorder;
import org.ecs160.a2.Utilities.Config;
import org.ecs160.a2.Utilities.WidgetFactory;
import org.ecs160.a2.Utilities.WorkspaceUtil;

public class ViewTaskbar extends Container {
    private ViewWorkspace workspace;

    private String[] buttonNames =
            {       "OR", "AND", "XOR", "NOT", "SWI",
                    "NOR","NAND","XNOR", "LED", "SUB"};
    private String[] strategies =
            {       "GateOR", "GateAND", "GateXOR", "GateNOT", "Switch",
                    "GateNOR","GateNAND","GateXNOR", "Led", "SUB"};

    private Hashtable<String, Button> buttons = new Hashtable<String, Button>();

    public ViewTaskbar(ViewWorkspace workspace) {
        super();
        this.workspace = workspace;
        this.setLayout(new GridLayout(2, 5));
        this.getAllStyles().setBgColor(0xffffff);
        this.getAllStyles().setBgTransparency(255);

        Config.GetImage();

        for (int i=0; i < buttonNames.length -1 ;i++) {
            String buttonName = buttonNames[i];
            String widgetStrat = strategies[i];
            Image img = null;
            Button button;

            Boolean gate = false;
            if (buttonName == "OR"){img = Config.GateOR; gate=true;}
            if (buttonName == "XOR"){img = Config.GateXOR;gate=true;}
            if (buttonName == "AND"){img = Config.GateAND;gate=true;}
            if (buttonName == "NOT"){img = Config.GateNOT;gate=true;}
            if (buttonName == "NAND"){img = Config.GateNAND;gate=true;}
            if (buttonName == "NOR"){img = Config.GateNOR;gate=true;}
            if (buttonName == "XNOR"){img = Config.GateXNOR;gate=true;}

            if (gate){
                 button = new Button(img, "Button");
            }
            else{
                 button = new Button(buttonName);
            }

            button.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
            button.getAllStyles().setBorder(RoundRectBorder.create());
            this.add(button);
            buttons.put(buttonName,button);

            button.addActionListener(ee ->{
                WorkspaceUtil.getInstance().setWidgetAddingStrategy(widgetStrat);
            });
            //this.add(button);


        }

        
        Button loadSubCircuitButton = new Button("SUB");
        loadSubCircuitButton.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
        loadSubCircuitButton.getAllStyles().setBorder(RoundRectBorder.create());
        buttons.put("SUB",loadSubCircuitButton);
        this.add(loadSubCircuitButton);

        String[] fileNames = Storage.getInstance().listEntries();
        loadSubCircuitButton.addActionListener((evt) ->{
            Dialog d = new Dialog("Select Subcircuit");
            d.setLayout(BoxLayout.y());
            d.getContentPane().setScrollableY(true);
            Button cancel = new Button("Cancel");
            cancel.getAllStyles().setBgColor(Config.getInstance().cancelButtonColor);
            cancel.getAllStyles().setBorder(RoundRectBorder.create());
            cancel.addActionListener(evt2->{
                d.dispose();
            });
            for(int i=0; i < fileNames.length; i++){
                System.out.print(fileNames[i]);
                Button mb = new Button(fileNames[i]);
                mb.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
                mb.getAllStyles().setBorder(RoundRectBorder.create());
                d.add(mb);
                mb.addActionListener(ee->{
                    //loadSubCircuitButton.setText(mb.getText()); //Change button to name of button
                    WidgetFactory.getInstance().setSubCircuitName(mb.getText());  
                    WorkspaceUtil.getInstance().setWidgetAddingStrategy("Circuit");
                    d.dispose();
                });
            }
            d.add(cancel);
            d.showPopupDialog(loadSubCircuitButton);
        });


    }

    public Button getButton(String buttonName) { return buttons.get(buttonName);}
    public final int getLength(String[] names) { return names.length;};

}
