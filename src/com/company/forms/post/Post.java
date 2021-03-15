/*
 * Created by JFormDesigner on Fri Mar 12 18:28:00 EET 2021
 */

package com.company.forms.post;

import java.awt.event.*;
import java.time.Instant;
import java.util.Date;
import javax.swing.*;

import com.company.dto.PostDTO;
import com.company.dto.UserDTO;
import com.company.service.PostService;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class Post extends JFrame {

    private final UserDTO user;

    public Post(UserDTO userDTO) {
        initComponents();
        this.user = userDTO;
        nameLabel.setText(userDTO.getFirstName() + " " + userDTO.getLastName());
    }

    private void addPostButtonActionPerformed(ActionEvent e) {
        PostDTO postDTO = new PostDTO();
        postDTO.setUserId(user.getUserId());
        postDTO.setPostText(postTextArea.getText());
        postDTO.setPostDate(Date.from(Instant.now()));
        PostService.getInstance().addPost(postDTO);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        backLabel = new JLabel();
        nameLabel = new JLabel();
        postScrollPanel = new JScrollPane();
        postTextArea = new JTextArea();
        addPostButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- backLabel ----
        backLabel.setText("Back");
        contentPane.add(backLabel, "cell 0 0 2 1");
        contentPane.add(nameLabel, "cell 16 0 4 1");

        //======== postScrollPanel ========
        {

            //---- postTextArea ----
            postTextArea.setRows(4);
            postScrollPanel.setViewportView(postTextArea);
        }
        contentPane.add(postScrollPanel, "cell 1 1 18 4");

        //---- addPostButton ----
        addPostButton.setText("Add post");
        addPostButton.addActionListener(e -> addPostButtonActionPerformed(e));
        contentPane.add(addPostButton, "cell 16 5");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel backLabel;
    private JLabel nameLabel;
    private JScrollPane postScrollPanel;
    private JTextArea postTextArea;
    private JButton addPostButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
