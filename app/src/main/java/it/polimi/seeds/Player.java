package it.polimi.seeds;


public class Player {

    /* In Player implemento i metodi relativi agli spostamenti all'interno del campo di gioco del singolo giocatore. */
    public int name;
    public int container[] = new int [7];

    public void setContainer(int[] container){
        this.container=container;
    }

    public int[] getContainer(){
        return container;
    }

    public int checkBowl(int[] container){

    	/* Controlla se i Bowls del giocatore sono tutti vuoti. */

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

    	/* Sposta tutti i seeds nel Tray. E' la mossa finale del gioco. */

        int i;

        for (i=0; i<=5; i++){
            container[6]=container[6]+container[i];
            container[i]=0;
        }
    }

}
