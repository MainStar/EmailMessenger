package exemple.Window;

import exemple.Send;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

    JPanel mainPanel = new JPanel();
    JPanel secondPanel = new JPanel();

    JLabel labelTo = new JLabel("Емейл получателя");
    JLabel labelFrom = new JLabel("Емейл отправителя");
    JLabel labelSubject = new JLabel("Тема письма");
    JLabel labelPassword = new JLabel("Пароль от почты: ");
    JLabel labelLetterStatus = new JLabel();
    JLabel labelBack = new JLabel("Для повторной отправки, нажмите здесь");

    JPasswordField password = new JPasswordField(50);
    JTextField textTo = new JTextField(70);
    JTextField textFrom = new JTextField(70);
    JTextField textSubject = new JTextField(70);

    JTextArea textArea = new JTextArea(27, 70);
    JScrollPane scrollPane = new JScrollPane(textArea);

    JButton buttonSend= new JButton("Send");

    private String from;
    private String to;
    private String password2;
    private String text;
    private String subject;

    public void mainFrame(){

        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setName("Email Messenger");

        buttonSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                sendEmail();
            }
        });

        add(mainPanel);

        mainPanel.add(labelFrom);
        mainPanel.add(textFrom);
        mainPanel.add(labelTo);
        mainPanel.add(textTo);
        mainPanel.add(labelSubject);
        mainPanel.add(textSubject);
        mainPanel.add(scrollPane);
        mainPanel.add(labelPassword);
        mainPanel.add(password);
        mainPanel.add(buttonSend);

        secondPanel.add(labelLetterStatus);

    }

    public void sendEmail(){

        from = textFrom.getText().toString();
        to = textTo.getText().toString();
        password2 = password.getText().toString();
        text = textArea.getText().toString();
        subject = textSubject.getText().toString();

        System.out.println(from + " " + to + " " + password2 + " ");

        Send send = new Send(from, password2);
        String check = send.send(subject, text, from, to);

        if (check != null) {
            if (check == "true") {
                mainPanel.setVisible(false);
                add(secondPanel);

                labelLetterStatus.setText("Письмо успешно отправленно!");
                labelLetterStatus.setForeground(Color.GREEN);
            } else {
                mainPanel.setVisible(false);
                add(secondPanel);

                labelLetterStatus.setText("Письмо не отправленно. Проверьте правильность ввода данных!               ");
                labelLetterStatus.setForeground(Color.RED);

                labelBack.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {

                        secondPanel.setVisible(false);
                        mainPanel.setVisible(true);

                    }
                });

                secondPanel.add(labelBack);
            }
        }
    }
}
