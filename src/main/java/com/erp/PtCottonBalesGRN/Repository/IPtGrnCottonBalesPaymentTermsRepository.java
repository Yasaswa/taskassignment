package com.erp.PtCottonBalesGRN.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.PtCottonBalesGRN.Model.CPtGrnCottonBalesPaymentTermsModel;

import javax.transaction.Transactional;
import java.util.List;


public interface IPtGrnCottonBalesPaymentTermsRepository extends JpaRepository<CPtGrnCottonBalesPaymentTermsModel, Integer> {

	@Query(value = "FROM CPtGrnCottonBalesPaymentTermsModel model where model.is_delete =0 and model.grn_cotton_bales_payment_terms_transaction_id = ?1 and model.company_id = ?2")
	CPtGrnCottonBalesPaymentTermsModel FnShowParticularRecordForUpdate(int grn_cotton_bales_payment_terms_transaction_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_grn_cotton_bales_payment_terms set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where goods_receipt_no = ?1 and financial_year = ?2 and company_id = ?3", nativeQuery = true)
	void updateStatus(String goodsReceiptNo, String financialYear, int companyId);

	@Modifying
	@Transactional
	@Query(value = "update pt_grn_cotton_bales_payment_terms set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?2 where grn_cotton_bales_master_transaction_id=?1 and is_delete=0", nativeQuery = true)
	void deleteGRNCottonBalesPaymentTerms(int goodsReceiptMasterTransactionId, String userName);

	@Query(value = "from CPtGrnCottonBalesPaymentTermsModel model where model.is_delete = 0 and model.goods_receipt_no = ?1 and model.goods_receipt_version = ?2 and model.financial_year = ?3 and model.company_id = ?4 and model.is_delete = 0")
    List<CPtGrnCottonBalesPaymentTermsModel> FnShowGRNCottonBalesPaymentTermsRecords(String goodsReceiptNo, int goodsReceiptVersion, String financialYear, int companyId);
}
