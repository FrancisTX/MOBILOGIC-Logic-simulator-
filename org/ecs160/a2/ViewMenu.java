package org.ecs160.a2;
import com.codename1.ui.*;
<<<<<<< HEAD
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
=======
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import org.ecs160.a2.Objects.Gate.GateAND;
import com.codename1.ui.Command;
import org.ecs160.a2.Utilities.WidgetFactory;
import org.ecs160.a2.Utilities.WorkspaceUtil;

import java.io.IOException;

public class ViewMenu extends Container {
    private ViewWorkspace workspace;

    public ViewMenu(ViewWorkspace workspace) {
        super();
        this.workspace = workspace;

        this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        Button testingSaveButton = new Button("Save");
        testingSaveButton.addActionListener((evt) -> {
            TextField circuitName = new TextField("", "Circuit Name", 20, TextArea.ANY);
            Command enter = new Command("Ok");
            if(Dialog.show("Save the Circuit", circuitName, enter, new Command("Cancel")) == enter){
                workspace.mainCircuit.save(circuitName.getText());
            }
        });
        this.add(testingSaveButton);

        Image canImg;
        String url = System.getProperty("user.dir") + "/src/Images/trash_can.jpg";
        try {
            canImg = Image.createImage("file:" + url);
            Button Trash = new Button(canImg);
            //now add a click listener that will remove the selected widget
            Trash.addActionListener(evt -> {
                workspace.removeHighlighted();
                workspace.repaint();
            });
            this.add(Trash);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Button addANDButton = new Button("And Gate");
//        addANDButton.addActionListener((evt) -> {
//            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateAND");
//        });
//        this.add(addANDButton);
//
//        Button addORButton = new Button("Or Gate");
//        addORButton.addActionListener((evt) -> {
//            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateOR");
//        });
//        this.add(addORButton);

        Button testingSave = new Button("S(Test)");
        testingSave.addActionListener((evt) -> {
            workspace.mainCircuit.save("Testing");
        });
        this.add(testingSave);

        Button testingLoad = new Button("L(Test)");
        testingLoad.addActionListener((evt) -> {
            workspace.loadMain("Testing");
        });
        this.add(testingLoad);

        Button testingLoadSub = new Button("LS(Test)");
        testingLoadSub.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().setWidgetAddingStrategy("Circuit");
            WidgetFactory.getInstance().setCircuitName("Testing");
        });
        this.add(testingLoadSub);
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
