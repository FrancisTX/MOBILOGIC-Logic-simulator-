package org.ecs160.a2;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;

public class ViewForm extends Form {
    public ViewForm() {
        super(new BorderLayout());
        ViewWorkspace workspace = new ViewWorkspace();
        this.add(BorderLayout.NORTH, "Arrange The Titles").add(BorderLayout.CENTER, workspace);
    }
}
