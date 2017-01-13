/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pharma.eclipse.externe;

import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;

/**
 *
 * @author sgl
 */
public class EssaiExterneService extends GenericServiceImpl<EssaiExterne>{

    
    public EssaiExterneService(EssaiExterneDao essaiExterneDao) {
        super(essaiExterneDao);
    }

    
}
