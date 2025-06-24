package com.erp.RawMaterial.SmProductRmBomDtl.Service;

import com.erp.RawMaterial.SmProductRmBomDtl.Repository.ISmProductRmBomDtlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CSmProductRmBomDtlServiceImpl implements ISmProductRmBomDtlService {

	@Autowired
	ISmProductRmBomDtlRepository iSmProductRmBomDtlRepository;


}
