package com.exampple.dicegame
import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Gamescreen : AppCompatActivity() {
    //initalizing the variables I will need for this project
    var Player_Onescore:Int = 0
    var COM_Score:Int = 0
    var PlayerOne_Online:Boolean = true
    var endgame: String? = null
    var endgame_default:String? = null
    //This class and Function create the computer which goes against the player
    class ComputerPlayer{
        fun rollDice(): Int{
            return (1..6).random()
        }
    }
    // This function changes the images of the dice based on the what the random number variable generates
    fun getDiceImage(randomNumber: Int) :Int {
        return when (randomNumber){
            1->  R.drawable.die_face_1
            2->  R.drawable.die_face_2
            3->  R.drawable.die_face_3
            4->  R.drawable.die_face_4
            5->  R.drawable.die_face_5
            else ->  R.drawable.die_face_6

        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamescreen)
        //More variables used in the creation of the function below
        val diceRollButton: Button = findViewById(R.id.button4)
        val comPoints: TextView = findViewById(R.id.COM_Point)
        val playerPoints: TextView = findViewById(R.id.PO_Point)
        val diceImage: ImageView = findViewById(R.id.dice_image)
        var Game_over_msg = findViewById<TextView>(R.id.GameOver)
        val computerplayer = ComputerPlayer()
//This function simulates the computer taking a turn after the player
        fun simulateComputerTurn() {
    // Using the handler class and delay function I set a 2 second delay to invoke a realism of dice being thrown
            Handler().postDelayed({
                val random_number = computerplayer.rollDice()
                diceImage.setImageResource(getDiceImage(random_number))

                if (!PlayerOne_Online) {
                    COM_Score = comPoints.text.toString().toInt() + random_number
                    comPoints.text = COM_Score.toString()
//This nested if statemnt checks if the game winner requirement has been satisfied and then prints out a winner tag
                    if (COM_Score >= 100) {
                        endgame_default = resources.getText(R.string.com_won).toString()
                        endgame = "$endgame_default  Try Again"
                        Game_over_msg.text = endgame

//disables the button after the requirement has been met prompting the user to return back to main menu
                        diceRollButton.isEnabled = false
                    } else {
                        PlayerOne_Online= true
                    }
                }
            }, 1500)
        }


//THe funtction roll dice is the human side where if they press a button this activates and will iterate through each if statement
        fun rollDice() {
            val randomNumber = (1..6).random()
            diceImage.setImageResource(getDiceImage(randomNumber))

            if (PlayerOne_Online) {
                Player_Onescore += randomNumber
                if (Player_Onescore >= 100) {
                    endgame_default = resources.getText(R.string.game_over).toString()
                    //prints out the game winning message
                    endgame = endgame_default + " Player One"
                    Game_over_msg.text = endgame


                    diceRollButton.isEnabled = false

        }else{
            //this tallys together the each dice roll
                playerPoints.text = Player_Onescore.toString()
                    PlayerOne_Online = false
                    //This is a recursive call where if the above ones are not met then it will call the previous function to go ahead
                    simulateComputerTurn()
                }
        }
            }
        diceRollButton.setOnClickListener {
            rollDice()
        }





        val button: Button = findViewById(R.id.btnOpenMain)
        button.setOnClickListener {
           val i = Intent(this@Gamescreen, MainActivity::class.java)
            startActivity(i)


    }

    }
}