/**
 * Fonction en charge de lancer les fonctions javascript en charge de préparer
 * chaque page.
 */
function preparePage() {
	prepareMandatory();
	resizeMenuButton();
}

function clearMenuButton(){
	jQuery("#addContactButton_menu>div>ul>li:lt(8)").remove();
	jQuery("#addPersonneButton_menu>div>ul>li:lt(3)").remove();
	jQuery("#stockButtonRacourci_menu>div>ul>li:lt(5)").remove();
	jQuery("#produitAddButton_menu>div>ul>li:lt(3)").remove();
}

/**
 * Fonction en charge de parcourir les éléments mandatory et de leur ajouter une *
 * en fin.
 */
function prepareMandatory() {
	jQuery('.mandatory').each(function() {
		var $mandatoryItem = jQuery(this);
		if(!$mandatoryItem.hasClass('mandatory-decorated')) {
			$mandatoryItem.html($mandatoryItem.attr("innerHTML") + "<span>&nbsp;*</span>");
			$mandatoryItem.addClass('mandatory-decorated');
		}
	});
}

function expand(){
	var tableau = jQuery("#idsNodesToExpand").val().split(",");
	for (i in tableau) {
		if (tableau[i] != "") {
			jQuery("#" + tableau[i]).expand();
		}
	}
}

/**
 * Fonction permettant de gérer le cochage/décochage de tous les éléments input
 * checkbox.
 * 
 * @param elt
 *            Case à cocher All.
 */
function selectAll(elt) {
	jQuery("INPUT:checkbox").attr('checked',
			jQuery('#' + elt.id).is(':checked'));
}

/**
 * Fonction en charge de vérifier que la checkbox est la seule cochée pour la
 * liste des checkbox portant la classe passée en paramètre.
 * 
 * @param className
 *            Nom de la classe du groupe des checkbox à inspecter (sans le .).
 * @param object
 *            CheckBox à cocher.
 */
function checkUniqueness(className, object) {
	value = object.checked;
	var classWithDot = "." + className;
	jQuery(classWithDot).attr("checked", false)
	object.checked=value;
}

/**
 * Fonction chargée de désactiver les styles appliqués aux habilitations
 * sélectionnées dans les tableaux de l'onglet contact de l'essai clinique.
 */
function disableHabilitationSelectionStyle() {
	jQuery("#dc_outpanel_habilitations TR.ui-state-highlight").each(function() {
		disableSelectionStyle(jQuery(this), 'ui-state-highlight');
	});
}


/**
 * Fonction chargée de désactiver les styles appliqués aux conditionnements
 * sélectionnées dans les tableaux de l'onglet prescriptions d'un produit.
 */
function disableConditionnementSelectionStyle() {
	jQuery("#conditionnements_data TR.ui-state-highlight").each(function() {
		disableSelectionStyle(jQuery(this), 'ui-state-highlight');
	});
}

/**
 * Fonction chargée de désactiver les styles appliqués aux details stockages
 * sélectionnées dans les tableaux de l'onglet logistique des produits.
 */
function disableStockageSelectionStyle() {
	jQuery("#stockages_data TR.ui-state-highlight").each(function() {
		disableSelectionStyle(jQuery(this), 'ui-state-highlight');
	});
}

/**
 * Fonction chargée de désactiver les styles appliqués aux details stockages retours
 * sélectionnées dans les tableaux de l'onglet logistique des produits.
 */
function disableRetourSelectionStyle() {
	jQuery("#stockages_retours_data TR.ui-state-highlight").each(function() {
		disableSelectionStyle(jQuery(this), 'ui-state-highlight');
	});
}


/**
 * Fonction chargée de désactiver les styles "selection" appliqués au tableau dont l'identifiant est en parametre.
 * @param data Id du tableau
 */
function disableSelectionStyleForAll(data) {
	var s = '#'+data+"_data TR.ui-state-highlight";
	jQuery(s).each(function() {
		disableSelectionStyle(jQuery(this), 'ui-state-highlight');
	});
}

/**
 * Fonction chargée de désactiver un style sur un élément.
 * 
 * @param elementJQuery
 *            Elément jQuery.
 * @param style
 *            Style à supprimer.
 */
function disableSelectionStyle(elementJQuery, style) {
	elementJQuery.removeClass(style);
}

/**
 * Fonction en charge de fermer une popup si les champs saisis sont valides.
 * Cette méthode doit être appelée sur un oncomplete (cf.
 * ongletStockage_popup.xhtml).
 * 
 * @param formId
 *            Identifiant du formulaire à valider si les champs sont valides.
 * @param widgetVar
 *            Popup (objet) à fermer si les champs sont valides.
 * @param xhr
 *            Paramètre retourné par l'appel ajax.
 * @param status
 *            Paramètre retourné par l'appel ajax.
 * @param args
 *            Paramètre retourné par l'appel ajax (contient le paramètre
 *            isValid, qui détermine si les champs sont valides).
 */
