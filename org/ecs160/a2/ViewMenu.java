package org.ecs160.a2;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import java.util.ArrayList;
import java.util.Vector;
import com.codename1.io.Log;

import org.ecs160.a2.Objects.Gate.*;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Utilities.Config;

public class ViewMenu extends Container {
    public static Vector<LogicGate> ImageArray;
    public ViewMenu() {
        super(new BorderLayout());

        Container GateSelection = new Container(new GridLayout(2, 4));
        this.add(BorderLayout.SOUTH, GateSelection);

        Config.GetImage();
        ImageArray = new Vector<LogicGate>();
        //this.addTaskBarListener();

        Button GateNOT = new Button(Config.GateNOT);
        GateSelection.add(GateNOT);
        GateNOT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                GateNOT Gate = new GateNOT();
                ImageArray.add(Gate);
                Log.p("Image Array Add");
            }
        });

        Button GateAND = new Button(Config.GateAND);
        GateSelection.add(GateAND);
        GateAND.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                GateAND Gate = new GateAND();
                ImageArray.add(Gate);
                Log.p("Image Array Add");
            }
        });

        Button GateOR = new Button(Config.GateOR);
        GateSelection.add(GateOR);
        GateOR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                GateOR Gate = new GateOR();
                ImageArray.add(Gate);
                Log.p("Image Array Add");
            }
        });

        Button GateXOR = new Button(Config.GateXOR);
        GateSelection.add(GateXOR);
        GateXOR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                GateXOR Gate = new GateXOR();
                ImageArray.add(Gate);
                Log.p("Image Array Add");
            }
        });

        Button GateNAND = new Button(Config.GateNAND);
        GateSelection.add(GateNAND);
        GateNAND.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                GateNAND Gate = new GateNAND();
                ImageArray.add(Gate);
                Log.p("Image Array Add");
            }
        });

        Button GateNOR = new Button(Config.GateNOR);
        GateSelection.add(GateNOR);
        GateNOR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                GateNOR Gate = new GateNOR();
                ImageArray.add(Gate);
                Log.p("Image Array Add");
            }
        });

        Button GateXNOR = new Button(Config.GateXNOR);
        GateSelection.add(GateXNOR);
        GateXNOR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                GateXNOR Gate = new GateXNOR();
                ImageArray.add(Gate);
                Log.p("Image Array Add");
            }
        });
    }

    /**
    public void addTaskBarListener() {
        this.addPointerPressedListener(evt -> {
            this.x = evt.getX();
            this.y = evt.getY();
            Log.p("X and Y");
            Image current = ImageArray.get(0);
            current.rotate90Degrees(true);
            this.repaint();
        });
    }
**/
    /**public void paint(Graphics g){
        if (ImageArray.size() > 0) {
            g.drawImage(ImageArray.get(0), x, y);
        }
    }**/

    /**
     *     public void paint(Graphics g){
     *         if (ViewMenu.ImageArray.size() > 0) {
     *             g.drawImage(ViewMenu.ImageArray.get(0), ViewMenu.x, ViewMenu.y);
     *         }
     *     }
     */
}
