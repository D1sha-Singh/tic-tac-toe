package game;

/**
 *
 * @author Asus
 */
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener{
    /**
     * @param args the command line arguments
     */
    public static int BOARD_SIZE=3;
    public static enum GameStatus{
            INCOMPLETE, XWINS, OWINS, TIE };
    private JButton[][] buttons=new JButton[BOARD_SIZE][BOARD_SIZE];
    boolean crossTurn=true;
    
    public Game()
    {
        super.setTitle("Tic Tac Toe");
        super.setSize(800,800);
        GridLayout grid=new GridLayout(BOARD_SIZE,BOARD_SIZE);
        super.setLayout(grid);
        super.setResizable(false);
        Font font=new Font("Comic Sans",1,150);
        for(int i=0;i<BOARD_SIZE;i++)
        {
            for(int j=0;j<BOARD_SIZE;j++)
            {
                JButton button=new JButton("");
                buttons[i][j]=button;
                button.setFont(font);
                button.addActionListener(this);
                super.add(button);
            }
        }
        super.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton clickedButton=(JButton)e.getSource();
        makeMove(clickedButton);
        GameStatus gs=this.getGameStatus();
        if(gs==GameStatus.INCOMPLETE)
            return;
        declareWinner(gs);
        int ch=JOptionPane.showConfirmDialog(this,"Do you want to replay?");
        if(ch==JOptionPane.YES_OPTION)
        {
            for(int i=0;i<BOARD_SIZE;i++)
            {
                for(int j=0;j<BOARD_SIZE;j++)
                {
                    buttons[i][j].setText("");
                }
            }
                crossTurn=true;
        }
            else{
                    super.dispose();
                    }
    }
    private void makeMove(JButton clickedButton)
    {
        String btntext=clickedButton.getText();
        if(btntext.length()>0){
            JOptionPane.showMessageDialog(this,"Invalid Move");
        }
        else{
            if(crossTurn){
                clickedButton.setText("X");
            }
            else
            {  clickedButton.setText("O");
            }
            crossTurn=!crossTurn;
        }
    }
    private GameStatus getGameStatus()
    {
        String t1="",t2="";
        int r=0,c=0;
        r=0;
        while(r<BOARD_SIZE){
            c=0;
            while(c<BOARD_SIZE-1){
                t1=buttons[r][c].getText();
                t2=buttons[r][c+1].getText();
                if(!t1.equals(t2)|| t1.length()==0)
                    break;
            }
            c++;
            if(c==BOARD_SIZE-1)
            {
                if(t1.equals("X"))
                        return GameStatus.XWINS;
                else
                    return GameStatus.OWINS;
            }
            r++;
        }
        c=0;
        while(c<BOARD_SIZE){
            r=0;
            while(r<BOARD_SIZE-1){
                t1=buttons[r][c].getText();
                t2=buttons[r+1][c].getText();
                if(!t1.equals(t2)|| t1.length()==0)
                    break;
            }
            r++;
            if(r==BOARD_SIZE-1)
            {
                if(t1.equals("X"))
                        return GameStatus.XWINS;
                else
                    return GameStatus.OWINS;
            }
            c++;
        }
        r=0;
        c=0;
        while(r<BOARD_SIZE-1){
                t1=buttons[r][c].getText();
                t2=buttons[r+1][c+1].getText();
                if(!t1.equals(t2)|| t1.length()==0)
                    break;
            }
            r++;
            c++;
            if(r==BOARD_SIZE-1)
            {
                if(t1.equals("X"))
                        return GameStatus.XWINS;
                else
                    return GameStatus.OWINS;
            }
            r=BOARD_SIZE-1;
            c=0;
        while(r>0){
                t1=buttons[r][c].getText();
                t2=buttons[r-1][c+1].getText();
                if(!t1.equals(t2)|| t1.length()==0)
                    break;
            }
            r--;
            c++;
            if(r==0)
            {
                if(t1.equals("X"))
                        return GameStatus.XWINS;
                else
                    return GameStatus.OWINS;
            }
            String text="";
            for(int i=0;i<BOARD_SIZE;i++)
            {
                for(int j=0;j<BOARD_SIZE;j++)
                {
                    text=buttons[i][j].getText();
                    if(text.length()==0)
                        return GameStatus.INCOMPLETE;
                }
            }
            return GameStatus.TIE;
    }
    private void declareWinner(GameStatus gs)
    {
        if(gs==GameStatus.XWINS)
            JOptionPane.showMessageDialog(this,"XWins");
        else if(gs==GameStatus.OWINS)
            JOptionPane.showMessageDialog(this, "OWins");
        else
            JOptionPane.showMessageDialog(this, "Tie");
        
    }
}
