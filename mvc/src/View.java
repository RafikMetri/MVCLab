import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created with IntelliJ IDEA.
 * User: rmetri
 * Date: 11/17/13
 * Time: 10:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class View extends JPanel implements PropertyChangeListener, ActionListener{
    private PropertyChangeSupport changer = new PropertyChangeSupport(this);
    private Model model = new Model();
    private JTextField textFieldSetup, textFieldGame;
    private JLabel answerLabel, guessLabel;
    private int result;

    public View(){
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panel1 = new JPanel();
        panel1.setSize(new Dimension(300, 250));
        JPanel panel2 = new JPanel();
        panel2.setSize(new Dimension(300, 250));

        textFieldSetup = getTextFieldSetup();
        textFieldSetup.setPreferredSize(new Dimension(100, 18));
        textFieldSetup.addPropertyChangeListener("value", this);

        JButton setButton = new JButton("Set");
        setButton.setPreferredSize(new Dimension(60, 18));
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setGuesses(0);
                guessLabel.setText("Number of guesses: " + model.getGuesses());
            }
        });

        panel1.add(textFieldSetup);
        panel1.add(setButton);

        guessLabel = new JLabel("Number of guesses: " + model.getGuesses());
        answerLabel = new JLabel("Your guess was: ");

        textFieldGame = new JTextField("Enter your guess here");
        textFieldGame.setPreferredSize(new Dimension(150, 18));

        JButton guessButton = new JButton("Guess");
        guessButton.setPreferredSize(new Dimension(75, 18));
        guessButton.addActionListener(this);

        panel2.add(guessLabel);
        panel2.add(textFieldGame);
        panel2.add(guessButton);
        panel2.add(answerLabel);

        tabbedPane.addTab("Setup", null, panel1, "Displays the setup");
        tabbedPane.addTab("View", null, panel2, "Displays the game");
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        add(tabbedPane);
    }

    public void guess(int _guess){
        result = model.guess(_guess);
        answerLabel.setText("Your guess was: " + result);
        guessLabel.setText("Number of guesses: " + model.getGuesses());
    }

    public JTextField getTextFieldSetup(){
        if(textFieldSetup == null){
            textFieldSetup = new JTextField(15);
            textFieldSetup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        model.setNumber(Integer.parseInt(textFieldSetup.getText()));
                        System.out.println("new number: " + model.getNumber());
                    }
                    catch(Exception ex){
                        System.out.println(ex.toString());
                    }
                }
            });
        }
        return textFieldSetup;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getNewValue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        guess(Integer.parseInt(textFieldGame.getText()));

        answerLabel.setText("Your guess was: " + result);
        guessLabel.setText("Number of guesses: " + model.getGuesses());
    }
}
