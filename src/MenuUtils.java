//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.TimeZone;
//
//public class MenuUtils {
//    // 선택한 주문 내역 출력
//    private void printOrder(Order selectedOrder) {
//        int num = selectedOrder.getOrderNum();
//        System.out.println("대기 번호 : " + num);
//        System.out.println("주문 상품 목록 : ");
//        UserMenuHandler.printMenuItems(selectedOrder.orderItems);
//        System.out.println("주문 총 가격 : " + selectedOrder.getTotalPrice());
//        System.out.println("요청 사항: " + selectedOrder.getRequestContent());
//
//        // 날짜는 ISO 8601 형식으로 ex)2016-10-27T17:13:40+00:00
//        Date date = selectedOrder.getOrderDate();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:XXX");
//        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
//        String dateString = sdf.format(date);
//        System.out.println("주문 일시: " + dateString);
//    }// printOrder() of the end
//    // 모든 주문 상세 출력
//    public static void printOrders(List<Order> orders) {
//        for (int i=0; i<orders.size(); i++) {
//            printOrder(orders.get(i));
//            System.out.println();
//        }
//    }
//}