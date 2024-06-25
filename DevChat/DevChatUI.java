package DevChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DevChatUI extends JFrame {

    private JPanel messagesPanel;
    private JPanel messageContainer;
    private JPanel currentChatPanel;
    private JLabel currentFriendLabel;

    public DevChatUI() {
        initComponents();
        setTitle("DevChat");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        // Main panel to hold left, right, and bottom panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);
        getContentPane().add(mainPanel);

        // Left panel for messages
        messagesPanel = new JPanel(new BorderLayout());
        messagesPanel.setBackground(Color.DARK_GRAY);
        mainPanel.add(messagesPanel, BorderLayout.CENTER);

        // Panel for current chat
        currentChatPanel = new JPanel(new BorderLayout());
        currentChatPanel.setBackground(Color.DARK_GRAY);
        messagesPanel.add(currentChatPanel, BorderLayout.CENTER);

        // Message container
        messageContainer = new JPanel();
        messageContainer.setLayout(new BoxLayout(messageContainer, BoxLayout.Y_AXIS));
        currentChatPanel.add(new JScrollPane(messageContainer), BorderLayout.CENTER);

        // Right panel for friends list
        JPanel friendsPanel = new JPanel();
        friendsPanel.setBackground(Color.DARK_GRAY);
        friendsPanel.setPreferredSize(new Dimension(200, 0));
        mainPanel.add(new JScrollPane(friendsPanel), BorderLayout.EAST);

        // Example friends (replace with actual list component)
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        friendsPanel.add(createFriendLabel("Friend 1"));
        friendsPanel.add(createFriendLabel("Friend 2"));
        friendsPanel.add(createFriendLabel("Friend 3"));

        // Bottom panel for input box and send button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.DARK_GRAY);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        JTextField inputField = new JTextField();
        inputField.setBackground(Color.WHITE);
        inputField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(inputField, BorderLayout.CENTER);

        JButton sendButton = new JButton("Send");
        sendButton.setBackground(Color.GRAY);
        sendButton.setForeground(Color.WHITE);
        sendButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        bottomPanel.add(sendButton, BorderLayout.EAST);

        // Action listener for send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messageText = inputField.getText();
                if (!messageText.isEmpty()) {
                    sendMessage("Me", messageText);
                    inputField.setText("");
                }
            }
        });
    }

    // Method to create friend label with click listener
    private JLabel createFriendLabel(String friendName) {
        JLabel friendLabel = new JLabel(friendName);
        friendLabel.setForeground(Color.WHITE);
        friendLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        friendLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        friendLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                currentFriendLabel = friendLabel;
                showChatWith(friendName);
            }
        });
        return friendLabel;
    }

    // Method to show chat with a friend
    private void showChatWith(String friendName) {
        currentChatPanel.removeAll();
        currentChatPanel.revalidate();
        currentChatPanel.repaint();

        currentFriendLabel.setForeground(Color.GREEN); // Highlight selected friend (optional)

        // Create a panel for showing messages
        JPanel chatPanel = new JPanel();
        chatPanel.setBackground(Color.DARK_GRAY);
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

        // Example messages (replace with actual messages)
        chatPanel.add(createMessageBubble("Hello!", false, getTime()));
        chatPanel.add(createMessageBubble("Hi there!", true, getTime()));

        JScrollPane scrollPane = new JScrollPane(chatPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Adjust scroll speed

        currentChatPanel.add(scrollPane, BorderLayout.CENTER);
        currentChatPanel.revalidate();
        currentChatPanel.repaint();
    }

    // Method to create message bubble
    private JPanel createMessageBubble(String message, boolean sentByMe, String timeStamp) {
        JPanel bubble = new JPanel(new BorderLayout());
        bubble.setBackground(sentByMe ? Color.GRAY.brighter() : Color.WHITE);
        bubble.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        bubble.setAlignmentX(sentByMe ? Component.RIGHT_ALIGNMENT : Component.LEFT_ALIGNMENT);

        JLabel text = new JLabel("<html><body style='width: 200px;'>" + message + "<br>" + timeStamp + "</body></html>");
        text.setForeground(sentByMe ? Color.WHITE : Color.BLACK);
        bubble.add(text, BorderLayout.CENTER);

        return bubble;
    }

    // Method to send a message
    private void sendMessage(String sender, String message) {
        JPanel messageBubble = createMessageBubble(message, true, getTime());
        messageContainer.add(messageBubble);
        messageContainer.revalidate();
        messageContainer.repaint();
    }

    // Method to get current time
    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DevChatUI::new);
    }
}
