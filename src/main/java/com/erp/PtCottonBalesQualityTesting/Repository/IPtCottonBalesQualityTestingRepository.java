package com.erp.PtCottonBalesQualityTesting.Repository;

import com.erp.PtCottonBalesQualityTesting.Model.CPtCottonBalesQualityTestingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IPtCottonBalesQualityTestingRepository extends JpaRepository<CPtCottonBalesQualityTestingModel,Integer> {
    @Query(value = "FROM CPtCottonBalesQualityTestingModel model where model.is_delete =0 and model.company_id=?1 and model.quality_testing_no = ?2")
    List<CPtCottonBalesQualityTestingModel> FnShowParticularRecordForUpdate(int companyId, String unitTestingNo);


    @Transactional
    @Modifying
    @Query(value = "UPDATE sm_product_material_stock_new SET quality_status = :status WHERE batch_no = :batchNo AND company_id = :companyId", nativeQuery = true)
    void updateSingleBatchStatus(@Param("batchNo") String batchNo,
                                 @Param("status") String status,
                                 @Param("companyId") int companyId);

}
