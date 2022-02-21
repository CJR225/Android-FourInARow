/**
 * GameActivity - Creates main game of gridlayout and displays scores and imageicons, includes reset button
 *
 * @author Chris Rocco
 * @date 2/20/2022
 */

package edu.quinnipiac.ser210.fourinarow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    boolean gameActive = true;
    // Player representation
    // 1 - X
    // 0 - O
    private int activePlayer = 1;
    private int roundCount;
    private int playerscore = 0;
    private int computerscore = 0;
    private int player, location;

    String username;
    public static FourInARow FIRboard = new FourInARow();

    private TextView playerScore, computerScore;
    private static final int ROWS = 6, COLS = 6;
    private int[][] board = new int[ROWS][COLS];


    private List<ImageButton> buttons;
    private static final int[] BUTTON_IDS = {
            R.id.box00,
            R.id.box01,
            R.id.box02,
            R.id.box03,
            R.id.box04,
            R.id.box05,
            R.id.box06,
            R.id.box07,
            R.id.box08,
            R.id.box09,
            R.id.box10,
            R.id.box11,
            R.id.box12,
            R.id.box13,
            R.id.box14,
            R.id.box15,
            R.id.box16,
            R.id.box17,
            R.id.box18,
            R.id.box19,
            R.id.box20,
            R.id.box21,
            R.id.box22,
            R.id.box23,
            R.id.box24,
            R.id.box25,
            R.id.box26,
            R.id.box27,
            R.id.box28,
            R.id.box29,
            R.id.box30,
            R.id.box31,
            R.id.box32,
            R.id.box33,
            R.id.box34,
            R.id.box35,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerScore = findViewById(R.id.playerscore);
        computerScore = findViewById(R.id.computerscore);

        buttons = new ArrayList<ImageButton>(BUTTON_IDS.length);
        for (int id : BUTTON_IDS) {
            ImageButton button = (ImageButton) findViewById(id);
            button.setOnClickListener(this); // maybe
            buttons.add(button);

        }
        username = getIntent().getStringExtra("usernameKey");
        updatePlayerPoints();
    }

    public void onClickReset(View view) {
        resetGame();
    }

    @Override
    public void onClick(View view) {
        String buttonID = view.getResources().getResourceEntryName(view.getId());
        location = Integer.parseInt(buttonID.substring(buttonID.length() - 2, buttonID.length()));

        if (!((ImageButton) view).getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.boxspace_foreground).getConstantState())) {
            return;
        }
        if (activePlayer == 1) {
            ((ImageButton) view).setBackgroundResource(R.drawable.x_tictactoe);
            FIRboard.setMove(2, location);

        }

        if (FIRboard.checkForWinner() == 2) {
            playerWins();
        } else if (FIRboard.checkForWinner() == 3) {
            computerWins();
        } else if (FIRboard.checkForWinner() == 1) {
            gameTIE();
        } else {
            int computerMove = FIRboard.getComputerMove();

            FIRboard.setMove(1, computerMove);
            buttons.get(computerMove).setBackgroundResource(R.drawable.o_tictactoe);


        }
    }


    public int randNum() {
        int ranLocation = ((int) (Math.random() * ((36 - 0))));
        return ranLocation;
    }


    public void clearButtons() {
        for (int i = 0; i < buttons.size(); i++) {

            buttons.get(i).setBackgroundResource(R.drawable.boxspace_foreground);
        }
        FIRboard.clearBoard();
    }


    private void updatePlayerPoints() {
        playerScore.setText(username + " : " + playerscore);
    }

    private void updateComputerPoints() {
        computerScore.setText("Computer : " + computerscore);
    }

    private void playerWins() {
        playerscore++;
        updatePlayerPoints();
        Toast.makeText(this, "Player Has Won!", Toast.LENGTH_LONG).show();
        clearButtons();

    }

    private void computerWins() {
        computerscore++;
        updateComputerPoints();
        Toast.makeText(this, "Computer Has Won!", Toast.LENGTH_LONG).show();

        clearButtons();

    }

    private void gameTIE() {
        //WinText.setText(R.string.tie_game);
        Toast.makeText(this, "Game is a Tie!", Toast.LENGTH_LONG).show();
        clearButtons();

    }

    private void resetGame() {
        clearButtons();
        computerscore = 0;
        playerscore = 0;
        updateComputerPoints();
        updatePlayerPoints();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putInt("RoundCount", roundCount);
        outState.putInt("playerscore", playerscore);
        outState.putInt("computerscore", computerscore);
        outState.putInt("activePlayer", activePlayer);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("RoundCount");
        playerscore = savedInstanceState.getInt("playerscore");
        computerscore = savedInstanceState.getInt("computerscore");
        activePlayer = savedInstanceState.getInt("activePlayer");
    }
}