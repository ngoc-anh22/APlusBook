package fit.se2.APlusBook.dto;

import java.math.BigDecimal;

public class CartItem {
    // mã sản phẩm
    private int productId;

    // tên sản phẩme
    private String productName;

    // số lương sản phẩm
    private int quantity;

    // đơn giá sản phẩm
    private BigDecimal priceUnit;

    // avatar
    private String avatar;

    // category
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quanlity) {
        this.quantity = quanlity;
    }

    public BigDecimal getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(BigDecimal priceUnit) {
        this.priceUnit = priceUnit;
    }

}
