package fit.se2.APlusBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se2.APlusBook.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
