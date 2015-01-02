package it.polimi.seeds;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);


            int i;
            Game game = new Game();

	    /* inizializzazione del gioco */

            game.state="active";
            game.round=1;
            game.winner=0;
            game.player1.name=1;
            game.player2.name=1;

            for (i=0;i<=5;i++) {
                game.player1.container[i] = 3;
                game.player2.container[i] = 3;
            }
            game.player1.container[6] = 0;
            game.player2.container[6] = 0;

            while (game.state.equals("active")){
                game.gameController(game.state, game.player1, game.player2, game.player, game.container, game.winner, game.dateTime, game.input);

                System.out.println("A chi tocca? Giocatore:");
                System.out.println(game.round);
            }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
