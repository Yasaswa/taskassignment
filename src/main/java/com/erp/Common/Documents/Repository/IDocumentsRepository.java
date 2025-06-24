package com.erp.Common.Documents.Repository;

import com.erp.Common.Documents.Model.CDocumentsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface IDocumentsRepository extends JpaRepository<CDocumentsModel, Integer> {
	@Query(value = "FROM CDocumentsModel dm order by dm.document_id Desc")
	Page<CDocumentsModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "FROM CDocumentsModel dm where dm.is_delete =0  order by dm.document_id Desc")
	Page<CDocumentsModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "FROM CDocumentsModel dm where dm.is_delete =0 and dm.group_id = ?1 and dm.document_group =?2")
	Page<CDocumentsModel> FnShowParticularRecord(String group_id, String document_group, Pageable pageable);

	@Query(value = "FROM CDocumentsModel dm where dm.document_name =?1 and dm.company_id = ?2 and dm.document_group=?3 and dm.is_delete = 0")
	CDocumentsModel checkIfExist(String documentName, Integer company_id, String document_group);

	@Query(value = "FROM CDocumentsModel docM where docM.document_id =?1")
	CDocumentsModel FnShowParticularRecordForUpdate(int document_id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE am_documents SET is_active = true WHERE group_id IN ?1 AND is_delete = 0", nativeQuery = true)
	void updateDocActive(List<String> groupIds);

}
