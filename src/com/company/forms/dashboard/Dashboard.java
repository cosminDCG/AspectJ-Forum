/*
 * Created by JFormDesigner on Wed Mar 10 23:13:58 EET 2021
 */

package com.company.forms.dashboard;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;

import com.company.forms.login.Login;
import com.company.forms.post.Post;
import com.company.dto.PostDTO;
import com.company.dto.UserDTO;
import com.company.forms.view.ViewPost;
import com.company.service.PostService;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class Dashboard extends JFrame {

    private final UserDTO user;
    private final List<PostDTO> postDTOList;

    public Dashboard(UserDTO userDTO) {
        initComponents();
        this.user = userDTO;
        this.postDTOList = PostService.getInstance().getAllPosts();
        addPosts();
        nameLabel.setText(userDTO.getFirstName() + " " + userDTO.getLastName());
    }

    private void addPosts() {
        int postIndex = 2;
        for (PostDTO postDTO : postDTOList) {
            JTextArea jta = new JTextArea(postDTO.getPostText());
            jta.setEditable(false);
            jta.setRows(3);
            jta.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    viewPostMouseClicked(e, postDTO);
                }
            });
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setViewportView(jta);
            getContentPane().add(jScrollPane, "cell 1 " + postIndex + " 25 4");
            postIndex += 5;
        }
    }

    private void viewPostMouseClicked(MouseEvent e, PostDTO postDTO) {
        JFrame viewPostFrame = new ViewPost(user, postDTO);
        viewPostFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewPostFrame.setSize(400, 400);
        viewPostFrame.setVisible(true);
        this.dispose();
    }

    private void newPostLabelMouseClicked(MouseEvent e) {
        JFrame postFrame = new Post(user);
        postFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        postFrame.setSize(400, 400);
        postFrame.setVisible(true);
        this.dispose();
    }

    private void logoutLabelMouseClicked(MouseEvent e) {
        JFrame loginFrame = new Login();
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        nameLabel = new JLabel();
        newPostLabel = new JLabel();
        logoutLabel = new JLabel();

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
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));
        contentPane.add(nameLabel, "cell 0 0 3 1");

        //---- newPostLabel ----
        newPostLabel.setText("Add new post");
        newPostLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                newPostLabelMouseClicked(e);
            }
        });
        contentPane.add(newPostLabel, "cell 17 0 3 1");

        //---- logoutLabel ----
        logoutLabel.setText("Logout");
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logoutLabelMouseClicked(e);
            }
        });
        contentPane.add(logoutLabel, "cell 20 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel nameLabel;
    private JLabel newPostLabel;
    private JLabel logoutLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
