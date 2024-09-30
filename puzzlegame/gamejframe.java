package puzzlegame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class gamejframe extends JFrame implements KeyListener, ActionListener {


    String photos="puzzle\\puzzle game";
    int photo[][] = new int[4][4];
    int end[][] = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    int x, y;

    int count2 = 0;

    //初始化菜单


    JMenuItem replay = new JMenuItem("重新游戏");
    JMenuItem closegame = new JMenuItem("关闭游戏");

    JMenuItem account = new JMenuItem("公众号");



    public gamejframe() {

        initjframe();//初始化界面

        initjmenubar();//初始化菜单

        initdate();//初始化数据

        initImage();//往界面插入图片

        //让界面可见
        this.setVisible(true);
    }

    private void initdate() {

        int arr[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < 16; i++) {
            int temp1 = r.nextInt(arr.length);
            int temp2 = arr[i];
            arr[i] = arr[temp1];
            arr[temp1] = temp2;
        }


        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[count] == 0) {
                    x = count / 4;
                    y = count % 4;
                }
                    photo[i][j] = arr[count];
                    count++;

            }
        }


    }


    private void initImage() {

        this.getContentPane().removeAll();
       if (iswin() == true) // 胜利背景的插入
       {
           JLabel win = new JLabel(new ImageIcon("C:\\Users\\刘建安\\puzzle\\puzzle game\\100.jpeg"));
           win.setBounds(400, 400, 500, 342);
           this.getContentPane().add(win);
       }

        JLabel step = new JLabel("步数:" + count2);
        step.setBounds(20, 20, 50, 50);
        this.getContentPane().add(step);

        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 4; i++) {
                JLabel label = new JLabel(new ImageIcon("C:\\Users\\刘建安\\puzzle\\puzzle game\\妹妹\\"+photo[j][i]+".jpg"));
                label.setBounds(90 * i + 100, 159 * j + 100, 90, 159);
                this.getContentPane().add(label);
                label.setBorder(new BevelBorder(1));


            }
        //插入背景图片
        JLabel label2 = new JLabel(new ImageIcon("C:\\Users\\刘建安\\puzzle\\puzzle game\\18.jpeg"));
        label2.setBounds(0, 0, 1000, 800);
        this.getContentPane().add(label2);

        this.getContentPane().repaint();
    }

    private void initjmenubar() {

        JMenuBar menu = new JMenuBar();

        JMenu function = new JMenu("功能");
        JMenu aboutus = new JMenu("关于我们");
        //将项目添加到菜单
        function.add(replay);
        function.add(closegame);
        aboutus.add(account);

        menu.add(function);
        menu.add(aboutus);

        //给项目绑定事件
        replay.addActionListener(this);
        closegame.addActionListener(this);
        account.addActionListener(this);

        //将菜单添加到界面
        this.setJMenuBar(menu);
    }

    private void initjframe() {
        //设置大小
        this.setSize(1000, 800);

        this.setTitle("拼图游戏");
        //设置置顶
        this.setAlwaysOnTop(true);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //设置居中
        this.setLocationRelativeTo(null);
        //取消界面输入图片时默认居中的模式
        this.setLayout(null);

        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int temp = e.getKeyCode();
        if (temp == 65)//   65 : 表示a
        {

            this.getContentPane().removeAll();
            JLabel wholephoto = new JLabel(new ImageIcon("C:\\Users\\刘建安\\puzzle\\puzzle game\\妹妹\\16.jpg"));
            wholephoto.setBounds(100, 100, 360, 638);
            this.getContentPane().add(wholephoto);

            JLabel background = new JLabel(new ImageIcon("C:\\\\Users\\\\刘建安\\\\puzzle\\\\puzzle game\\\\18.jpeg"));
            background.setBounds(0, 0, 1000, 800);
            this.getContentPane().add(background);


            this.getContentPane().repaint();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
//左：37   上：38    右：39   下：40
        int temp = e.getKeyCode();
        if (iswin() == true) {
            return;
        }

        if (temp == 40) {
            if (x == 3) {
                return;
            } else {
                photo[x][y] = photo[x + 1][y];
                photo[x + 1][y] = 0;
                x++;
                count2++;
            }
        } else if (temp == 38) {
            if (x == 0) {
                return;
            } else {
                photo[x][y] = photo[x - 1][y];
                photo[x - 1][y] = 0;
                x--;
                count2++;
            }
        } else if (temp == 37) {
            if (y == 0) {
                return;
            } else {
                photo[x][y] = photo[x][y - 1];
                photo[x][y - 1] = 0;
                y--;
                count2++;
            }
        } else if (temp == 39) {

            if (y == 3) {
                return;
            } else {
                photo[x][y] = photo[x][y + 1];
                photo[x][y + 1] = 0;
                y++;
                count2++;
            }
        } else if (temp == 65)//判断键盘录入是否为： a
        {
            initImage();
        } else if (temp == 87) {
            photo = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
        initImage();
    }


    public Boolean iswin() {
        int count = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if (photo[j][i] == end[j][i]) {
                    count++;// return false;
                }
            }
        }
        if (count >= 14) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();
        if (obj == replay) {

                count2=0;
               initdate();
                initImage();
        }
        else if(obj==closegame)
        {
            System.exit(0);
        }
        else if (obj==account)
        {

        }
    }
}



