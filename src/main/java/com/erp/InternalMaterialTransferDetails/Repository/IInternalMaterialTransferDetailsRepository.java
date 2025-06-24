package com.erp.InternalMaterialTransferDetails.Repository;

import com.erp.InternalMaterialTransferDetails.Model.CInternalMaterialTransferDetailsModel;
import com.erp.PtMaterialLoanMaster.Model.CPtMaterialLoanDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IInternalMaterialTransferDetailsRepository extends JpaRepository<CInternalMaterialTransferDetailsModel,Integer> {


    @Query(value = "FROM CInternalMaterialTransferDetailsModel model where model.is_delete =0 and model.inter_material_transfer_details_id = ?1 and model.company_id = ?2")
    CPtMaterialLoanDetailsModel FnShowParticularRecordForUpdate(int inter_material_transfer_details_id, int company_id);


    @Modifying
    @Transactional
    @Query(value = "update pt_inter_material_transfer_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where inter_material_transfer_no = ?1 and financial_year = ?2 and company_id = ?3", nativeQuery = true)
    void updateStatus(String inter_material_transfer_no,  String financialYear, int companyId);

    @Modifying
    @Transactional
    @Query(value = "update pt_inter_material_transfer_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?2 where inter_material_transfer_master_id = ?1 and is_delete = 0", nativeQuery = true)
    void deleteInternalMaterialDetails(int inter_material_transfer_master_id, String userName);

    @Query(value = "SELECT * FROM ptv_inter_material_transfer_details WHERE inter_material_transfer_no = ?1 and company_id = ?3 and financial_year = ?2", nativeQuery = true)
    List<Map<String, Object>> FnShowInternalTransferDetailsRecords(String interMaterialTransferNo, String financialYear, int companyId);
    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete= false and model.day_closed = false and model.product_rm_id IN ?1 and company_id = ?2 and closing_balance_quantity > 0 and godown_id = ?3")
    List<CSmProductRmStockDetailsModel> FnGetAllProductRmStockDetailsRawMaterials(List<String> distinctMaterialIds, Integer fromCompanyId, Integer fromGodownId);
}
