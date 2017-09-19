package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MilionareBoard extends JFrame {
    private static final String A = "     A: ";
    private static final String B = "     B: ";
    private static final String C = "     C: ";
    private static final String D = "     D: ";
    private static final int BUTTON_ANSWER_WIDTH = 200;
    private static final int BUTTON_ANSWER_HEIGHT = 30;
    private static final Color BLACK = Color.black;
    private static final Color GRAY = Color.gray;
    private static final Color GREEN = Color.green;
    private static final Color RED = Color.red;
    private static final Color ORANGE = Color.orange;
    private static final Color WHITE = Color.white;
    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    private static final String REWARD_100 = "100 ZŁ";
    private static final String REWARD_200 = "200 ZŁ";
    private static final String REWARD_300 = "300 ZŁ";
    private static final String REWARD_500 = "500 ZŁ";
    private static final String REWARD_1K = "1 000 ZŁ";
    private static final String REWARD_2K = "2 000 ZŁ";
    private static final String REWARD_4K = "4 000 ZŁ";
    private static final String REWARD_8K = "8 000 ZŁ";
    private static final String REWARD_16K = "16 000 ZŁ";
    private static final String REWARD_32K = "32 000 ZŁ";
    private static final String REWARD_64K = "64 000 ZŁ";
    private static final String REWARD_125K = "125 000 ZŁ";
    private static final String REWARD_250K = "250 000 ZŁ";
    private static final String REWARD_500K = "500 000 ZŁ";
    private static final String REWARD_1MLN = "1 MILION ZŁ";
    private static JLabel[] diamondLabelArray = new JLabel[15];
    private static JLabel[] rewardLabelArray = new JLabel[15];
    private int questionCounter = 0;
    private Question question;
    private int rightAnswer;
    private boolean isAnimationPlaying = false;
    private final MilionareButton btnanswerA;
    private final MilionareButton btnanswerB;
    private final MilionareButton btnanswerC;
    private final MilionareButton btnanswerD;
    private final MilionareButton btnHelp50;
    private final MilionareButton btnHelpPhone;
    private final MilionareButton btnHelpAudience;
    private final MilionareButton btnClaimPrize;
    private boolean isAAvailable = true;
    private boolean isBAvailable = true;
    private boolean isCAvailable = true;
    private boolean isDAvailable = true;
    private ArrayList<Question> predefinedQuestionList;
    private ArrayList<Question> gameQuestionList;

    MilionareBoard() {

        prepareJFrame();
        paintOptionPane();
        createRewardArray();
        fillRewardArray();
        preparePredefinedQuestionList();
        prepareGameQuestionList();

        JLabel lblTitle = new JLabel("MILIONERZY");
        lblTitle.setSize(420, 50);
        lblTitle.setLocation(10, 120);
        lblTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.white);
        add(lblTitle);

        JLabel lblQuestion = new JLabel("Dummy Question");
        lblQuestion.setSize(420, 50);
        lblQuestion.setLocation(10, 230);
        lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuestion.setForeground(Color.white);
        lblQuestion.setBorder(BorderFactory.createLineBorder(Color.blue));
        add(lblQuestion);

        btnanswerA = new MilionareButton(A);
        btnanswerA.setSize(BUTTON_ANSWER_WIDTH, BUTTON_ANSWER_HEIGHT);
        btnanswerA.setLocation(10, 290);
        btnanswerA.setHorizontalAlignment(SwingConstants.LEFT);
        add(btnanswerA);

        btnanswerB = new MilionareButton(B);
        btnanswerB.setSize(BUTTON_ANSWER_WIDTH, BUTTON_ANSWER_HEIGHT);
        btnanswerB.setLocation(230, 290);
        btnanswerB.setHorizontalAlignment(SwingConstants.LEFT);
        add(btnanswerB);

        btnanswerC = new MilionareButton(C);
        btnanswerC.setSize(BUTTON_ANSWER_WIDTH, BUTTON_ANSWER_HEIGHT);
        btnanswerC.setLocation(10, 330);
        btnanswerC.setBackground(Color.black);
        btnanswerC.setHorizontalAlignment(SwingConstants.LEFT);
        add(btnanswerC);

        btnanswerD = new MilionareButton(D);
        btnanswerD.setSize(BUTTON_ANSWER_WIDTH, BUTTON_ANSWER_HEIGHT);
        btnanswerD.setLocation(230, 330);
        btnanswerD.setBackground(Color.black);
        btnanswerD.setHorizontalAlignment(SwingConstants.LEFT);
        add(btnanswerD);

        btnHelp50 = new MilionareButton("50:50");
        btnHelp50.setSize(90, 40);
        btnHelp50.setLocation(10, 10);
        add(btnHelp50);

        btnHelpPhone = new MilionareButton("TELEFON");
        btnHelpPhone.setSize(90, 40);
        btnHelpPhone.setLocation(180, 10);
        btnHelpPhone.setMargin(new Insets(20, 5, 20, 5));
        add(btnHelpPhone);

        btnHelpAudience = new MilionareButton("PUBLICZNOŚĆ");
        btnHelpAudience.setSize(90, 40);
        btnHelpAudience.setLocation(340, 10);
        btnHelpAudience.setMargin(new Insets(0, 0, 0, 0));
        add(btnHelpAudience);

        btnClaimPrize = new MilionareButton("REZYGNUJE");
        btnClaimPrize.setSize(100, 40);
        btnClaimPrize.setLocation(460, 320);
        btnClaimPrize.setMargin(new Insets(0, 0, 0, 0));
        add(btnClaimPrize);



        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);

        repaint();

        btnanswerA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAnimationPlaying = true;
                switch (rightAnswer) {
                    case 1:
                        questionCounter++;
                        waitOneSecond();
                        btnanswerA.setRolloverColor(GREEN);
                        showMessageBox("Prawidłowa odpowiedź!", "Prawidłowa odpowiedź!");
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                    case 2:
                        btnanswerB.setBackground(GREEN);
                        btnanswerB.setForeground(BLACK);
                        repaint();
                        waitOneSecond();
                        btnanswerA.setRolloverColor(RED);
                        showDefeatMessage();
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                    case 3:
                        btnanswerC.setBackground(GREEN);
                        btnanswerC.setForeground(BLACK);
                        repaint();
                        waitOneSecond();
                        btnanswerA.setRolloverColor(RED);
                        showDefeatMessage();
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                    case 4:
                        btnanswerD.setBackground(GREEN);
                        btnanswerD.setForeground(BLACK);
                        repaint();
                        waitOneSecond();
                        btnanswerA.setRolloverColor(RED);
                        showDefeatMessage();
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                }
            }
        });

        btnanswerB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAnimationPlaying = true;
                switch (rightAnswer) {
                    case 1:
                        btnanswerA.setBackground(GREEN);
                        btnanswerA.setForeground(BLACK);
                        repaint();
                        waitOneSecond();
                        btnanswerB.setRolloverColor(RED);
                        showDefeatMessage();
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                    case 2:
                        questionCounter++;
                        waitOneSecond();
                        btnanswerB.setRolloverColor(GREEN);
                        showMessageBox("Prawidłowa odpowiedź!", "Prawidłowa odpowiedź!");
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                    case 3:
                        btnanswerC.setBackground(GREEN);
                        btnanswerC.setForeground(BLACK);
                        repaint();
                        waitOneSecond();
                        btnanswerB.setRolloverColor(RED);
                        showDefeatMessage();
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                    case 4:
                        btnanswerD.setBackground(GREEN);
                        btnanswerD.setForeground(BLACK);
                        repaint();
                        waitOneSecond();
                        btnanswerB.setRolloverColor(RED);
                        showDefeatMessage();
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                }
            }
        });

        btnanswerC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAnimationPlaying = true;
                switch (rightAnswer) {
                    case 1:
                        btnanswerA.setBackground(GREEN);
                        btnanswerA.setForeground(BLACK);
                        repaint();
                        waitOneSecond();
                        btnanswerC.setRolloverColor(RED);
                        showDefeatMessage();
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                    case 2:
                        btnanswerB.setBackground(GREEN);
                        btnanswerB.setForeground(BLACK);
                        repaint();
                        waitOneSecond();
                        btnanswerC.setRolloverColor(RED);
                        showDefeatMessage();
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                    case 3:
                        questionCounter++;
                        waitOneSecond();
                        btnanswerC.setRolloverColor(GREEN);
                        showMessageBox("Prawidłowa odpowiedź!", "Prawidłowa odpowiedź!");
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                    case 4:
                        btnanswerD.setBackground(GREEN);
                        btnanswerD.setForeground(BLACK);
                        repaint();
                        waitOneSecond();
                        btnanswerC.setRolloverColor(RED);
                        showDefeatMessage();
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                }
            }

        });

        btnanswerD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAnimationPlaying = true;
                switch (rightAnswer) {
                    case 1:
                        btnanswerA.setBackground(GREEN);
                        btnanswerA.setForeground(BLACK);
                        repaint();
                        waitOneSecond();
                        btnanswerD.setRolloverColor(RED);
                        showDefeatMessage();
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                    case 2:
                        btnanswerB.setBackground(GREEN);
                        btnanswerB.setForeground(BLACK);
                        repaint();
                        waitOneSecond();
                        btnanswerD.setRolloverColor(RED);
                        showDefeatMessage();
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                    case 3:
                        btnanswerC.setBackground(GREEN);
                        btnanswerC.setForeground(BLACK);
                        repaint();
                        waitOneSecond();
                        btnanswerD.setRolloverColor(RED);
                        showDefeatMessage();
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                    case 4:
                        questionCounter++;
                        waitOneSecond();
                        btnanswerD.setRolloverColor(GREEN);
                        showMessageBox("Prawidłowa odpowiedź!", "Prawidłowa odpowiedź!");
                        prepareNewQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
                        break;
                }
            }
        });

        btnHelp50.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random help50Randomizer = new Random();
                int disableWrongAnswers = help50Randomizer.nextInt(3) + 1;
                switch (rightAnswer) {
                    case 1:
                        if (disableWrongAnswers == 1) {
                            btnanswerB.setText("");
                            isBAvailable = false;
                            btnanswerB.setEnabled(false);
                            btnanswerC.setText("");
                            isCAvailable = false;
                            btnanswerC.setEnabled(false);
                        } else if (disableWrongAnswers == 2) {
                            btnanswerC.setText("");
                            isCAvailable = false;
                            btnanswerC.setEnabled(false);
                            btnanswerD.setText("");
                            isDAvailable = false;
                            btnanswerD.setEnabled(false);
                        } else {
                            btnanswerB.setText("");
                            isBAvailable = false;
                            btnanswerB.setEnabled(false);
                            btnanswerD.setText("");
                            isDAvailable = false;
                            btnanswerD.setEnabled(false);
                        }
                        btnHelp50.setEnabled(false);
                        btnHelp50.setText("");
                        break;
                    case 2:
                        if (disableWrongAnswers == 1) {
                            btnanswerA.setText("");
                            isAAvailable = false;
                            btnanswerA.setEnabled(false);
                            btnanswerC.setText("");
                            isCAvailable = false;
                            btnanswerC.setEnabled(false);
                        } else if (disableWrongAnswers == 2) {
                            btnanswerC.setText("");
                            isCAvailable = false;
                            btnanswerC.setEnabled(false);
                            btnanswerD.setText("");
                            isDAvailable = false;
                            btnanswerD.setEnabled(false);
                        } else {
                            btnanswerA.setText("");
                            isAAvailable = false;
                            btnanswerA.setEnabled(false);
                            btnanswerD.setText("");
                            isDAvailable = false;
                            btnanswerD.setEnabled(false);
                        }
                        btnHelp50.setEnabled(false);
                        btnHelp50.setText("");
                        break;
                    case 3:
                        if (disableWrongAnswers == 1) {
                            btnanswerA.setText("");
                            isAAvailable = false;
                            btnanswerA.setEnabled(false);
                            btnanswerB.setText("");
                            isBAvailable = false;
                            btnanswerB.setEnabled(false);
                        } else if (disableWrongAnswers == 2) {
                            btnanswerB.setText("");
                            isBAvailable = false;
                            btnanswerB.setEnabled(false);
                            btnanswerD.setText("");
                            isDAvailable = false;
                            btnanswerD.setEnabled(false);
                        } else {
                            btnanswerA.setText("");
                            isAAvailable = false;
                            btnanswerA.setEnabled(false);
                            btnanswerD.setText("");
                            isDAvailable = false;
                            btnanswerD.setEnabled(false);
                        }
                        btnHelp50.setEnabled(false);
                        btnHelp50.setText("");
                        break;

                    case 4:
                        if (disableWrongAnswers == 1) {
                            btnanswerA.setText("");
                            isAAvailable = false;
                            btnanswerA.setEnabled(false);
                            btnanswerB.setText("");
                            isBAvailable = false;
                            btnanswerB.setEnabled(false);
                        } else if (disableWrongAnswers == 2) {
                            btnanswerB.setText("");
                            isBAvailable = false;
                            btnanswerB.setEnabled(false);
                            btnanswerC.setText("");
                            isCAvailable = false;
                            btnanswerC.setEnabled(false);
                        } else {
                            btnanswerA.setText("");
                            isAAvailable = false;
                            btnanswerA.setEnabled(false);
                            btnanswerC.setText("");
                            isCAvailable = false;
                            btnanswerC.setEnabled(false);
                        }

                        btnHelp50.setEnabled(false);
                        btnHelp50.setText("");
                        break;

                }
            }
        });

        btnHelpPhone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (rightAnswer) {
                    case 1:
                        showMessageBox("Telefon do przyjaciela", "Wydaję mi się że odpowiedź na to pytanie to: A: "
                                + question.getAnswerA());
                        break;
                    case 2:
                        showMessageBox("Telefon do przyjaciela", "Wydaję mi się że odpowiedź na to pytanie to: B: "
                                + question.getAnswerB());
                        break;
                    case 3:
                        showMessageBox("Telefon do przyjaciela", "Wydaję mi się że odpowiedź na to pytanie to: C: "
                                + question.getAnswerC());
                        break;
                    case 4:
                        showMessageBox("Telefon do przyjaciela", "Wydaję mi się że odpowiedź na to pytanie to: D: "
                                + question.getAnswerD());
                        break;
                }
                btnHelpPhone.setEnabled(false);
                btnHelpPhone.setText("");
            }
        });

        btnHelpAudience.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String audienceA;
                String audienceB;
                String audienceC;
                String audienceD;

                if (isAAvailable) {
                    audienceA = question.getAudienceHelpA();
                } else {
                    audienceA = "";
                }
                if (isBAvailable) {
                    audienceB = question.getAudienceHelpB();
                } else {
                    audienceB = "";
                }
                if (isCAvailable) {
                    audienceC = question.getAudienceHelpC();
                } else {
                    audienceC = "";
                }
                if (isDAvailable) {
                    audienceD = question.getAudienceHelpD();
                } else {
                    audienceD = "";
                }
                showMessageBox("Pytanie do publiczności", audienceA + audienceB + audienceC + audienceD);
                btnHelpAudience.setEnabled(false);
                btnHelpAudience.setText("");
            }
        });

        btnClaimPrize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (questionCounter) {
                    case 0:
                        showMessageBox("Rezygnujesz z dalszej gry", "Nie odpowiedziałeś na żadne pytanie!");
                        break;
                    case 1:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_100);
                        break;
                    case 2:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_200);
                        break;
                    case 3:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_300);
                        break;
                    case 4:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_500);
                        break;
                    case 5:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_1K);
                        break;
                    case 6:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_2K);
                        break;
                    case 7:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_4K);
                        break;
                    case 8:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_8K);
                        break;
                    case 9:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_16K);
                        break;
                    case 10:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_32K);
                        break;
                    case 11:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_64K);
                        break;
                    case 12:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_125K);
                        break;
                    case 13:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_250K);
                        break;
                    case 14:
                        showMessageBox("Rezygnujesz z dalszej gry", "Wygrywasz " + REWARD_500K);
                        break;
                }
                dispose();
            }
        });
    }

    private void showDefeatMessage() {
        if (questionCounter > 4 && questionCounter <= 9) {
            showMessageBox("Przegrałeś!", "Przegrałeś! Ale dostajesz gwarantowane 1000zł");
        } else if (questionCounter > 9) {
            showMessageBox("Przegrałeś!", "Przegrałeś! Ale dostajesz gwarantowane 32000zł");
        } else {
            showMessageBox("Przegrałeś!", "Przegrałeś!");
        }
        reset();
    }

    private void paintOptionPane() {
        UIManager.put("OptionPane.background", Color.black);
        UIManager.put("Panel.background", Color.black);
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("Button.background", Color.black);
        UIManager.put("Button.foreground", Color.white);
        UIManager.put("Button.border", BorderFactory.createLineBorder(Color.blue));
    }

    private void prepareNewQuestion(JLabel lblQuestion, MilionareButton btnanswerA, MilionareButton btnanswerB, MilionareButton btnanswerC, MilionareButton btnanswerD) {
        checkQuestionCounter();
        newQuestion(lblQuestion, btnanswerA, btnanswerB, btnanswerC, btnanswerD);
        isAAvailable = true;
        isBAvailable = true;
        isCAvailable = true;
        isDAvailable = true;
        setRolloverColorToDefault();
        btnanswerA.setEnabled(true);
        btnanswerB.setEnabled(true);
        btnanswerC.setEnabled(true);
        btnanswerD.setEnabled(true);
        rightAnswer = question.getRightAnswer();
    }

    private void waitOneSecond() {
        if (isAnimationPlaying) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void checkQuestionCounter() {
        if (questionCounter < 15) {
            for (int i = 0; i <= questionCounter; i++) {
                rewardLabelArray[i].setBackground(ORANGE);
                rewardLabelArray[i].setForeground(BLACK);
                if (i > 0) {
                    rewardLabelArray[i - 1].setForeground(ORANGE);
                    rewardLabelArray[i - 1].setBackground(TRANSPARENT);
                    diamondLabelArray[i - 1].setText("◆");
                    repaint();
                }
                if (i >= 5) {
                    rewardLabelArray[4].setForeground(WHITE);
                    rewardLabelArray[4].setBackground(TRANSPARENT);
                    diamondLabelArray[4].setText("◆");
                    repaint();
                }

                if (i >= 10) {
                    rewardLabelArray[9].setForeground(WHITE);
                    rewardLabelArray[9].setBackground(TRANSPARENT);
                    diamondLabelArray[4].setText("◆");

                }
            }
        }
        if (questionCounter == 15) {
            rewardLabelArray[14].setForeground(WHITE);
            rewardLabelArray[14].setBackground(TRANSPARENT);
            diamondLabelArray[14].setText("◆");
            repaint();
            showMessageBox("Gratulacje!", "Gratulacje! Wygrałeś 1 MILION ZŁ");
            reset();
        }
    }

    private void reset() {
        questionCounter = 0;
        isAnimationPlaying = false;
        for (int i = 0; i < diamondLabelArray.length; i++) {
            diamondLabelArray[i].setText("");
        }
        resetRewardArray();
        prepareGameQuestionList();
        isAAvailable = true;
        isBAvailable = true;
        isCAvailable = true;
        isDAvailable = true;
        btnanswerA.setBackground(BLACK);
        btnanswerB.setBackground(BLACK);
        btnanswerC.setBackground(BLACK);
        btnanswerD.setBackground(BLACK);
        btnanswerA.setForeground(WHITE);
        btnanswerB.setForeground(WHITE);
        btnanswerC.setForeground(WHITE);
        btnanswerD.setForeground(WHITE);
        setRolloverColorToDefault();
        btnHelp50.setEnabled(true);
        btnHelp50.setText("50:50");
        btnHelpPhone.setEnabled(true);
        btnHelpPhone.setText("TELEFON");
        btnHelpAudience.setEnabled(true);
        btnHelpAudience.setText("PUBLICZNOŚĆ");
        repaint();
    }

    private void setRolloverColorToDefault() {
        btnanswerA.setRolloverColor(GRAY);
        btnanswerB.setRolloverColor(GRAY);
        btnanswerC.setRolloverColor(GRAY);
        btnanswerD.setRolloverColor(GRAY);
    }

    private void resetRewardArray() {
        for (int i = 0; i < rewardLabelArray.length; i++) {
            rewardLabelArray[i].setBackground(BLACK);
            rewardLabelArray[i].setForeground(ORANGE);
            if (i == 4 || i == 9 || i == 14) {
                rewardLabelArray[i].setForeground(WHITE);
            }
        }
        rewardLabelArray[0].setBackground(ORANGE);
        rewardLabelArray[0].setForeground(BLACK);
    }

    private void newQuestion(JLabel lblQuestion, MilionareButton btnanswerA, MilionareButton btnanswerB, MilionareButton btnanswerC, MilionareButton btnanswerD) {

        Random questionRandomizer = new Random();
        int nextQuestion = questionRandomizer.nextInt(gameQuestionList.size());
        question = gameQuestionList.get(nextQuestion);
        gameQuestionList.remove(nextQuestion);
        lblQuestion.setText(question.getQuestion());
        btnanswerA.setText(A + question.getAnswerA());
        btnanswerB.setText(B + question.getAnswerB());
        btnanswerC.setText(C + question.getAnswerC());
        btnanswerD.setText(D + question.getAnswerD());

    }

    private void preparePredefinedQuestionList() {
        predefinedQuestionList = new ArrayList<Question>();

        predefinedQuestionList.add(new Question("Test1?", "tak", "nie", "nie", "nie", 1,
                "A: 67% ", "B: 3% ", "C: 9% ", "D: 21% "));
        predefinedQuestionList.add(new Question("Test2?", "nie", "tak", "nie", "nie", 2,
                        "A: 2% ", "B: 81% ", "C: 10% ", "D: 7% "));
        predefinedQuestionList.add(new Question("Test3?", "nie", "nie", "tak", "nie", 3,
                        "A: 16% ", "B: 14% ", "C: 54% ", "D: 16% "));
        predefinedQuestionList.add(new Question("Test4?", "nie", "nie", "nie", "tak", 4,
                        "A: 4% ", "B: 6% ", "C: 27% ", "D: 63% "));
        predefinedQuestionList.add(new Question("Test5?", "tak", "nie", "nie", "nie", 1,
                "A: 67% ", "B: 3% ", "C: 9% ", "D: 21% "));
        predefinedQuestionList.add(new Question("Test6?", "nie", "tak", "nie", "nie", 2,
                "A: 2% ", "B: 81% ", "C: 10% ", "D: 7% "));
        predefinedQuestionList.add(new Question("Test7?", "nie", "nie", "tak", "nie", 3,
                "A: 16% ", "B: 14% ", "C: 54% ", "D: 16% "));
        predefinedQuestionList.add(new Question("Test8?", "nie", "nie", "nie", "tak", 4,
                "A: 4% ", "B: 6% ", "C: 27% ", "D: 63% "));
        predefinedQuestionList.add(new Question("Test9?", "tak", "nie", "nie", "nie", 1,
                "A: 67% ", "B: 3% ", "C: 9% ", "D: 21% "));
        predefinedQuestionList.add(new Question("Test10?", "nie", "tak", "nie", "nie", 2,
                "A: 2% ", "B: 81% ", "C: 10% ", "D: 7% "));
        predefinedQuestionList.add(new Question("Test11?", "nie", "nie", "tak", "nie", 3,
                "A: 16% ", "B: 14% ", "C: 54% ", "D: 16% "));
        predefinedQuestionList.add(new Question("Test12?", "nie", "nie", "nie", "tak", 4,
                "A: 4% ", "B: 6% ", "C: 27% ", "D: 63% "));
        predefinedQuestionList.add(new Question("Test13?", "tak", "nie", "nie", "nie", 1,
                "A: 67% ", "B: 3% ", "C: 9% ", "D: 21% "));
        predefinedQuestionList.add(new Question("Test14?", "nie", "tak", "nie", "nie", 2,
                "A: 2% ", "B: 81% ", "C: 10% ", "D: 7% "));
        predefinedQuestionList.add(new Question("Test15?", "nie", "nie", "tak", "nie", 3,
                "A: 16% ", "B: 14% ", "C: 54% ", "D: 16% "));
        predefinedQuestionList.add(new Question("Test16?", "nie", "nie", "nie", "tak", 4,
                "A: 4% ", "B: 6% ", "C: 27% ", "D: 63% "));

    }

    private void prepareGameQuestionList() {

        gameQuestionList = new ArrayList<Question>(predefinedQuestionList);
        Collections.shuffle(gameQuestionList);
    }

    private static void showMessageBox(final String Title, final String Message) {
        JOptionPane theOptionPane = new JOptionPane(Message, JOptionPane.INFORMATION_MESSAGE);
        JPanel buttonPanel = (JPanel) theOptionPane.getComponent(1);
        JButton buttonOk = (JButton) buttonPanel.getComponent(0);
        buttonOk.setText(" OK ");
        buttonOk.setFocusPainted(false);
        buttonOk.setPreferredSize(new Dimension(50, 25));  //Set Button size here
        buttonOk.validate();
        JDialog theDialog = theOptionPane.createDialog(null, Title);
        theDialog.setVisible(true);
    }

    private void fillRewardArray() {
        fillRewardArrayItem(0, "     " + REWARD_100);
        fillRewardArrayItem(1, "     " + REWARD_200);
        fillRewardArrayItem(2, "     " + REWARD_300);
        fillRewardArrayItem(3, "     " + REWARD_500);
        fillRewardArrayItem(4, "     " + REWARD_1K);
        fillRewardArrayItem(5, "     " + REWARD_2K);
        fillRewardArrayItem(6, "     " + REWARD_4K);
        fillRewardArrayItem(7, "     " + REWARD_8K);
        fillRewardArrayItem(8, "     " + REWARD_16K);
        fillRewardArrayItem(9, "     " + REWARD_32K);
        fillRewardArrayItem(10, "     " + REWARD_64K);
        fillRewardArrayItem(11, "     " + REWARD_125K);
        fillRewardArrayItem(12, "     " + REWARD_250K);
        fillRewardArrayItem(13, "     " + REWARD_500K);
        fillRewardArrayItem(14, "     " + REWARD_1MLN);
    }

    private void fillRewardArrayItem(int position, String reward) {
        rewardLabelArray[0].setBackground(ORANGE);
        rewardLabelArray[0].setForeground(BLACK);
        rewardLabelArray[position].setText(rewardLabelArray[position].getText() + reward);
        if (position == 4 || position == 9 || position == 14) {
            rewardLabelArray[position].setForeground(WHITE);
        }
    }

    private void createRewardArray() {

        for (int i = 0; i < rewardLabelArray.length; i++) {
            rewardLabelArray[i] = new JLabel();
            rewardLabelArray[i].setSize(100, 20);
            rewardLabelArray[i].setLocation(460, 290 - i * 20);
            rewardLabelArray[i].setOpaque(true);
            rewardLabelArray[i].setBackground(BLACK);
            rewardLabelArray[i].setForeground(ORANGE);
            if (i < 9) {
                rewardLabelArray[i].setText("  " + (i + 1));
            } else {
                rewardLabelArray[i].setText("" + (i + 1));
            }
            add(rewardLabelArray[i]);
        }

        for (int i = 0; i < diamondLabelArray.length; i++) {
            diamondLabelArray[i] = new JLabel();
            diamondLabelArray[i].setSize(20, 20);
            diamondLabelArray[i].setLocation(475, 290 - i * 20);
            diamondLabelArray[i].setForeground(WHITE);
            add(diamondLabelArray[i]);
        }
    }

    private void prepareJFrame() {
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(580, 400);
        setResizable(false);
        getContentPane().setBackground(BLACK);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Milionerzy");
    }
}
