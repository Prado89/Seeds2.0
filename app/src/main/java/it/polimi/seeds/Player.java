package it.polimi.seeds;


public class Player {

    /* In Player there are the methods related to the internal movements (the only ones involving only its Bowls)*/
    public int name;
    public int container[] = new int [7];

    public void setContainer(int[] container){
        this.container=container;
    }

    public int[] getContainer(){
        return container;
    }

    public int checkBowl(int[] container){

    	/* Check if all Bowls are empties */

        int i;
        int totNumOfSeeds = 0;

        for (i=0; i<=5; i++){
            totNumOfSeeds = totNumOfSeeds+container[i];
        }
        if (totNumOfSeeds==0){
            return 0;
        }
        else {
            return 1;
        }
    }

    public void finalMove(int[] container){

    	/* Move all the Seeds into the Tray. It' the final move of the game */

        int i;

        for (i=0; i<=5; i++){
            container[6]=container[6]+container[i];
            container[i]=0;
        }
    }

}
