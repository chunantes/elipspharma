/*
jQuery.ganttView v.0.8.2
Copyright (c) 2010 JC Grubbs - jc.grubbs@devmynd.com
MIT License Applies
 */

/*
 Options
 -----------------
 showWeekends: boolean
 data: object
 start: date
 end: date
 cellWidth: number
 cellHeight: number
 slideWidth: number
 behavior: {
 clickable: boolean,
 draggable: boolean,
 resizable: boolean,
 onClick: function,
 onDrag: function,
 onResize: function
 }
 */

var weekOrMonthValue;

var sizeForWeekOrMonth = [];


function display(date, data, size, weekOrMonth){
	
				if (weekOrMonth == null){
					if (weekOrMonthValue != null)
						weekOrMonth = weekOrMonthValue;
					else 
						weekOrMonth='month';
				}
				weekOrMonthValue = weekOrMonth;
				var debut =date.debut;
				var fin = date.fin;
				gantViewDeploy(size, 
						data, new Date(debut.annee, debut.mois, debut.jours),  new Date(fin.annee, fin.mois, fin.jours), weekOrMonth);

				jQuery('.ganttview-block').click(function(){
					var id = jQuery(this).attr('sequence');
					jQuery('.sequence_'+id).click();
				});
			}

function gantViewDeploy(size, data, dateDebut, dateFin, weekOrMonth) {
	jQuery("#ganttChart").html("");
	jQuery("#ganttChart").ganttView({ 
		data: data,
		start: dateDebut,
		end: dateFin, 
		cellWidth: size,
		slideWidth: 676,
		week :weekOrMonth
	
	});
	jQuery(".ganttview-hzheader-day").css("display", "none"); 
	jQuery(".ganttview-grid-row-cell").css("width",size-1 +"px");
};  

