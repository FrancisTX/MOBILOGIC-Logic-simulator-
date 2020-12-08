package org.ecs160.a2.Utilities;

import com.codename1.ui.Button;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class Config {

    public final int logicGateWidth = 200;
    public final int logicGateHeight = 170;

    public final int nodeWidth = 30;
    public final int nodeHeight = 30;
    public final int nodeRadius = nodeWidth / 2;

    public final int ledWidth = 130;
    public final int ledHeight = 130;
    public final int ledIndicatorWidth = 70;
    public final int ledIndicatorHeight = 70;

    public final int switchWidth = 170;
    public final int switchHeight = 170;
    public final int switchButtonWidth = 60;
    public final int switchButtonHeight = 60;
    public final int switchButtonRadius = switchButtonWidth / 2;

    public final int subCircuitWidth = 100;
    public final int subCircuitHeight = 100;

    public final int unselectedWidgetColor = 0x0000ff;
    public final int selectedWidgetColor = 0x52BE80;
    public final int unselectedLogicGateLineWidth = 3;
    public final int selectedLogicGateLineWidth = 10;


    public final int unselectedNodeColor = 0x0000ff;
    public final int selectedNodeColor = 0x52BE80;
    public final int unselectedNodeLineWidth = 3;
    public final int selectedNodeLineWidth = 10;

    public final int wireOffColor = 0x0000ff;
    public final int wireOnColor = 0x52BE80;
    public final int wireWidth = 5;

    public final int ledIndicatorOnColor = 0x58D68D;
    public final int ledIndicatorOffColor = 0xEC7063;

    public static Image GateNOT;
    public static Image GateAND;
    public static Image GateOR;
    public static Image GateXOR;
    public static Image GateNAND;
    public static Image GateNOR;
    public static Image GateXNOR;

    public static void GetImage() {
        try {
            GateNOT = Image.createImage("/NOT_Gate.png").scaledLargerRatio(100, 100);

            GateAND = Image.createImage("/AND_Gate.png").scaledLargerRatio(100, 100);

            GateOR = Image.createImage("/OR_Gate.png").scaledLargerRatio(100, 100);

            GateXOR = Image.createImage("/XOR_Gate.png").scaledLargerRatio(100, 100);

            GateNAND = Image.createImage("/NAND_Gate.png").scaledLargerRatio(100, 100);

            GateNOR = Image.createImage("/NOR_Gate.png").scaledLargerRatio(100, 100);

            GateXNOR = Image.createImage("/XNOR_Gate.png").scaledLargerRatio(100, 100);
        } catch (java.io.IOException e) {
            System.out.println("Fail to get SVG" + e);
        }
    }

    private static final Config instance = new Config();
    private Config() {}
    public static Config getInstance() { return instance; }
}
