/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Sudoku.View;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author AliAl
 */
public class Game extends javax.swing.JPanel {

    private JTextField[][] cells = new JTextField[9][9];
    private JTextField selectedcell;
    private int[][] boradData;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private View view = new View();
    private Stack<UserAction> history = new Stack<>();

    public Game(int[][] boradData) {
        initComponents();
        constructSudokuPanel();
        this.boradData = boradData;
    }

    public void constructSudokuPanel() {
        Sudokutable.setLayout(new GridLayout(9, 9, 0, 0));
        Sudokutable.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
                int top = (row % 3 == 0) ? 2 : 1;
                int left = (col % 3 == 0) ? 2 : 1;
                int bottom = 1;
                int right = 1;
                cell.setBorder(javax.swing.BorderFactory.createMatteBorder(top, left, bottom, right, java.awt.Color.BLACK));
                cell.putClientProperty("row", row);
                cell.putClientProperty("col", col);
                cell.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent e) {
                        selectedcell = (JTextField) e.getSource();
                        selectedRow = (int) selectedcell.getClientProperty("row");
                        selectedCol = (int) selectedcell.getClientProperty("col");
                        selectedcell.setBackground(Color.BLUE);
                        System.out.println("Selected Index: [" + selectedRow + "][" + selectedCol + "]");
                    }

                    public void focusLost(java.awt.event.FocusEvent e) {
                        JTextField lostCell = (JTextField) e.getSource();
                        lostCell.setBackground(Color.WHITE);
                    }
                });
                cells[row][col] = cell;

                Sudokutable.add(cell);
            }
        }
        Sudokutable.revalidate();
        Sudokutable.repaint();
    }

    private void setNumberInSelectedCell(String number) {
        if (selectedcell != null && selectedcell.isEditable()) {
            int oldVar = selectedcell.getText().isEmpty() ? 0 : Integer.parseInt(selectedcell.getText());
            int newVar = Integer.parseInt(number);
            if (newVar == oldVar) {
                return;
            }
            UserAction userAction = new UserAction(selectedRow, selectedCol, oldVar, newVar);
            view.logUserAction(userAction);
            history.push(userAction);

            selectedcell.setText(number);
            selectedcell.requestFocus();
        }

    }

    public void loadGame(int[][] boardData) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int value = boardData[row][col];
                JTextField currentCell = cells[row][col];
                if (value != 0) {
                    currentCell.setText(String.valueOf(value));
                    currentCell.setEditable(false);
                    currentCell.setBackground(Color.LIGHT_GRAY);
                } else {
                    currentCell.setText("");
                    currentCell.setEditable(true);
                    currentCell.setBackground(Color.WHITE);
                }
            }
        }
    }

    private int[][] getBoard() {
        int[][] boradData = new int[9][9];
        int numOfZeros = 0;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String value = cells[row][col].getText();
                if (!value.isEmpty()) {
                    boradData[row][col] = Integer.parseInt(value);
                } else {
                    boradData[row][col] = 0;
                    numOfZeros++;
                }
            }

        }
        return numOfZeros > 5 ? null : boradData;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Sudokutable = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        SolveButton = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout SudokutableLayout = new javax.swing.GroupLayout(Sudokutable);
        Sudokutable.setLayout(SudokutableLayout);
        SudokutableLayout.setHorizontalGroup(
            SudokutableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );
        SudokutableLayout.setVerticalGroup(
            SudokutableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 181, Short.MAX_VALUE)
        );

        add(Sudokutable, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 20, -1, -1));
        Sudokutable.getAccessibleContext().setAccessibleName("");

        jButton1.setText("1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton8)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9))))
        );

        jButton1.getAccessibleContext().setAccessibleName("jButton1");
        jButton2.getAccessibleContext().setAccessibleName("jButton2");
        jButton3.getAccessibleContext().setAccessibleName("jButton3");

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 66, -1, -1));

        SolveButton.setBackground(new java.awt.Color(0, 204, 0));
        SolveButton.setText("Solve");
        jPanel1.add(SolveButton);

        jButton10.setText("Undo");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10);

        jButton11.setBackground(new java.awt.Color(232, 22, 22));
        jButton11.setText("Exit");
        jPanel1.add(jButton11);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 228, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setNumberInSelectedCell("1");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setNumberInSelectedCell("2");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setNumberInSelectedCell("3");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        setNumberInSelectedCell("4");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        setNumberInSelectedCell("5");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        setNumberInSelectedCell("6");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        setNumberInSelectedCell("7");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        setNumberInSelectedCell("8");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        setNumberInSelectedCell("9");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if (!history.isEmpty()) {

            UserAction lastAction = history.pop();
            int row = lastAction.getRow();
            int col = lastAction.getCol();
            int oldVar = lastAction.getOldValue();
        if(oldVar==0)
        cells[row][col].setText("");
        else        cells[row][col].setText(String.valueOf(col));}
    }//GEN-LAST:event_jButton10ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SolveButton;
    private javax.swing.JPanel Sudokutable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
