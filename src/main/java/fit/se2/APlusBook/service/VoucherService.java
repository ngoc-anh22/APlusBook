package fit.se2.APlusBook.service;

import fit.se2.APlusBook.model.Voucher;
import org.springframework.stereotype.Service;

@Service
public class VoucherService extends BaseService<Voucher> {
    @Override
    protected Class<Voucher> clazz() {
        return Voucher.class;
    }
}
