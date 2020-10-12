/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 *
 *  @author  STEALTH
 *
CrudService interface
 * @param <T> to manage an entity
 * @param <ID> Resource id type
 *
 */
package com.stealth.yash.FaceRecognition.service;

import java.util.Set;

public interface CrudService<T, ID> {

    /**
     * Find all resources
     */
    Set<T> findAll();

    /**
     * Find resource by id.
     *
     * @param id Resource id
     * @return resource
     */
    T findById(ID id);

    /**
     * Save object
     *
     * @param object  resource object
     * @return resource
     */
    T save(T object);

    /**
     * Delete existing resource.
     *
     * @param object Resource object
     */
    void delete(T object);


    /**
     * Delete all existing records having indicated id
     * @param id resource id
     */
    void deleteById(ID id);
}
