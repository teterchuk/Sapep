package sweeper;

import java.util.SplittableRandom;

class Bomb
{
    private Matrix bombMap;
    private int totalBombs;

    Bomb (int totalBombs) //конструктор
    {
        this.totalBombs = totalBombs;
        fixBombCount();
    }

    void start ()
    {
        bombMap = new Matrix(Box.ZERO);
        for (int j = 0; j < totalBombs; j++)
            placeBomb();
    }

    Box get (Cord cord)
    {
        return bombMap.get(cord);
    }

    private void fixBombCount () //исправить количество бомб
    {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    private void placeBomb() //функция размещения одной бомбы
    {
        while (true)
        {
            Cord cord = Ranges.getRandomCorde();
            if (Box.BOMB == bombMap.get(cord))  //проверка наложения бомб друг на друга
                continue; //продолжаем вы полнение цикла
            bombMap.set(cord, Box.BOMB);
            incNumbersAroundBomb(cord);
            break;
        }
    }

    private void incNumbersAroundBomb (Cord cord) //увеличить числа вокруг бомбы
    {
        for (Cord arround : Ranges.getCordsAround(cord))
            if (Box.BOMB != bombMap.get(arround))
            bombMap.set(arround, bombMap.get(arround).getNextNumberBox()); //вокруг каждой бомбы единицы
    }

    int getTotalBombs()
    {
        return totalBombs;
    }

}
