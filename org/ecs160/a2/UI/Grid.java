package org.ecs160.a2.UI;

import com.codename1.ui.Display;
import com.codename1.ui.Graphics;

public class Grid {
    private static final Grid instance = new Grid();
    private final int gridblocksize;
    private final int deviceWidth, deviceHeight;
    private final int numGridBlocksWide, numGridBlocksHigh;
    public static Grid getInstance() { return instance; }
    private Grid(){
        deviceWidth = Display.getInstance().getDisplayWidth();
        deviceHeight = Display.getInstance().getDisplayHeight();
        numGridBlocksWide = 20;
        gridblocksize = deviceWidth/numGridBlocksWide;
        numGridBlocksHigh = deviceHeight/gridblocksize;
    }

    public void draw(Graphics g){
        g.setColor(0xFFFFFF);
        g.fillRect(0,0, deviceWidth, deviceHeight);
        int nodeSize = 10;
        g.setColor(0xE6E6E6);
        for(int i = 0; i < numGridBlocksWide; i++){
            for(int j = 0; j< numGridBlocksHigh; j++){
                g.fillRoundRect(i*gridblocksize, j*gridblocksize, nodeSize, nodeSize, nodeSize, nodeSize);
            }
        }
    }
    //this function will convert an absolute coordinate to a corresponding grid coordinate
    public int convertCoordAbstoGrid(int absCoordinate, char dimension){
        int gridCoordinate = absCoordinate/gridblocksize;
        if(dimension == 'y'){
            //check if relCoordinate is out of bounds(if item starts on last row of gridblocks it will be out of bounds)
            if(gridCoordinate > numGridBlocksHigh-1){
                gridCoordinate = numGridBlocksHigh-1;
            }
        }else{
            //check if relCoordinate is out of bounds(if item starts on last column of gridblocks it will be out of bounds)
            if(gridCoordinate > numGridBlocksWide-1){
                gridCoordinate = numGridBlocksWide-1;
            }
        }
        return gridCoordinate*gridblocksize;
    }
}
