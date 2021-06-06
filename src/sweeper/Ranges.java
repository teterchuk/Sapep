package sweeper;

import javax.naming.PartialResultException;
import java.util.ArrayList;
import java.util.Random;

public class Ranges
{

    private static Cord size; //координаты размер экрана
    private static ArrayList<Cord> allCords; //поле ля всех координат
    private static Random random = new Random(); //поле генератора случайных чисел

    static void setSize(Cord _size) //функция установки размера (сеттер)
    {
        size = _size;
        allCords = new ArrayList<Cord>(); //заполняем координаты
        for(int y = 0; y < size.y; y ++ )
            for (int x = 0; x < size.x; x ++)
                allCords.add(new Cord(x, y));
    }

    public static Cord getSize() //сгенерированный геттер
    {
    return size;
    }

    public static ArrayList<Cord> getAllCords () //сеттер для возвращения всех координат
    {
        return allCords;
    }

    static boolean inRange (Cord cord)
    {
        return cord.x >= 0 && cord.x < size.x //находится ли координата в наших границах
                &&  cord.y >= 0 && cord.y < size.y;
    }

    static Cord getRandomCorde()
    {
    return new Cord(random.nextInt(size.x),//вернуть случайную координату
                    random.nextInt(size.y));
    }

    static ArrayList<Cord> getCordsAround(Cord cord) //перебор всех клеток вокруг одной клетки
    {
        Cord arround;
        ArrayList<Cord> list = new ArrayList<Cord>();
        for (int x = cord.x - 1; x <= cord.x + 1; x++) //перебираем клетки вокруг
            for (int y = cord.y - 1; y <= cord.y + 1; y++)
                if (inRange(arround = new Cord(x,y)))
                    if (!arround.equals(cord))
                        list.add(arround);
        return list;
    }
}
