import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BlackJackGame extends JFrame implements ActionListener {
    setBlackJack player = new setBlackJack(); // player 생성자
    setBlackJack dealer = new setBlackJack(); // dealer 생성자
    
    int isBlackjack = 0; // 블랙잭 여부를 나타내는 변수
    int isBust = 0; // 버스트 상태를 나타내는 변수 
    int isStand = 0; // 스탠드 상태를 나타내는 변수
    int isHit = 0; // 히트 상태를 나타내는 변수
    int isDoubleDown = 0; // 더블다운 상태를 나타내는 변수
    int BetDone = 0; // 배팅 완료 여부를 나타내는 변수
    int numPlayers = 1; // 플레이어 수
    
    private CardLayout cardLayout; // 각 화면들을 담을 카드 레이아웃
    private Container contentPane;
    
    private JPanel mainPanel; // 시작화면 패널
    private JPanel gamePanel; // 게임화면 패널
    
    private JPanel buttonPanel; // 시작화면에 넣을 버튼 패널
    private JButton[] menuButtons; // 시작화면에 넣을 메뉴 버튼들
    private String[] menuButton = {"혼자 할래요!", "같이 할래요!", "블랙잭이 처음이에요ㅠ", "그만 하고 싶어요!"}; // 시작화면 메뉴 버튼 텍스트
    private JPanel gameButtonPanel; // 게임화면에 넣을 버튼 패널
    private JButton[] gameButtons; // 게임화면에 넣은 버튼들
    private String[] gameButton = {"Hit", "Double Down", "Stand"}; // 게임화면 진행 버튼 텍스트
    private JPanel[] playerCard; // 플레이어 게임판 패널
    private JPanel[] cardPanel; // 뽑은 카드를 보여줄 패널
    private JPanel imagePanel; // 시작화면 이미지 패널
    private JLabel mainImage; // 시작화면 이미지 레이블
    private JTextField[] score; // 점수를 보여주는 텍스트 필드
    
    private JPanel[] playersouthPanel; // 플레이어 패널의 하단 패널
    private JPanel[] playerNorthPanel; // 플레이어 패널의 상단 패널
    private JTextField[] reMoney; // 잔액을 표시할 텍스트 필드
    private JTextField[] betAmount; // 베팅금액을 입력할 텍스트 필드
    private JButton betButton; // 베팅 버튼
    
    private JPanel overlayPanel; // 한 판 종료시 채팅 입력창 위에 나타날 패널
    private JLabel endLabel; // 게임 종료 메시지
    private JButton[] endButton; // 게임 종료시 다음 동작을 위한 버튼
    private JPanel endPanel; // 게임 종료시 나타날 버튼을 배치할 패널
    
    private JPanel chatSystem; // 채팅 입력창 패널
    private JPanel chatPanel; // 채팅 내용과 스크롤을 배치 할 패널
    private JPanel inputPanel; // 채팅의 입력과 버튼을 배치 할 패널
    private JTextArea chatText; // 채팅 내용을 표시할 텍스트 영역
    private JTextField chatInput; // 채팅을 입력할 텍스트 필드
    private JButton sendButton; // 채팅 전송 버튼
    
    private JPanel rulePanel; // 게임 규칙을 배치할 패널
    private JTextArea ruleText; // 게임 규칙의 내용
    
    // BlackJackGame 생성자
    public BlackJackGame() {
        int totalplayer = numPlayers + 1; // 플레이어의 수를 입력받은 값 + 딜러
        contentPane = getContentPane(); // 메인 패널을 따로 저장
        
        // 플레이어와 딜러의 초기 잔액 설정
        player.setBalance(50000);
        dealer.setBalance(100000);
        
        setTitle("Blackjack Game"); // 제목
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500,800); // 프레임 크기 설정
        
        cardLayout = new CardLayout(); // 카드 레이아웃 생성
        setLayout(cardLayout); 
        mainPanel = new JPanel(); // 시작 화면 생성
        mainPanel.setLayout(null);
        
        // 시작화면에 블랙잭 이미지 삽입
        imagePanel = new JPanel(); // 이미지를 담을 패널 생성
        mainImage = new JLabel(""); // 새로운 레이블 생성
        ImageIcon icon = new ImageIcon("blackjack-3.png"); // 이미지 불러옴
        mainImage.setIcon(icon); // 이미지를 레이블에 삽입
        mainImage.setSize(1000,500); // 크기 조절
        imagePanel.add(mainImage); // 이미지를 패널에 추가
        imagePanel.setBackground(new Color(170,205,223)); // 이미지 패널 배경색 설정
        mainPanel.add(imagePanel); // 이미지를 시작화면에 배치
        mainPanel.setBackground(new Color(170,205,223)); // 시작화면 배경색 설정
        imagePanel.setLocation(250,50); // 이미지 위치 조정
        imagePanel.setSize(1000,500); // 이미지 크기 조정
        
        // 시작화면 메뉴 버튼 삽입
        buttonPanel = new JPanel(); // 버튼 패널 생성
        buttonPanel.setLayout(new GridLayout(1,4,3,3)); // 1행 4열의 그리드 레이아웃 설정
        menuButtons = new JButton[4]; // 시작 메뉴 버튼 배열 할당
        
        for(int i = 0; i < 4; i++) { // 각 인덱스에 해당하는 메뉴 버튼 생성
            menuButtons[i] = new JButton(menuButton[i]);
            menuButtons[i].setSize(50,30); // 크기 조절
            menuButtons[i].addActionListener(this); // 이벤트 처리
            buttonPanel.add(menuButtons[i]); // 버튼 패널에 4개의 버튼 추가
        }
        mainPanel.add(buttonPanel); // 버튼 패널을 시작화면에 배치
        buttonPanel.setLocation(400,600); // 버튼 패널 위치 설정
        buttonPanel.setSize(700,50); // 버튼 패널 크기 설정
        
        add(mainPanel); // 1. 시작 화면 추가
        setVisible(true);
        
        // 게임 진행 버튼을 1행 3열의 그리드 레이아웃으로 생성
        gameButtonPanel = new JPanel(new GridLayout(1, 3, 3, 3));
        gameButtons = new JButton[3]; // 버튼 배열 할당
        String[] gameButtonText = {"Hit", "Double Down", "Stand"};
        
        for(int i = 0; i < 3; i++) { // 각 버튼 생성
            gameButtons[i] = new JButton(gameButtonText[i]);
            gameButtons[i].addActionListener(this); // 이벤트 처리
            gameButtons[i].setSize(100, 50); // 크기 조절
            gameButtonPanel.add(gameButtons[i]); // 게임 버튼 패널에 3개의 버튼 추가
        }
        
        playerCard = new JPanel[2]; // 플레이어 게임판 패널 생성
        playersouthPanel = new JPanel[2]; // 게임판 패널의 하단 패널 생성
        playerNorthPanel = new JPanel[2]; // 게임판 패널의 상단 패널 생성
        reMoney = new JTextField[2]; // 잔액 필드 생성
        betAmount = new JTextField[2]; // 베팅 금액 입력 필드 생성
        score = new JTextField[2]; // 점수 필드 생성
        betButton = new JButton("Betting"); // 베팅 버튼 생성
        betButton.addActionListener(this); // 이벤트 처리
        cardPanel = new JPanel[2]; // 카드판 패널 생성
        gamePanel = new JPanel(); // 게임 화면 패널 생성
        gamePanel.setLayout(new GridBagLayout()); // GridBagLayout 설정
        GridBagConstraints gbc = new GridBagConstraints();
        
        JPanel playerPanel = new JPanel(new BorderLayout()); // 플레이어 게임판 패널 생성
        JPanel dealerPanel = new JPanel(new BorderLayout()); // 딜러 게임판 패널 생성
        
        // 플레이어, 딜러의 게임 화면 구성
        for(int i = 0; i < 2; i++) {
        	Font Font1 = new Font("휴먼모음T", Font.BOLD, 20); // 폰트 설정
            playerCard[i] = new JPanel(new BorderLayout()); // 플레이어 게임판
            playerNorthPanel[i] = new JPanel(new GridLayout(2, 1)); // 플레이어 게임판의 상단
            playersouthPanel[i] = new JPanel(new BorderLayout()); // 플레이어 게임판의 하단
            cardPanel[i] = new JPanel(new GridLayout(3, 3, 3, 3)); // 카드판
            cardPanel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // 카드판 테두리
            score[i] = new JTextField(); // 점수
            score[i].setFont(Font1); // 점수 폰트 설정
            score[i].setEnabled(false); // 점수 쓰기 불가능
            reMoney[i] = new JTextField(); // 잔액
            reMoney[i].setSize(10, 10); // 잔액 크기 설정
            reMoney[i].setFont(Font1); // 잔액 폰트 설정
            reMoney[i].setEnabled(false); // 쓰기 불가능
            betAmount[i] = new JTextField(10); // 베팅금액 
            betAmount[i].setFont(Font1); // 베팅금액 폰트 설정
            playerCard[i].add(cardPanel[i], "Center"); // 카드판을 게임판의 가운데에 위치
            playerNorthPanel[i].add(reMoney[i]); // 잔액을 게임판 상단에 위치
            playerNorthPanel[i].add(score[i]); // 점수를 게임판 상단에 위치
            playerCard[i].add(playerNorthPanel[i], "North"); // 게임판의 상단을 게임판 상단에 위치
            
            if (i == 0) { // 플레이어일 경우
                reMoney[i].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance())); // 잔액 표시
                playersouthPanel[i].add(betAmount[i], "Center"); // 베팅 입력창을 게임판 하단의 가운데에 위치
                playersouthPanel[i].add(betButton, "East"); // 베팅 버튼은 게임판 하단의 오른쪽에 위치
                playerCard[i].add(playersouthPanel[i], "South"); // 게임판 하단을 게임판의 하단에 위치
                playerPanel.add(playerCard[i], BorderLayout.CENTER); // 게임판을 플레이어 게임 화면 가운데에 위치
            } else if (i == 1) { // 딜러일 경우
                reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance())); // 잔액 표시
                playersouthPanel[1].add(betAmount[1], "Center"); // 베팅 입력창을 게임판 하단의 가운데에 위치
                dealerPanel.add(playersouthPanel[1], BorderLayout.SOUTH); // 게임판 하단을 딜러 게임 화면 하단에 위치
                dealerPanel.add(playerCard[i], BorderLayout.CENTER); // 게임판을 딜러 게임 화면 가운데에 위치
            }
            playerCard[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 게임판 패널의 테두리를 설정
        }
        
        // 플레이어 게임판 크기, 위치 설정
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gamePanel.add(playerPanel, gbc);
        
        // 딜러 게임판 크기, 위치 설정
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gamePanel.add(dealerPanel, gbc);
        
        // 게임 진행 버튼 크기, 위치 설정
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gamePanel.add(gameButtonPanel, gbc); // 게임 진행 버튼들은 하단에 위치

        // 채팅 입력창 구현
        Font chatFont = new Font("한컴 말랑말랑 Bold", Font.BOLD, 17); // 폰트 설정
        chatSystem = new JPanel(new BorderLayout()); // 채팅 입력창 패널
        chatPanel = new JPanel(new BorderLayout()); // 채팅 내용판 패널
        chatText = new JTextArea(11, 20); // 텍스트 영역
        chatText.setFont(chatFont); // 채팅 폰트 설정
        chatText.setEditable(false); // 쓰기 불가능
        inputPanel = new JPanel(new BorderLayout()); // 채팅 입력 패널
        chatInput = new JTextField(30); // 채팅 입력 텍스트 필드
        chatInput.setFont(chatFont); // 폰트 설정
        sendButton = new JButton("Send"); // 채팅 전송 버튼
        sendButton.addActionListener(this); // 이벤트 처리

        JScrollPane scroll = new JScrollPane(chatText); // 스크롤 생성
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 무조건 세로 스크롤
        chatPanel.add(scroll, BorderLayout.CENTER); // 채팅 내용판에 스크롤 추가
        inputPanel.add(chatInput, BorderLayout.CENTER); // 채팅 입력 필드를 채팅 입력 패널 가운데에 위치
        inputPanel.add(sendButton, BorderLayout.EAST); // 전송 버튼을 채팅 입력 패널 오른쪽에 위치
        chatSystem.add(chatPanel, BorderLayout.CENTER); // 채팅 내용판을 채팅 입력창 가운데에 위치
        chatSystem.add(inputPanel, BorderLayout.SOUTH); // 채팅 입력 패널을 채팅 입력창 하단에 위치
        
        // 채팅 입력창 크기, 위치 설정
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 1.0;
        gamePanel.add(chatSystem, gbc); // 게임 화면에 채팅창 삽입
        
        add(gamePanel); // 2. 게임 화면 삽입
        setVisible(true);
        
        // 게임 종료 시
     	overlayPanel = new JPanel(new BorderLayout()); // 다음 게임을 진행할 패널
     	overlayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 테두리 설정
     	overlayPanel.setBackground(new Color(240,214,229)); // 배경색 설정
     	endLabel = new JLabel("게임이 종료되었습니다."); // 게임 종료 메시지
     	endLabel.setHorizontalAlignment(SwingConstants.CENTER);
        endLabel.setFont(new Font("휴먼모음T", Font.BOLD, 20)); // 폰트 설정
     	endButton = new JButton[4]; // 다음 게임을 진행할 버튼
     	endButton[0] = new JButton("게임 종료");
     	endButton[0].addActionListener(this);
     	endButton[1] = new JButton("계속 진행");
     	endButton[1].addActionListener(this);
     	endButton[2] = new JButton("시작 화면");
     	endButton[2].addActionListener(this);
     	endButton[3] = new JButton("시작 화면");
     	endButton[3].addActionListener(this);
     	endPanel = new JPanel(new FlowLayout()); // 버튼을 담을 패널
     	endPanel.add(endButton[0]);
     	endPanel.add(endButton[1]);
     	endPanel.add(endButton[2]);
     	overlayPanel.add(endLabel, "Center"); // 게임 종료 메시지를 패널 가운데에 위치
     	overlayPanel.add(endPanel, "South"); // 다음 게임 진행을 위한 버튼들을 패널 하단에 위치
     	
     	// 게임 규칙 
     	rulePanel = new JPanel(new BorderLayout()); // 게임 규칙
     	ruleText = new JTextArea(80,200);
     	ruleText.setFont(new Font("휴먼모음T",Font.BOLD,20)); // 폰트 설정
     	rulePanel.setBorder(BorderFactory.createLineBorder(Color.black,2)); // 테두리 설정
     	// 규칙 내용 추가
     	String rule = ("<<---  게임 방법  --->>\n"
     			+ " 사용자는 자금이 50000원, 딜러는 100000원으로 시작한다. \n"
     			+ " 먼저 베팅을 한다. 딜러가 자신을 포함한 참가자 전원에게 카드 두 장을 나누어주는데 딜러의 카드 한 장은 상대에게 보이지 않는다.\n"
     			+ " 카드의 합이 딜러보다 먼저 21이 되거나 딜러보다 21에 가깝게 되면 이기고 카드를 더 받았는데 21을 초과하면 버스트 된다(Bust) .\n"
     			+ " 먼저 받은 카드 두 장의 합이 21에 못 미치면 히트(Hit)라고 말한 뒤 한 장씩 더 받을 수 있고 멈추려면 스탠드(Stand) 라고 말한다.\n"
     			+ " 더블다운 첫 번째 히트를 할 때 이전에 베팅한 금액만큼 더 베팅하면서 3장째를 받을 수 있다. 더블다운을 하면 더 이상 카드를 받을 수 없다.\n"
     			+ " 딜러는 카드의 합이 16이하면 무조건 한 장을 더 받아야 하고 17이상의 경우에는 멈추어야 한다. 딜러의 카드와 합이 같으면 비긴 것이 된다.\n"
     			+ " 에이스 카드는 1이나 11로 취급할 수 있고 10, J, Q, K는 모두 10으로 계산한다.\n"
     			+ " 처음 받은 카드 두 장이 에이스와 10, J, Q, K 중의 하나로 합이 21이 되면 블랙잭이 되고 베팅한 금액의 두 배로 돈을 받는다.\n"
     			+ " 게임 한 판이 끝나면 게임 종료, 계속 진행, 시작 화면 버튼을 골라 다음을 진행할 수 있다.");
     	ruleText.append(rule);
     	ruleText.setEditable(false); // 쓰기 불가능
     	rulePanel.add(ruleText,"Center"); // 규칙 내용을 패널 가운데에 위치
     	rulePanel.add(endButton[3],"South"); // "시작 화면" 버튼을 패널 하단에 위치
     		
     	add(rulePanel); // 3. 규칙 패널 삽입
     	setVisible(true);
	}

	public static void main(String[] args) {
		BlackJackGame bj = new BlackJackGame(); // 객체 생성
	}
	
	// 플레이어의 카드 표시
	private void printMycards() {
		playerCard[0].remove(cardPanel[0]); // 기존 카드 패널 내용 삭제
		cardPanel[0].removeAll(); // 카드 패널의 모든 컴포넌트 제거
		// 플레이어가 뽑은 카드 출력
		String[] drawedcard = player.getCard(); // 뽑은 카드 저장
		
		// 뽑은 카드 수 만큼 반복
		for (int i = 0; i < drawedcard.length; i++) {
			String filename=drawedcard[i]+".png"; // 카드의 파일 이름 설정
				JLabel cardImage = new JLabel(); // 카드 이미지 라벨 생성
				ImageIcon cardimage = new ImageIcon(filename); // 카드 이미지 생성
				cardImage.setIcon(cardimage); // 라벨에 카드 이미지 설정
				cardImage.setHorizontalAlignment(SwingConstants.CENTER); // 이미지 수평 정렬
				cardImage.setVerticalAlignment(SwingConstants.CENTER); // 이미지 수직 정렬
				cardPanel[0].add(cardImage); // 카드 패널에 카드 이미지 추가
				cardPanel[0].setVisible(true);
				playerCard[0].add(cardPanel[0]); // 플레이어 카드 패널에 카드 추가
		}
		playerCard[0].revalidate(); // 카드 패널을 다시 그리도록 설정
	    playerCard[0].repaint(); // 카드 패널 다시 그리기
	}
	
	// 딜러의 카드는 마지막 한 장의 카드를 뒤집어 놓음
	// 위 내용을 제외하고는 플레이어와 동일함
	public void printDealercards() {
		// 딜러 카드 패널에서 기존 카드 패널 내용 삭제
		playerCard[playerCard.length-1].remove(cardPanel[cardPanel.length-1]);
		cardPanel[cardPanel.length-1].removeAll();
		
		String[] dealercard = dealer.getCard();
		
		for(int i = 0; i < dealercard.length; i++) {
			String filename = dealercard[i]+".png";
			JLabel cardImage = new JLabel();
			ImageIcon cardimage = new ImageIcon(filename);
			cardImage.setIcon(cardimage);
			cardImage.setHorizontalAlignment(SwingConstants.CENTER);
			cardImage.setVerticalAlignment(SwingConstants.CENTER);
			
			// 카드를 뽑을 때 스탠드 상태가 아니면 마지막 카드 한 장을 뒷면으로 표시
			if(i == dealer.getDrawCount() - 1 && isStand != 1) {
				JLabel BackImage = new JLabel();
				ImageIcon backimage = new ImageIcon("back.png");
				BackImage.setIcon(backimage);
				BackImage.setHorizontalAlignment(SwingConstants.CENTER);
				BackImage.setVerticalAlignment(SwingConstants.CENTER);
				cardPanel[cardPanel.length - 1].add(BackImage);
				cardPanel[cardPanel.length - 1].setVisible(true);
				playerCard[playerCard.length - 1].add(cardPanel[playerCard.length-1]);
			}else { // 게임이 종료되었을 경우 모든 카드를 보여줌
				cardPanel[cardPanel.length - 1].add(cardImage);
				cardPanel[cardPanel.length - 1].setVisible(true);
				playerCard[playerCard.length - 1].add(cardPanel[playerCard.length-1]);
			}	
		}
		
		if(isStand != 1) { // 스탠드 상태가 아니면 점수 표시 X
			score[score.length - 1].setText("♠ ♡ ♣ ◇");
		}else { // 스탠드 상태가 되면 딜러의 총점 표시
			score[score.length - 1].setText(Integer.toString(dealer.total));
		}
		playerCard[playerCard.length - 1].revalidate();
	    playerCard[playerCard.length - 1].repaint();
	}
	

	// 게임 진행 버튼 구현
	public void actionPerformed(ActionEvent e) { // 이벤트 처리
		String actionCommand = e.getActionCommand(); // 버튼의 내용을 문자열로 받음
		
		if(actionCommand=="혼자 할래요!") { // "혼자 할래요!" 버튼을 눌렀을 경우
			// 각 모든 변수들 초기화
			player.setBalance(50000);
			dealer.setBalance(100000);
			cardLayout.next(contentPane);
			player.init();
			dealer.init();
			playerCard[playerCard.length - 1].remove(cardPanel[cardPanel.length - 1]);
			cardPanel[cardPanel.length - 1].removeAll();
			playerCard[0].remove(cardPanel[0]);
			cardPanel[0].removeAll();
			playerCard[playerCard.length - 1].revalidate();
		    playerCard[playerCard.length - 1].repaint();
		    playerCard[0].revalidate();
		    playerCard[0].repaint();
		    isStand = 0;
		    isDoubleDown = 0;
		    isBust = 0;
		    BetDone = 0;
		    isBlackjack = 0;
		    betAmount[0].setText("");
		    betAmount[0].setEnabled(true);
		    betAmount[1].setText("");
		    score[0].setText("");
		    score[score.length - 1].setText("");
		    playersouthPanel[0].add(betButton,"East");
		    chatSystem.remove(overlayPanel);
		    chatSystem.repaint();
			chatText.append("♠ ♡ ♣ ◇ : 베팅 금액을 입력해 주세요!\n");
			
		// "같이 할래요!" 버튼은 구현 못함...
		}else if(actionCommand == "블랙잭이 처음이에요ㅠ") { // "블랙잭이 처음이에요ㅠ" 버튼을 눌렀을 경우
			cardLayout.last(contentPane); // 마지막 패널(규칙패널) 표시
		}else if(actionCommand == "그만 하고 싶어요!") { // "그만 하고 싶어요!" 버튼을 눌렀을 경우
			setVisible(false); // 창 안보이게 함
			System.exit(1); // 시스템 종료
		}else if (actionCommand == "Send") { // 전송 버튼을 눌렀을 경우
	        String chatMessage = chatInput.getText(); // 채팅 내용창에 입력 내용 저장
	        if(!chatMessage.isEmpty()) { // 채팅 입력 내용이 있으면
	        	chatText.append("Player : " + chatMessage + "\n"); // 채팅 내용창에 삽입
	        	chatInput.setText(""); // 텍스트 필드에 내용을 지움
	        }
	    }else if (actionCommand == "Betting") { // "Betting" 버튼을 눌렀을 경우 
	    	 // 베팅이 올바르지 않은 경우
	    	if(betAmount[0].getText().isEmpty()) { // 베팅금액 입력되지 않았을 경우
	    		chatText.append("♠ ♡ ♣ ◇ : 베팅 금액을 입력하지 않았습니다.\n 베팅을 해주세요.\n");
	    	}
	    	// 베팅금액이 현재 잔액보다 큰 경우
	    	else if(player.getBalance() < Integer.parseInt(betAmount[0].getText())) {
	    		chatText.append("♠ ♡ ♣ ◇ : 베팅 금액이 현재 잔액 보다 큽니다..\n 다시 베팅 해주세요.\n");
	    	}
	    	else if(!betAmount[0].getText().isEmpty()) { // 베팅금액이 올바르게 입력 되었을 경우
		    	player.setBetAmount(Integer.parseInt(betAmount[0].getText())); // 플레이어의 베팅금액 세팅
		    	reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance())); // 현재 잔액 조정
		    	playersouthPanel[0].remove(betButton); // 베팅 버튼 삭제
		    	playersouthPanel[0].revalidate(); // 하단 패널을 다시 그리도록 설정
		    	playersouthPanel[0].repaint(); // 하단 패널을 그림
		    	// 딜러가 플레이어가 베팅한 만큼 베팅이 가능한지 확인
		    	if(dealer.getBalance() >= Integer.parseInt(betAmount[0].getText())) {
		    		dealer.setBetAmount(Integer.parseInt(betAmount[0].getText())); // 플레이어가 베팅한 만큼 베팅
		    		betAmount[1].setText(betAmount[0].getText()); // 베팅금액 표시
			    	
		    	}else { // 딜러가 베팅이 불가능 하다면
		    		int gap = player.getBetAmount() - dealer.getBalance(); // 딜러가 부족한 돈 계산
		    		player.loseBet(gap); // 부족한 만큼 플레이어의 베팅 금액 감소
		    		betAmount[0].setText(Integer.toString(player.getBetAmount())); // 변경된 베팅금액 표시
		    		reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance())); // 플레이어의 현재 잔액 표시
		    		dealer.setBetAmount(dealer.getBalance()); // 딜러가 가진 모든 잔액 베팅
		    		reMoney[1].setText("Dealer 현재 잔액 : 0"); // 딜러의 현재 잔액 0으로 표시
		    		betAmount[1].setText(Integer.toString(dealer.getBetAmount())); // 딜러의 베팅 금액 표시
			    	
		    	}
		    	reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance())); // 딜러의 현재 잔액 표시
		    	
		    	BetDone = 1; // 베팅이 완료되었음을 나타냄
		    	// 채팅창에 게임 시작을 알림
		    	chatText.append("♠ ♡ ♣ ◇ : 베팅이 완료되었습니다. 카드를 뽑겠습니다.\n");
		    	// 플레이어와 딜러가 카드를 2번씩 뽑음
				player.drawCard();
				player.drawCard();
				dealer.drawCard();
				dealer.drawCard();
				
				// 플레이어가 뽑은 카드 출력
				printMycards();
				score[0].setText(Integer.toString(player.getTotal())); // 점수 표시
				// 딜러가 뽑은 카드 출력
				printDealercards();
				
				// 블랙잭인지 확인 (동일한 부분은 주석 생략)
				if(player.total == 21 && dealer.total != 21) { // 플레이어가 블랙잭일 경우
					chatText.append("♠ ♡ ♣ ◇ : 플레이어 BlackJack!\n 축하합니다. 플레이어의 승리입니다.\n");
					chatText.append("♠ ♡ ♣ ◇ : 플레이어가 베팅 금액 만큼의 돈을 가져갑니다.\n");
					player.earnMoney((3 * player.getBetAmount())); // 베팅 금액의 3배를 현재 잔액에 더해야 배팅금액의 2배 만큼 돈을 얻게됨
					reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance())); // 현재 잔액 설정
					dealer.loseMoney(player.getBetAmount()); // 딜러가 베팅 금액만큼 돈을 잃음
					reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance())); // 현재 잔액 설정
					isBlackjack = 1; // 블랙잭임을 표시
					isStand = 1; // 스탠드 상태로 설정
					printDealercards(); // 딜러의 모든 카드 출력
					
					// 현재 잔액이 남아있는지 확인
					if(player.getBalance() <= 0) { // 플레이어 현재 잔액 0원
						chatText.append("♠ ♡ ♣ ◇ : 플레이어가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]); // 계속 진행 버튼 삭제
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel); // 게임 종료시 나타나는 패널 화면에 추가
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else if(dealer.getBalance()<=0) { // 딜러 현재 잔액 0원일 경우
						chatText.append("♠ ♡ ♣ ◇ : 딜러가 모든 돈을 잃었습니다ㅠㅠ \n");
						endPanel.remove(endButton[1]);
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel); // 게임 종료시 나타나는 패널 화면에 추가
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else { // 현재 잔액이 모두 남아있을 경우 정상적으로 게임이 종료됨을 표시
				    chatSystem.add(overlayPanel,"North");
					}
				}else if (player.total != 21 && dealer.total == 21) { // 딜러가 블랙잭일 경우
					chatText.append("♠ ♡ ♣ ◇ : 딜러 BlackJack!\n 아쉬워요. 플레이어의 패배입니다.\n");
					chatText.append("♠ ♡ ♣ ◇ : 딜러가 베팅 금액 만큼의 돈을 가져갑니다.\n");
					dealer.earnMoney((3 * player.getBetAmount())); // 배팅금액의 3배를 현재 잔액에 더해야 배팅금액의 2배만큼 돈을 얻게됨
					player.loseMoney(player.getBetAmount()); // 플레이어가 베팅 금액만큼 돈을 잃음
					// 현재 잔액 조정
					reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
					reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
					isBlackjack=1;
					isStand=1;
					printDealercards();
					
					// 현재 잔액이 남아있는지 확인
					if(player.getBalance() <= 0) { // 플레이어 현재 잔액 0원
						chatText.append("♠ ♡ ♣ ◇ : 플레이어가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]); // 계속 진행 버튼 삭제
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel); // 게임 종료시 나타나는 패널 화면에 추가
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else if(dealer.getBalance() <= 0) { // 딜러 현재 잔액 0원
						chatText.append("♠ ♡ ♣ ◇ : 딜러가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]); // 계속 진행 버튼 삭제
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel); // 게임 종료시 나타나는 패널 화면에 추가
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else { // 플레이어, 딜러 모두 현재 잔액이 남아있는 경우
				    chatSystem.add(overlayPanel,"North");
					}
				// 플레이어, 딜러 모두 블랙잭일 경우	
				}else if (player.total == 21 && dealer.total == 21) {
					chatText.append("♠ ♡ ♣ ◇ : 모두 블랙잭임으로 무승부입니다.\n");
					chatText.append("♠ ♡ ♣ ◇ : 무승부임으로 베팅 금액을 돌려받습니다.\n");
					dealer.earnMoney((player.getBetAmount())); // 베팅 금액 만큼 돌려 받음
					player.earnMoney(player.getBetAmount());
					// 현재 잔액 조정
					reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
					reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
					isBlackjack=1;
					isStand=1;
					printDealercards();
				    chatSystem.add(overlayPanel,"North"); // 게임 종료시 나타나는 패널 화면에 추가	
				}
	    	}
	    }
		// "Hit" 버튼을 눌렀고 베팅을 완료했고 블랙잭과 버스트가 아닐 경우
		else if(actionCommand == "Hit" && BetDone == 1 && isBlackjack != 1 && isBust != 1) {
			if(isDoubleDown == 1) { // 더블다운일 경우
				chatText.append("♠ ♡ ♣ ◇ : 더블 다운입니다.\n Hit을 할 수 없습니다.\n");
			}else { // 더블다운이 아니면 Hit 진행
				isHit = 1; // 히트 했음을 표시
				player.drawCard();	// 플레이어가 카드 뽑음
				printMycards(); // 카드 출력
				chatText.append("♠ ♡ ♣ ◇ : 플레이어의 카드를 뽑겠습니다.\n");
				score[0].setText(Integer.toString(player.getTotal())); // 점수 업데이트
				if(dealer.total < 16) { // 딜러의 카드 합이 16보다 작으면 카드 뽑음
					dealer.drawCard();
					chatText.append("♠ ♡ ♣ ◇ : 딜러의 카드를 뽑겠습니다.\n");
					printDealercards();
					
				}
				// 플레이어가 버스트일 경우
				if(player.checkBust() == 1 && dealer.checkBust() != 1) {
					chatText.append("♠ ♡ ♣ ◇ : 플레이어 Bust!\n 아쉬워요. 플레이어가 패배하였습니다.\n");
			    	chatText.append("♠ ♡ ♣ ◇ : Player 점수 : " + Integer.toString(player.total) + "\n");
			    	chatText.append("♠ ♡ ♣ ◇ : Dealer 점수 : " + Integer.toString(dealer.total) + "\n");
			    	chatText.append("♠ ♡ ♣ ◇ : 딜러가 베팅 금액 만큼의 돈을 가져갑니다.\n");
					dealer.earnMoney(2 * (player.getBetAmount())); // 딜러가 베팅 금액만큼 돈을 받음
					// 현재 잔액 조정
					reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
					reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
					isBust = 1;
					isStand = 1;
					printDealercards(); // 딜러의 모든 카드 출력
					// 현재 잔액이 남아있는지 확인
					if(player.getBalance() <= 0) {
						chatText.append("♠ ♡ ♣ ◇ : 플레이어가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]);
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel);
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else if(dealer.getBalance() <= 0) {
						chatText.append("♠ ♡ ♣ ◇ : 딜러가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]);
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel);
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else {
				    chatSystem.add(overlayPanel,"North");
					}
				// 딜러가 버스트일 경우
				}else if(dealer.checkBust() == 1 && player.checkBust() != 1) {
					chatText.append("♠ ♡ ♣ ◇ : 딜러 Bust!\n 축하합니다! 플레이어가 승리하였습니다.\n");
					chatText.append("♠ ♡ ♣ ◇ : Player 점수 : " + Integer.toString(player.total) + "\n");
			    	chatText.append("♠ ♡ ♣ ◇ : Dealer 점수 : " + Integer.toString(dealer.total) + "\n");
			    	chatText.append("♠ ♡ ♣ ◇ : 플레이어가 베팅 금액 만큼 돈을 가져갑니다.\n");
					player.earnMoney(2 * (player.getBetAmount())); // 플레이어가 베팅 금액만큼 돈을 받음
					// 현재 잔액 조정
					reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
					reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
					isBust = 1;
					isStand = 1;
					printDealercards();
					// 현재 잔액이 남아있는지 확인
					if(player.getBalance() <= 0) {
						chatText.append("♠ ♡ ♣ ◇ : 플레이어가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]);
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel);
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else if(dealer.getBalance() <= 0) {
						chatText.append("♠ ♡ ♣ ◇ : 딜러가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]);
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel);
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else {
				    chatSystem.add(overlayPanel,"North");
					}
				// 플레이어와 딜러 모두 버스트일 경우
				}else if(player.checkBust() == 1 && dealer.checkBust() == 1) {
					chatText.append("♠ ♡ ♣ ◇ : 둘 다 버스트하여 무승부입니다.\n");
					chatText.append("♠ ♡ ♣ ◇ : Player 점수 : " + Integer.toString(player.total) + "\n");
			    	chatText.append("♠ ♡ ♣ ◇ : Dealer 점수 : " + Integer.toString(dealer.total) + "\n");
			    	chatText.append("♠ ♡ ♣ ◇ : 무승부임으로 베팅 금액을 돌려받습니다.\n");
					dealer.earnMoney((player.getBetAmount()));
					player.earnMoney(player.getBetAmount());
					// 현재 잔액 조정
					reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
					reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
					isBust = 1;
					isStand = 1;
					printDealercards();
					// 현재 잔액이 남아있는지 확인
					if(player.getBalance() <= 0) {
						chatText.append("♠ ♡ ♣ ◇ : 플레이어가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]);
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel);
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else if(dealer.getBalance() <= 0) {
						chatText.append("♠ ♡ ♣ ◇ : 딜러가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]);
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel);
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else {
				    chatSystem.add(overlayPanel,"North");
					}
				}
			}
		// "Double Down" 버튼을 눌렀고 베팅을 완료했고 블랙잭과 버스트가 아닐 경우
		}else if(actionCommand == "Double Down" && BetDone == 1 && isBlackjack != 1 && isBust != 1) {
			// 더블 다운 시 베팅 금액을 2배로 해야하는데 현재 잔액이 부족한 경우
			if(isHit == 1 || player.getBalance() < player.getBetAmount()) {
				chatText.append("♠ ♡ ♣ ◇ : 잔액이 부족하여 Double Down 할 수 없습니다.\n");
			}else { // 더블 다운이 가능하면
				chatText.append("♠ ♡ ♣ ◇ : 플레이어 Double Down!\n 카드를 뽑겠습니다.\n 플레이어는 stand만 할 수 있습니다.");
				player.setBetAmount(Integer.parseInt(betAmount[0].getText())); // 배팅금액을 한 번 더 베팅하여 2배로 늘림
				betAmount[0].setText(Integer.toString(2 * Integer.parseInt(betAmount[0].getText()))); // 베팅 금액 변경
				reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance())); // 베팅 금액 조정
				playersouthPanel[0].remove(betButton);
		    	playersouthPanel[0].revalidate();
		    	playersouthPanel[0].repaint();
		    	
				player.drawCard(); // 플레이어 카드 뽑기
				printMycards(); // 플레이어 카드 출력
				score[0].setText(Integer.toString(player.getTotal()));
				
				// 딜러의 베팅도 변경
		    	dealer.setBetAmount(Integer.parseInt(betAmount[1].getText()));
		    	betAmount[1].setText(Integer.toString(2 * Integer.parseInt(betAmount[1].getText())));
		    	reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
		    	
		    	// 딜러의 점수가 16보다 작으면 카드 뽑음
				if(dealer.total < 16) {
					dealer.drawCard();
					printDealercards();
					chatText.append("♠ ♡ ♣ ◇ : 딜러의 카드를 뽑겠습니다.\n");
					score[score.length - 1].setText("♠ ♡ ♣ ◇");
				}
				isStand = 1; // 스탠드 상태로 변경
				// 플레이어가 버스트일 경우
				if(player.checkBust() == 1 && dealer.checkBust() != 1) { 
					chatText.append("♠ ♡ ♣ ◇ : 플레이어 Bust!\n 아쉬워요. 플레이어가 패배하였습니다.\n");
					chatText.append("♠ ♡ ♣ ◇ : Player 점수 : " + Integer.toString(player.total) + "\n");
			    	chatText.append("♠ ♡ ♣ ◇ : Dealer 점수 : " + Integer.toString(dealer.total) + "\n");
			    	chatText.append("♠ ♡ ♣ ◇ : 딜러가 베팅 금액 만큼의 돈을 가져갑니다.\n");
					dealer.earnMoney(2 * (player.getBetAmount()));
					reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
					reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
					isBust = 1;
					isStand = 1;
					printDealercards();
					// 현재 잔액이 남아있는지 확인
					if(player.getBalance() <= 0) {
						chatText.append("♠ ♡ ♣ ◇ : 플레이어가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]);
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel);
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else if(dealer.getBalance() <= 0) {
						chatText.append("♠ ♡ ♣ ◇ : 딜러가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]);
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel);
						overlayPanel.repaint();
						chatSystem.add(overlayPanel, "North");
					}else {
				    chatSystem.add(overlayPanel, "North");
					}
				// 딜러가 버스트일 경우
				}else if(dealer.checkBust() == 1 && player.checkBust() != 1) {
					chatText.append("♠ ♡ ♣ ◇ : 딜러 Bust!\n 축하합니다! 플레이어가 승리하였습니다.\n");
					chatText.append("♠ ♡ ♣ ◇ : Player 점수 : " + Integer.toString(player.total) + "\n");
			    	chatText.append("♠ ♡ ♣ ◇ : Dealer 점수 : " + Integer.toString(dealer.total) + "\n");
			    	chatText.append("♠ ♡ ♣ ◇ : 플레이어가 베팅 금액 만큼의 돈을 가져갑니다.\n");
					player.earnMoney(2 * (dealer.getBetAmount()));
					// 현재 잔액 조정
					reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
					reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
					isBust = 1;
					isStand = 1;
					printDealercards();
					// 현재 잔액이 남아있는지 확인
					if(player.getBalance() <= 0) {
						chatText.append("♠ ♡ ♣ ◇ : 플레이어가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]);
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel);
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else if(dealer.getBalance() <= 0) {
						chatText.append("♠ ♡ ♣ ◇ : 딜러가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]);
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel);
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else {
				    chatSystem.add(overlayPanel,"North");
					}
				// 플레이어, 딜러 모두 버스트일 경우	
				}else if(player.checkBust() == 1 && dealer.checkBust() == 1) {
					chatText.append("♠ ♡ ♣ ◇ : 둘 다 버스트하여 무승부입니다.\n");
					chatText.append("♠ ♡ ♣ ◇ : Player 점수 : " + Integer.toString(player.total) + "\n");
			    	chatText.append("♠ ♡ ♣ ◇ : Dealer 점수 : " + Integer.toString(dealer.total) + "\n");
			    	chatText.append("♠ ♡ ♣ ◇ : 무승부임으로 베팅 금액을 돌려받습니다.\n");
					dealer.earnMoney((player.getBetAmount()));
					player.earnMoney(player.getBetAmount());
					// 현재 잔액 조정
					reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
					reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
					isBust = 1;
					isStand = 1;
					printDealercards();
					// 현재 잔액이 남아있는지 확인
					if(player.getBalance() <= 0) {
						chatText.append("♠ ♡ ♣ ◇ : 플레이어가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]);
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel);
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else if(dealer.getBalance() <= 0) {
						chatText.append("♠ ♡ ♣ ◇ : 딜러가 모든 돈을 잃었습니다ㅠㅠ\n");
						endPanel.remove(endButton[1]);
						endPanel.repaint();
						overlayPanel.remove(endPanel);
						overlayPanel.add(endPanel);
						overlayPanel.repaint();
						chatSystem.add(overlayPanel,"North");
					}else {
				    chatSystem.add(overlayPanel,"North");
					}
				}
			}
		// "Stand" 버튼을 눌렀고 베팅을 완료했고 블랙잭, 버스트가 아닐 경우
		}else if(actionCommand == "Stand" && BetDone == 1 && isBlackjack != 1 &&isBust != 1) {
			isStand = 1; // 스탠드 상태로 변경
			while(dealer.total < 16) { // 딜러가 부족한 만큼 카드를 뽑음
				if(dealer.total < 16) {
					dealer.drawCard();
					printDealercards();
					score[score.length - 1].setText(Integer.toString(dealer.getTotal()));
				}
			}
			printDealercards(); // 딜러 카드 출력
			chatText.append("♠ ♡ ♣ ◇ : 게임이 종료되었습니다. 점수를 계산합니다.\n");
			// 플레이어가 버스트일 경우
			if(player.checkBust() == 1 && dealer.checkBust() != 1) {
				chatText.append("♠ ♡ ♣ ◇ : 플레이어 Bust!\n 아쉬워요. 플레이어가 패배하였습니다.\n");
				chatText.append("♠ ♡ ♣ ◇ : Player 점수 : " + Integer.toString(player.total) + "\n");
		    	chatText.append("♠ ♡ ♣ ◇ : Dealer 점수 : " + Integer.toString(dealer.total) + "\n");
		    	chatText.append("♠ ♡ ♣ ◇ : 딜러가 베팅 금액 만큼의 돈을 가져갑니다.\n");
				dealer.earnMoney((2 * player.getBetAmount()));
				// 현재 잔액 조정
				reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
				reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
				isBust = 1;
				isStand = 1;
				printDealercards();
			// 딜러가 버스트일 경우
			}else if(dealer.checkBust() == 1 && player.checkBust() != 1) {
				chatText.append("♠ ♡ ♣ ◇ : 딜러 Bust!\n 축하합니다! 플레이어가 승리하였습니다.\n");
				chatText.append("♠ ♡ ♣ ◇ : Player 점수 : " + Integer.toString(player.total) + "\n");
		    	chatText.append("♠ ♡ ♣ ◇ : Dealer 점수 : " + Integer.toString(dealer.total) + "\n");
		    	chatText.append("♠ ♡ ♣ ◇ : 플레이어가 베팅 금액 만큼의 돈을 얻습니다.\n");
				player.earnMoney((2 * player.getBetAmount()));
				// 현재 잔액 조정
				reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
				reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
				isBust = 1;
				isStand = 1;
				printDealercards();
			// 플레이어, 딜러 모두 버스트일 경우
			}else if(player.checkBust() == 1 && dealer.checkBust() == 1) {
				chatText.append("♠ ♡ ♣ ◇ : 둘 다 버스트하여 무승부입니다.\n");
				chatText.append("♠ ♡ ♣ ◇ : Player 점수 : " + Integer.toString(player.total) + "\n");
		    	chatText.append("♠ ♡ ♣ ◇ : Dealer 점수 : " + Integer.toString(dealer.total) + "\n");
		    	chatText.append("♠ ♡ ♣ ◇ : 무승부임으로 베팅 금액을 돌려받습니다.\n");
				dealer.earnMoney((player.getBetAmount()));
				player.earnMoney(player.getBetAmount());
				// 현재 잔액 조정
				reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
				reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
				isBust = 1;
				isStand = 1;
				printDealercards();
				
			}			
			// 승부 결정
			else if (player.total > dealer.total) { // 플레이어의 점수가 더 높을 경우
		    	chatText.append("♠ ♡ ♣ ◇ : 축하합니다! 플레이어가 승리하였습니다.\n");
		    	chatText.append("♠ ♡ ♣ ◇ : Player 점수 : " + Integer.toString(player.total) + "\n");
		    	chatText.append("♠ ♡ ♣ ◇ : Dealer 점수 : " + Integer.toString(dealer.total) + "\n");
		    	chatText.append("♠ ♡ ♣ ◇ : 플레이어가 베팅 금액 만큼의 돈을 가져갑니다.\n");
				player.earnMoney((2 * player.getBetAmount()));
				// 현재 잔액 조정
				reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
				reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
		    } else if(dealer.total > player.total) { // 딜러의 점수가 더 높을 경우
		    	chatText.append("♠ ♡ ♣ ◇ : 아쉬워요. 플레이어가 패배하였습니다.\n");
		    	chatText.append("♠ ♡ ♣ ◇ : Player 점수 : " + Integer.toString(player.total) + "\n");
		    	chatText.append("♠ ♡ ♣ ◇ : Dealer 점수 : " + Integer.toString(dealer.total) + "\n");
		    	chatText.append("♠ ♡ ♣ ◇ : 딜러가 베팅 금액 만큼의 돈을 가져갑니다.\n");
				dealer.earnMoney((2 * player.getBetAmount()));
				// 현재 잔액 조정
				reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
				reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
		    }
		    else if(player.total == dealer.total) { // 무승부일 경우
		    	chatText.append("♠ ♡ ♣ ◇ : 무승부입니다.\n");
		    	chatText.append("♠ ♡ ♣ ◇ : Player 점수 : " + Integer.toString(player.total) + "\n");
		    	chatText.append("♠ ♡ ♣ ◇ : Dealer 점수 : " + Integer.toString(dealer.total) + "\n");
		    	chatText.append("♠ ♡ ♣ ◇ : 무승부임으로 베팅 금액을 돌려받습니다.\n");
				dealer.earnMoney((player.getBetAmount()));
				player.earnMoney(player.getBetAmount());
				// 현재 잔액 조정
				reMoney[0].setText("Player 현재 잔액 : " + Integer.toString(player.getBalance()));
				reMoney[1].setText("Dealer 현재 잔액 : " + Integer.toString(dealer.getBalance()));
				
				
		    }
			// 현재 잔액이 남아있는지 확인
			if(player.getBalance() <= 0) {
				chatText.append("♠ ♡ ♣ ◇ : 플레이어가 모든 돈을 잃었습니다ㅠㅠ\n");
				endPanel.remove(endButton[1]);
				endPanel.repaint();
				overlayPanel.remove(endPanel);
				overlayPanel.add(endPanel);
				overlayPanel.repaint();
				chatSystem.add(overlayPanel,"North");
			}else if(dealer.getBalance() <= 0) {
				chatText.append("♠ ♡ ♣ ◇ : 딜러가 모든 돈을 잃었습니다ㅠㅠ\n");
				endPanel.remove(endButton[1]);
				endPanel.repaint();
				overlayPanel.remove(endPanel);
				overlayPanel.add(endPanel);
				overlayPanel.repaint();
				chatSystem.add(overlayPanel,"North");
			}else {
		    chatSystem.add(overlayPanel,"North");
			}
			
		// "게임 종료" 버튼을 눌렀을 경우
		}else if(actionCommand == "게임 종료") {
			setVisible(false); // 창 안보이게 함
			System.exit(1); // 시스템 종료
			
		// "계속 진행" 버튼을 눌렀을 경우
		}else if(actionCommand == "계속 진행") {
			chatText.append("♠ ♡ ♣ ◇ : 게임을 다시 시작합니다.\n 게임판을 초기화합니다.\n");
			// 각 모든 변수 초기화
			player.init();
			dealer.init();
			playerCard[playerCard.length - 1].remove(cardPanel[cardPanel.length - 1]);
			cardPanel[cardPanel.length - 1].removeAll();
			playerCard[0].remove(cardPanel[0]);
			cardPanel[0].removeAll();
			playerCard[playerCard.length - 1].revalidate();
		    playerCard[playerCard.length - 1].repaint();
		    playerCard[0].revalidate();
		    playerCard[0].repaint();
		    isStand = 0;
		    isBust = 0;
		    isDoubleDown = 0;
		    BetDone = 0;
		    isBlackjack = 0;
		    betAmount[0].setEditable(true);;
		    betAmount[0].setText("");
		    betAmount[1].setText("");
		    score[0].setText("");
		    score[score.length-1].setText("");
		    playersouthPanel[0].add(betButton,"East");
		    chatSystem.remove(overlayPanel);
		    chatSystem.repaint();
		    chatText.append("♠ ♡ ♣ ◇ : 베팅 금액을 입력해 주세요!\n");
		}else if(actionCommand == "시작 화면") { // "시작 화면" 버튼을 눌렀을 경우
			cardLayout.first(contentPane);// 시작 화면으로 되돌아감
		}
	}
	
}

