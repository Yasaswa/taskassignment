package com.erp.CmBanksList.Repository;

import com.erp.CmBanksList.Model.CCmBanksListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ICmBanksListRepository extends JpaRepository<CCmBanksListModel, Integer> {

	@Query(value = "FROM CCmBanksListModel model where model.is_delete =0 and model.bank_id = ?1 and model.company_id = ?2")
	CCmBanksListModel FnShowParticularRecordForUpdate(int bank_id, int company_id);

	@Query(value = "FROM CCmBanksListModel model where model.is_delete =0 and model.bank_name = ?1 and model.company_id = ?2")
	CCmBanksListModel checkIfNameExist(String bank_name, int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_banks_List WHERE ( bank_name = ?1 or bank_short_name = ?2 ) and company_id = ?3 and is_delete = 0")
	CCmBanksListModel checkIfNameExist(String bank_name, String bank_short_name, int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_banks_List WHERE (bank_name = ?1 or (?2 IS NOT NULL AND bank_short_name = ?2)) and is_delete = 0")
	CCmBanksListModel getCheck(String bank_name, String bank_short_name, int company_id);


}
