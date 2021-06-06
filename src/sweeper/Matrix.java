package sweeper;

class Matrix
{
    private Box [] [] matrix; //массив
    Matrix(Box defaultBox) //конструктор
    {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y]; //создаем матрицу, заполненную нужными элементами
        for (Cord cord : Ranges.getAllCords())
            matrix[cord.x][cord.y] = defaultBox;
    }

    Box get (Cord cord) //геттер
    {
        if (Ranges.inRange (cord)) //проверка на переполнение
            return matrix[cord.x][cord.y];
        return null;
    }

    void set (Cord cord, Box box) //сеттер
    {
        if (Ranges.inRange (cord))
            matrix[cord.x][cord.y] = box;
    }
}
