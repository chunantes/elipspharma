/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pharma.eclipse.validator.save.impl;

import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 *
 * @author sgl
 */
public class EvenementSaveValidator implements SaveValidator<Evenement>{

    @Override
    public void validate(Evenement bean, GenericService<Evenement> beanService) {
      if (TypeEvenement.VISITE.equals(bean.getTypeEvenement()) && bean.getEssai()==null) {
          throw new ValidationException("evenement.visite", new String[]{"error" }, bean);
      }
    }
    
}
