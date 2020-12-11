package org.ecs160.a2;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;

public class ViewForm extends Form {
    public ViewForm() {
        super(new BorderLayout());
        ViewWorkspace workspace = new ViewWorkspace();
        ViewTaskbar taskbar = new ViewTaskbar();
        super.getToolbar().add(BorderLayout.CENTER, new ViewMenu(workspace));
        this.add(BorderLayout.CENTER, workspace);
        this.add(BorderLayout.SOUTH, taskbar);
    }
}
