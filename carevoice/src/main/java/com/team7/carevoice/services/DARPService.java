package com.team7.carevoice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team7.carevoice.model.DARP;
import com.team7.carevoice.repository.DARPRepository;

@Service
public class DARPService {
	
	@Autowired
	private DARPRepository darpRepository;

	public List<DARP> findAllByPatientId(Long patientId) {
        return darpRepository.findByPatientId(patientId);
    }

	public DARP getDARPById(Long id) {
		return darpRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("DARP not found with ID: " + id));
	}

	public DARP updateDARP(Long id, DARP updatedDARP) {
		Optional<DARP> existingDARPOpt = darpRepository.findById(id);
		if(!existingDARPOpt.isPresent()) {
			return null;
		}
		DARP existingDARP = existingDARPOpt.get();

		if(updatedDARP.getAction() != null) {
			existingDARP.setAction(updatedDARP.getAction());
		}
		if(updatedDARP.getPlan() != null) {
			existingDARP.setPlan(updatedDARP.getPlan());
		}
		if(updatedDARP.getData() != null) {
			existingDARP.setData(updatedDARP.getData());
		}
		if(updatedDARP.getResponse() != null) {
			existingDARP.setResponse(updatedDARP.getResponse());
		}

		return darpRepository.save(existingDARP);
	}


	//To-do: save method
	
}
