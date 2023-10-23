# ShakeShack Burger 주문 관리 시스템

*ShakeShack Burger* 의 주문 관리 시스템을 구현한 것입니다. <br/>
사용자는 메뉴를 선택하고, 주문을 추가하며, 결제를 진행할 수 있습니다. <br/>
또한 관리자는 대기 중인 주문을 확인하고, 처리할 수 있으며, 상품 생성 및 삭제도 가능합니다.

## 기능 요구사항
- 필수 요구사항

  > **Java 클래스 설계 시 필수 요구사항!**
  >
  > - 메뉴 클래스는 이름, 설명 필드를 가지는 클래스로 만들어주세요.
  > - 상품 클래스는 이름, 가격, 설명 필드를 가지는 클래스로 만들어주세요.
  > - 상품 클래스의 이름, 설명 필드는 메뉴 클래스를 상속받아 사용하는 구조로 개발해주세요.
  > - 주문 클래스도 만들어서 상품 객체를 담을 수 있도록 해주세요.

1. 메인 메뉴판 화면
    - 메인 메뉴판이 출력되며 메뉴판에는 상품 메뉴가 출력 됩니다.
    - 상품 메뉴는 간단한 설명과 함께 출력 되며 최소 3개 이상 출력 됩니다.
    - 상품 메뉴 아래에는 Order(주문)와 Cancel(주문 취소) 옵션을 출력해줍니다.
```java
"SHAKESHACK BURGER 에 오신걸 환영합니다."
        아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.

        [ SHAKESHACK MENU ]
        1. Burgers         | 앵거스 비프 통살을 다져만든 버거
        2. Forzen Custard  | 매장에서 신선하게 만드는 아이스크림
        3. Drinks          | 매장에서 직접 만드는 음료
        4. Beer            | 뉴욕 브루클린 브루어리에서 양조한 맥주

        [ ORDER MENU ]
        5. Order       | 장바구니를 확인 후 주문합니다.
        6. Cancel      | 진행중인 주문을 취소합니다.
```
2. 상품 메뉴판 화면
    - 상품 메뉴 선택 시 해당 카테고리의 메뉴판이 출력됩니다.
    - 메뉴판에는 각 메뉴의 이름과 가격과 간단한 설명이 표시됩니다.
```java
      "SHAKESHACK BURGER 에 오신걸 환영합니다."
      아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.

[ Burgers MENU ]
1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
3. Shroom Burger | W 9.4 | 몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거
3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거
4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거
```
3. 구매 화면
    - 상품 선택 시 해당 상품을 장바구니에 추가할지 확인하는 문구가 출력 됩니다.
    - `1.확인` 입력 시 장바구니에 추가되었다는 안내 문구와 함께 메인 메뉴로 다시 출력 됩니다.
```java
"Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거"
위 메뉴를 장바구니에 추가하시겠습니까?
1. 확인        2. 취소
```
```java
Hamburger 가 장바구니에 추가되었습니다.

"SHAKESHACK BURGER 에 오신걸 환영합니다."
아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.

[ SHAKESHACK MENU ]
1. Burgers         | 앵거스 비프 통살을 다져만든 버거
2. Forzen Custard  | 매장에서 신선하게 만드는 아이스크림
3. Drinks          | 매장에서 직접 만드는 음료
4. Beer            | 뉴욕 브루클린 브루어리에서 양조한 맥주

[ ORDER MENU ]
5. Order       | 장바구니를 확인 후 주문합니다.
6. Cancel      | 진행중인 주문을 취소합니다.
```
4. 주문 화면
    - `5.Order` 입력 시 장바구니 목록을 출력해줍니다.
    - 장바구니에서는 추가된 메뉴들과 총 가격의 합을 출력해줍니다.
    - `1.주문` 입력 시 주문완료 화면으로 넘어가고, `2.메뉴판` 입력 시 다시 메인 메뉴로 돌아옵니다.
```java
아래와 같이 주문 하시겠습니까?

[ Orders ]
ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거

[ Total ]
W 15.8

1. 주문      2. 메뉴판
```
5. 주문완료 화면
    - `1.주문` 입력 시 대기번호를 발급해줍니다.
    - 장바구니는 초기화되고 3초 후에 메인 메뉴판으로 돌아갑니다.
```java
주문이 완료되었습니다!

대기번호는 [ 1 ] 번 입니다.
(3초후 메뉴판으로 돌아갑니다.)
```
```java
"SHAKESHACK BURGER 에 오신걸 환영합니다."
아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.

[ SHAKESHACK MENU ]
1. Burgers         | 앵거스 비프 통살을 다져만든 버거
2. Forzen Custard  | 매장에서 신선하게 만드는 아이스크림
3. Drinks          | 매장에서 직접 만드는 음료
4. Beer            | 뉴욕 브루클린 브루어리에서 양조한 맥주

[ ORDER MENU ]
5. Order       | 장바구니를 확인 후 주문합니다.
6. Cancel      | 진행중인 주문을 취소합니다.
```
6. 주문 취소 화면
    - 메뉴판에서 `6.Cancel` 입력시 주문을 취소할지 확인을 요청하는 문구가 출력 됩니다.
    - `1.확인` 을 입력하면 장바구니는 초기화되고 취소 완료 문구와 함께 메뉴판이 출력 됩니다.
