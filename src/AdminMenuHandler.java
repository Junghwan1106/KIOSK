import java.util.*;
import java.text.SimpleDateFormat;

public class AdminMenuHandler {
    private MenuContext menuContext;
    private Scanner scanner;

    public AdminMenuHandler(MenuContext menuContext) {
        this.menuContext = menuContext;
        this.scanner = new Scanner(System.in);
    }
    /**             메인 관리자 메뉴           */

    // 메인 관리자 메뉴를 표시합니다.
    public void displayAdminMenu() {
        System.out.println("Admin ToToRo's 페이지다. 이말이양!");
        System.out.println("1. 대기주문 목록");
        System.out.println("2. 완료주문 목록");
        System.out.println("3. 메인 페이지");
        System.out.print("항목을 선택하세요: ");

        int input = 0;

        try{
            input = scanner.nextInt();
        }catch (Exception e){
            System.out.println("잘못된 요청입니다. 다시 입력해주세요.");
            displayAdminMenu();
            return;
        }

        switch (input) {
            case 1:
                displayWaitingOrder();
                break;
            case 2:
                printCompletedOrder();
                break;
            case 3:
                UserMenuHandler.displayMainMenu();
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                displayAdminMenu();
        }
    }
    /**         주문 처리 관련 메소드           */

    // 모든 주문 상세 정보를 출력하는 메소드
    public static void printOrders(List<Order> orders) {
        for (int i=0; i<orders.size(); i++) {
            printOrder(orders.get(i));
            System.out.println();
        }
    }
    // 최근 3개의 주문 상세 정보를 출력하는 메소드
    public static void printRecentOrders(List<Order> orders) { //
        int tempNum=orders.size()-3;
        if (tempNum < 0){
            tempNum =0;
        }
        for (int i = tempNum; i < orders.size(); i++) {
            printOrder(orders.get(i));
            Date date = orders.get(i).getCompleteDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:XXX");
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            String dateString = sdf.format(date);
            System.out.println("완료 일시: " + dateString);
            System.out.println();
        }
    }
    // 선택한 주문 내역을 출력하는 메소드
    private static void printOrder(Order selectedOrder) {
        int num = selectedOrder.getOrderNum();
        System.out.println("대기 번호 : " + num);
        System.out.println("주문 상품 목록 : ");
        UserMenuHandler.printMenuItems(selectedOrder.orderItems);
        System.out.println("주문 총 가격 : " + selectedOrder.getTotalPrice());
        System.out.println("요청 사항: " + selectedOrder.getRequestContent());

        // 날짜는 ISO 8601 형식으로 ex)2023-10-23T17:13:40+00:00
        Date date = selectedOrder.getOrderDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:XXX");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String dateString = sdf.format(date);
        System.out.println("주문 일시: " + dateString);
    }
    /**         대기 중인 주문 처리 관련 함수           */

    //대기 중인 주문 조회 및 완료 화면
    private void displayWaitingOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================================");
        System.out.println("대기 중인 주문 목록입니다.\n");

        System.out.println("[ 대기 주문 목록 ]");
        List<Order> waitingOrders = menuContext.getWaitingOrders();
        if(waitingOrders.isEmpty()){
            System.out.println("대기 중인 주문이 없습니다.");
            System.out.println("========================================");
            UserMenuHandler.displayMainMenu();
        }else {
            printOrders(waitingOrders);

            System.out.println("완료할 주문 대기 번호를 입력해주세요.");
            handleWaitingOrders(waitingOrders);
        }
    }

    // 대기 중인 주문 처리
    private void handleWaitingOrders(List<Order> orders) {
        int input = scanner.nextInt();
        Order selectedOrder = new Order();
        boolean hasSelectedOrder = false;

        for (Order order : orders) {
            if (order.getOrderNum() == input) {
                selectedOrder = order;
                hasSelectedOrder = true;
            }
        }

        if (hasSelectedOrder) {
            confirmCompleteOrder(selectedOrder);
        } else {
            System.out.println("해당 번호에 맞는 주문이 없습니다.");
            System.out.println("다시 입력해주세요.");
            handleWaitingOrders(orders);
        }

    }

    // 완료할 것인지 확인 후 처리하는 부분
    private void confirmCompleteOrder(Order selectedOrder) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("아래 주문을 완료 처리 하시겠습니까?\n");

        printOrder(selectedOrder);

        System.out.println("1. 완료      2. 메뉴판");
        int confirm = scanner.nextInt();
        if(confirm==1){
            setCompleteOrder(selectedOrder);
            resetWaitingOrder(selectedOrder);
            System.out.println("해당 주문을 완료 처리 하였습니다.");
            System.out.println("========================================");
            UserMenuHandler.displayMainMenu();
        }else if(confirm==2){
            System.out.println("========================================");
            UserMenuHandler.displayMainMenu();
        }else {
            System.out.println("잘못된 입력입니다.");
            confirmCompleteOrder(selectedOrder);
        }
    }
    /**         완료된 주문 리스트에 추가/삭제 함수들           */

    // 완료된 주문 리스트에 추가하는 부분
    private void setCompleteOrder(Order selectedOrder) {
        Order order = new Order();
        Date now = new Date();

        List<Item> it = new ArrayList<>();
        for(Item its : selectedOrder.getOrderItems()){
            it.add(its);
        }

        order.setOrderItems(it);
        order.setTotalPrice(selectedOrder.getTotalPrice());
        order.setRequestContent(selectedOrder.getRequestContent());
        order.setOrderDate(selectedOrder.getOrderDate()); // 주문 일시
        order.setCompleteDate(now); // 완료 주문 일시
        order.setOrderNum(selectedOrder.OrderNum);
        menuContext.addToCompleteOrder(order);
    }

    // 대기 중인 리스트에서 삭제하는 부분
    private void resetWaitingOrder(Order selectedOrder) {
        menuContext.getWaitingOrders().remove(selectedOrder);
    }

    // 완료된 주문 목록 출력
    private void printCompletedOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("처리 완료된 주문 목록입니다.\n");

        System.out.println("[ 완료 주문 목록 ]");
        if(!menuContext.getCompletedOrders().isEmpty()) {
            printOrders(menuContext.getCompletedOrders());
        }else{
            System.out.println("완료된 주문이 없습니다.");
        }
        System.out.println("========================================");

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
            UserMenuHandler.displayMainMenu();
        }else {
            System.out.println("잘못된 입력입니다.");
            System.out.println("========================================");
            printCompletedOrder();
        }
    }
}