package org.ecs160.a2.UI;

//implementing Grid Class
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import org.ecs160.a2.Utilities.Config;

public class Grid {
    private static final Grid instance = new Grid();
    //in the future, gridblocksize, numGridBlocksWide, and numGridBlocksHigh may not be final
    private final int gridblocksize;
    private final int deviceWidth, deviceHeight;
    private final int numGridBlocksWide, numGridBlocksHigh;
    public static Grid getInstance() { return instance; }
    //Constructor
    private Grid(){
        deviceWidth = Display.getInstance().getDisplayWidth();
        deviceHeight = Display.getInstance().getDisplayHeight();
        //this is default, we can change it with zooming implementation
        numGridBlocksWide = 20;
        gridblocksize = deviceWidth/numGridBlocksWide;
        numGridBlocksHigh = deviceHeight/gridblocksize;
    }

    //this will draw the grid
    public void draw(Graphics g){
        g.setColor(0xFFFFFF);
        g.fillRect(0,0, deviceWidth, deviceHeight);
        //draw dots between gridblocks
        int nodeSize = 10;
        g.setColor(0xE6E6E6);
        for(int i = 0; i < numGridBlocksWide; i++){
            for(int j = 0; j< numGridBlocksHigh; j++){
                //draw dot at absolute position (i*gridblocksize,j*gridblocksize)
                g.fillRoundRect(i*gridblocksize, j*gridblocksize, nodeSize, nodeSize, nodeSize, nodeSize);
            }
        }
    }
    //this function will convert an absolute coordinate to a corresponding grid coordinate
    public int convertCoordAbstoGrid(int absCoordinate, char dimension){
        if(dimension == 'y'){
            //use Height
            int gridCoordinate = absCoordinate/gridblocksize;
            //check if relCoordinate is out of bounds(if item starts on last row of gridblocks it will be out of bounds)
            if(gridCoordinate > numGridBlocksHigh-1){
                //if out of bounds, place on second to last gridblock
                gridCoordinate = numGridBlocksHigh-1;
            }
            return gridCoordinate*gridblocksize;
        }else{
            //use Width
            int gridCoordinate = absCoordinate/gridblocksize;
            //check if relCoordinate is out of bounds(if item starts on last column of gridblocks it will be out of bounds)
            if(gridCoordinate > numGridBlocksWide-1){
                //if out of bounds, place on second to last gridblock
                gridCoordinate = numGridBlocksWide-1;
            }
            return gridCoordinate*gridblocksize;
        }
    }
}
