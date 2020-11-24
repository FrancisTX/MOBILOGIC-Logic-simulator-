package org.ecs160.a2;

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

public class ViewTaskbar extends Container {
    private String[] buttonNames =
            {       "AND", "NAND", "NOR", " NOT",
                    "OR", "XNOR", "XOR", "DMMY"};

    private Hashtable<String, Button> buttons = new Hashtable<String, Button>();

    public ViewTaskbar() {
        super();
        this.setLayout(new GridLayout(2, 4));
        for (String buttonName : buttonNames) {
            Button button = new Button(buttonName);
            buttons.put(buttonName, button);
            this.add(button);
        }

    }

    public Button getButton(String buttonName) { return buttons.get(buttonName);}
}
