import javax.swing.*; //библиотека для JFrame
import java.awt.*;
import sweeper.Box;
import sweeper.Cord;
import sweeper.Game;
import sweeper.Ranges;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

public class JavaSweeper extends JFrame
{
    private Game game;
    private JPanel panel; //добавление панели
    private JLabel label;
    private final int COLS = 9; //константа кол-во столбцов
    private final int ROWS = 9; //строк
    private final int IMAGE_SIZE = 50; //размер одной картинки 50*50
    private final int BOMBS =10;
    public static void main(String[] args)
    {
        new JavaSweeper(); //создание экземпляра
    }
    private JavaSweeper () //конструктор
    {
        game = new Game(COLS, ROWS, BOMBS); //экзамеляр игры
        game.start();
        setImages();
        initLabel();
        initPanel(); //инициализация панели
        initFrame(); //вызов инициализаций фрэйма
    }

    private void initLabel() //добавление метки игры на экран
    {
        label = new JLabel("Teterchuk 2A");
        add (label, BorderLayout.SOUTH);
    }

    private void initPanel() //метод инициализации панели
    {
        panel = new JPanel() //создаем панель
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for(Cord cord : Ranges.getAllCords()) //Перебор всех координат по полю
                    g.drawImage((Image) game.getBox(cord).Image, //обращение к Game к нужным координатам
                            cord.x * IMAGE_SIZE, cord.y * IMAGE_SIZE, this);
            }
        };

        panel.addMouseListener(new MouseAdapter() //мышечный адаптер
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Cord cord = new Cord(x,y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(cord);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(cord);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMassage()); //вывод сообщений
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE)); //устанавливаем размер поля
        add (panel); //добавляяем панель на форму
    }

    private String getMassage() //функция возвращающая отображающуюся строку в зависимости от состояния игры
    {
        switch (game.getState())
        {
            case PLAYED: return "Try to win!";
            case BOMBED: return "Oops!";
            case WINNER: return "You are cool!";
            default: return "Welcome";
        }
    }

    private void initFrame () //функция
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //закрытие программы по умолчанию
        setTitle("Java Sweeper"); //установка заголовка
        setResizable(false); //нельзя изменять размер окна
        setVisible(true); // чтобы форму было видно
        setIconImage(getImage("pyat")); //установление иконки на окно
        pack(); //метод устанавляивает такой минимальный размер контейнера, который достаточек для отображения всех комане
        setLocationRelativeTo(null); //установка окошка по центру
    }

    private void setImages() //функция установки картинок
    {
        for(Box box : Box.values()) //цикл, перебирающий все картинки
            box.Image = getImage(box.name().toLowerCase()); //имена элементов бокса совпадают с картинками
    }

    private Image getImage (String name) //функция получения картинок
    {
        String filename = "img/" + name + ".png"; //имя файла для каждой картинки
        ImageIcon icon = new ImageIcon(getClass().getResource(filename)); //получаем доступ к файлу
        return icon.getImage(); //возврат картинки
    }

}