```java
진행하던 주문을 취소하시겠습니까?
1. 확인        2. 취소
```
```java
진행하던 주문이 취소되었습니다.

"SHAKESHACK BURGER 에 오신걸 환영합니다."
아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.

[ SHAKESHACK MENU ]
1. Burgers         | 앵거스 비프 통살을 다져만든 버거
2. Forzen Custard  | 매장에서 신선하게 만드는 아이스크림
3. Drinks          | 매장에서 직접 만드는 음료
4. Beer            | 뉴욕 브루클린 브루어리에서 양조한 맥주

[ ORDER MENU ]
5. Order       | 장바구니를 확인 후 주문합니다.
6. Cancel      | 진행중인 주문을 취소합니다.
```
## 기능

### 사용자(User) 기능
1. **메인 메뉴 출력**: 메인 메뉴를 화면에 출력합니다.
2. **상품 선택 및 확인**: 버거, 아이스크림, 음료, 맥주 카테고리의 상품들을 출력하고 선택하여 장바구니에 추가하는 기능입니다.
3. **주문하기**: 장바구니에 담긴 상품들을 확인하고 주문하는 기능입니다.
4. **주문 취소하기**: 진행 중인 주문을 취소하는 기능입니다.

### 관리자(Admin) 기능
1. **대기중인 주문 조회 및 완료 처리**: 대기중인 모든 주문들을 조회하고 완료처리 할 수 있는 기능입니다.
2. **완료된 주문 조회**: 완료된 모든 주문들을 조회하는 기능입니다.

## 코드 구조

코드는 크게 `UserMenuHandler`와 `AdminMenuHandler` 두 개의 클래스로 나뉩니다.

- `UserMenuHandler`: 사용자가 시스템과 상호작용할 때 필요한 로직이 구현되어 있습니다.
- `AdminMenuHandler`: 관리자가 시스템과 상호작용할 때 필요한 로직이 구현되어 있습니다.

각 핸들러 클래스는 다음과 같은 일련의 과정으로 작동합니다:

1. 특정 메뉴나 페이지를 화면에 출력(display)
2. 사용자로부터 입력 받음(handle input)
3. 입력에 따라 적절한 동작 수행(execute action)

## 예외 처리 <br/>
### 1. Scanner 예외 처리 <br/>
```java
private static void handleMainMenuInput() {
		Scanner scanner = new Scanner(System.in);
		int input = 0;
		try{
			input = scanner.nextInt();
		}catch (InputMismatchException e){
			System.out.println("잘못된 요청입니다. 다시 입력해주세요");
			displayMainMenu();
		}
```
```java
private static void displayAdminMenu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("이곳은 관리자 페이지입니다.");

		System.out.println("1. 대기주문 목록");
		System.out.println("2. 완료주문 목록");
		System.out.println("3. 상품 생성");
		System.out.println("4. 상품 삭제");
		System.out.println("5. 메인 페이지");
		System.out.print("항목을 선택하세요: ");

		int input = 0;
    
		try{
			input = scanner.nextInt();
		}catch (Exception e){
			System.out.println("잘못된 요청입니다. 다시 입력해주세요.");
		}
    
		switch (input) {
    
			--중략-- 
      
			default:
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				displayAdminMenu();
				break;
		}
	}
```
```java
	private static void printCompletedOrder() {
		Scanner scanner = new Scanner(System.in);

		-- 중략 --
    
		System.out.println("1. 메뉴판");
		int input = 0;
		try{
			input = scanner.nextInt();
		}catch (Exception e){
			System.out.println("잘못된 요청입니다. 다시 입력하세요!");
			printCompletedOrder();
		}
		if(input==1){
			System.out.println("========================================");
			displayMainMenu();
		}else {
			System.out.println("잘못된 입력입니다.");
			System.out.println("========================================");
			printCompletedOrder();
		}
	}
```
* 메소드 내에서 Scanner를 통해 입력값을 int형으로 받고 있습니다. <br/>
* 만약, 입력 받은 값이 int형 변수인 input에 저장이 원활히 되지 않을 경우 <br/>
  **"잘못된 요청입니다. 다시 입력해주세요."** 를 출력하도록 예외처리를 해두었습니다. <br/> <br/>
* 입력받은 값이 int형은 맞으나, swith ~ case문 혹은 if ~ else문 내부 조건따라 다시 입력받거나 메인페이지로 이동합니다.<br/>
* 다시 입력을 받기 위해 switch ~ case문의 default의 코드가 실행됨으로써 **재귀 호출**을 수행합니다. <br/><br/>

## *README에 설명 더 추가 예정*

## 환경설정<br/>
Language : Java<br/>
IDLE : IntelliJ community<br/>
JDK : 17.0.8.1 LTS <br/>