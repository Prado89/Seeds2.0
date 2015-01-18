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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        this.game.initGame(); //initializing the game

        /*initializing the views*/
        TextView win = (TextView) findViewById(R.id.Winner);
        if (game.winner == 1 || game.winner == 2) {
            win.setText("The winner is player ");
            win.append(Integer.toString(game.winner));
        }

        TextView round = (TextView) findViewById(R.id.Round);
        round.setText("Round ");
        round.append(Integer.toString(game.round));

        Button tray1 = (Button) findViewById(R.id.Tray1);
        tray1.setText(Integer.toString(game.player1.container[6]));

        Button tray2 = (Button) findViewById(R.id.Tray2);
        tray2.setText(Integer.toString(game.player2.container[6]));

        this.pl1 = (LinearLayout) findViewById(R.id.player1);
        this.pl2 = (LinearLayout) findViewById(R.id.player2);

        for (int ii = 0; ii < 5; ii++) {
            View view1 = pl1.getChildAt(ii);
            Button button1 = (Button) view1;
            button1.setText(Integer.toString(game.player1.container[ii]));
            View view2 = pl2.getChildAt(ii);
            Button button2 = (Button) view2;
            button2.setText(Integer.toString(game.player2.container[ii]));
        }

        game.gameController(game.state, game.player1, game.player2, game.player, game.container, game.winner, game.dateTime, game.input);

           for (int ii = 0; ii < pl1.getChildCount(); ii++) {
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
        disableButton();
        updateButton();
        }

    @Override
    public void onClick(View view) {
        disableButton();
        this.game.input=(view.getId())%6;
        this.game.gameController(game.state, game.player1, game.player2, game.player, game.container, game.winner, game.dateTime, game.input);
        updateButton();
    }

    public void updateButton(){
        for (int ii = 0; ii < pl1.getChildCount(); ii++) {
            View view = pl1.getChildAt(ii);
            Button button = (Button) view;
            button.setText(Integer.toString(game.player1.container[ii]));
            if(game.round==1&&game.player1.container[ii]!=0){
                    button.setEnabled(true);
            }
        }

        for (int ii = 0; ii < pl2.getChildCount(); ii++) {
            View view = pl2.getChildAt(ii);
            Button button = (Button) view;
            button.setText(Integer.toString(game.player2.container[ii]));
            if(game.round==2&&game.player1.container[ii]!=0){
                button.setEnabled(true);
            }
        }
    }

    public void disableButton(){

        for (int ii = 0; ii < pl1.getChildCount(); ii++) {
            View view = pl1.getChildAt(ii);
            Button button = (Button) view;
            button.setText(Integer.toString(game.player1.container[ii]));
            button.setEnabled(false);

        }

        for (int ii = 0; ii < pl2.getChildCount(); ii++) {
            View view = pl2.getChildAt(ii);
            Button button = (Button) view;
            button.setText(Integer.toString(game.player2.container[ii]));
            button.setEnabled(false);

        }
    }
}

