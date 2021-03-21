/*
 * Created by JFormDesigner on Sat Mar 20 12:44:57 EET 2021
 */

package com.company.forms.view;

import com.company.dto.CommDTO;
import com.company.dto.PostDTO;
import com.company.dto.UserDTO;
import com.company.forms.dashboard.Dashboard;
import com.company.service.CommService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * @author unknown
 */
public class ViewPost extends JFrame {

    private final UserDTO user;
    private final PostDTO post;

    public ViewPost(UserDTO userDTO, PostDTO postDTO) {
        initComponents();
        this.user = userDTO;
        this.post = postDTO;
        this.post.setComments(CommService.getInstance().getCommentsByPostId(this.post.getPostId()));
        nameLabel.setText(userDTO.getFirstName() + " " + userDTO.getLastName());
        questionTextArea.setText(post.getPostText());
        questionTextArea.setEditable(false);
        initComments(this.post.getComments());
    }

    private void initComments(List<CommDTO> commDTOList) {
        int pos = 5;
        for (CommDTO commDTO : commDTOList) {
            JTextArea jta = new JTextArea(commDTO.getUser().getFirstName() + " " + commDTO.getUser().getLastName() +
                    ": " +commDTO.getCommText());
            jta.setEditable(false);
            jta.setRows(2);
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setViewportView(jta);
            getContentPane().add(jScrollPane, "cell 2 " + pos + " 32 2");
            pos += 5;
        }

        JTextField jtf = new JTextField();
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jtf);
        getContentPane().add(jScrollPane, "cell 2 " + pos + " 32 2");
        pos += 5;

        JButton button = new JButton("Add comment");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommDTO commDTO = new CommDTO();
                commDTO.setUser(user);
                commDTO.setPostId(post.getPostId());
                commDTO.setCommText(jtf.getText());
                commDTO.setCommDate(Date.from(Instant.now()));
                CommService.getInstance().addComment(commDTO);
            }
        });
        getContentPane().add(button, "cell 32 " + pos);
    }

    private void backLabelMouseClicked(MouseEvent e) {
        JFrame dashboardFrame = new Dashboard(user);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setSize(400, 400);
        dashboardFrame.setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        backLabel = new JLabel();
        nameLabel = new JLabel();
        scrollPane1 = new JScrollPane();
        questionTextArea = new JTextArea();
        label1 = new JLabel();

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
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backLabelMouseClicked(e);
            }
        });
        contentPane.add(backLabel, "cell 0 0 2 1");
        contentPane.add(nameLabel, "cell 22 0 4 1");

        //======== scrollPane1 ========
        {

            //---- questionTextArea ----
            questionTextArea.setRows(3);
            scrollPane1.setViewportView(questionTextArea);
        }
        contentPane.add(scrollPane1, "cell 2 1 32 2");

        //---- label1 ----
        label1.setText("Comments:");
        contentPane.add(label1, "cell 2 3");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel backLabel;
    private JLabel nameLabel;
    private JScrollPane scrollPane1;
    private JTextArea questionTextArea;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
