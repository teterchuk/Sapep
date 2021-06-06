package sweeper;

public class Game
{
    private Bomb bomb;
    private Flag flag;

    public GameState getState()
    {
        return state;
    }

    private GameState state;

    public Game (int cols, int rows, int bombs) //констурктор
    {
        Ranges.setSize(new Cord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start ()
    {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }

    public Box getBox (Cord cord) //если верхний слой открыт -то показываем его, иначе - нижний
    {
        if (flag.get(cord) == Box.OPENED)
            return bomb.get(cord);
        else
            return flag.get(cord); //возвращение верхнего слоя
    }

    public void pressLeftButton(Cord cord)
    {
        if (gameOver()) return;
        openBox (cord); //вызов рекрусивной функции
        checkWinner();
    }

    private void checkWinner() //проверка на победу
    {
        if (state == GameState.PLAYED)
            if(flag.getCountOfClosedBoxes() == bomb.getTotalBombs()) //если количесвт закртых клеток равно числу бомб
                state = GameState.WINNER;
    }

    private void openBox(Cord cord)
    {
        switch (flag.get(cord))
        {
            case OPENED: /*setOpenedToClosedBoxesAroundNumber(cord);*/ return;
            case FLAGED: return;
            case CLOSED:
                switch (bomb.get(cord))
                {
                    case ZERO: openBoxesAround(cord); return;
                    case BOMB: openBombs(cord); return;
                    default  : flag.setOpenedToBox(cord); return;
                }
        }
    }

    private void setOpenedToClosedBoxesAroundNumber (Cord cord)
    {
        if (bomb.get(cord) != Box.BOMB)
            if (flag.getCountOfFlagedBoxesAround(cord) == bomb.get(cord).getNumber())
                for (Cord around : Ranges.getCordsAround(cord))
                    if (flag.get(around) == Box.CLOSED)
                        openBox(around);
    }

    private void openBombs(Cord bombed)
    {
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for(Cord cord: Ranges.getAllCords())
            if (bomb.get(cord) == Box.BOMB)
                flag.setOpenedToClosedBomBox(cord); //открыть боксы на закрытые клетки с бомбой
            else
                flag.setNobombToFlagedSafeBox(cord);
    }

    private void openBoxesAround(Cord cord)
    {
        flag.setOpenedToBox(cord);
        for (Cord around : Ranges.getCordsAround(cord))
            openBox(around);
    }

    public void pressRightButton(Cord cord)
    {
        if (gameOver()) return;
        flag.toggleFlagedToBox(cord); //установить открытие в клетку
    }

    private boolean gameOver()
    {
        if (state == GameState.PLAYED)
            return false;
        start();
        return true;
    }

}