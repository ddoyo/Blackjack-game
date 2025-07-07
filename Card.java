import java.util.*;

public class Card {
	private String suit, rank; // 카드의 모양과 숫자
	protected int total, drawCount = 0, aceCount = 0; // 점수 계산 변수
	protected int balance; // 잔액
    protected int betAmount; // 베팅금액
	
	void cardinit() { // 변수 초기화
		this.total = 0;
		this.drawCount = 0;
		this.aceCount = 0;
	}
	
	int[][] check = new int[4][13]; // 중복 카드를 확인하는 2차원 배열
	String[] cardHistory = new String[10]; // 뽑았던 카드 목록을 저장할 배열
	
	// 배열을 초기화하는 메서드
	void initarray() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++) {
				this.check[i][j] = 0; // 중복 체크 배열을 0으로 초기화
			}
		}
		
		for(int i = 0; i < this.cardHistory.length; i++) {
			this.cardHistory[i] = null; // 카드 저장 배열을 초기화
		}
	}
	
	int[][] getCheck() { // 카드 중복 체크 배열 반환
		return check;
	}
	
	String[] getCard() { // 카드 저장 배열 반환
		return cardHistory;
	}
	
	int randomSuit() { // 0~3 숫자 랜덤
		Random rand = new Random();
		return rand.nextInt(4);
	}
	
	int randomRank() { // 0~12 숫자 랜덤
		Random rand = new Random();
		return rand.nextInt(13);
	}
	
	void printcardhistory(int t) { // 뽑았던 카드의 목록을 출력하는 함수
		for(int i = 0; i < t; i++) {
			System.out.print(cardHistory[i]+" ");
		}
		System.out.println();
	}
	
	// 숫자에 따라 카드의 모양을 결정하는 메서드
	void changeSuit(int i) {
		switch(i) {
		case 0 : // 0일경우
			this.suit = "♠"; // 스페이드
			break;
		case 1 : // 1일경우
			this.suit = "♣"; // 클로버
			break;
		case 2 : // 2일경우
			this.suit = "♡"; // 하트
			break;
		case 3 : // 3일경우
			this.suit = "◇"; // 다이아
			break;
		default :
			break;
		}
	}
	
	// 숫자에 따라 카드의 숫자를 결정하는 메서드
	void changeRank(int j) {
		switch(j) {
		case 0 :
			this.rank = "Ace";
			break;
		case 1 :
			this.rank = "2";
			break;
		case 2 :
			this.rank = "3";
			break;
		case 3 :
			this.rank = "4";
			break;
		case 4 :
			this.rank = "5";
			break;
		case 5 :
			this.rank = "6";
			break;
		case 6 :
			this.rank = "7";
			break;
		case 7 :
			this.rank = "8";
			break;
		case 8 :
			this.rank = "9";
			break;
		case 9 :
			this.rank = "10";
			break;
		case 10 :
			this.rank = "Jack";
			break;
		case 11 :
			this.rank = "Queen";
			break;
		case 12 :
			this.rank = "King";
			break;
		default :
			break;
		}
	}
	
	// 카드 숫자에 따라 점수를 계산하는 메서드
	void calscore(int j) {
		int score = 0;
		if(j >= 9) { // 카드가 10, jack, queen, king 일 경우 10으로 계산
			score = 10;
		}else if(j != 0) { // 카드가 ace 가 아니고 위의 4개의 카드가 아니라면 그 숫자대로 계산
			score = j + 1;
		}else if(aceCount == 1 && j == 0) { // 에이스를 이미 1로 계산하기로 했으면 이 후에 뽑은 에이스들은 모두 1로 계산
			score = 1;
		}else if(aceCount != 1 && j == 0) { // 에이스를 뽑았으면 점수는 일단 11로 계산
			score = 11;
		}
		
		this.total += score; // 점수 총 합에 지금 카드 숫자를 더함
		
		// 총합이 21이 넘고 에이스를 뽑았고, 총합에서 10을 뺐을 때 21보다 작거나 같고 에스가 1이 아닐 경우
		if((total > 21 && j == 0) && (total - 10 <= 21) && aceCount != 1) {
			aceCount = 1; // 에이스를 1로 계산
			this.total -= 10; // 에이스 값의 변동만큼 10을 뺌
		// 에이스 4장의 카드 중 한장이라도 뽑혔고 에이스가 1이 아니고 총합이 21이 넘을 경우
		} else if((check[0][0] == 1 || check[1][0] == 1 || check[2][0] == 1 || check[3][0] == 1 ) && aceCount != 1 && total > 21) {
			aceCount = 1;
			this.total -= 10;
		}
	}
	
	// 카드 중복 확인 메서드
	void checking(int i, int j) {
		if(this.check[i][j] ==  1) { // 카드가 중복일 경우
			this.checking(this.randomSuit(),this.randomRank()); // 중복이 아닐 때까지 재귀호출
		} else { // 중복이 아니라면
			this.check[i][j] = 1; // 중복 카트 배열에 저장
			this.changeSuit(i); // 모양판단
			this.changeRank(j); // 숫자판단
			this.calscore(j); // 점수계산
			
			this.cardHistory[this.drawCount] = this.suit + " " + this.rank; // 뽑은 카드 배열에 저장
			this.drawCount++; // 카드를 뽑은 횟수 카운트
		}
	}
	
	// 점수를 반환하는 메서드
	int getTotal() {
		return this.total;
	}
}
