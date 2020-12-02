package org.ecs160.a2.Utilities;

public class Config {

    public final int logicGateWidth = 150;
    public final int logicGateHeight = 170;

    public final int nodeWidth = 30;
    public final int nodeHeight = 30;
    public final int nodeRadius = nodeWidth / 2;

    public final int ledWidth = 100;
    public final int ledHeight = 100;
    public final int ledIndicatorWidth = 70;
    public final int ledIndicatorHeight = 70;

    public final int switchWidth = 100;
    public final int switchHeight = 100;
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
    public final int ledIndicatorOffColor = 0xEC7063;

    private static final Config instance = new Config();
    private Config() {}
    public static Config getInstance() { return instance; }
}
