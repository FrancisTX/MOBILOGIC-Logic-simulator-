package org.ecs160.a2;
import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.geom.GeneralPath;
import com.codename1.ui.layouts.*;
import java.util.*;
import org.ecs160.a2.Widgets.*;
import org.ecs160.a2.Widgets.Switch;

public class ViewForm extends Form {
    public ViewForm() {
        super(new BorderLayout());
        ViewWorkspace workspace = new ViewWorkspace();
        this.add(BorderLayout.NORTH, "Arrange The Titles").add(BorderLayout.CENTER, workspace);
    }
}
