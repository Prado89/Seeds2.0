package it.polimi.seeds;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainView extends Activity implements View.OnClickListener {

    public Game game=new Game();
    public LinearLayout pl1;
    public LinearLayout pl2;
    public TextView win;
    public TextView round;
    public Button tray1;
    public Button tray2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        this.game.initGame(); //initializing the game

        this.win = (TextView) findViewById(R.id.Winner);    //defining and initializing the views
        this.round = (TextView) findViewById(R.id.Round);
        this.tray1 = (Button) findViewById(R.id.Tray1);
        this.tray2 = (Button) findViewById(R.id.Tray2);
        this.pl1 = (LinearLayout) findViewById(R.id.player1);
        this.pl2 = (LinearLayout) findViewById(R.id.player2);
        firstView();

        game.gameController(game.state, game.player1, game.player2, game.player, game.container, game.winner, game.dateTime, game.input);
                                                                    //first call of the game...
        for (int ii = 0; ii < pl1.getChildCount(); ii++) {          //setting listener
            View view = pl1.getChildAt(ii);
            Button button = (Button) view;
            button.setOnClickListener(this);
            button.setId(ii);
            button.setText(Integer.toString(game.player1.container[ii]));
        }

        for (int ii = pl2.getChildCount()-1; ii >=0; ii--) {
            View view = pl2.getChildAt(ii);
            Button button = (Button) view;
            button.setOnClickListener(this);
            button.setId(12-ii);
            button.setText(Integer.toString(game.player2.container[ii]));
        }

        disableButton();        //disable all buttons
        updateButton();         //and enable only the buttons of the right player
        }

    private void firstView() {                          //initialize the first view of the game
        if (game.winner == 1 || game.winner == 2) {     //it's the first set of data
            win.setText("The winner is player ");
            win.append(Integer.toString(game.winner));
        }
        round.setText("Round ");
        round.append(Integer.toString(game.round));
        tray1.setText(Integer.toString(game.player1.container[6]));
        tray2.setText(Integer.toString(game.player2.container[6]));

        for (int ii = 0; ii < 5; ii++) {
            View view1 = pl1.getChildAt(ii);
            Button button1 = (Button) view1;
            button1.setText(Integer.toString(game.player1.container[ii]));
            View view2 = pl2.getChildAt(ii);
            Button button2 = (Button) view2;
            button2.setText(Integer.toString(game.player2.container[ii]));
        }
    }

    @Override
    public void onClick(View view) {
        disableButton();
        this.game.input=(view.getId())%6;
        this.game.gameController(game.state, game.player1, game.player2, game.player, game.container, game.winner, game.dateTime, game.input);
        updateButton();
    }

    public void updateButton(){                                 //enabling only buttons containing some seeds
        for (int ii = 0; ii < pl1.getChildCount(); ii++) {      //and of the right player
            View view = pl1.getChildAt(ii);                     //set the new views
            Button button = (Button) view;
            button.setText(Integer.toString(game.player1.container[ii]));
            if(game.round==1&&game.player1.container[ii]!=0){
                    button.setEnabled(true);
            }
        }

        for (int ii = pl2.getChildCount()-1; ii >=0; ii--) {
            View view = pl2.getChildAt(ii);
            Button button = (Button) view;
            button.setText(Integer.toString(game.player2.container[ii]));
            if(game.round==2&&game.player1.container[ii]!=0){
                button.setEnabled(true);
            }
        }

        if (game.winner == 1 || game.winner == 2) {
            win.setText("The winner is player ");
            win.append(Integer.toString(game.winner));
        }
        round.setText("Round ");
        round.append(Integer.toString(game.round));
        tray1.setText(Integer.toString(game.player1.container[6]));
        tray2.setText(Integer.toString(game.player2.container[6]));
    }

    public void disableButton(){                            //disabling all buttons

        for (int ii = 0; ii < pl1.getChildCount(); ii++) {
            View view = pl1.getChildAt(ii);
            Button button = (Button) view;
            button.setText(Integer.toString(game.player1.container[ii]));
            button.setEnabled(false);

        }

        for (int ii = pl2.getChildCount()-1; ii >=0; ii--) {
            View view = pl2.getChildAt(ii);
            Button button = (Button) view;
            button.setText(Integer.toString(game.player2.container[ii]));
            button.setEnabled(false);

        }
    }
}

