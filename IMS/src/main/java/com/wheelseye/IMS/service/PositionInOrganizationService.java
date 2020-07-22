package com.wheelseye.IMS.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wheelseye.IMS.model.PositionInOrganization;
import com.wheelseye.IMS.repository.PositionInOrganizationRepository;

@Service
@Transactional
public class PositionInOrganizationService {
	@Autowired
	PositionInOrganizationRepository repo;
	
	public List<PositionInOrganization> listAll() {
        return repo.findAll();
    }
	
	public void save(PositionInOrganization positionInOrganization)
	{
		repo.save(positionInOrganization);
	}
	
	public PositionInOrganization get(Long id)
	{
		return repo.findById(id).get();
	}
	
	public void delete(Long id)
	{
		repo.deleteById(id);;
	}
}
