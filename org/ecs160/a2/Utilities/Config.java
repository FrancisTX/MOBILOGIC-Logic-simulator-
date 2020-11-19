package org.ecs160.a2.Utilities;

public class Config {

    public final int logicGateWidth = 200;
    public final int logicGateHeight = 150;

    public final int nodeWidth = 20;
    public final int nodeHeight = 20;
    public final int nodeRadius = nodeWidth / 2;

    public final int ledWidth = 100;
    public final int ledHeight = 100;

    public final int switchWidth = 100;
    public final int switchHeight = 100;

    public final int subCircuitWidth = 100;
    public final int subCircuitHeight = 100;

    public final int unselectedLogicGateColor = 0x0000ff;
    public final int selectedLogicGateColor = 0x52BE80;
    public final int unselectedLogicGateLineWidth = 3;
    public final int selectedLogicGateLineWidth = 10;


    public final int unselectedNodeColor = 0x0000ff;
    public final int selectedNodeColor = 0x52BE80;
    public final int unselectedNodeLineWidth = 3;
    public final int selectedNodeLineWidth = 10;

    public final int wireOffColor = 0x0000ff;
    public final int wireOnColor = 0x52BE80;

    private static final Config instance = new Config();
    private Config() {}
    public static Config getInstance() { return instance; }
}