function closePopupIfValid(formId, widgetVar, xhr, status, args) {
	if (formId && widgetVar && args) {
		var isValid = args.isValid;
		if (isValid) {
			document.getElementById(formId).submit();
			widgetVar.hide();
		}
	}
}

/**
 * Fonction générale pour le scroll automatique vers un élément 
 * (seulement sur l'axe x).
 * @param element Elément vers lequel on souhaite scroller.
 * @return
 */
function scrollToElement(element) {
	jQuery.scrollTo(element,800,{axis:'y'});
}

/**
 * Fonction en charge de positionner le scroll de la page sur l'onglet actif.
 */
function scrollToActiveOnglet() {
	jQuery(document).ready(function() {
		// si il y a des erreurs, on ne scroll pas.
		if (!jQuery(".ui-messages-error-summary").size() || !jQuery('.ui-messages-info-summary').size()){
			scrollToElement(jQuery('li.ui-tabs-selected.ui-state-active'));
			
		}
	});
}

/**
 * Fonction en charge d'initialiser un écouteur sur le premier onglet de la page,
 * pour scroller dessus lors d'un clic.
 * @param firstTabViewId (string) Identifiant du composant p:tab représentant le premier onglet de la page.
 */
function configureTabClickHandler(firstTabViewId, functionToCall) {
	var hrefTarget = "#"+firstTabViewId;
	var selector="a[href='"+hrefTarget+"']";
	jQuery(selector).click(functionToCall);
}

/**
 * Fonction en charge d'initialiser un écouteur sur le premier onglet de la page,
 * pour scroller dessus lors d'un clic.
 * @param firstTabViewId (string) Identifiant du composant p:tab représentant le premier onglet de la page.
 */
function configureFirstOngletScrollTo(firstTabViewId) {
	configureTabClickHandler(firstTabViewId, function() {
		scrollToActiveOnglet();
	});
}

/**
 * Fonction en charge de gérer le scroll automatique sur la page d'édition de l'essai.
 */
function launchScrollEditEssai() {
	scrollToActiveOnglet();
}
/**
 * Fonction en charge de gérer le scroll automatique sur la page d'édition de produit,
 * après vérification que le scroll n'est pas désactivé.
 */
function launchScrollEditProduit() {
	jQuery(document).ready(function() {
		var element = jQuery("#disableScroll");
		var scrollDisabled = (element!=null && element.val() == "true");
		if (scrollDisabled==false) {
			launchScrollEditProduitImmediate();
		}
	});
}

/**
 * Fonction en charge de forcer le scroll automatique sur la page d'édition de produit,
 * sans vérification que le scroll n'est pas désactivé.
 */
function forceScrollEditProduitImmediate() {
	jQuery(document).ready(function() {
		var element = jQuery("#disableScroll");
		if (element != null) {
			element.val("false");
		}
		launchScrollEditProduitImmediate();
	});
}

/**
 * Fonction en charge de gérer le scroll automatique sur la page d'édition de produit,
 * sans vérification que le scroll n'est pas désactivé.
 */
function launchScrollEditProduitImmediate() {
	jQuery(document).ready(function() {
		scrollToElement(jQuery('#ancreScroll'));
	});
}

function scrollToErrorIfExist(){
	if (jQuery(".ui-messages-error-summary").size() || jQuery('.ui-messages-info-summary').size()){
		scrollToElement(jQuery('#common_outp_msgs'));
	}
}

function resizeMenuButton(){
	jQuery('body>div.yui-module.yui-overlay.ui-menu.ui-widget.ui-widget-content.ui-corner-all').each(function(){
		var width = 0;
		jQuery('.bd>ul>li',this).each(function(){
			if (jQuery(this).width()>width)
				width = jQuery(this).width();
		});
		
		jQuery('.bd>ul>li',this).each(function(){
			jQuery(this).width(width);
		}); 
	});
	
}

function showWait(){	
	jQuery('html').css('overflow-y', 'hidden');
	jQuery('.popWait').show();
	window.scrollTo(0,0);	
}

function hideWait(){
	jQuery('html').css('overflow-y', 'scroll');
	jQuery('.popWait').hide();
}

function closePopup(xhr, status, args, popup)
{
	var isValid = args.isValid;
	if (isValid){
		popup.hide();
	} 
}  
 
function clickOnMenu(id){  
	var id_string = "#"+id;
	var sousMenu = jQuery(id_string);
	jQuery('.wijmo-wijmenu-parent:eq('+sousMenu.parent().parent().parent().parent().index()+')').find('ul li:eq('+sousMenu.index()+') a').click();
}

/**
 * Gestion des appels ajax
 * pour chaque appel ajax envoyé, on affiche la popup loading
 * des que la requete ajax est passé on ferme la popup loading
 * la function inline permet d'eviter les conflits avec printfaces car on change de scope
 */
(function(){
	jQuery(document).ajaxSend(function() {
		jQuery("*").addClass("cursor-progress");
	});
	
	jQuery(document).ajaxComplete(function() {
		jQuery("*").removeClass("cursor-progress");	
	});
})();
