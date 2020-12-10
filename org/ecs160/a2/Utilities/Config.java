package org.ecs160.a2.Utilities;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class Config {

    public final int logicGateWidth = 150;
    public final int logicGateHeight = 170;

    public final int nodeWidth = 30;
    public final int nodeHeight = 30;
    public final int nodeRadius = nodeWidth / 2;

    public final int ledWidth = 100;
    public final int ledHeight = 100;
    public final int ledIndicatorWidth = 80;
    public final int ledIndicatorHeight = 80;

    public final int switchWidth = 150;
    public final int switchHeight = 150;
    public final int switchButtonWidth = 60;
    public final int switchButtonHeight = 60;
    public final int switchButtonRadius = switchButtonWidth / 2;

    public final int subCircuitWidth = 200;
    public final int subCircuitHeight = 200;

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
    public final int ledIndicatorOffColor = 0x0000ff;

    public static Image GateNOT;
    public static Image GateAND;
    public static Image GateOR;
    public static Image GateXOR;
    public static Image GateNAND;
    public static Image GateNOR;
    public static Image GateXNOR;

    public final int taskButtonColor = 0xe1e3e1; //Includes toolbarbuttons
    public final int cancelButtonColor = 0xb0aeae; //Includes toolbarbuttons

    public static void GetImage() {

        try {
            String url = System.getProperty("user.dir") + "/src/Images/NOT_GATE.png";
            GateNOT = Image.createImage("file:" + url).scaledWidth(83).scaledHeight(83);

            url = System.getProperty("user.dir") + "/src/Images/AND_GATE.png";
            Log.p(url);
            GateAND = Image.createImage("file:" + url).scaledWidth(80).scaledHeight(83);

            url = System.getProperty("user.dir") + "/src/Images/OR_GATE.png";
            GateOR = Image.createImage("file:" + url).scaledWidth(83).scaledHeight(83);

            url = System.getProperty("user.dir") + "/src/Images/XOR_GATE.png";
            GateXOR = Image.createImage("file:" + url).scaledWidth(83).scaledHeight(83);

            url = System.getProperty("user.dir") + "/src/Images/NAND_GATE.png";
            GateNAND = Image.createImage("file:" + url).scaledWidth(80).scaledHeight(83);

            url = System.getProperty("user.dir") + "/src/Images/NOR_GATE.png";
            GateNOR = Image.createImage("file:" + url).scaledWidth(83).scaledHeight(83);

            url = System.getProperty("user.dir") + "/src/Images/XNOR_GATE.png";
            GateXNOR = Image.createImage("file:" + url).scaledWidth(83).scaledHeight(83);
        } catch (java.io.IOException e) {
            System.out.println("Fail to get png" + e);
        }
    }

    private static final Config instance = new Config();
    private Config() {}
    public static Config getInstance() { return instance; }
}
