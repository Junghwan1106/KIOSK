import java.util.Date;
import java.util.List;

public class Order {

    int OrderNum; // 주문 대기 번호
    List<Item> orderItems; // 주문 상품 목록
    int orderCnt; // 총 수량
    double totalPrice; // 총 가격
    String requestContent; // 주문 요청 사항
    Date orderDate; // 주문 일시
    Date completeDate; // 완료주문 일시


    // getter() , setter()
    public List<Item> getOrderItems() {
        return orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setOrderItems(List<Item> orderItems) {
        this.orderItems = orderItems;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public int getOrderNum() {
        return OrderNum;
    }

    public void setOrderNum(int orderNum) {
        OrderNum = orderNum;
    }

    public void generateOrderCnt() {
        this.orderCnt = orderItems.size();
    }
}
