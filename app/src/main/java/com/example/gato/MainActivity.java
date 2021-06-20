package com.example.gato;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity
{
    private final boolean[] availableBox = new boolean[9];
    private final ImageButton[] boxes = new ImageButton[9];
    private boolean[] belongs = new boolean[9];
    private boolean turn = true;
    private int timesP1 = 0, timesP2 = 0;
    private boolean p1Win = false, p2Win = false;
    private TextView textViewP1, textViewP2, textViewResult;
    private ConstraintLayout constraintLayout;
    private LottieAnimationView lottieAnimationView;
    private LottieAnimationView lottieAnimationView2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.result);
        lottieAnimationView = findViewById(R.id.animationView);
        lottieAnimationView2 = findViewById(R.id.animationView2);

        setup();
        setUpAvailableBox();
        play();
    }

    //Configuramos los botones y textos de la partida
    private void setup()
    {
        boxes[0] = findViewById(R.id.box1);
        boxes[1] = findViewById(R.id.box2);
        boxes[2] = findViewById(R.id.box3);
        boxes[3] = findViewById(R.id.box4);
        boxes[4] = findViewById(R.id.box5);
        boxes[5] = findViewById(R.id.box6);
        boxes[6] = findViewById(R.id.box7);
        boxes[7] = findViewById(R.id.box8);
        boxes[8] = findViewById(R.id.box9);

        textViewP1 = findViewById(R.id.textViewP1);
        textViewP2 = findViewById(R.id.textViewP2);
        textViewResult = findViewById(R.id.textResult);
        textViewP1.setVisibility(View.VISIBLE);
        textViewP2.setVisibility(View.GONE);

        for(int i = 0; i < 9; i++)
        {
            ImageButton box = boxes[i];
            box.setImageDrawable(null);
        }
    }

    //Configuramos las casillas disponibles
    private void setUpAvailableBox()
    {
        for(int i = 0; i < 9; i++)
            availableBox[i] = true;
    }

    //Obtenemos todos los botones para saber cuando son clickeados
    private void play()
    {
        for(int i = 0; i < 9; i++)
        {
            ImageButton box = boxes[i];
            int finalI = i;
            box.setOnClickListener(v ->
            {
                changeTurn(box, finalI);
            });
        }
    }

    //Cambiar el turno del jugador y dibujar en el tablero X/O
    private void changeTurn(ImageButton box, int finalI)
    {
        //Si nadie ha ganado y la casilla que tocaron está disponible:
        if(!p1Win && !p2Win && !checkIfTied())
        {
            if(availableBox[finalI])
            {
                availableBox[finalI] = false;
                //turno del jugador1
                if (turn)
                {
                    textViewP1.setVisibility(View.GONE);
                    textViewP2.setVisibility(View.VISIBLE);
                    belongs[finalI] = true;
                    box.setImageDrawable(AppCompatResources.getDrawable(MainActivity.this, R.drawable.x));
                    turn = false;
                    timesP1++;
                }
                //turno del jugador2
                else
                {
                    textViewP1.setVisibility(View.VISIBLE);
                    textViewP2.setVisibility(View.GONE);
                    belongs[finalI] = false;
                    box.setImageDrawable(AppCompatResources.getDrawable(MainActivity.this, R.drawable.circle));
                    turn = true;
                    timesP2++;
                }
            }

            //Si cualquiera ha tirado 3 veces o más, revisamos si alguien ganó
            if(timesP1 >= 3 || timesP2 >= 3)
                checkIfWin();
        }
    }

    //Revisamos si alguien ganó
    private void checkIfWin()
    {
        //linea recta de izquierda a derecha primera fila
        if(!p1Win && !p2Win && !availableBox[0] && !availableBox[1] && !availableBox[2])
        {
            check(0, 1, 2);
            /*
            for(int i = 0; i < 3; i++)
            {
                p1Win = belongs[i];
                if(!belongs[i])
                    break;
            }

            if(!p1Win)
            {
                for(int i = 0; i < 3; i++)
                {
                    if(!belongs[i])
                        p2Win = true;
                    else
                    {
                        p2Win = false;
                        break;
                    }
                }
            }
             */


            /*
            if (belongs[0] && belongs[1] && belongs[2])
            {
                p1Win = true;
                p2Win = false;
            }
            else if (!belongs[0] && !belongs[1] && !belongs[2])
            {
                p1Win = false;
                p2Win = true;
            }
            else
            {
                p1Win = false;
                p2Win = false;
            }

             */
        }

        //linea recta de izquieda a derecha segunda fila
        if(!p1Win && !p2Win && !availableBox[3] && !availableBox[4] && !availableBox[5])
        {
            check(3, 4, 5);
            /*
            for(int i = 3; i < 6; i++)
            {
                p1Win = belongs[i];
                if(!belongs[i])
                    break;
            }

            if(!p1Win)
            {
                for(int i = 3; i < 6; i++)
                {
                    if(!belongs[i])
                        p2Win = true;
                    else
                    {
                        p2Win = false;
                        break;
                    }
                }
            }

             */

            /*
            if (belongs[3] && belongs[4] && belongs[5])
            {
                p1Win = true;
                p2Win = false;
            }
            else if (!belongs[3] && !belongs[4] && !belongs[5])
            {
                p1Win = false;
                p2Win = true;
            }
            else
            {
                p1Win = false;
                p2Win = false;
            }

             */
        }

        //linea recta de izquierda a derecha tercer fila
        if(!p1Win && !p2Win && !availableBox[6] && !availableBox[7] && !availableBox[8])
        {
            check(6, 7, 8);
            /*
            for(int i = 6; i < 9; i++)
            {
                p1Win = belongs[i];
                if(!belongs[i])
                    break;
            }

            if(!p1Win)
            {
                for(int i = 6; i < 9; i++)
                {
                    if(!belongs[i])
                        p2Win = true;
                    else
                    {
                        p2Win = false;
                        break;
                    }
                }
            }
             */

            /*
            if (belongs[6] && belongs[7] && belongs[8])
            {
                p1Win = true;
                p2Win = false;
            }
            else if (!belongs[6] && !belongs[7] && !belongs[8])
            {
                p1Win = false;
                p2Win = true;
            }
            else
            {
                p1Win = false;
                p2Win = false;
            }

             */
        }

        //Diagonal superior de izquieda a derecha
        if(!p1Win && !p2Win && !availableBox[0] && !availableBox[4] && !availableBox[8])
        {
            check(0, 4, 8);
            /*
            for(int i = 0; i < 9; i+=4)
            {
                p1Win = belongs[i];
                if(!belongs[i])
                    break;
            }

            if(!p1Win)
            {
                for(int i = 0; i < 9; i+=4)
                {
                    if(!belongs[i])
                        p2Win = true;
                    else
                    {
                        p2Win = false;
                        break;
                    }
                }
            }

             */

            /*
            if (belongs[0] && belongs[4] && belongs[8])
            {
                p1Win = true;
                p2Win = false;
            }
            else if (!belongs[0] && !belongs[4] && !belongs[8])
            {
                p1Win = false;
                p2Win = true;
            }
            else
            {
                p1Win = false;
                p2Win = false;
            }

             */
        }

        //Diagonal superior de derecha a izquierda
        if(!p1Win && !p2Win && !availableBox[2] && !availableBox[4] && !availableBox[6])
        {
            check(2, 4, 6);
            /*
            for(int i = 2; i < 7; i+=2)
            {
                p1Win = belongs[i];
                if(!belongs[i])
                    break;
            }

            if(!p1Win)
            {
                for(int i = 2; i < 7; i+=2)
                {
                    if(!belongs[i])
                        p2Win = true;
                    else
                    {
                        p2Win = false;
                        break;
                    }
                }
            }



            /*
            if (belongs[0] && belongs[4] && belongs[8])
            {
                p1Win = true;
                p2Win = false;
            }
            else if (!belongs[0] && !belongs[4] && !belongs[8])
            {
                p1Win = false;
                p2Win = true;
            }
            else
            {
                p1Win = false;
                p2Win = false;
            }

             */
        }

        //linea recta primer columna
        if(!p1Win && !p2Win && !availableBox[0] && !availableBox[3] && !availableBox[6])
        {
            check(0, 3, 6);
            /*
            for(int i = 0; i < 7; i+=3)
            {
                p1Win = belongs[i];
                if(!belongs[i])
                    break;
            }

            if(!p1Win)
            {
                for(int i = 0; i < 7; i+=3)
                {
                    if(!belongs[i])
                        p2Win = true;
                    else
                    {
                        p2Win = false;
                        break;
                    }
                }
            }

             */


            /*
            if (belongs[0] && belongs[3] && belongs[6])
            {
                p1Win = true;
                p2Win = false;
            }
            else if (!belongs[0] && !belongs[3] && !belongs[6])
            {
                p1Win = false;
                p2Win = true;
            }
            else
            {
                p1Win = false;
                p2Win = false;
            }

             */
        }

        //linea recta segunda columna
        if(!p1Win && !p2Win && !availableBox[1] && !availableBox[4] && !availableBox[7])
        {
            check(1, 4, 7);
            /*
            for(int i = 1; i < 8; i+=3)
            {
                p1Win = belongs[i];
                if(!belongs[i])
                    break;
            }

            if(!p1Win)
            {
                for(int i = 1; i < 8; i+=3)
                {
                    if(!belongs[i])
                        p2Win = true;
                    else
                    {
                        p2Win = false;
                        break;
                    }
                }
            }


            /*
            if (belongs[0] && belongs[3] && belongs[6])
            {
                p1Win = true;
                p2Win = false;
            }
            else if (!belongs[0] && !belongs[3] && !belongs[6])
            {
                p1Win = false;
                p2Win = true;
            }
            else
            {
                p1Win = false;
                p2Win = false;
            }

             */
        }

        //linea recta tercer columna
        if(!p1Win && !p2Win && !availableBox[2] && !availableBox[5] && !availableBox[8])
        {
            check(2, 5, 8);
            /*
            for(int i = 2; i < 9; i+=3)
            {
                p1Win = belongs[i];
                if(!belongs[i])
                    break;
            }

            if(!p1Win)
            {
                for(int i = 2; i < 9; i+=3)
                {
                    if(!belongs[i])
                        p2Win = true;
                    else
                    {
                        p2Win = false;
                        break;
                    }
                }
            }


            /*
            if (belongs[0] && belongs[3] && belongs[6])
            {
                p1Win = true;
                p2Win = false;
            }
            else if (!belongs[0] && !belongs[3] && !belongs[6])
            {
                p1Win = false;
                p2Win = true;
            }
            else
            {
                p1Win = false;
                p2Win = false;
            }

             */
        }

        if(p1Win)
        {
            visibility();
            textViewResult.setText(R.string.p1Win);
            lottieAnimationView.setVisibility(View.VISIBLE);
            lottieAnimationView.playAnimation();
            lottieAnimationView2.setVisibility(View.INVISIBLE);
        }
        else if(p2Win)
        {
            visibility();
            textViewResult.setText(R.string.p2Win);
            lottieAnimationView.setVisibility(View.VISIBLE);
            lottieAnimationView.playAnimation();
            lottieAnimationView2.setVisibility(View.INVISIBLE);
        }
        else if(checkIfTied())
        {
            visibility();
            textViewResult.setText(R.string.tie);
            lottieAnimationView2.setVisibility(View.VISIBLE);
            lottieAnimationView2.playAnimation();
            lottieAnimationView.setVisibility(View.INVISIBLE);
        }
    }

    //Visibilidad de las segunda pantalla
    private void visibility()
    {
        textViewP1.setVisibility(View.GONE);
        textViewP2.setVisibility(View.GONE);
        constraintLayout.setVisibility(View.VISIBLE);
    }

    //Revisamos cada posible manera de ganar
    private void check(int begin, int intermediate, int end)
    {
        int rest = intermediate-begin;
        for(int i = begin; i < end+1; i+=rest)
        {
            p1Win = belongs[i];
            if(!belongs[i])
                break;
        }

        if(!p1Win)
        {
            for(int i = begin; i < end+1; i+=rest)
            {
                if(!belongs[i])
                    p2Win = true;
                else
                {
                    p2Win = false;
                    break;
                }
            }
        }
    }

    //Revisamos si empataron
    private boolean checkIfTied()
    {
        return (timesP1 + timesP2) == 9 && !p1Win && !p2Win;
    }

    //Limpiamos la pantalla para que vuelvan a jugar
    public void clean(View view)
    {
        timesP1 = 0;
        timesP2 = 0;
        p1Win = false;
        p2Win = false;
        turn = true;
        belongs = new boolean[9];
        setup();
        setUpAvailableBox();
        constraintLayout.setVisibility(View.GONE);
    }

    public void exit(View view)
    {
        finish();
    }
}