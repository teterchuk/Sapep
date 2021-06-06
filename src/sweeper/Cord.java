package sweeper;

import java.util.Objects;

public class Cord
{
    public int x;
    public int y;

    public Cord (int x, int y) //конструктор
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Cord)
        {
            Cord to = (Cord) o; //приведение типа
            return to.x == x && to.y == y;
        }
        return super.equals(o);
    }
}

