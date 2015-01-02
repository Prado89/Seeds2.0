package it.polimi.seeds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Game {

    public int input=6;
    public String dateTime;
    public int round;
    public int winner;
    public String state;
    public Player player1=new Player();
    public Player player2=new Player();
    public int container[] = new int[14];
    public Player player[]=new Player[2];

    public Player[] createPrivatePlayer(Player[] player, Player player1, Player player2){

        player[0]=player1;
        player[1]=player2;
        return player;
    }

    public void createMyPlayer(Player player1, Player player2, int[] container){

        for (int i=0;i<7;i++){
            player1.container[i]=container[i];
            player2.container[i]=container[i+7];
        }

    }

    public int[] createContainer(int[] container, Player player1, Player player2) {

        for (int i=0;i<7;i++){
            container[i]=player1.container[i];
            container[i+7]=player2.container[i];
        }

        return container;
    }

    public void gameController(String state, Player player1, Player player2, Player[] player, int[]container, int winner, String dateTime, int input){

    	/* Stampa la situazione attuale, controlla che la mossa sia valida, che lo stato del gioco sia attivo,
    	 * lancia i metodi che implementano le regole del gioco, la situazione dei giocatori a turno terminato
    	 * e il calcolo sull'eventuale vincitore */

        int i;

        System.out.println("giocatore 1:");
        for (i=0;i<7;i++){
            System.out.println(player1.container[i]);
        }
        System.out.println("giocatore 2");
        for (i=0;i<7;i++){
            System.out.println(player2.container[i]);
        }

        System.out.println("Giocatore");
        System.out.println(this.round);

        while(input<0||input>5){
            System.out.println("scegli una mossa valida -da 0 a 5-:");
            InputStreamReader reader = new InputStreamReader (System.in);
            BufferedReader myInput = new BufferedReader (reader);
            String str= new String();
            try {
                str = myInput.readLine();
            }
            catch (IOException e) {
                System.out.println ("Si � verificato un errore: " + e);
                System.exit(-1);
            }

            input=Integer.parseInt(str);/*select the bowl to begin the game */
        }
        container=createContainer(container, player1, player2);
        player=createPrivatePlayer(player, player1, player2);

        if (state.equals("stopped")){
            return;
        }
        else if (state.equals("paused")){
            state = "active";
        }
        else if (state.equals("active")){

            playerController(state, player1, player2, player, container, winner, dateTime, input);
            createMyPlayer(player1, player2, container);
            checkWinner(player, winner, dateTime, state);

            System.out.println("C'� un vincitore?");
            System.out.println(winner);


        }
    }

    public void checkWinner(Player[] player, int winner, String dateTime, String state){

    	/* Controlla se c'� un vincitore ed, eventualmente, che giocatore �. Termina il gioco e salva data e ora.*/

        int i;

        for (i=0; i<=1; i++){
            player[i].checkBowl(player[i].container);

            if (player[0].checkBowl(player[0].container) == 1 && player[1].checkBowl(player[1].container) == 1) {
                return;
            }

            else {
                player[i].finalMove(player[i].container);
                if (player[0].container[6] > player[1].container[6]) {
                    winner = 1;
                } else if (player[0].container[6] == player[1].container[6]) {
                    winner = 0;
                } else if (player[0].container[6] < player[1].container[6]) {
                    winner = 2;
                }

                dateTime=new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());;
                state = "stopped";

            }
        }
    }

    public void playerController(String state, Player player1, Player player2, Player[] player, int[]container, int winner, String dateTime, int input){

    	/* Riconosce se il giocatore � Human o Computer (AI non ancora implementata, il giocatore � sempre Human),
    	 * esegue le regole del gioco. */

        int last;

        /*if (player.name==0 equals("COMPUTER")){
            ai
        }

        else if (player.name==1 equals("HUMAN")){
             get input da button and assign it to input
        	*/

        int flag;

        if (this.round==1) {
            flag = input;
        }
        else {
            flag = input + 7;
        }
        last=move(flag, container);

        if (container[last]==1 && last!=6 && last!=13){
            container[last]=0;
            if (this.round==1) {
                container[6]=container[6]+1;
            }
            else {
                container[13]=container[13]+1;
            }
            captureSeeds(last, container);
        }

        if (last==6||last==13){
            return;
        }
        else {
            changeRound();
        }

    }

    public int move(int flag, int[] container){

    	/* Semina i seeds prelevati nei successivi container. */

        int selectedNumOfSeeds;
        int j;

        selectedNumOfSeeds=container[flag];
        container[flag]=0;

        for (j=selectedNumOfSeeds;j>0;j--){
            flag=flag+1;
            if (flag<14) {
                container[flag]= container[flag] + 1;
            }
            else {
                flag = 0;
                container[flag]= container[flag] + 1;
            }
        }

        return flag;
    }

    public void changeRound(){

    	/* Cambia il turno. */

        if (this.round == 1){
            this.round = 2;
        }
        else {
            this.round = 1;
        }
    }

    public void captureSeeds(int last, int[] container){

    	/* Esegue il furto di seeds dal container opposto*/

        if (this.round==1){
            container[6]=container[6]+container[12-last];
            container[12-last]=0;
        }
        else{
            last=last-7;
            container[13]=container[13]+container[5-last];
            container[5-last]=0;
        }

    }

}
