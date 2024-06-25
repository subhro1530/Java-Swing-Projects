package DevChat;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class DevChat extends JFrame implements ActionListener {

    private JPanel messagesPanel;
    private JPanel messageContainer;
    private JPanel currentChatPanel;
    private JLabel currentFriendLabel;
    private JTextField inputField;
    private JButton sendButton;
    private String currentFriend;

    public DevChat() {
        initComponents();
        setTitle("DevChat");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Favicon
        ImageIcon logoIcon = new ImageIcon("D://Downloads//short.png");
        setIconImage(logoIcon.getImage());
        setVisible(true);
    }

    private void initComponents() {
        // Main panel to hold the left, right, and bottom panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);
        getContentPane().add(mainPanel);

        // Left panel for history
        messagesPanel = new JPanel(new BorderLayout());
        messagesPanel.setBackground(Color.DARK_GRAY);
        mainPanel.add(messagesPanel, BorderLayout.CENTER);

        currentChatPanel = new JPanel(new BorderLayout());
        currentChatPanel.setBackground(Color.DARK_GRAY);
        messagesPanel.add(currentChatPanel, BorderLayout.CENTER);

        // Message container
        messageContainer = new JPanel();
        messageContainer.setLayout(new BoxLayout(messageContainer, BoxLayout.Y_AXIS));
        messageContainer.setBackground(new Color(45, 45, 45)); // Set background color
        currentChatPanel.add(new JScrollPane(messageContainer), BorderLayout.CENTER);

        // Right panel for friends list
        JPanel friendsPanel = new JPanel();
        friendsPanel.setBackground(Color.DARK_GRAY);
        friendsPanel.setPreferredSize(new Dimension(200, 0));
        mainPanel.add(new JScrollPane(friendsPanel), BorderLayout.EAST);

        // Friends
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        friendsPanel.add(createFriendLabel("Rima Rai"));
        friendsPanel.add(createFriendLabel("Suman Ghosh"));
        friendsPanel.add(createFriendLabel("Rohini Kundu"));

        // Bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.DARK_GRAY);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        inputField = new JTextField();
        inputField.setBackground(Color.WHITE);
        inputField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(inputField, BorderLayout.CENTER);

        sendButton = new JButton("Send");
        sendButton.setBackground(new Color(0, 128, 0)); // WhatsApp-like green color
        sendButton.setForeground(Color.WHITE);
        sendButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        bottomPanel.add(sendButton, BorderLayout.EAST);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messageText = inputField.getText();
                if (!messageText.isEmpty() && currentFriend != null) {
                    sendMessage("Me", messageText);
                    inputField.setText("");
                }
            }
        });
    }

    private JLabel createFriendLabel(String friendName) {
        JLabel friendLabel = new JLabel(friendName);
        friendLabel.setForeground(Color.WHITE);
        friendLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        friendLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        friendLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (currentFriendLabel != null) {
                    currentFriendLabel.setForeground(Color.WHITE); // Reset previous selected friend
                }
                currentFriendLabel = friendLabel;
                currentFriendLabel.setForeground(Color.GREEN); // Highlight selected friend
                currentFriend = friendName;
                showChatWith(friendName);
            }
        });
        return friendLabel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DevChat::new);
    }

    private void showChatWith(String friendName) {
        currentChatPanel.removeAll();

        // Add friend's name at the top
        JLabel friendNameLabel = new JLabel(friendName, SwingConstants.CENTER);
        friendNameLabel.setForeground(Color.WHITE);
        friendNameLabel.setBackground(new Color(0, 128, 0));
        friendNameLabel.setOpaque(true);
        friendNameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        currentChatPanel.add(friendNameLabel, BorderLayout.NORTH);

        currentChatPanel.add(new JScrollPane(messageContainer), BorderLayout.CENTER);
        currentChatPanel.revalidate();
        currentChatPanel.repaint();
    }

    // Method to send a message
    private void sendMessage(String sender, String message) {
        JPanel messageBubble = createMessageBubble(message, true, getTime());
        messageContainer.add(messageBubble);
        messageContainer.revalidate();
        messageContainer.repaint();
    }

    private JPanel createMessageBubble(String message, boolean sentByMe, String timeStamp) {
        JPanel bubble = new JPanel(new BorderLayout());
        bubble.setBackground(sentByMe ? new Color(0, 128, 0) : Color.WHITE);
        bubble.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        bubble.setAlignmentX(sentByMe ? Component.RIGHT_ALIGNMENT : Component.LEFT_ALIGNMENT);

        JLabel text = new JLabel("<html><body style='width: 200px;'>" + message + "<br><i style='font-size: 10px;'>" + timeStamp + "</i></body></html>");
        text.setForeground(sentByMe ? Color.WHITE : Color.BLACK);
        bubble.add(text, BorderLayout.CENTER);

        return bubble;
    }

    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Action performed logic
    }
}
