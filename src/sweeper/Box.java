package sweeper; //класс перечислений

public enum Box {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;

    public Object Image; //хранение картинки

    Box getNextNumberBox()
    {
        return Box.values()[this.ordinal()+1]; //берем из массива элемент со следующим номером
    }

    int getNumber ()
    {
        return this.ordinal();
    }

}
