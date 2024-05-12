package fit.se2.APlusBook.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fit.se2.APlusBook.dto.Cart;
import fit.se2.APlusBook.dto.CartItem;
import fit.se2.APlusBook.model.Book;
import fit.se2.APlusBook.model.Order;
import fit.se2.APlusBook.model.OrderItem;
import fit.se2.APlusBook.model.User;
import fit.se2.APlusBook.repository.BookRepository;
import fit.se2.APlusBook.repository.OrderRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepositoryService;

    @RequestMapping(value = { "/cart/checkout" }, method = RequestMethod.GET)
    public String cartCheckout(final Model model,
                               final HttpServletRequest request,
                               final HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();

        session.setAttribute("totalItems", getTotalItems(request));
        session.setAttribute("totalPrice", calculateTotalPrice(request));

        return "customer/shoppingCart"; // -> đường dẫn tới View.
    }

    @RequestMapping(value = { "/cart/checkout" }, method = RequestMethod.POST)
    public String cartFinished(final Model model,
                               final HttpServletRequest request,
                               final HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        List<Book> productList = bookRepositoryService.findAll();
        model.addAttribute("productList", productList);

        // Lấy thông tin khách hàng
        String customerFullName = request.getParameter("customerFullName");
        String customerEmail 	= request.getParameter("customerEmail");
        String customerPhone 	= request.getParameter("customerPhone");
        String customerAddress 	= request.getParameter("customerAddress");

        // tạo hóa đơn + với thông tin khách hàng lấy được
        Order saleOrder = new Order();
        User user = new User();
        user.setFullName(customerFullName);
        user.setEmail(customerEmail);
        user.setPhoneNum(customerPhone);
        user.setAddress(customerAddress);

        saleOrder.setId(System.currentTimeMillis()); // mã hóa đơn
        saleOrder.setTotalPrice(calculateTotalPrice(request).toString());

        // lấy giỏ hàng
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        BigDecimal tol = cart.getTotalPrice();

        session.setAttribute("totalItems", getTotalItems(request));
        session.setAttribute("totalPrice", calculateTotalPrice(request));

        // lấy sản phẩm trong giỏ hàng
        for (CartItem cartItem : cart.getCartItems()) {
            tol = tol.add(cartItem.getPriceUnit());
            OrderItem saleOrderProducts = new OrderItem();
            saleOrderProducts.setBook(bookRepositoryService.getById(cartItem.getProductId()));
            saleOrderProducts.setOrderQuantity(cartItem.getQuantity());

            saleOrder.addSaleOrderItem(saleOrderProducts);
        }

        // lưu vào database
        orderRepository.save(saleOrder);

        // thực hiện reset lại giỏ hàng của Session hiện tại
        session.setAttribute("cart", null);
        session.setAttribute("totalItems", 0);

        return "customer/cart_success"; // -> đường dẫn tới View.

    }

    @RequestMapping(value = { "/ajax/addToCart" }, method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> ajax_AddToCart(final Model model,
                                                              final HttpServletRequest request,
                                                              final HttpServletResponse response,
                                                              final @RequestBody CartItem cartItem) throws UnsupportedEncodingException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        Cart cart = null;

        if (session.getAttribute("cart") != null) {
            cart = (Cart) session.getAttribute("cart");
        } else {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        List<CartItem> cartItems = cart.getCartItems();

        boolean isExists = false;
        for (CartItem item : cartItems) {
            if (item.getProductId() == cartItem.getProductId()) {
                isExists = true;
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
            }
        }
        if (!isExists) {
            Book productInDb = bookRepositoryService.getById(cartItem.getProductId());

            cartItem.setProductName(productInDb.getTitle());
            cartItem.setPriceUnit(productInDb.getPrice());
            cartItem.setAvatar(productInDb.getAvatar());
            cartItem.setCategory(productInDb.getCategory().getName());

            cart.getCartItems().add(cartItem); // thêm mới sản phẩm vào giỏ hàng
        }

        session.setAttribute("totalPrice", calculateTotalPrice(request));

        // tính tổng tiền
//		this.calculateTotalPrice(request);

        //set giá trị avatar cho cartItem
//		List<ProductEntity> productList = productService.findAll();
//		getAvatar(cartItem, request, productList);
//
//		//set giá trị category cho cartItem
//		getCategory(cartItem, request, productList);

        // trả về kết quả
        Map<String, Object> jsonResult = new HashMap<String, Object>();
        jsonResult.put("code", 200);
        jsonResult.put("status", "TC");
        jsonResult.put("totalItems", getTotalItems(request));

        // lưu totalItems vào session
        // tất cả các giá trị lưu trên session đều có thể truy cập được từ View
        session.setAttribute("totalItems", getTotalItems(request));
        // lưu totalPrice vào session
        session.setAttribute("totalPrice", calculateTotalPrice(request));

        return ResponseEntity.ok(jsonResult);
    }

    //Tính tổng tiền
    private BigDecimal calculateTotalPrice(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute("cart") == null) {
            return BigDecimal.ZERO;
        }

        Cart cart = (Cart) httpSession.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItems();

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cartItems) {
            int ii =  item.getQuantity();
            BigDecimal tpi = item.getPriceUnit().multiply(BigDecimal.valueOf(ii));
            total = total.add(tpi);
        }

        return total;

    }

    private int getTotalItems(final HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute("cart") == null) {
            return 0;
        }

        Cart cart = (Cart) httpSession.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItems();

        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getQuantity();
        }

        return total;
    }


    @RequestMapping(value = { "/ajax/updateQuantity" }, method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> ajax_UpdateCartItem(final Model model,
                                                                   final HttpServletRequest request,
                                                                   final HttpServletResponse response,
                                                                   final @RequestBody CartItem cartItem) throws UnsupportedEncodingException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        List<CartItem> cartItems = cart.getCartItems();
        setCurrenyQuantity(cartItem, request);

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        jsonResult.put("code", 200);
        jsonResult.put("status", "TC");
        jsonResult.put("totalItems", getTotalItems(request));
        jsonResult.put("cartItems", cartItems);

        session.setAttribute("totalItems", getTotalItems(request));
        session.setAttribute("totalPrice", calculateTotalPrice(request));

        return ResponseEntity.ok(jsonResult);
    }

    //Hàm nhận cartItem, request để set số lượng hiện tại của item trên data của session
    private void setCurrenyQuantity(final CartItem cartItem, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        List<CartItem> cartItems = cart.getCartItems();

        int currentQuantity = 0;
        var size = cartItems.size();
        for (var i = 0; i<size; i++) {
            if (cartItem.getProductId()==cartItems.get(i).getProductId()) {
                currentQuantity = cartItems.get(i).getQuantity() + cartItem.getQuantity();
                cartItems.get(i).setQuantity(currentQuantity);
                if (cartItems.get(i).getQuantity()<=0) {
                    cartItems.remove(cartItems.get(i));
                }
            }
        }
    }

    @RequestMapping(value = { "/ajax/deleteItems" }, method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> ajax_DeleteCartItem(final Model model,
                                                                   final HttpServletRequest request,
                                                                   final HttpServletResponse response,
                                                                   final @RequestBody CartItem cartItem) throws UnsupportedEncodingException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItems();

        DeleteItem(cartItem, request);

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        jsonResult.put("code", 200);
        jsonResult.put("status", "TC");
        jsonResult.put("totalItems", getTotalItems(request));

        return ResponseEntity.ok(jsonResult);
    }

    private void DeleteItem(final CartItem cartItem, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        // Lấy danh sách sản phẩm đang có trong giỏ hàng
        List<CartItem> cartItems = cart.getCartItems();

        var size = cartItems.size();
        for (var i = 0; i<size; i++) {
            if (cartItem.getProductId()==cartItems.get(i).getProductId()) {
                cartItems.remove(cartItems.get(i));
            }
        }
    }
}
