package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Experty;

public interface ExpertiseService extends CrudService<Experty, Long> {

    Experty findByProfessorIdAndExpertyID(Long professorId, Long ExpertyID);

}
