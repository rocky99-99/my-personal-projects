package Projects.CookieClicker;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.plaf.DimensionUIResource;

    public class CookieClicker extends JFrame
    {   
        private JTextField counterTextField;
        public CookieClicker() 
        {
            super("Cookie Clicker");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setPreferredSize(new Dimension(600,800));
            pack();
            setResizable(false);
            setLocationRelativeTo(null);

            addGuiComponents();

            
        }

        private void addGuiComponents()
        {
            SpringLayout springLayout = new SpringLayout();
            JPanel jPanel = new JPanel();
            jPanel.setLayout(springLayout);

            // 1. Banner Image 

            JLabel bannerImage = loadImage("resources/banner.png", true, 450,100);
            jPanel.add(bannerImage);

            springLayout.putConstraint(SpringLayout.WEST, bannerImage, 60, SpringLayout.WEST, jPanel);
            springLayout.putConstraint(SpringLayout.NORTH, bannerImage, 45, SpringLayout.NORTH, jPanel);

            // 2. Cookie Button 
            JButton cookieButton = createCookieButton("resources/cookie.png");
            cookieButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    int counter = Integer.parseInt(counterTextField.getText());
                    counterTextField.setText(Integer.toString(++counter));
                   
                }
            });
            jPanel.add(cookieButton);

            springLayout.putConstraint(SpringLayout.WEST, cookieButton, 95, SpringLayout.WEST, jPanel);
            springLayout.putConstraint(SpringLayout.NORTH, cookieButton, 175, SpringLayout.NORTH, jPanel);

            // 3. Counter Label 
            JLabel counterLabel = new JLabel("Clicks");
            counterLabel.setFont(new Font("Dialog", Font.BOLD, 26));
            jPanel.add(counterLabel);

            springLayout.putConstraint(SpringLayout.WEST, counterLabel, 95, SpringLayout.WEST, jPanel);
            springLayout.putConstraint(SpringLayout.NORTH, counterLabel, 575, SpringLayout.NORTH, jPanel);

            // 4. Counter Text
            counterTextField = new JTextField(10);
            counterTextField.setText("0");
            counterTextField.setEditable(false);
            counterTextField.setFont(new Font("Dialog", Font.BOLD, 26));
            counterTextField.setHorizontalAlignment(SwingConstants.RIGHT);
            jPanel.add(counterTextField);

            springLayout.putConstraint(SpringLayout.WEST, counterTextField, 210, SpringLayout.WEST, jPanel);
            springLayout.putConstraint(SpringLayout.NORTH, counterTextField, 575, SpringLayout.NORTH, jPanel);

            this.getContentPane().add(jPanel);

            // 5 . Reset Button
            JButton resetButton = new JButton("Reset");
            resetButton.setFont(new Font("Dialog", Font.PLAIN, 16));
            jPanel.add(resetButton);

            springLayout.putConstraint(SpringLayout.WEST, resetButton, 250, SpringLayout.WEST, jPanel);
            springLayout.putConstraint(SpringLayout.NORTH, resetButton, 670, SpringLayout.NORTH, jPanel);

            resetButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    counterTextField.setText("0");
                }
                
            });


        }

        private JButton createCookieButton(String filename)
        {
            JButton button;
            try {
                InputStream inputStream = this.getClass().getResourceAsStream(filename);
                Image image = ImageIO.read(inputStream);
                button = new JButton(new ImageIcon(image));
                return button;
            } catch (Exception e) {
                System.out.println("Error: "+e);
                return null;
            }
        }
        private JLabel loadImage(String filename, boolean isResizable, int targetWidth, int targetHeight)
        {
            BufferedImage image;
            JLabel imageContainer;
            try {
                InputStream inputStream = this.getClass().getResourceAsStream(filename);
                image = ImageIO.read(inputStream);
                if(isResizable){
                    image = resizeImage(image, targetWidth, targetHeight);
                }
                imageContainer = new JLabel(new ImageIcon(image));
                return imageContainer;

            } catch (Exception e) {
                System.out.println("Error: "+e);
                return null;
            }
        }

        private BufferedImage resizeImage(BufferedImage image, int targetWidth, int targetHeight)
        {
            BufferedImage newImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2d = newImage.createGraphics();
            graphics2d.drawImage(image, 0, 0, targetWidth, targetHeight, null);
            graphics2d.dispose();
            return newImage;
        }

    }