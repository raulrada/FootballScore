package com.example.android.footballscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int scoreSteaua = 0;
    int shotsSteaua = 0;
    int shotsTargetSteaua = 0;
    int cornersSteaua = 0;
    int foulsSteaua = 0;
    int yellowCardsSteaua = 0;
    int redCardsSteaua = 0;
    int scoreReal = 0;
    int shotsReal = 0;
    int shotsTargetReal = 0;
    int cornersReal = 0;
    int foulsReal = 0;
    int yellowCardsReal=0;
    int redCardsReal = 0;
    int possessionSteaua=0;
    int possessionReal=0;
    boolean matchFinished = false;

    TextView scoreViewSteaua;
    TextView scoreViewReal;
    TextView shotsViewSteaua;
    TextView shotsViewReal;
    TextView shotsTargetViewSteaua;
    TextView shotsTargetViewReal;
    TextView cornersViewSteaua;
    TextView cornersViewReal;
    TextView foulsViewSteaua;
    TextView foulsViewReal;
    TextView yellowCardsViewSteaua;
    TextView yellowCardsViewReal;
    TextView redCardsViewSteaua;
    TextView redCardsViewReal;
    TextView possessionTitleView;
    TextView possessionSteauaView;
    TextView possessionRealView;

    Button[] buttonArray;

    Button finishMatchButton;

    RelativeLayout possessionLayout;
    LinearLayout.LayoutParams lay;

    private static final String KEY_SCORE_STEAUA = "scoreSteaua";
    private static final String KEY_SCORE_REAL = "scoreReal";
    private static final String KEY_SHOTS_STEAUA="shotsSteaua";
    private static final String KEY_SHOTS_REAL="shotsReal";
    private static final String KEY_SHOTS_TARGET_STEAUA="shotsTargetSteaua";
    private static final String KEY_SHOTS_TARGET_REAL="shotsTargetReal";
    private static final String KEY_CORNERS_STEAUA = "cornersSteaua";
    private static final String KEY_CORNERS_REAL = "cornersReal";
    private static final String KEY_FOULS_STEAUA = "foulsSteaua";
    private static final String KEY_FOULS_REAL = "foulsReal";
    private static final String KEY_YELLOW_CARDS_STEAUA = "yellowSteaua";
    private static final String KEY_YELLOW_CARDS_REAL = "yellowReal";
    private static final String KEY_RED_CARDS_STEAUA = "redSteaua";
    private static final String KEY_RED_CARDS_REAL = "redReal";
    private static final String KEY_POSSESSION_STEAUA = "possessionSteaua";
    private static final String KEY_POSSESSION_REAL = "possessionReal";
    private static final String KEY_POSSESSION_WEIGHT = "possessionWeight";
    private static final String KEY_MATCH_FINISHED = "matchFinished";

    public static final int MAX_POSSESSION = 60;
    public static final int CUMULATED_POSSESSION=100;
    public static final int MIN_POSSESSION = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreViewSteaua = (TextView) findViewById(R.id.score_steaua);
        scoreViewReal = (TextView) findViewById(R.id.score_real);
        shotsViewSteaua = (TextView) findViewById(R.id.shots_steaua);
        shotsViewReal = (TextView) findViewById(R.id.shots_real);
        shotsTargetViewSteaua = (TextView) findViewById(R.id.shots_target_steaua);
        shotsTargetViewReal = (TextView) findViewById(R.id.shots_target_real);
        cornersViewSteaua = (TextView) findViewById(R.id.corners_steaua);
        cornersViewReal = (TextView) findViewById(R.id.corners_real);
        foulsViewSteaua = (TextView) findViewById(R.id.fouls_steaua);
        foulsViewReal = (TextView) findViewById(R.id.fouls_real);
        yellowCardsViewSteaua = (TextView) findViewById(R.id.yellow_cards_steaua);
        yellowCardsViewReal = (TextView) findViewById(R.id.yellow_cards_real);
        redCardsViewSteaua = (TextView) findViewById(R.id.red_cards_steaua);
        redCardsViewReal = (TextView) findViewById(R.id.red_cards_real);
        possessionSteauaView = (TextView) findViewById(R.id.possession_steaua);
        possessionRealView = (TextView) findViewById(R.id.possession_real);
        possessionTitleView = (TextView) findViewById(R.id.possession_title);

        possessionLayout = (RelativeLayout) findViewById(R.id.possession_relative_layout);

        // make 0 the weight of the RelativeLayout while the match is ongoing
        lay = (LinearLayout.LayoutParams) possessionLayout.getLayoutParams();
        lay.weight=0;

        buttonArray = new Button[14];

        buttonArray[0] = (Button) findViewById(R.id.add_goal_steaua);
        buttonArray[1] = (Button) findViewById(R.id.add_goal_real);
        buttonArray[2] = (Button) findViewById(R.id.add_shots_steaua);
        buttonArray[3] = (Button) findViewById(R.id.add_shots_real);
        buttonArray[4] = (Button) findViewById(R.id.add_shots_target_steaua);
        buttonArray[5] = (Button) findViewById(R.id.add_shots_target_real);
        buttonArray[6] = (Button) findViewById(R.id.add_corners_steaua);
        buttonArray[7] = (Button) findViewById(R.id.add_corners_real);
        buttonArray[8] = (Button) findViewById(R.id.add_fouls_steaua);
        buttonArray[9] = (Button) findViewById(R.id.add_fouls_real);
        buttonArray[10] = (Button) findViewById(R.id.add_yellow_cards_steaua);
        buttonArray[11] = (Button) findViewById(R.id.add_yellow_cards_real);
        buttonArray[12] = (Button) findViewById(R.id.add_red_cards_steaua);
        buttonArray[13] = (Button) findViewById(R.id.add_red_cards_real);

        finishMatchButton = (Button) findViewById(R.id.finish_match_button);

    }

    /**
     *override onRestoreInstanceState to restore value of variables
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        scoreSteaua = savedInstanceState.getInt(KEY_SCORE_STEAUA);
        displayNumber(scoreSteaua, scoreViewSteaua);

        shotsSteaua = savedInstanceState.getInt(KEY_SHOTS_STEAUA);
        displayNumber(shotsSteaua, shotsViewSteaua);

        shotsTargetSteaua = savedInstanceState.getInt(KEY_SHOTS_TARGET_STEAUA);
        displayNumber(shotsTargetSteaua, shotsTargetViewSteaua);

        cornersSteaua = savedInstanceState.getInt(KEY_CORNERS_STEAUA);
        displayNumber(cornersSteaua, cornersViewSteaua);

        foulsSteaua = savedInstanceState.getInt(KEY_FOULS_STEAUA);
        displayNumber(foulsSteaua, foulsViewSteaua);

        yellowCardsSteaua = savedInstanceState.getInt(KEY_YELLOW_CARDS_STEAUA);
        displayNumber(yellowCardsSteaua, yellowCardsViewSteaua);

        redCardsSteaua = savedInstanceState.getInt(KEY_RED_CARDS_STEAUA);
        displayNumber(redCardsSteaua, redCardsViewSteaua);

        possessionSteaua = savedInstanceState.getInt(KEY_POSSESSION_STEAUA);
        displayPercentage(possessionSteaua, possessionSteauaView);

        scoreReal = savedInstanceState.getInt(KEY_SCORE_REAL);
        displayNumber(scoreReal, scoreViewReal);

        shotsReal = savedInstanceState.getInt(KEY_SHOTS_REAL);
        displayNumber(shotsReal, shotsViewReal);

        shotsTargetReal = savedInstanceState.getInt(KEY_SHOTS_TARGET_REAL);
        displayNumber(shotsTargetReal, shotsTargetViewReal);

        cornersReal = savedInstanceState.getInt(KEY_CORNERS_REAL);
        displayNumber(cornersReal, cornersViewReal);

        foulsReal = savedInstanceState.getInt(KEY_FOULS_REAL);
        displayNumber(foulsReal, foulsViewReal);

        yellowCardsReal = savedInstanceState.getInt(KEY_YELLOW_CARDS_REAL);
        displayNumber(yellowCardsReal, yellowCardsViewReal);

        redCardsReal = savedInstanceState.getInt(KEY_RED_CARDS_REAL);
        displayNumber(redCardsReal, redCardsViewReal);

        possessionReal = savedInstanceState.getInt(KEY_POSSESSION_REAL);
        displayPercentage(possessionReal, possessionRealView);

        lay.weight = savedInstanceState.getFloat(KEY_POSSESSION_WEIGHT);

        matchFinished = savedInstanceState.getBoolean(KEY_MATCH_FINISHED);


        //keep the VISIBLE / GONE status for the add buttons
        if (matchFinished){
            finishMatchButton.setEnabled(false);
            for (int i=0;i<buttonArray.length;i++){
                buttonArray[i].setVisibility(View.GONE);
            }
        }

        if (!matchFinished){
            finishMatchButton.setEnabled(true);
            for (int i=0;i<buttonArray.length;i++){
                buttonArray[i].setVisibility(View.VISIBLE);
            }
        }

    }


    /**
     *override onRestoreInstanceState to memorize value of variables in program
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt(KEY_SCORE_STEAUA, scoreSteaua);
        savedInstanceState.putInt(KEY_SHOTS_STEAUA, shotsSteaua);
        savedInstanceState.putInt(KEY_SHOTS_TARGET_STEAUA, shotsTargetSteaua);
        savedInstanceState.putInt(KEY_CORNERS_STEAUA, cornersSteaua);
        savedInstanceState.putInt(KEY_FOULS_STEAUA, foulsSteaua);
        savedInstanceState.putInt(KEY_YELLOW_CARDS_STEAUA, yellowCardsSteaua);
        savedInstanceState.putInt(KEY_RED_CARDS_STEAUA, redCardsSteaua);
        savedInstanceState.putInt(KEY_POSSESSION_STEAUA, possessionSteaua);

        savedInstanceState.putInt(KEY_SCORE_REAL, scoreReal);
        savedInstanceState.putInt(KEY_SHOTS_REAL, shotsReal);
        savedInstanceState.putInt(KEY_SHOTS_TARGET_REAL, shotsTargetReal);
        savedInstanceState.putInt(KEY_CORNERS_REAL, cornersReal);
        savedInstanceState.putInt(KEY_FOULS_REAL, foulsReal);
        savedInstanceState.putInt(KEY_YELLOW_CARDS_REAL, yellowCardsReal);
        savedInstanceState.putInt(KEY_RED_CARDS_REAL, redCardsReal);
        savedInstanceState.putInt(KEY_POSSESSION_REAL, possessionReal);

        savedInstanceState.putFloat(KEY_POSSESSION_WEIGHT, lay.weight);

        savedInstanceState.putBoolean(KEY_MATCH_FINISHED, matchFinished);

        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     *add a goal for Steaua.
     *also increase the number of shots and the number of shots on target for Steaua
     * (otherwise no goal would be possible)
     */
    public void goalSteaua(View view) {
        scoreSteaua += 1;
        displayNumber (scoreSteaua, scoreViewSteaua);

        shotsTargetSteaua += 1;
        displayNumber(shotsTargetSteaua, shotsTargetViewSteaua);

        shotsSteaua += 1;
        displayNumber(shotsSteaua, shotsViewSteaua);
    }

    /**
     *add a shot for Steaua
     */
    public void shotSteaua(View view) {
        shotsSteaua += 1;
        displayNumber(shotsSteaua, shotsViewSteaua);
    }

    /**
     * add a shot on target for Steaua.
     * A shot on target is also a shot, so increase the number of shots as well.
     */
    public void shotTargetSteaua(View view) {
        shotsSteaua += 1;
        displayNumber(shotsSteaua, shotsViewSteaua);
        shotsTargetSteaua += 1;
        displayNumber(shotsTargetSteaua, shotsTargetViewSteaua);
    }

    /**
     *add a corner for Steaua
     */
    public void cornerSteaua(View view) {
        cornersSteaua += 1;
        displayNumber(cornersSteaua, cornersViewSteaua);
    }

    /**
     *add a foul against Steaua
     */
    public void foulSteaua(View view) {
        foulsSteaua += 1;
        displayNumber(foulsSteaua, foulsViewSteaua);
    }

    /**
     *add a yellow card for Steaua
     */
    public void yellowCardSteaua(View view) {
        yellowCardsSteaua += 1;
        displayNumber(yellowCardsSteaua, yellowCardsViewSteaua);
    }

    /**
     *add a red card for Steaua
     */
    public void redCardSteaua(View view) {
        redCardsSteaua += 1;
        displayNumber(redCardsSteaua, redCardsViewSteaua);
    }

    /**
     *add a goal for Real Madrid
     * also increase the number of shots and the number of shots on target for Real Madrid
     * (otherwise no goal would be possible)
     */
    public void goalReal(View view) {
        scoreReal += 1;
        displayNumber(scoreReal, scoreViewReal);

        shotsTargetReal += 1;
        displayNumber(shotsTargetReal, shotsTargetViewReal);

        shotsReal += 1;
        displayNumber(shotsReal, shotsViewReal);
    }

    /**
     * add a shot for Real Madrid
     */
    public void shotReal(View view) {
        shotsReal += 1;
        displayNumber(shotsReal, shotsViewReal);
    }

    /**
     * add a shot on target for Real Madrid.
     * A shot on target is also a shot, so increase the number of shots as well.
     */
    public void shotTargetReal(View view) {
        shotsReal += 1;
        displayNumber(shotsReal, shotsViewReal);
        shotsTargetReal += 1;
        displayNumber(shotsTargetReal, shotsTargetViewReal);
    }

    /**
     * add a corner for Real Madrid
     * */
    public void cornerReal(View view) {
        cornersReal += 1;
        displayNumber(cornersReal, cornersViewReal);
    }

    /**
     *add a foul against Real Madrid
     */
    public void foulReal(View view) {
        foulsReal += 1;
        displayNumber(foulsReal, foulsViewReal);
    }

    /**
     *add a yellow card for Real Madrid
     */
    public void yellowCardReal(View view) {
        yellowCardsReal += 1;
        displayNumber(yellowCardsReal, yellowCardsViewReal);
    }

    /**
     *add a red card for Real Madrid
     */
    public void redCardReal(View view) {
        redCardsReal += 1;
        displayNumber(redCardsReal, redCardsViewReal);
    }


    /**
    *display provided value to a provided TextView
    * @param toDisplay number to be displayed
    * @param tv TextView in which the number provided shall be displayed
     */
    public void displayNumber (int toDisplay, TextView tv){
        tv.setText(String.valueOf(toDisplay));
    }

    /**
     * display provided value to a provided TextView as a percentage
     * @param toDisplay number to be display
     * @param tv TextView in which the number provided shall be displayed
     */
    public void displayPercentage (int toDisplay, TextView tv){
        tv.setText(String.valueOf(toDisplay)+"%");
    }

    /**
     *make possession stats visible and make the add buttons invisible, so that the stats
     * can no longer be updated
     */
    public void finishMatch(View view) {

        matchFinished = true;

        lay.weight = 1;

        possessionTitleView.setVisibility(View.GONE);
        possessionSteauaView.setVisibility(View.GONE);
        possessionRealView.setVisibility(View.GONE);

        possessionTitleView.setVisibility(View.VISIBLE);
        possessionSteauaView.setVisibility(View.VISIBLE);
        possessionRealView.setVisibility(View.VISIBLE);

        for (int i=0;i<buttonArray.length;i++){
            buttonArray[i].setVisibility(View.GONE);
        }

        generatePossession(generateRandom());

        /*disable Finish Match button after the match is finished, to prevent resetting
        of possession stats*/
        finishMatchButton.setEnabled(false);

    }


    /**
     * @return a random number between 0 and 60
     */
    public int generateRandom(){
        Random random = new Random();
        return random.nextInt(MAX_POSSESSION)+1;
    }


    /**
     * Generates the possession figures for Steaua and Real and displays them.
     * Use random number to add to minimum level of possession, in order to determine the
     * possession for Steaua. The possession for Real is the difference up to 100.
     * @param rand random number used to determine the possession for Steaua
     */
    public void generatePossession(int rand){
        possessionSteaua = rand + MIN_POSSESSION;
        displayPercentage(possessionSteaua,possessionSteauaView);
        possessionReal = CUMULATED_POSSESSION - possessionSteaua;
        displayPercentage(possessionReal, possessionRealView);
    }

    /**
     * reset stats in app. Hide possession stats until the match is finished and make
     * the add buttons visible again.
     */
    public void reset(View view) {

        matchFinished=false;
        lay.weight = 0;

        possessionTitleView.setVisibility(View.GONE);
        possessionSteauaView.setVisibility(View.GONE);
        possessionRealView.setVisibility(View.GONE);

        for (int i=0;i<buttonArray.length;i++){
            buttonArray[i].setVisibility(View.VISIBLE);
        }

        finishMatchButton.setEnabled(true);

        scoreSteaua = 0;
        displayNumber(scoreSteaua, scoreViewSteaua);

        shotsSteaua = 0;
        displayNumber(shotsSteaua, shotsViewSteaua);

        shotsTargetSteaua = 0;
        displayNumber(shotsTargetSteaua, shotsTargetViewSteaua);

        cornersSteaua = 0;
        displayNumber(cornersSteaua, cornersViewSteaua);

        foulsSteaua = 0;
        displayNumber(foulsSteaua, foulsViewSteaua);

        yellowCardsSteaua = 0;
        displayNumber(yellowCardsSteaua, yellowCardsViewSteaua);

        redCardsSteaua = 0;
        displayNumber(redCardsSteaua, redCardsViewSteaua);

        scoreReal = 0;
        displayNumber(scoreReal, scoreViewReal);

        shotsReal = 0;
        displayNumber(shotsReal, shotsViewReal);

        shotsTargetReal = 0;
        displayNumber(shotsTargetReal, shotsTargetViewReal);

        cornersReal = 0;
        displayNumber(cornersReal, cornersViewReal);

        foulsReal = 0;
        displayNumber(foulsReal, foulsViewReal);

        yellowCardsReal=0;
        displayNumber(yellowCardsReal, yellowCardsViewReal);

        redCardsReal = 0;
        displayNumber(redCardsReal, redCardsViewReal);

        possessionSteaua = 0;
        displayPercentage(possessionSteaua, possessionSteauaView);

        possessionReal = 0;
        displayPercentage(possessionReal, possessionRealView);

    }

}
