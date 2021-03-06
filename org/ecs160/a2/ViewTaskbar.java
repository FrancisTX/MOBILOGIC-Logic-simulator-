package org.ecs160.a2;

import com.codename1.io.Storage;
import com.codename1.ui.layouts.GridLayout;

import java.util.*;
import com.codename1.ui.Dialog;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.*;
import com.codename1.ui.plaf.RoundRectBorder;
import org.ecs160.a2.Utilities.Config;
import org.ecs160.a2.Utilities.WidgetFactory;
import org.ecs160.a2.Utilities.WorkspaceUtil;

public class ViewTaskbar extends Container {
	private final String[] buttonNames =
			{       "OR", "AND", "XOR", "NOT", "SWI",
					"NOR","NAND","XNOR", "LED", "SUB"};
	private final String[] strategies =
			{       "GateOR", "GateAND", "GateXOR", "GateNOT", "Switch",
					"GateNOR","GateNAND","GateXNOR", "Led", "Circuit"};

	private final Hashtable<String, Button> buttons = new Hashtable<>();

	public ViewTaskbar() {
		super();
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
			if (buttonName.equals("OR")){img = Config.GateOR; gate=true;}
			if (buttonName.equals("XOR")){img = Config.GateXOR;gate=true;}
			if (buttonName.equals("AND")){img = Config.GateAND;gate=true;}
			if (buttonName.equals("NOT")){img = Config.GateNOT;gate=true;}
			if (buttonName.equals("NAND")){img = Config.GateNAND;gate=true;}
			if (buttonName.equals("NOR")){img = Config.GateNOR;gate=true;}
			if (buttonName.equals("XNOR")){img = Config.GateXNOR;gate=true;}

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
				int color;
				for(int j = 0; j < buttonNames.length;j++) {
					color = (WorkspaceUtil.getInstance().getWidgetAddingType() != null && WorkspaceUtil.getInstance().getWidgetAddingType().equals(strategies[j])) ?
							0xb7dcff : Config.getInstance().taskButtonColor;
					getButton(buttonNames[j]).getAllStyles().setBgColor(color);
				}
				this.revalidate();
			});
		}

		Button loadSubCircuitButton = new Button("SUB");
		loadSubCircuitButton.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
		loadSubCircuitButton.getAllStyles().setBorder(RoundRectBorder.create());
		buttons.put("SUB",loadSubCircuitButton);
		this.add(loadSubCircuitButton);

		loadSubCircuitButton.addActionListener((evt) ->{
			String[] fileNames = Storage.getInstance().listEntries();
			WorkspaceUtil.getInstance().setWidgetAddingStrategy("Circuit");
			Dialog d = new Dialog("Select Subcircuit");
			d.setLayout(BoxLayout.y());
			d.getContentPane().setScrollableY(true);
			Button cancel = new Button("Cancel");
			cancel.getAllStyles().setBgColor(Config.getInstance().cancelButtonColor);
			cancel.getAllStyles().setBorder(RoundRectBorder.create());
			cancel.addActionListener(evt2->{
				WorkspaceUtil.getInstance().setWidgetAddingStrategy("Circuit");
				loadSubCircuitButton.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
				d.dispose();
			});
			for (String fileName : fileNames) {
				System.out.print(fileName + "\n");
				Button mb = new Button(fileName);
				mb.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
				mb.getAllStyles().setBorder(RoundRectBorder.create());
				d.add(mb);
				mb.addActionListener(ee -> {
					WidgetFactory.getInstance().setSubCircuitName(mb.getText());
					d.dispose();
				});
			}
			d.add(cancel);

			 int color;
			 for(int j = 0; j < buttonNames.length; j++) {
			 	color = (WorkspaceUtil.getInstance().getWidgetAddingType() != null && WorkspaceUtil.getInstance().getWidgetAddingType().equals(strategies[j])) ?
			 			0xb7dcff : Config.getInstance().taskButtonColor;
			 	getButton(buttonNames[j]).getAllStyles().setBgColor(color);
			 }
			 this.revalidate();
			if(WorkspaceUtil.getInstance().getWidgetAddingType() != null && WorkspaceUtil.getInstance().getWidgetAddingType().equals("Circuit")) {
				d.showPopupDialog(loadSubCircuitButton);
			}
		});


	}

	public Button getButton(String buttonName) { return buttons.get(buttonName);}
}