(function(jQuery) {
	jQuery.fn.ganttView = function(options) {

		var els = this;
		var defaults = {
			showWeekends : true,
			cellWidth : 28,
			cellHeight : 31,
			week : 'month',
			slideWidth : 400,
			vHeaderWidth : 100,
			behavior : { 
				clickable : true,
				draggable : true,
				resizable : true,
				onClick : null,
				onDrag : null,
				onResize : null
			}
		};
		var opts = jQuery.extend(true, defaults, options);
		
		var months = Chart.getMonths(opts.start, opts.end);
		
		if (defaults.week == 'week'){
			var weeks = Chart.getWeeks(opts.start, opts.end);
		}
		els.each(function() {

			var container = jQuery(this);
			var div = jQuery("<div>", {
				"class" : "ganttview"
			});

			Chart.addVtHeader(div, opts.data, opts.cellHeight);

			var slideDiv = jQuery("<div>", {
				"class" : "ganttview-slide-container",
				"css" : {
					"width" : opts.slideWidth + "px"
				}
			});

			Chart.addHzHeader(slideDiv, months, opts.cellWidth, weeks);

			var slideBodyDiv = jQuery("<div>", {
				"class" : "ganttview-slide-body",
				"css" : {
					"width" : opts.slideWidth + "px"
				}
			});
			Chart.addGrid(slideBodyDiv, opts.data, months, opts.cellWidth,
					opts.showWeekends);
			Chart.addBlockContainers(slideBodyDiv, opts.data);
			Chart
					.addBlocks(slideBodyDiv, opts.data, opts.cellWidth,
							opts.start);

			div.append(slideDiv);
			slideDiv.append(slideBodyDiv);

			div.append(jQuery("<div>", {
				"css" : {
					"clear" : "both"
				}
			}));
			container.append(div);

			var w = jQuery("div.ganttview-vtheader", container).outerWidth()
					+ jQuery("div.ganttview-slide-container", container)
							.outerWidth();
			container.css("width", (w + 2) + "px");

			Chart.applyLastClass(container);

			if (opts.behavior.clickable) {
				Behavior.bindBlockClick(container, opts.behavior.onClick);
			}

			if (opts.behavior.resizable) {
				Behavior.bindBlockResize(container, opts.cellWidth, opts.start,
						opts.behavior.onResize);
			}

		});
	};

	var Chart = {

		monthNames : [ "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
				"Juillet", "Aout", "Septembre", "Octobre", "Novembre",
				"Décembre" ],
		dayNames : [ "Dimanche", "Lundi", "Mardin", "Mercredi", "Jeudi",
				"Vendredi", "Samedi" ],

		getMonths : function(start, end) {
			start = Date.parse(start);
			end = Date.parse(end);
			var index = [];
			var months = [];
			index[start.getMonth()+ "" + start.getYear()] = i;
			i++;
			months[index[start.getMonth()+ "" + start.getYear()]] = [];
			months[index[start.getMonth()+ "" + start.getYear()]]['nbJours'] = [ start ];
			months[index[start.getMonth()+ "" + start.getYear()]]['month'] = start.getMonth();
			var last = start;
			while (last.compareTo(end) == -1) {
				var next = last.clone().addDays(1);
				if (!index[next.getMonth()+ "" + next.getYear()]){
					index[next.getMonth()+ "" + next.getYear()] = i;
					i++;
				}
				
				if (!months[index[next.getMonth()+ "" + next.getYear()]]) {
					months[index[next.getMonth()+ "" + next.getYear()]]= [];
					months[index[next.getMonth()+ "" + next.getYear()]]['nbJours'] = [];
					months[index[next.getMonth()+ "" + next.getYear()]]['month']=next.getMonth();
				}
				months[index[next.getMonth()+ "" + next.getYear()]]['nbJours']
						.push(next);
				last = next;
			}
			return months;
		},

		getWeeks : function(start, end) {
			start = Date.parse(start);
			end = Date.parse(end);
			var i=1;
			var index = [];
			var weeks = [];
			index[DateUtils.getNumSemaine(start)+ "" + start.getYear()] = i;
			var weekStart = DateUtils.getNumSemaine(start);
			var weekEnd = DateUtils.getNumSemaine(end);
			
			
			weeks[index[weekStart+ "" + start.getYear()]] = [];
			weeks[index[weekStart+ "" + start.getYear()]]['nbJours'] = [ start ];
			weeks[index[weekStart+ "" + start.getYear()]]['libelle'] = DateUtils.getNumSemaine(start);
			var last = start;
			i++;
			while (last.compareTo(end) == -1) {
				var next = last.clone().addDays(1);
				if (!index[ DateUtils.getNumSemaine(next)+ "" + next.getYear()]){
					index[DateUtils.getNumSemaine(next)+ "" + next.getYear()] = i;
					i++;
				}
				
				if (!weeks[index[DateUtils.getNumSemaine(next)+ "" + next.getYear()]]) {
					weeks[index[DateUtils.getNumSemaine(next)+ "" + next.getYear()]]= [];
					weeks[index[DateUtils.getNumSemaine(next)+ "" + next.getYear()]]['nbJours'] = [];
					weeks[index[DateUtils.getNumSemaine(next)+ "" + next.getYear()]]['libelle']=DateUtils.getNumSemaine(next);
				}
				weeks[index[DateUtils.getNumSemaine(next)+ "" + next.getYear()]]['nbJours'].push(next);
				last = next;
			}
			return weeks;
		},

		addVtHeader : function(div, data, cellHeight) {
			var headerDiv = jQuery("<div>", {
				"class" : "ganttview-vtheader"
			});
			for ( var i = 0; i < data.length; i++) {
				var itemDiv = jQuery("<div>", {
					"class" : "ganttview-vtheader-item"
				});
				itemDiv.append(jQuery("<div>", {
					"class" : "ganttview-vtheader-item-name",
					"css" : {
						"height" : (cellHeight) + "px",
						"margin-left" : (data[i].niveau * 20) + "px"
					}
				}).append(data[i].itemName));
				headerDiv.append(itemDiv);
			}
			div.append(headerDiv);
		},

		addHzHeader : function(div, months, cellWidth, weeks) {
			var headerDiv = jQuery("<div>", {
				"class" : "ganttview-hzheader"
			});
			
			if (weeks){
				var monthsDiv = jQuery("<div>", {
					"class" : "ganttview-hzheader-weeks"
				});
			} else {
				var monthsDiv = jQuery("<div>", {
					"class" : "ganttview-hzheader-months"
				});
			}
			var daysDiv = jQuery("<div>", {
				"class" : "ganttview-hzheader-months"
			});
			var totalW = 0;

			
			if (weeks){
				for ( var i = 0; i < weeks.length; i++) {
					if (weeks[i]) {
						var w = weeks[i]['nbJours'].length* cellWidth;
						totalW = totalW + w;
						monthsDiv.append(jQuery("<div>", {
							"class" : "ganttview-hzheader-month",
							"css" : {
								"width" : (w - 1) + "px"
							}
						}).append(weeks[i]['libelle']));
						for ( var j = 0; j < weeks[i]['nbJours'].length; j++) {
							daysDiv.append(jQuery("<div>", {
								"class" : "ganttview-hzheader-day"
							}).append(weeks[i]['nbJours'][j].getDate()));
						}
					}
				}
				
				
			} else {
				for ( var i = 0; i < months.length; i++) {
					if (months[i]) {
						var w = months[i]['nbJours'].length * cellWidth;
						totalW = totalW + w;
						monthsDiv.append(jQuery("<div>", {
							"class" : "ganttview-hzheader-month",
							"css" : {
								"width" : (w - 1) + "px"
							}
						}).append(Chart.monthNames[months[i]['month']]));
						for ( var j = 0; j < months[i]['nbJours'].length; j++) {
							daysDiv.append(jQuery("<div>", {
								"class" : "ganttview-hzheader-day"
							}).append(months[i]['nbJours'][j].getDate()));
						}
					}
				}
			}
			monthsDiv.css("width", totalW + "px");
			daysDiv.css("width", totalW + "px");
			headerDiv.append(monthsDiv).append(daysDiv);
			div.append(headerDiv);
		},

		addGrid : function(div, data, months, cellWidth, showWeekends) {
			var gridDiv = jQuery("<div>", {
				"class" : "ganttview-grid"
			});
			var rowDiv = jQuery("<div>", { 
				"class" : "ganttview-grid-row"
			});
			for ( var i = 0; i < months.length; i++) {
				if (months[i]) {
					for ( var j = 0; j < months[i]['nbJours'].length; j++) {
						var cellDiv = jQuery("<div>", {
							"class" : "ganttview-grid-row-cell "
						});
						if (DateUtils.isWeekend(months[i]['nbJours'][j]) && showWeekends) {
							cellDiv.addClass("ganttview-weekend");
						}
						rowDiv.append(cellDiv);
					}
				}
			}
			var w = jQuery("div.ganttview-grid-row-cell", rowDiv).length
					* cellWidth;
			rowDiv.css("width", w + "px");
			gridDiv.css("width", w + "px");
			for ( var i = 0; i < data.length; i++) {
				gridDiv.append(rowDiv.clone());
			}
			div.append(gridDiv);
		},

		addBlockContainers : function(div, data) {
			var blocksDiv = jQuery("<div>", {
				"class" : "ganttview-blocks"
			}); 
			for ( var i = 0; i < data.length; i++) {
				blocksDiv.append(jQuery("<div>", {
					"class" : "ganttview-block-container container_"+i
				}));

				blocksDiv.append(jQuery("<div>", {
					"css" : {
						"clear" : "both"
					}
				}));
			}
			div.append(blocksDiv);

		},

		addBlocks : function(div, data, cellWidth, start) {
			
			for ( var i = 0; i < data.length; i++) {
				var rowIdx = 0;
				var rows = jQuery(
						"div.ganttview-blocks div.container_"+i, div);
				var lastLeftPos = 0;
				for ( var j = 0; j < data[i].series.length; j++) {
					var series = data[i].series[j];
					var debut = new Date(series.debut.annee, series.debut.mois,
							series.debut.jours);
					var fin = new Date(series.fin.annee, series.fin.mois,
							series.fin.jours)

					var size = DateUtils.daysBetween(debut, fin) ;
					if (size && size > 0) {
						if (size > 365) {
							size = 365;
						} // Keep blocks from overflowing a year

						var offset = DateUtils.daysBetween(start, debut);
						var marginLeft = (((offset * cellWidth) - lastLeftPos) + 3);
						var block = jQuery("<div>",
								{
									"class" : "ganttview-block",
									"title" : series.seriesName + ", " + size
											+ " jours",
									"css" : {
										// "float" : "left",
										"width" : ((size * cellWidth) - 9)
												+ "px", 
										// "left": (((offset * cellWidth) -
										// lastLeftPos) + 3) + "px",
										"left" : ((offset * cellWidth) + 3)
												+ "px"
												
									// "top" : -412
									// "left": ((((offset * cellWidth) ) +
									// 3)-lastLeftPos) + "px"
									},
									"sequence" : series.idSequence
								}); 
						var width = ((size * cellWidth) - 9);
						// Calcul de la position du bloc suivant
						lastLeftPos = width;
						Chart.addBlockData(block, data[i], series);
						if (data[i].series[j].color) {
							block.css("background-color",
									data[i].series[j].color);
						}
						block.append(jQuery("<div>", {
							"class" : "ganttview-block-text"
						}).text(data[i].series[j].seriesName));
						jQuery(rows[rowIdx]).append(block);
					}
				}
				if(data[i].series.length){
					rowIdx = rowIdx + 1;
				}
			}
		},

		addBlockData : function(block, data, series) {
			// This allows custom attributes to be added to the series data
			// objects
			// and makes them available to the 'data' argument of click, resize,
			// and drag handlers
			var blockData = {
				id : data.id,
				itemName : data.itemName
			};
			jQuery.extend(blockData, series);
			block.data("block-data", blockData);
		},

		applyLastClass : function(div) {
			jQuery(
					"div.ganttview-grid-row div.ganttview-grid-row-cell:last-child",
					div).addClass("last");
			jQuery(
					"div.ganttview-hzheader-days div.ganttview-hzheader-day:last-child",
					div).addClass("last");
			jQuery(
					"div.ganttview-hzheader-months div.ganttview-hzheader-month:last-child",
					div).addClass("last");
		}

	};

	var Behavior = {

		bindBlockClick : function(div, callback) {
			jQuery("div.ganttview-block", div).live("click", function() {
				if (callback) {
					callback(jQuery(this).data("block-data"));
				}
			});
		},

		bindBlockResize : function(div, cellWidth, startDate, callback) {
			jQuery("div.ganttview-block", div).resizable( {
				grid : cellWidth,
				handles : "z"

			});
		},

		bindBlockDrag : function(div, cellWidth, startDate, callback) {
			jQuery("div.ganttview-block", div).draggable(
					{
						axis : "x",
						grid : [ cellWidth, cellWidth ],
						stop : function() {
							var block = jQuery(this);
							Behavior.updateDataAndPosition(div, block,
									cellWidth, startDate);
							if (callback) {
								callback(block.data("block-data"));
							}
						}
					});
		},

		updateDataAndPosition : function(div, block, cellWidth, startDate) {
			var container = jQuery("div.ganttview-slide-container", div);
			var scroll = container.scrollLeft();
			var offset = block.offset().left - container.offset().left - 1
					+ scroll;

			// Set new start date
			var daysFromStart = Math.round(offset / cellWidth);
			var newStart = startDate.clone().addDays(daysFromStart);
			block.data("block-data").start = newStart;

			// Set new end date
			var width = block.outerWidth();
			var numberOfDays = Math.round(width / cellWidth) - 1;
			block.data("block-data").end = newStart.clone().addDays(
					numberOfDays);
			jQuery("div.ganttview-block-text", block).text(numberOfDays + 1);

			// Remove top and left properties to avoid incorrect block
			// positioning,
			// set position to relative to keep blocks relative to scrollbar
			// when scrolling
			block.css("top", "").css("left", "").css("position", "relative")
					.css("margin-left", offset + "px");
		}
	};

	var ArrayUtils = {

		contains : function(arr, obj) {
			var has = false;
			for ( var i = 0; i < arr.length; i++) {
				if (arr[i] == obj) {
					has = true;
				}
			}
			return has;
		}
	};

	var DateUtils = {

		daysBetween : function(start, end) {
			if (!start || !end) {
				return 0;
			}
			start = Date.parse(start);
			end = Date.parse(end);
			if (start.getYear() == 1901 || end.getYear() == 8099) {
				return 0;
			}
			var count = 0, date = start.clone();
			while (date.compareTo(end) == -1) {
				count = count + 1;
				date.addDays(1);
			}
			return count;
		},

		isWeekend : function(date) {
			return date.getDay() % 6 == 0;
		},
		
		getNumSemaine : function(date){
				var onejan = new Date(date.getFullYear(),0,1);
				return Math.ceil((((date - onejan) / 86400000) + onejan.getDay())/7);
		}
	};

})(jQuery);