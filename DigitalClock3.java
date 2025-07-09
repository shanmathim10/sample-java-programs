import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock3 extends JFrame {

    private JLabel timeLabel, dateLabel;
    private JButton toggleThemeButton, colorPickerButton;
    private boolean darkMode = false;
    private JPanel panel;

    public DigitalClock3() {
        setTitle("Digital Clock");
        setSize(500, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 2));
        buttonPanel.setMaximumSize(new Dimension(300, 40));

        toggleThemeButton = new JButton("Dark Mode");
        toggleThemeButton.setFont(new Font("Verdana", Font.PLAIN, 11));
        toggleThemeButton.setPreferredSize(new Dimension(110, 25));
        toggleThemeButton.addActionListener(this::toggleTheme);

        colorPickerButton = new JButton("Set Background");
        colorPickerButton.setFont(new Font("Verdana", Font.PLAIN, 11));
        colorPickerButton.setPreferredSize(new Dimension(130, 25));
        colorPickerButton.addActionListener(this::chooseBackgroundColor);

        buttonPanel.add(toggleThemeButton);
        buttonPanel.add(colorPickerButton);

        panel.add(timeLabel);
        panel.add(Box.createVerticalStrut(5)); // small spacing
        panel.add(dateLabel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(buttonPanel);

        add(panel);
        startClock();
        updateTheme();

        setVisible(true);
    }

    private void startClock() {
        Timer timer = new Timer(1000, e -> {
            Date now = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");

            timeLabel.setText(timeFormat.format(now));
            dateLabel.setText(dateFormat.format(now));
        });
        timer.start();
    }

    private void toggleTheme(ActionEvent e) {
        darkMode = !darkMode;
        toggleThemeButton.setText(darkMode ? "Light Mode" : "Dark Mode");
        updateTheme();
    }

    private void updateTheme() {
        Color fgColor = darkMode ? Color.GREEN : Color.BLACK;
        timeLabel.setForeground(fgColor);
        dateLabel.setForeground(fgColor);
        toggleThemeButton.setForeground(fgColor);
        colorPickerButton.setForeground(fgColor);
    }

    private void chooseBackgroundColor(ActionEvent e) {
        Color selectedColor = JColorChooser.showDialog(this, "Select Background Color", panel.getBackground());
        if (selectedColor != null) {
            panel.setBackground(selectedColor);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DigitalClock3::new);
    }
}
