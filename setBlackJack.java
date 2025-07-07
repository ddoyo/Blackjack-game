import java.util.*;

public class setBlackJack extends Card {
	
	//블랙잭 게임에 필요한 제어 변수들
	private int doubledown = 0, stand = 0, bust = 0, drawcount = 0;
	
	void init() { //제어 변수들 초기화
		this.doubledown = 0;
		this.stand = 0;
		this.bust = 0;
		this.drawcount = 0;
		this.betAmount = 0;
		this.total = 0;
		this.cardinit();
		this.initarray();
	}
	
	// 카드 뽑기 메소드
	void drawCard() { 
		// 더블다운, 스탠드, 버스트 상태일 경우
		if(this.doubledown == 1 || this.stand == 1 || this.bust == 1) {
			System.out.println("추가 카드를 뽑을 수 없는 상태입니다."); // 카드를 못뽑음
			this.stand = 1; //스탠드 상태로 설정
		}else {
			this.checking(this.randomSuit(), this.randomRank()); // 카드 뽑음
			this.drawcount++; // 카드를 뽑은 횟수 카운트
		}
	} 
	
	void setBalance(int balance) { // 잔액 설정
		this.balance = balance;
	}
	
	int getBalance() { // 현재 잔액 반환
		return this.balance;
	}
	
	void earnMoney(int amount) { // 게임에서 이겼을 경우 잔액 플러스
		this.balance += amount;
	}
	
	void loseMoney(int amount) { // 게임에서 졌을 경우 잔액 마이너스
		this.balance -= amount;
	}
	
	void loseBet(int amount) { // 베팅금액이 맞지 않을 경우 베팅금액을 줄이고 소지금 플러스
		this.betAmount -= amount;
		this.balance += amount;
	}
	
	int getBetAmount() { // 현재 베팅금액 반환
		return this.betAmount;
	}
	
	void setBetAmount(int amount) { // 베팅금액 설정
		this.betAmount += amount;
		this.balance -= amount;
		}
	
	int getDrawCount() { // 카드를 뽑은 횟수 반환
		return this.drawcount;
	}
	
	int checkBust() { // 버스트인지 확인
		if(this.total > 21) { // 카드의 총합이 21점을 초과할 경우
			this.bust = 1; // 버스트 상태로 설정
		}
		return this.bust;
	}
}

