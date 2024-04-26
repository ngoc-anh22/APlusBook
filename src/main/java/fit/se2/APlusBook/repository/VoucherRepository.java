package fit.se2.APlusBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fit.se2.APlusBook.model.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    
}
