package org.ecs160.a2;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;


public class ViewForm extends Form {
    public ViewForm() {
        super(new BorderLayout());
        ViewWorkspace workspace = new ViewWorkspace();
        this.add(BorderLayout.NORTH, "Arrange The Titles").add(BorderLayout.CENTER, workspace);

        Container Menu = new ViewMenu();
        this.add(BorderLayout.SOUTH, Menu);
    }
}
