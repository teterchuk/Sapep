package sweeper;

import java.util.Optional;

class Flag
{
    private Matrix flagMap; //матрица флагов
    private int countOfClosedBoxes; //количество закрытых боксов

    void start () //запуск
    {
        flagMap = new Matrix(Box.CLOSED); //инициализация матрицы на закрытые ячейки
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y; //сначала все клетки закрытые
    }

    Box get (Cord cord) //геттер возвращение координаты
    {
        return flagMap.get(cord);
    }

    void setOpenedToBox(Cord cord)
    {
        flagMap.set(cord, Box.OPENED); //установили в клетку открытие
        countOfClosedBoxes--; //при открытии количесвто уменьшается
    }

    void toggleFlagedToBox(Cord cord)
    {
        switch (flagMap.get(cord))
        {
            case FLAGED : setClosedToBox (cord); break;
            case CLOSED : setFlagedToBox (cord); break;
        }
    }

    void setClosedToBox(Cord cord)
    {
        flagMap.set(cord, Box.CLOSED);
    }

    private void setFlagedToBox(Cord cord)
    {
        flagMap.set(cord, Box.FLAGED); //установили в клетку открытие
    }

    int getCountOfClosedBoxes()
    {
    return countOfClosedBoxes;
    }

    void setBombedToBox (Cord cord)
    {
        flagMap.set(cord, Box.BOMBED);
    }

    void setNobombToFlagedSafeBox(Cord cord)
    {
    if (flagMap.get(cord) == Box.FLAGED )//если закрыта-открыть
        flagMap.set(cord, Box.NOBOMB);
    }

    void setOpenedToClosedBomBox(Cord cord)
    {
     if (flagMap.get(cord) == Box.CLOSED )//если клетка помечена - бомбы нет
        flagMap.set(cord, Box.OPENED);
    }

    int getCountOfFlagedBoxesAround(Cord cord) //открывает закрытые ячейки вокург числа
    {
    int count = 0;
    for (Cord around: Ranges.getCordsAround(cord))
        if (flagMap.get(around) == Box.FLAGED);
            count ++;
    return count;
    }

}
