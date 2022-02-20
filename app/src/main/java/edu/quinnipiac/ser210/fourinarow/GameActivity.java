package edu.quinnipiac.ser210.fourinarow;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, IGame {

    boolean gameActive = true;
    // Player representation
    // true - X
    // false - O
    int activePlayer = 1;
    private int roundCount;
    int playerscore = 0;
    int computerscore = 0;

    private List<ImageButton> buttons;
    private static final int[] BUTTON_IDS = {
            R.id.box1,
            R.id.box2,
            R.id.box3,
            R.id.box4,
            R.id.box5,
            R.id.box6,
            R.id.box7,
            R.id.box8,
            R.id.box9,
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
            R.id.box36,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttons = new ArrayList<ImageButton>(BUTTON_IDS.length);
        for (int id : BUTTON_IDS) {
            ImageButton button = (ImageButton) findViewById(id);
            button.setOnClickListener(this); // maybe
            buttons.add(button);

        }
    }

    public void onClickReset(View view) {
        clearBoard();
    }

    @Override
    public void onClick(View view) {


        if (!((ImageButton) view).getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.boxspace_foreground).getConstantState())) {
            return;
        }
        if (activePlayer == 1) {
            ((ImageButton) view).setBackgroundResource(R.drawable.x_tictactoe);
            roundCount++;
        }

        if (checkForWinner() == 2) {
            playerWins();
        } else if (checkForWinner() == 3) {
            computerWins();
        } else if (roundCount == 36) {
            gameTIE();
        } else {
            activePlayer = 0;
            getComputerMove();
        }

    }

    public int randNum() {
        int ranLocation = ((int) (Math.random() * ((36 - 0))));
        return ranLocation;
    }

    @Override
    public void clearBoard() {
        for (int i = 0; i < buttons.size(); i++) {

            buttons.get(i).setBackgroundResource(R.drawable.boxspace_foreground);
        }
    }

    @Override
    public void setMove(int player, int location) {
    }

    @Override
    public int getComputerMove() {

        if (activePlayer == 0) {

            if (buttons.get(randNum()).getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.boxspace_foreground).getConstantState())
                    && !(buttons.get(randNum()).getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.x_tictactoe).getConstantState()))) {
                buttons.get(randNum()).setBackgroundResource(R.drawable.o_tictactoe);
                roundCount++;
            } else {
                getComputerMove();
            }
            activePlayer = 1;
        }
        return 0;
    }

    @Override
    public int checkForWinner() {


        for (int i = 0; i < buttons.size(); i++) {


            if (buttons.get(i) == buttons.get(i + 1) && buttons.get(i) == buttons.get(i + 2)
                    && buttons.get(i) == buttons.get(i + 3)) {

                if (buttons.get(i).equals(getResources().getDrawable(R.drawable.o_tictactoe))) {

                    return 3;
                } else if (buttons.get(i).equals(getResources().getDrawable(R.drawable.x_tictactoe))) {
                    return 2;
                }

            }


        }


        for (int i = 0; i < buttons.size(); i++) {

            if (buttons.get(i) == buttons.get(i + 6) && buttons.get(i) == buttons.get(i + 12)
                    && buttons.get(i) == buttons.get(i + 18)) {
                if (buttons.get(i).equals(getResources().getDrawable(R.drawable.o_tictactoe))) {
                    return 3;
                } else if (buttons.get(i).equals(getResources().getDrawable(R.drawable.x_tictactoe))) {
                    return 2;
                }
            }

        }

        for (int i = 0; i < buttons.size(); i++) {

            if (buttons.get(i) == buttons.get(i + 7) && buttons.get(i) == buttons.get(i + 14)
                    && buttons.get(i) == buttons.get(i + 21)) {

                if (buttons.get(i).getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.o_tictactoe).getConstantState())) {
                    return 3;
                } else if (buttons.get(i).getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.x_tictactoe).getConstantState())) {
                    return 2;
                }
            }
        }
        return 0;


    }


    private void playerWins() {
        findViewById(R.id.playerscore).equals("Player : " + playerscore);
        Toast.makeText(this, "Player Has Won!", Toast.LENGTH_LONG).show();
        clearBoard();
        playerscore++;
    }

    private void computerWins() {

        findViewById(R.id.playerscore).equals("Computer : " + computerscore);
        Toast.makeText(this, "Computer Has Won!", Toast.LENGTH_LONG).show();
        clearBoard();
        computerscore++;
    }

    private void gameTIE() {
        //WinText.setText(R.string.tie_game);
        Toast.makeText(this, "Game is a Tie!", Toast.LENGTH_LONG).show();
        clearBoard();
    }

}