package com.erp.SortTransfer.Repository;

import com.erp.SortTransfer.Model.CSortTransferDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SortTransferDetailsDataRepository extends JpaRepository<CSortTransferDetailsModel, Long> {

    CSortTransferDetailsModel save(CSortTransferDetailsModel sortTransfer);
}
