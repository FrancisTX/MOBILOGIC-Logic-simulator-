package org.ecs160.a2.UI;

import com.codename1.ui.*;
import org.ecs160.a2.ViewWorkspace;
import java.io.IOException;

public class TrashCan extends Container {
   private Image canImg;
   private Button Trash;
    public TrashCan(ViewWorkspace workspace){
        String url = System.getProperty("user.dir") + "/src/Images/trash_can.jpg";
        try {
            canImg = Image.createImage("file:" + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Trash = new Button(canImg);
        //now add a click listener that will remove the selected widget
        Trash.addActionListener(evt -> {
            workspace.removeHighlighted();
            workspace.repaint();
        });
        this.add(Trash);
    }
}

