package org.ecs160.a2;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;

public class ViewForm extends Form {
    public ViewForm() {
        super(new BorderLayout());
        ViewWorkspace workspace = new ViewWorkspace();
        super.getToolbar().add(BorderLayout.WEST, new ViewMenu(workspace));
        this.add(BorderLayout.CENTER, workspace);
    }
}
