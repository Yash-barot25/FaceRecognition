/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 *
 *  @author  STEALTH
 *
ExpertiseService interface extending CRUDService
 * @param <Experty> to manage the department entity
 * @param <Long> Resource id type
 *
 */
package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Experty;

public interface ExpertiseService extends CrudService<Experty, Long> {

    // query method for finding experty by ProfessorID and ExpertyID
    Experty findByProfessorIdAndExpertyID(Long professorId, Long ExpertyID);

}
