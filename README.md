# WallTennis

########## ########## ########## ########## ########## ########## ########## ########## 

* @author  Salvatore Palazzo
* @version 1.0
* @since   2020-02-01

* A simple tennis survival game that use JFrame to build a UI and KeyListener/KeyEvent from the class JPanel to bind the keyboard keys to pad moves.

* Every two round the ball speed increase and so pad does as well but with a slightly different ratio.

* The goal of the game it's survive as longer as you can to score the highest record.

########## ########## ########## ########## ########## ########## ########## ########## 

* @WallTennis this Class, instantiate the listener for the keyboard binds, create the frame to display the UI and override the method paint to draw the ball and pad.


* @Pad Class contains the info to draw a pad and the keyboard binds code

* @Ball Class contains the info to draw a ball and the game and collision logic to determine whatever
    the ball hit the pad how to increase the ball and the pad speed during the game or if hit the
    the edges of the board to bounce in the opposite direction.

* @Score Class contains the logic to display the score at game over.

########## ########## ########## ########## ########## ########## ########## ########## 
 
