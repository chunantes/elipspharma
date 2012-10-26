(function(az) {
	var au = az.fullCalendar = {};
	var aZ = au.views = {};
	var ax = {
		defaultView : "month",
		aspectRatio : 1.35,
		header : {
			left : "title",
			center : "",
			right : "today prev,next"
		},
		weekends : true,
		allDayDefault : true,
		lazyFetching : true,
		startParam : "start",
		endParam : "end",
		titleFormat : {
			month : "MMMM yyyy",
			week : "MMM d[ yyyy]{ '&#8212;'[ MMM] d yyyy}",
			day : "dddd, MMM d, yyyy"
		},
		columnFormat : {
			month : "ddd",
			week : "ddd d/M",
			day : "dddd d/M"
		},
		timeFormat : {
			"" : "H(:mm)t"
		},
		isRTL : false,
		firstDay : 0,
		monthNames : [ "January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November",
				"December" ],
		monthNamesShort : [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
				"Aug", "Sep", "Oct", "Nov", "Dec" ],
		dayNames : [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday", "Saturday" ],
		dayNamesShort : [ "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" ],
		buttonText : {
			prev : "&nbsp;&#9668;&nbsp;",
			next : "&nbsp;&#9658;&nbsp;",
			prevYear : "&nbsp;&lt;&lt;&nbsp;",
			nextYear : "&nbsp;&gt;&gt;&nbsp;",
			today : "today",
			month : "month",
			week : "week",
			day : "day"
		},
		theme : false,
		buttonIcons : {
			prev : "circle-triangle-w",
			next : "circle-triangle-e"
		}
	};
	var a3 = {
		header : {
			left : "next,prev today",
			center : "",
			right : "title"
		},
		buttonText : {
			prev : "&nbsp;&#9658;&nbsp;",
			next : "&nbsp;&#9668;&nbsp;",
			prevYear : "&nbsp;&gt;&gt;&nbsp;",
			nextYear : "&nbsp;&lt;&lt;&nbsp;"
		},
		buttonIcons : {
			prev : "circle-triangle-e",
			next : "circle-triangle-w"
		}
	};
	var ap = au.setDefaults = function(a) {
		az.extend(true, ax, a)
	};
	az.fn.fullCalendar = function(b) {
		if (typeof b == "string") {
			var c = Array.prototype.slice.call(arguments, 1), a;
			this.each(function() {
				var f = az.data(this, "fullCalendar");
				if (f) {
					var g = f[b].apply(this, c);
					if (a == aS) {
						a = g
					}
				}
			});
			if (a != aS) {
				return a
			}
			return this
		}
		var d = b.eventSources || [];
		delete b.eventSources;
		if (b.events) {
			d.push(b.events);
			delete b.events
		}
		d.unshift( []);
		b = az.extend(true, {}, ax, (b.isRTL || b.isRTL == aS && ax.isRTL) ? a3
				: {}, b);
		var e = b.theme ? "ui" : "fc";
		this
				.each(function() {
					var H = this, h = az(H).addClass("fc"), O, m = az(
							"<div class='fc-content "
									+ e
									+ "-widget-content' style='position:relative'/>")
							.prependTo(H), s, M = 0, G = 0, C = new Date(), o, v, z = {}, n;
					if (b.isRTL) {
						h.addClass("fc-rtl")
					}
					if (b.theme) {
						h.addClass("ui-widget")
					}
					if (b.year != aS && b.year != C.getFullYear()) {
						C.setDate(1);
						C.setMonth(0);
						C.setFullYear(b.year)
					}
					if (b.month != aS && b.month != C.getMonth()) {
						C.setDate(1);
						C.setMonth(b.month)
					}
					if (b.date != aS) {
						C.setDate(b.date)
					}
					function q(R) {
						if (R != o) {
							G++;
							var Q = v, S;
							if (Q) {
								if (Q.eventsChanged) {
									w();
									Q.eventDirty = Q.eventsChanged = false
								}
								if (Q.beforeHide) {
									Q.beforeHide()
								}
								aE(m, m.height());
								Q.element.hide()
							} else {
								aE(m, 1)
							}
							m.css("overflow", "hidden");
							if (z[R]) {
								(v = z[R]).element.show()
							} else {
								v = z[R] = az.fullCalendar.views[R]
										(
												S = n = az(
														"<div class='fc-view fc-view-"
																+ R
																+ "' style='position:absolute'/>")
														.appendTo(m), b)
							}
							if (u) {
								u.find("div.fc-button-" + o).removeClass(
										e + "-state-active");
								u.find("div.fc-button-" + R).addClass(
										e + "-state-active")
							}
							v.name = o = R;
							N();
							m.css("overflow", "");
							if (Q) {
								aE(m, 1)
							}
							if (!S && v.afterShow) {
								v.afterShow()
							}
							G--
						}
					}
					function N(Q) {
						if (J()) {
							G++;
							if (s == aS) {
								L()
							}
							if (!v.start || Q || C < v.start || C >= v.end) {
								v.render(C, Q || 0);
								I(true);
								if (!P || !b.lazyFetching || v.visStart < P
										|| v.visEnd > B) {
									K()
								} else {
									v.renderEvents(f)
								}
							} else {
								if (v.sizeDirty || v.eventsDirty
										|| !b.lazyFetching) {
									v.clearEvents();
									if (v.sizeDirty) {
										I()
									}
									if (b.lazyFetching) {
										v.renderEvents(f)
									} else {
										K()
									}
								}
							}
							O = h.outerWidth();
							v.sizeDirty = false;
							v.eventsDirty = false;
							if (u) {
								u.find("h2.fc-header-title").html(v.title);
								var R = new Date();
								if (R >= v.start && R < v.end) {
									u.find("div.fc-button-today").addClass(
											e + "-state-disabled")
								} else {
									u.find("div.fc-button-today").removeClass(
											e + "-state-disabled")
								}
							}
							G--;
							v.trigger("viewDisplay", H)
						}
					}
					function J() {
						return H.offsetWidth !== 0
					}
					function j() {
						return az("body")[0].offsetWidth !== 0
					}
					function k() {
						w();
						if (J()) {
							v.clearEvents();
							v.renderEvents(f);
							v.eventsDirty = false
						}
					}
					function w() {
						az.each(z, function() {
							this.eventsDirty = true
						})
					}
					function g() {
						y();
						if (J()) {
							L();
							I();
							v.rerenderEvents();
							v.sizeDirty = false
						}
					}
					function y() {
						az.each(z, function() {
							this.sizeDirty = true
						})
					}
					var f = [], P, B;
					function A(Q) {
						f = [];
						P = al(v.visStart);
						B = al(v.visEnd);
						var R = d.length, T = function() {
							if (--R == 0) {
								if (Q) {
									Q(f)
								}
							}
						}, S = 0;
						for (; S < d.length; S++) {
							i(d[S], T)
						}
					}
					function i(R, Q) {
						var V = v.name, S = al(C), T = function(Y) {
							if (V == v.name && +S == +C
									&& az.inArray(R, d) != -1) {
								for ( var X = 0; X < Y.length; X++) {
									aA(Y[X], b);
									Y[X].source = R
								}
								f = f.concat(Y);
								if (Q) {
									Q(Y)
								}
							}
						}, W = function(X) {
							T(X);
							t()
						};
						if (typeof R == "string") {
							var U = {};
							U[b.startParam] = Math.round(P.getTime() / 1000);
							U[b.endParam] = Math.round(B.getTime() / 1000);
							if (b.cacheParam) {
								U[b.cacheParam] = (new Date()).getTime()
							}
							p();
							az.ajax( {
								url : R,
								dataType : "json",
								data : U,
								cache : b.cacheParam || false,
								success : W
							})
						} else {
							if (az.isFunction(R)) {
								p();
								R(al(P), al(B), W)
							} else {
								T(R)
							}
						}
					}
					function K() {
						A(function(Q) {
							v.renderEvents(Q)
						})
					}
					var x = 0;
					function p() {
						if (!x++) {
							v.trigger("loading", H, true)
						}
					}
					function t() {
						if (!--x) {
							v.trigger("loading", H, false)
						}
					}
					var E = {
						render : function() {
							L();
							y();
							w();
							N()
						},
						changeView : q,
						getView : function() {
							return v
						},
						getDate : function() {
							return C
						},
						option : function(R, Q) {
							if (Q == aS) {
								return b[R]
							}
							if (R == "height" || R == "contentHeight"
									|| R == "aspectRatio") {
								b[R] = Q;
								g()
							}
						},
						destroy : function() {
							az(window).unbind("resize", l);
							if (u) {
								u.remove()
							}
							m.remove();
							az.removeData(H, "fullCalendar")
						},
						prev : function() {
							N(-1)
						},
						next : function() {
							N(1)
						},
						prevYear : function() {
							ad(C, -1);
							N()
						},
						nextYear : function() {
							ad(C, 1);
							N()
						},
						today : function() {
							C = new Date();
							N()
						},
						gotoDate : function(S, Q, R) {
							if (typeof S == "object") {
								C = al(S)
							} else {
								if (S != aS) {
									C.setFullYear(S)
								}
								if (Q != aS) {
									C.setMonth(Q)
								}
								if (R != aS) {
									C.setDate(R)
								}
							}
							N()
						},
						incrementDate : function(R, S, Q) {
							if (R != aS) {
								ad(C, R)
							}
							if (S != aS) {
								aP(C, S)
							}
							if (Q != aS) {
								aG(C, Q)
							}
							N()
						},
						updateEvent : function(R) {
							var T, V = f.length, Q, S = R.start - R._start, U = R.end ? (R.end - (R._end || v
									.defaultEventEnd(R)))
									: 0;
							for (T = 0; T < V; T++) {
								Q = f[T];
								if (Q._id == R._id && Q != R) {
									Q.start = new Date(+Q.start + S);
									if (R.end) {
										if (Q.end) {
											Q.end = new Date(+Q.end + U)
										} else {
											Q.end = new Date(+v
													.defaultEventEnd(Q)
													+ U)
										}
									} else {
										Q.end = null
									}
									Q.title = R.title;
									Q.url = R.url;
									Q.allDay = R.allDay;
									Q.className = R.className;
									Q.editable = R.editable;
									aA(Q, b)
								}
							}
							aA(R, b);
							k()
						},
						renderEvent : function(Q, R) {
							aA(Q, b);
							if (!Q.source) {
								if (R) {
									(Q.source = d[0]).push(Q)
								}
								f.push(Q)
							}
							k()
						},
						removeEvents : function(R) {
							if (!R) {
								f = [];
								for ( var S = 0; S < d.length; S++) {
									if (typeof d[S] == "object") {
										d[S] = []
									}
								}
							} else {
								if (!az.isFunction(R)) {
									var Q = R + "";
									R = function(T) {
										return T._id == Q
									}
								}
								f = az.grep(f, R, true);
								for ( var S = 0; S < d.length; S++) {
									if (typeof d[S] == "object") {
										d[S] = az.grep(d[S], R, true)
									}
								}
							}
							k()
						},
						clientEvents : function(Q) {
							if (az.isFunction(Q)) {
								return az.grep(f, Q)
							} else {
								if (Q) {
									Q += "";
									return az.grep(f, function(R) {
										return R._id == Q
									})
								}
							}
							return f
						},
						rerenderEvents : k,
						addEventSource : function(Q) {
							d.push(Q);
							i(Q, k)
						},
						removeEventSource : function(Q) {
							d = az.grep(d, function(R) {
								return R != Q
							});
							f = az.grep(f, function(R) {
								return R.source != Q
							});
							k()
						},
						refetchEvents : function() {
							A(k)
						}
					};
					az.data(this, "fullCalendar", E);
					var u, F = b.header;
					if (F) {
						u = az("<table class='fc-header'/>").append(
								az("<tr/>").append(
										az("<td class='fc-header-left'/>")
												.append(D(F.left))).append(
										az("<td class='fc-header-center'/>")
												.append(D(F.center))).append(
										az("<td class='fc-header-right'/>")
												.append(D(F.right))))
								.prependTo(h)
					}
					function D(R) {
						if (R) {
							var Q = az("<tr/>");
							az
									.each(
											R.split(" "),
											function(S) {
												if (S > 0) {
													Q
															.append("<td><span class='fc-header-space'/></td>")
												}
												var T;
												az
														.each(
																this.split(","),
																function(X, Y) {
																	if (Y == "title") {
																		Q
																				.append("<td><h2 class='fc-header-title'>&nbsp;</h2></td>");
																		if (T) {
																			T
																					.addClass(e
																							+ "-corner-right")
																		}
																		T = null
																	} else {
																		var Z;
																		if (E[Y]) {
																			Z = E[Y]
																		} else {
																			if (aZ[Y]) {
																				Z = function() {
																					W
																							.removeClass(e
																									+ "-state-hover");
																					q(Y)
																				}
																			}
																		}
																		if (Z) {
																			if (T) {
																				T
																						.addClass(e
																								+ "-no-right")
																			}
																			var W, V = b.theme ? aW(
																					b.buttonIcons,
																					Y)
																					: null, U = aW(
																					b.buttonText,
																					Y);
																			if (V) {
																				W = az("<div class='fc-button-"
																						+ Y
																						+ " ui-state-default'><a><span class='ui-icon ui-icon-"
																						+ V
																						+ "'/></a></div>")
																			} else {
																				if (U) {
																					W = az("<div class='fc-button-"
																							+ Y
																							+ " "
																							+ e
																							+ "-state-default'><a><span>"
																							+ U
																							+ "</span></a></div>")
																				}
																			}
																			if (W) {
																				W
																						.click(
																								function() {
																									if (!W
																											.hasClass(e
																													+ "-state-disabled")) {
																										Z()
																									}
																								})
																						.mousedown(
																								function() {
																									W
																											.not(
																													"."
																															+ e
																															+ "-state-active")
																											.not(
																													"."
																															+ e
																															+ "-state-disabled")
																											.addClass(
																													e
																															+ "-state-down")
																								})
																						.mouseup(
																								function() {
																									W
																											.removeClass(e
																													+ "-state-down")
																								})
																						.hover(
																								function() {
																									W
																											.not(
																													"."
																															+ e
																															+ "-state-active")
																											.not(
																													"."
																															+ e
																															+ "-state-disabled")
																											.addClass(
																													e
																															+ "-state-hover")
																								},
																								function() {
																									W
																											.removeClass(
																													e
																															+ "-state-hover")
																											.removeClass(
																													e
																															+ "-state-down")
																								})
																						.appendTo(
																								az(
																										"<td/>")
																										.appendTo(
																												Q));
																				if (T) {
																					T
																							.addClass(e
																									+ "-no-right")
																				} else {
																					W
																							.addClass(e
																									+ "-corner-left")
																				}
																				T = W
																			}
																		}
																	}
																});
												if (T) {
													T.addClass(e
															+ "-corner-right")
												}
											});
							return az("<table/>").append(Q)
						}
					}
					function L() {
						if (b.contentHeight) {
							s = b.contentHeight
						} else {
							if (b.height) {
								s = b.height - (u ? u.height() : 0) - an(m[0])
							} else {
								s = Math.round(m.width()
										/ Math.max(b.aspectRatio, 0.5))
							}
						}
					}
					function I(Q) {
						G++;
						v.setHeight(s, Q);
						if (n) {
							n.css("position", "relative");
							n = null
						}
						v.setWidth(m.width(), Q);
						G--
					}
					function l() {
						if (!G) {
							if (v.start) {
								var Q = ++M;
								setTimeout(function() {
									if (Q == M && !G && J()) {
										if (O != (O = h.outerWidth())) {
											G++;
											g();
											v.trigger("windowResize", H);
											G--
										}
									}
								}, 200)
							} else {
								r()
							}
						}
					}
					az(window).resize(l);
					q(b.defaultView);
					if (!j()) {
						r()
					}
					function r() {
						setTimeout(function() {
							if (!v.start && j()) {
								N()
							}
						}, 0)
					}
				});
		return this
	};
	var am = 0;
	function aA(a, b) {
		a._id = a._id || (a.id == aS ? "_fc" + am++ : a.id + "");
		if (a.date) {
			if (!a.start) {
				a.start = a.date
			}
			delete a.date
		}
		a._start = al(a.start = aY(a.start));
		a.end = aY(a.end);
		if (a.end && a.end <= a.start) {
			a.end = null
		}
		a._end = a.end ? al(a.end) : null;
		if (a.allDay == aS) {
			a.allDay = b.allDayDefault
		}
		if (a.className) {
			if (typeof a.className == "string") {
				a.className = a.className.split(/\s+/)
			}
		} else {
			a.className = []
		}
	}
	ap( {
		weekMode : "fixed"
	});
	aZ.month = function(a, b) {
		return new ay(
				a,
				b,
				{
					render : function(e, h) {
						if (h) {
							aP(e, h);
							e.setDate(1)
						}
						var i = this.start = al(e, true);
						i.setDate(1);
						this.end = aP(al(i), 1);
						var f = this.visStart = al(i), g = this.visEnd = al(this.end), d = b.weekends ? 0
								: 1;
						if (d) {
							ac(f);
							ac(g, -1, true)
						}
						aG(f, -((f.getDay() - Math.max(b.firstDay, d) + 7) % 7));
						aG(g, (7 - g.getDay() + Math.max(b.firstDay, d)) % 7);
						var c = Math.round((g - f) / (aj * 7));
						if (b.weekMode == "fixed") {
							aG(g, (6 - c) * 7);
							c = 6
						}
						this.title = ao(i, this.option("titleFormat"), b);
						this.renderGrid(c, b.weekends ? 7 : 5, this
								.option("columnFormat"), true)
					}
				})
	};
	aZ.basicWeek = function(a, b) {
		return new ay(
				a,
				b,
				{
					render : function(d, c) {
						if (c) {
							aG(d, c * 7)
						}
						var e = this.visStart = al(this.start = aG(al(d), -((d
								.getDay()
								- b.firstDay + 7) % 7))), f = this.visEnd = al(this.end = aG(
								al(e), 7));
						if (!b.weekends) {
							ac(e);
							ac(f, -1, true)
						}
						this.title = aK(e, aG(al(f), -1), this
								.option("titleFormat"), b);
						this.renderGrid(1, b.weekends ? 7 : 5, this
								.option("columnFormat"), false)
					}
				})
	};
	aZ.basicDay = function(a, b) {
		return new ay(a, b, {
			render : function(d, c) {
				if (c) {
					aG(d, c);
					if (!b.weekends) {
						ac(d, c < 0 ? -1 : 1)
					}
				}
				this.title = ao(d, this.option("titleFormat"), b);
				this.start = this.visStart = al(d, true);
				this.end = this.visEnd = aG(al(this.start), 1);
				this.renderGrid(1, 1, this.option("columnFormat"), false)
			}
		})
	};
	var aw;
	function ay(C, y, h) {
		var B, l, x, v, a, b, m, d, u, o, c, k, E, s = [], z, f = new at(
				function(G) {
					return E.find("td:eq(" + ((G - Math.max(l, x) + o) % o)
							+ ") div div")
				}), j = az.extend(this, aT, h, {
			renderGrid : q,
			renderEvents : A,
			rerenderEvents : i,
			clearEvents : p,
			setHeight : t,
			setWidth : D,
			defaultEventEnd : function(G) {
				return al(G.start)
			}
		});
		j.init(C, y);
		C.addClass("fc-grid");
		if (C.disableSelection) {
			C.disableSelection()
		}
		function q(G, M, P, K) {
			u = G;
			o = M;
			B = y.theme ? "ui" : "fc";
			x = y.weekends ? 0 : 1;
			l = y.firstDay;
			if (v = y.isRTL) {
				a = -1;
				b = o - 1
			} else {
				a = 1;
				b = 0
			}
			var O = j.start.getMonth(), L = aI(new Date()), H, Q, R, N = al(j.visStart);
			if (!E) {
				var I = az("<table/>").appendTo(C);
				H = "<thead><tr>";
				for (Q = 0; Q < o; Q++) {
					H += "<th class='fc-" + ah[N.getDay()] + " " + B
							+ "-state-default" + (Q == b ? " fc-leftmost" : "")
							+ "'>" + ao(N, P, y) + "</th>";
					aG(N, 1);
					if (x) {
						ac(N)
					}
				}
				k = az(H + "</tr></thead>").appendTo(I);
				H = "<tbody>";
				N = al(j.visStart);
				for (Q = 0; Q < u; Q++) {
					H += "<tr class='fc-week" + Q + "'>";
					for (R = 0; R < o; R++) {
						H += "<td class='fc-"
								+ ah[N.getDay()]
								+ " "
								+ B
								+ "-state-default fc-day"
								+ (Q * o + R)
								+ (R == b ? " fc-leftmost" : "")
								+ (u > 1 && N.getMonth() != O ? " fc-other-month"
										: "")
								+ (+N == +L ? " fc-today " + B
										+ "-state-highlight" : " fc-not-today")
								+ "'>"
								+ (K ? "<div class='fc-day-number'>"
										+ N.getDate() + "</div>" : "")
								+ "<div class='fc-day-content'><div style='position:relative'>&nbsp;</div></div></td>";
						aG(N, 1);
						if (x) {
							ac(N)
						}
					}
					H += "</tr>"
				}
				E = az(H + "</tbody>").appendTo(I);
				E.find("td").click(F);
				z = az(
						"<div style='position:absolute;z-index:8;top:0;left:0'/>")
						.appendTo(C)
			} else {
				p();
				var J = E.find("tr").length;
				if (u < J) {
					E.find("tr:gt(" + (u - 1) + ")").remove()
				} else {
					if (u > J) {
						H = "";
						for (Q = J; Q < u; Q++) {
							H += "<tr class='fc-week" + Q + "'>";
							for (R = 0; R < o; R++) {
								H += "<td class='fc-"
										+ ah[N.getDay()]
										+ " "
										+ B
										+ "-state-default fc-new fc-day"
										+ (Q * o + R)
										+ (R == b ? " fc-leftmost" : "")
										+ "'>"
										+ (K ? "<div class='fc-day-number'></div>"
												: "")
										+ "<div class='fc-day-content'><div style='position:relative'>&nbsp;</div></div></td>";
								aG(N, 1);
								if (x) {
									ac(N)
								}
							}
							H += "</tr>"
						}
						E.append(H)
					}
				}
				E.find("td.fc-new").removeClass("fc-new").click(F);
				N = al(j.visStart);
				E.find("td").each(
						function() {
							var S = az(this);
							if (u > 1) {
								if (N.getMonth() == O) {
									S.removeClass("fc-other-month")
								} else {
									S.addClass("fc-other-month")
								}
							}
							if (+N == +L) {
								S.removeClass("fc-not-today").addClass(
										"fc-today").addClass(
										B + "-state-highlight")
							} else {
								S.addClass("fc-not-today").removeClass(
										"fc-today").removeClass(
										B + "-state-highlight")
							}
							S.find("div.fc-day-number").text(N.getDate());
							aG(N, 1);
							if (x) {
								ac(N)
							}
						});
				if (u == 1) {
					N = al(j.visStart);
					k.find("th")
							.each(
									function() {
										az(this).text(ao(N, P, y));
										this.className = this.className
												.replace(/^fc-\w+(?= )/, "fc-"
														+ ah[N.getDay()]);
										aG(N, 1);
										if (x) {
											ac(N)
										}
									});
					N = al(j.visStart);
					E.find("td")
							.each(
									function() {
										this.className = this.className
												.replace(/^fc-\w+(?= )/, "fc-"
														+ ah[N.getDay()]);
										aG(N, 1);
										if (x) {
											ac(N)
										}
									})
				}
			}
		}
		function F(H) {
			var G = parseInt(this.className.match(/fc\-day(\d+)/)[1]), I = aG(
					al(j.visStart), Math.floor(G / o) * 7 + G % o);
			j.trigger("dayClick", this, I, true, H)
		}
		function t(M) {
			d = M;
			var L = E.find("tr td:first-child"), G = d - k.height(), I, J;
			if (y.weekMode == "variable") {
				I = J = Math.floor(G / (u == 1 ? 2 : 6))
			} else {
				I = Math.floor(G / u);
				J = G - I * (u - 1)
			}
			if (aw == aS) {
				var K = E.find("tr:first"), H = K.find("td:first");
				H.height(I);
				aw = I != H.height()
			}
			if (aw) {
				L.slice(0, -1).height(I);
				L.slice(-1).height(J)
			} else {
				aX(L.slice(0, -1), I);
				aX(L.slice(-1), J)
			}
		}
		function D(G) {
			m = G;
			f.clear();
			ag(k.find("th").slice(0, -1), c = Math.floor(m / o))
		}
		function A(G) {
			j.reportEvents(s = G);
			w(n(G))
		}
		function i(G) {
			p();
			w(n(s), G)
		}
		function p() {
			j._clearEvents();
			z.empty()
		}
		function n(I) {
			var P = al(j.visStart), G = aG(al(P), o), J = az.map(I, r), K, H, L, Q, N, M, O = [];
			for (K = 0; K < u; K++) {
				H = av(j.sliceSegs(I, J, P, G));
				for (L = 0; L < H.length; L++) {
					Q = H[L];
					for (N = 0; N < Q.length; N++) {
						M = Q[N];
						M.row = K;
						M.level = L;
						O.push(M)
					}
				}
				aG(P, 7);
				aG(G, 7)
			}
			return O
		}
		function w(H, G) {
			a2(H, u, j, 0, m, function(I) {
				return E.find("tr:eq(" + I + ")")
			}, f.left, f.right, z, e, G)
		}
		function r(G) {
			if (G.end) {
				var H = al(G.end);
				return (G.allDay || H.getHours() || H.getMinutes()) ? aG(H, 1)
						: H
			} else {
				return aG(al(G.start), 1)
			}
		}
		function e(G, H, I) {
			j.eventElementHandlers(G, H);
			if (G.editable || G.editable == aS && y.editable) {
				g(G, H);
				if (I.isEnd) {
					j.resizableDayEvent(G, H, c)
				}
			}
		}
		function g(G, H) {
			if (!y.disableDragging && H.draggable) {
				var I;
				H.draggable( {
					zIndex : 9,
					delay : 50,
					opacity : j.option("dragOpacity"),
					revertDuration : y.dragRevertDuration,
					start : function(K, J) {
						j.hideEvents(G, H);
						j.trigger("eventDragStart", H, G, K, J);
						I = new a0(function(M) {
							H.draggable("option", "revert", !M || !M.rowDelta
									&& !M.colDelta);
							if (M) {
								j.showOverlay(M)
							} else {
								j.hideOverlay()
							}
						});
						E.find("tr").each(function() {
							I.row(this)
						});
						var L = E.find("tr:first td");
						if (v) {
							L = az(L.get().reverse())
						}
						L.each(function() {
							I.col(this)
						});
						I.mouse(K.pageX, K.pageY)
					},
					drag : function(J) {
						I.mouse(J.pageX, J.pageY)
					},
					stop : function(K, J) {
						j.hideOverlay();
						j.trigger("eventDragStop", H, G, K, J);
						var L = I.cell;
						if (!L || !L.rowDelta && !L.colDelta) {
							if (az.browser.msie) {
								H.css("filter", "")
							}
							j.showEvents(G, H)
						} else {
							H.find("a").removeAttr("href");
							j.eventDrop(this, G, L.rowDelta * 7 + L.colDelta
									* a, 0, G.allDay, K, J)
						}
					}
				})
			}
		}
	}
	function a2(q, z, v, l, d, u, s, b, D, i, n) {
		var E = v.options, A = E.isRTL, h, o = q.length, r, j, F, B, k, w = "", m, f, y, c = {}, H = {}, e, g, x, t, C, p, G = [], a = [];
		for (h = 0; h < o; h++) {
			r = q[h];
			j = r.event;
			F = "fc-event fc-event-hori ";
			if (A) {
				if (r.isStart) {
					F += "fc-corner-right "
				}
				if (r.isEnd) {
					F += "fc-corner-left "
				}
				B = r.isEnd ? s(r.end.getDay() - 1) : l;
				k = r.isStart ? b(r.start.getDay()) : d
			} else {
				if (r.isStart) {
					F += "fc-corner-left "
				}
				if (r.isEnd) {
					F += "fc-corner-right "
				}
				B = r.isStart ? s(r.start.getDay()) : l;
				k = r.isEnd ? b(r.end.getDay() - 1) : d
			}
			w += "<div class='"
					+ F
					+ j.className.join(" ")
					+ "' style='position:absolute;z-index:8;left:"
					+ B
					+ "px'><a"
					+ (j.url ? " href='" + ar(j.url) + "'" : "")
					+ ">"
					+ "<span class='fc-event-title'>"
					+ ar(j.title)
					+ "</span></a>"
					+ ((j.editable || j.editable == aS && E.editable)
							&& !E.disableResizing && az.fn.resizable ? "<div class='ui-resizable-handle ui-resizable-"
							+ (A ? "w" : "e") + "'></div>"
							: "") + "</div>";
			r.left = B;
			r.outerWidth = k - B
		}
		D[0].innerHTML = w;
		m = D.children();
		for (h = 0; h < o; h++) {
			r = q[h];
			f = az(m[h]);
			j = r.event;
			y = v.trigger("eventRender", j, j, f);
			if (y === false) {
				f.remove()
			} else {
				if (y && y !== true) {
					f.remove();
					f = az(y).css( {
						position : "absolute",
						left : r.left
					}).appendTo(D)
				}
				r.element = f;
				if (j._id === n) {
					i(j, f, r)
				} else {
					f[0]._fci = h
				}
				v.reportEventElement(j, f)
			}
		}
		ai(D, q, i);
		for (h = 0; h < o; h++) {
			r = q[h];
			if (f = r.element) {
				g = c[e = r.key = ae(f[0])];
				r.hsides = g == aS ? (c[e] = aB(f[0], true)) : g
			}
		}
		for (h = 0; h < o; h++) {
			r = q[h];
			if (f = r.element) {
				f[0].style.width = r.outerWidth - r.hsides + "px"
			}
		}
		for (h = 0; h < o; h++) {
			r = q[h];
			if (f = r.element) {
				g = H[e = r.key];
				r.outerHeight = f[0].offsetHeight
						+ (g == aS ? (H[e] = aL(f[0])) : g)
			}
		}
		for (h = 0, x = 0; x < z; x++) {
			t = C = p = 0;
			while (h < o && (r = q[h]).row == x) {
				if (r.level != C) {
					t += p;
					p = 0;
					C++
				}
				p = Math.max(p, r.outerHeight || 0);
				r.top = t;
				h++
			}
			G[x] = u(x).find("td:first div.fc-day-content > div").height(t + p)
		}
		for (x = 0; x < z; x++) {
			a[x] = G[x][0].offsetTop
		}
		for (h = 0; h < o; h++) {
			r = q[h];
			if (f = r.element) {
				f[0].style.top = a[r.row] + r.top + "px";
				j = r.event;
				v.trigger("eventAfterRender", j, j, f)
			}
		}
	}
	ap( {
		allDaySlot : true,
		allDayText : "all-day",
		firstHour : 6,
		slotMinutes : 30,
		defaultEventMinutes : 120,
		axisFormat : "H(:mm)",
		timeFormat : {
			agenda : "H:mm{ - H:mm}"
		},
		dragOpacity : {
			agenda : 0.5
		},
		minTime : 0,
		maxTime : 24
	});
	aZ.agendaWeek = function(a, b) {
		return new aJ(
				a,
				b,
				{
					render : function(d, c) {
						if (c) {
							aG(d, c * 7)
						}
						var e = this.visStart = al(this.start = aG(al(d), -((d
								.getDay()
								- b.firstDay + 7) % 7))), f = this.visEnd = al(this.end = aG(
								al(e), 7));
						if (!b.weekends) {
							ac(e);
							ac(f, -1, true)
						}
						this.title = aK(e, aG(al(f), -1), this
								.option("titleFormat"), b);
						this.renderAgenda(b.weekends ? 7 : 5, this
								.option("columnFormat"))
					}
				})
	};
	aZ.agendaDay = function(a, b) {
		return new aJ(a, b, {
			render : function(d, c) {
				if (c) {
					aG(d, c);
					if (!b.weekends) {
						ac(d, c < 0 ? -1 : 1)
					}
				}
				this.title = ao(d, this.option("titleFormat"), b);
				this.start = this.visStart = al(d, true);
				this.end = this.visEnd = aG(al(this.start), 1);
				this.renderAgenda(1, this.option("columnFormat"))
			}
		})
	};
	function aJ(g, V, P) {
		var h, W, q, e, B, o, r, U, c, D, y, O, N = [], m, n, x, a, d, I, A, C, Q, v, H = new at(
				function(X) {
					return B.find("td:eq(" + X + ") div div")
				}), j = {}, t = az.extend(this, aT, P, {
			renderAgenda : S,
			renderEvents : p,
			rerenderEvents : s,
			clearEvents : T,
			setHeight : M,
			setWidth : G,
			beforeHide : function() {
				O = W.scrollTop()
			},
			afterShow : function() {
				W.scrollTop(O)
			},
			defaultEventEnd : function(Y) {
				var X = al(Y.start);
				if (Y.allDay) {
					return X
				}
				return aQ(X, V.defaultEventMinutes)
			}
		});
		t.init(g, V);
		g.addClass("fc-agenda");
		if (g.disableSelection) {
			g.disableSelection()
		}
		function S(bc, X) {
			o = bc;
			x = V.theme ? "ui" : "fc";
			d = V.weekends ? 0 : 1;
			a = V.firstDay;
			if (I = V.isRTL) {
				A = -1;
				C = o - 1
			} else {
				A = 1;
				C = 0
			}
			Q = a4(V.minTime);
			v = a4(V.maxTime);
			var aa = I ? aG(al(t.visEnd), -1) : al(t.visStart), bd = al(aa), ba = aI(new Date());
			if (!h) {
				var Y, Z, bb = V.slotMinutes % 15 == 0, ab = "<div class='fc-agenda-head' style='position:relative;z-index:4'><table style='width:100%'><tr class='fc-first"
						+ (V.allDaySlot ? "" : " fc-last")
						+ "'><th class='fc-leftmost "
						+ x
						+ "-state-default'>&nbsp;</th>";
				for (Y = 0; Y < o; Y++) {
					ab += "<th class='fc-" + ah[bd.getDay()] + " " + x
							+ "-state-default'>" + ao(bd, X, V) + "</th>";
					aG(bd, A);
					if (d) {
						ac(bd, A)
					}
				}
				ab += "<th class='" + x + "-state-default'>&nbsp;</th></tr>";
				if (V.allDaySlot) {
					ab += "<tr class='fc-all-day'><th class='fc-axis fc-leftmost "
							+ x
							+ "-state-default'>"
							+ V.allDayText
							+ "</th><td colspan='"
							+ o
							+ "' class='"
							+ x
							+ "-state-default'><div class='fc-day-content'><div style='position:relative'>&nbsp;</div></div></td><th class='"
							+ x
							+ "-state-default'>&nbsp;</th></tr><tr class='fc-divider fc-last'><th colspan='"
							+ (o + 2)
							+ "' class='"
							+ x
							+ "-state-default fc-leftmost'><div/></th></tr>"
				}
				ab += "</table></div>";
				h = az(ab).appendTo(g);
				h.find("td").click(w);
				m = az(
						"<div style='position:absolute;z-index:8;top:0;left:0'/>")
						.appendTo(h);
				bd = a1();
				var be = aQ(al(bd), v);
				aQ(bd, Q);
				ab = "<table>";
				for (Y = 0; bd < be; Y++) {
					Z = bd.getMinutes();
					ab += "<tr class='"
							+ (Y == 0 ? "fc-first" : (Z == 0 ? "" : "fc-minor"))
							+ "'><th class='fc-axis fc-leftmost "
							+ x
							+ "-state-default'>"
							+ ((!bb || Z == 0) ? ao(bd, V.axisFormat)
									: "&nbsp;")
							+ "</th><td class='fc-slot"
							+ Y
							+ " "
							+ x
							+ "-state-default'><div style='position:relative'>&nbsp;</div></td></tr>";
					aQ(bd, V.slotMinutes)
				}
				ab += "</table>";
				W = az(
						"<div class='fc-agenda-body' style='position:relative;z-index:2;overflow:auto'/>")
						.append(
								q = az(
										"<div style='position:relative;overflow:hidden'>")
										.append(e = az(ab))).appendTo(g);
				W.find("td").click(w);
				n = az(
						"<div style='position:absolute;z-index:8;top:0;left:0'/>")
						.appendTo(q);
				bd = al(aa);
				ab = "<div class='fc-agenda-bg' style='position:absolute;z-index:1'><table style='width:100%;height:100%'><tr class='fc-first'>";
				for (Y = 0; Y < o; Y++) {
					ab += "<td class='fc-"
							+ ah[bd.getDay()]
							+ " "
							+ x
							+ "-state-default "
							+ (Y == 0 ? "fc-leftmost " : "")
							+ (+bd == +ba ? x + "-state-highlight fc-today"
									: "fc-not-today")
							+ "'><div class='fc-day-content'><div>&nbsp;</div></div></td>";
					aG(bd, A);
					if (d) {
						ac(bd, A)
					}
				}
				ab += "</tr></table></div>";
				B = az(ab).appendTo(g)
			} else {
				T();
				h.find("tr:first th").slice(1, -1).each(
						function() {
							az(this).text(ao(bd, X, V));
							this.className = this.className.replace(
									/^fc-\w+(?= )/, "fc-" + ah[bd.getDay()]);
							aG(bd, A);
							if (d) {
								ac(bd, A)
							}
						});
				bd = al(aa);
				B.find("td").each(
						function() {
							this.className = this.className.replace(
									/^fc-\w+(?= )/, "fc-" + ah[bd.getDay()]);
							if (+bd == +ba) {
								az(this).removeClass("fc-not-today").addClass(
										"fc-today").addClass(
										x + "-state-highlight")
							} else {
								az(this).addClass("fc-not-today").removeClass(
										"fc-today").removeClass(
										x + "-state-highlight")
							}
							aG(bd, A);
							if (d) {
								ac(bd, A)
							}
						})
			}
		}
		function k() {
			var aa = a1(), Y = al(aa);
			Y.setHours(V.firstHour);
			var X = l(aa, Y) + 1, Z = function() {
				W.scrollTop(X)
			};
			Z();
			setTimeout(Z, 0)
		}
		function M(Y, X) {
			y = Y;
			j = {};
			W.height(Y - h.height());
			c = W.find("tr:first div").height() + 1;
			B.css( {
				top : h.find("tr").height(),
				height : Y
			});
			if (X) {
				k()
			}
		}
		function G(Z) {
			D = Z;
			H.clear();
			W.width(Z);
			e.width("");
			var aa = h.find("tr:first th"), Y = B.find("td"), X = W[0].clientWidth;
			e.width(X);
			r = 0;
			ag(h.find("tr:lt(2) th:first").add(W.find("tr:first th")).width("")
					.each(function() {
						r = Math.max(r, az(this).outerWidth())
					}), r);
			U = Math.floor((X - r) / o);
			ag(Y.slice(0, -1), U);
			ag(aa.slice(1, -2), U);
			ag(aa.slice(-2, -1), X - r - U * (o - 1));
			B.css( {
				left : r,
				width : X - r
			})
		}
		function w(ab) {
			var X = Math.floor((ab.pageX - B.offset().left) / U), Y = aG(
					al(t.visStart), C + A * X), aa = this.className
					.match(/fc-slot(\d+)/);
			if (aa) {
				var a6 = parseInt(aa[1]) * V.slotMinutes, Z = Math
						.floor(a6 / 60);
				Y.setHours(Z);
				Y.setMinutes(a6 % 60 + Q);
				t.trigger("dayClick", this, Y, false, ab)
			} else {
				t.trigger("dayClick", this, Y, true, ab)
			}
		}
		function p(ab, X) {
			t.reportEvents(N = ab);
			var a6, Y = ab.length, aa = [], Z = [];
			for (a6 = 0; a6 < Y; a6++) {
				if (ab[a6].allDay) {
					aa.push(ab[a6])
				} else {
					Z.push(ab[a6])
				}
			}
			K(u(aa), X);
			i(b(Z), X)
		}
		function s(X) {
			T();
			p(N, X)
		}
		function T() {
			t._clearEvents();
			m.empty();
			n.empty()
		}
		function u(a9) {
			var ab = av(t.sliceSegs(a9, az.map(a9, L), t.visStart, t.visEnd)), ba, a8 = ab.length, aa, X, Y, Z = [];
			for (ba = 0; ba < a8; ba++) {
				aa = ab[ba];
				for (X = 0; X < aa.length; X++) {
					Y = aa[X];
					Y.row = 0;
					Y.level = ba;
					Z.push(Y)
				}
			}
			return Z
		}
		function b(ab) {
			var ba = aQ(al(t.visStart), Q), bc = az.map(ab, L), bb, Y, bd, aa, X, be, Z = [];
			for (bb = 0; bb < o; bb++) {
				Y = av(t.sliceSegs(ab, bc, ba, aQ(al(ba), v - Q)));
				aq(Y);
				for (bd = 0; bd < Y.length; bd++) {
					aa = Y[bd];
					for (X = 0; X < aa.length; X++) {
						be = aa[X];
						be.col = bb;
						be.level = bd;
						Z.push(be)
					}
				}
				aG(ba, 1, true)
			}
			return Z
		}
		function K(Y, X) {
			if (V.allDaySlot) {
				a2(Y, 1, t, r, D, function() {
					return h.find("tr.fc-all-day")
				}, function(Z) {
					return r + H.left(F(Z))
				}, function(Z) {
					return r + H.right(F(Z))
				}, m, z, X);
				M(y)
			}
		}
		function i(bs, br) {
			var bl, Z = bs.length, aa, bn, bF, bu, bx, by, bC, bw, bz, bq, bH, bB, bv = "", Y, ab, bA, bD = {}, bE = {}, bG, X, bm, bt;
			for (bl = 0; bl < Z; bl++) {
				aa = bs[bl];
				bn = aa.event;
				bF = "fc-event fc-event-vert ";
				if (aa.isStart) {
					bF += "fc-corner-top "
				}
				if (aa.isEnd) {
					bF += "fc-corner-bottom "
				}
				bu = l(aa.start, aa.start);
				bx = l(aa.start, aa.end);
				by = aa.col;
				bC = aa.level;
				bw = aa.forward || 0;
				bz = r + H.left(by * A + C);
				bq = r + H.right(by * A + C) - bz;
				bq = Math.min(bq - 6, bq * 0.95);
				if (bC) {
					bH = bq / (bC + bw + 1)
				} else {
					if (bw) {
						bH = ((bq / (bw + 1)) - (12 / 2)) * 2
					} else {
						bH = bq
					}
				}
				bB = bz + (bq / (bC + bw + 1) * bC) * A + (I ? bq - bH : 0);
				aa.top = bu;
				aa.left = bB;
				aa.outerWidth = bH;
				aa.outerHeight = bx - bu;
				bv += "<div class='"
						+ bF
						+ bn.className.join(" ")
						+ "' style='position:absolute;z-index:8;top:"
						+ bu
						+ "px;left:"
						+ bB
						+ "px'><a"
						+ (bn.url ? " href='" + ar(bn.url) + "'" : "")
						+ "><span class='fc-event-bg'></span><span class='fc-event-time'>"
						+ ar(aK(bn.start, bn.end, t.option("timeFormat")))
						+ "</span><span class='fc-event-title'>"
						+ ar(bn.title)
						+ "</span></a>"
						+ ((bn.editable || bn.editable == aS && V.editable)
								&& !V.disableResizing && az.fn.resizable ? "<div class='ui-resizable-handle ui-resizable-s'>=</div>"
								: "") + "</div>"
			}
			n[0].innerHTML = bv;
			Y = n.children();
			for (bl = 0; bl < Z; bl++) {
				aa = bs[bl];
				bn = aa.event;
				ab = az(Y[bl]);
				bA = t.trigger("eventRender", bn, bn, ab);
				if (bA === false) {
					ab.remove()
				} else {
					if (bA && bA !== true) {
						ab.remove();
						ab = az(bA).css( {
							position : "absolute",
							top : aa.top,
							left : aa.left
						}).appendTo(n)
					}
					aa.element = ab;
					if (bn._id === br) {
						J(bn, ab, aa)
					} else {
						ab[0]._fci = bl
					}
					t.reportEventElement(bn, ab)
				}
			}
			ai(n, bs, J);
			for (bl = 0; bl < Z; bl++) {
				aa = bs[bl];
				if (ab = aa.element) {
					X = bD[bG = aa.key = ae(ab[0])];
					aa.vsides = X == aS ? (bD[bG] = an(ab[0], true)) : X;
					X = bE[bG];
					aa.hsides = X == aS ? (bE[bG] = aB(ab[0], true)) : X;
					bm = ab.find("span.fc-event-title");
					if (bm.length) {
						aa.titleTop = bm[0].offsetTop
					}
				}
			}
			for (bl = 0; bl < Z; bl++) {
				aa = bs[bl];
				if (ab = aa.element) {
					ab[0].style.width = aa.outerWidth - aa.hsides + "px";
					ab[0].style.height = (bt = aa.outerHeight - aa.vsides)
							+ "px";
					bn = aa.event;
					if (aa.titleTop != aS && bt - aa.titleTop < 10) {
						ab.find("span.fc-event-time").text(
								ao(bn.start, t.option("timeFormat")) + " - "
										+ bn.title);
						ab.find("span.fc-event-title").remove()
					}
					t.trigger("eventAfterRender", bn, bn, ab)
				}
			}
		}
		function L(X) {
			if (X.allDay) {
				if (X.end) {
					var Y = al(X.end);
					return (X.allDay || Y.getHours() || Y.getMinutes()) ? aG(Y,
							1) : Y
				} else {
					return aG(al(X.start), 1)
				}
			}
			if (X.end) {
				return al(X.end)
			} else {
				return aQ(al(X.start), V.defaultEventMinutes)
			}
		}
		function z(X, Y, Z) {
			t.eventElementHandlers(X, Y);
			if (X.editable || X.editable == aS && V.editable) {
				f(X, Y, Z.isStart);
				if (Z.isEnd) {
					t.resizableDayEvent(X, Y, U)
				}
			}
		}
		function J(X, Y, Z) {
			t.eventElementHandlers(X, Y);
			if (X.editable || X.editable == aS && V.editable) {
				var aa = Y.find("span.fc-event-time");
				R(X, Y, aa);
				if (Z.isEnd) {
					E(X, Y, aa)
				}
			}
		}
		function f(a8, X, Z) {
			if (!V.disableDragging && X.draggable) {
				var a9, aa, ba, ab = true, Y;
				X
						.draggable( {
							zIndex : 9,
							opacity : t.option("dragOpacity", "month"),
							revertDuration : V.dragRevertDuration,
							start : function(a6, a5) {
								t.hideEvents(a8, X);
								t.trigger("eventDragStart", X, a8, a6, a5);
								a9 = X.position();
								aa = X.width();
								ba = function() {
									if (!ab) {
										X.width(aa).height("").draggable(
												"option", "grid", null);
										ab = true
									}
								};
								Y = new a0(
										function(a7) {
											X.draggable("option", "revert", !a7
													|| !a7.rowDelta
													&& !a7.colDelta);
											if (a7) {
												if (!a7.row) {
													ba();
													t.showOverlay(a7)
												} else {
													if (Z && ab) {
														aX(
																X.width(U - 10),
																c
																		* Math
																				.round((a8.end ? ((a8.end - a8.start) / af)
																						: V.defaultEventMinutes)
																						/ V.slotMinutes));
														X.draggable("option",
																"grid",
																[ U, 1 ]);
														ab = false
													}
													t.hideOverlay()
												}
											} else {
												t.hideOverlay()
											}
										});
								Y.row(h.find("td"));
								B.find("td").each(function() {
									Y.col(this)
								});
								Y.row(W);
								Y.mouse(a6.pageX, a6.pageY)
							},
							drag : function(a6, a5) {
								Y.mouse(a6.pageX, a6.pageY)
							},
							stop : function(bc, a7) {
								t.hideOverlay();
								t.trigger("eventDragStop", X, a8, bc, a7);
								var a6 = Y.cell, a5 = A
										* (ab ? (a6 ? a6.colDelta : 0)
												: Math
														.floor((a7.position.left - a9.left)
																/ U));
								if (!a6 || !a5 && !a6.rowDelta) {
									ba();
									if (az.browser.msie) {
										X.css("filter", "")
									}
									t.showEvents(a8, X)
								} else {
									X.find("a").removeAttr("href");
									t
											.eventDrop(
													this,
													a8,
													a5,
													ab ? 0
															: Math
																	.round((X
																			.offset().top - q
																			.offset().top)
																			/ c)
																	* V.slotMinutes
																	+ Q
																	- (a8.start
																			.getHours() * 60 + a8.start
																			.getMinutes()),
													ab, bc, a7)
								}
							}
						})
			}
		}
		function R(aa, bc, ba) {
			if (!V.disableDragging && bc.draggable) {
				var Z, Y, X, a9, ab = false, bb;
				bc
						.draggable( {
							zIndex : 9,
							scroll : false,
							grid : [ U, c ],
							axis : o == 1 ? "y" : false,
							opacity : t.option("dragOpacity"),
							revertDuration : V.dragRevertDuration,
							start : function(a5, a6) {
								t.hideEvents(aa, bc);
								t.trigger("eventDragStart", bc, aa, a5, a6);
								if (az.browser.msie) {
									bc.find("span.fc-event-bg").hide()
								}
								Z = bc.position();
								Y = function() {
									if (ab) {
										ba.css("display", "");
										bc
												.draggable("option", "grid", [
														U, c ]);
										ab = false
									}
								};
								X = 0;
								bb = new a0(function(a7) {
									bc.draggable("option", "revert", !a7);
									if (a7) {
										if (!a7.row && V.allDaySlot) {
											if (!ab) {
												ab = true;
												ba.hide();
												bc.draggable("option", "grid",
														null)
											}
											t.showOverlay(a7)
										} else {
											Y();
											t.hideOverlay()
										}
									} else {
										t.hideOverlay()
									}
								});
								if (V.allDaySlot) {
									bb.row(h.find("td"))
								}
								B.find("td").each(function() {
									bb.col(this)
								});
								bb.row(W);
								bb.mouse(a5.pageX, a5.pageY)
							},
							drag : function(a7, a6) {
								a9 = Math.round((a6.position.top - Z.top) / c);
								if (a9 != X) {
									if (!ab) {
										var be = a9 * V.slotMinutes, a5 = aQ(
												al(aa.start), be), a8;
										if (aa.end) {
											a8 = aQ(al(aa.end), be)
										}
										ba.text(aK(a5, a8, t
												.option("timeFormat")))
									}
									X = a9
								}
								bb.mouse(a7.pageX, a7.pageY)
							},
							stop : function(a7, a6) {
								t.hideOverlay();
								t.trigger("eventDragStop", bc, aa, a7, a6);
								var a5 = bb.cell, a8 = A
										* (ab ? (a5 ? a5.colDelta : 0)
												: Math
														.floor((a6.position.left - Z.left)
																/ U));
								if (!a5 || !a9 && !a8) {
									Y();
									if (az.browser.msie) {
										bc.css("filter", "").find(
												"span.fc-event-bg").css(
												"display", "")
									}
									bc.css(Z);
									t.showEvents(aa, bc)
								} else {
									t.eventDrop(this, aa, a8, ab ? 0 : a9
											* V.slotMinutes, ab, a7, a6)
								}
							}
						})
			}
		}
		function E(X, Y, ab) {
			if (!V.disableResizing && Y.resizable) {
				var aa, Z;
				Y
						.resizable( {
							handles : {
								s : "div.ui-resizable-s"
							},
							grid : c,
							start : function(a8, a7) {
								aa = Z = 0;
								t.hideEvents(X, Y);
								if (az.browser.msie
										&& az.browser.version == "6.0") {
									Y.css("overflow", "hidden")
								}
								Y.css("z-index", 9);
								t.trigger("eventResizeStart", this, X, a8, a7)
							},
							resize : function(a8, a7) {
								aa = Math
										.round((Math.max(c, Y.height()) - a7.originalSize.height)
												/ c);
								if (aa != Z) {
									ab.text(aK(X.start, (!aa && !X.end) ? null
											: aQ(t.eventEnd(X), V.slotMinutes
													* aa), t
											.option("timeFormat")));
									Z = aa
								}
							},
							stop : function(a8, a7) {
								t.trigger("eventResizeStop", this, X, a8, a7);
								if (aa) {
									t.eventResize(this, X, 0, V.slotMinutes
											* aa, a8, a7)
								} else {
									Y.css("z-index", 8);
									t.showEvents(X, Y)
								}
							}
						})
			}
		}
		function l(Y, aa) {
			Y = al(Y, true);
			if (aa < aQ(al(Y), Q)) {
				return 0
			}
			if (aa >= aQ(al(Y), v)) {
				return q.height()
			}
			var Z = V.slotMinutes, ab = aa.getHours() * 60 + aa.getMinutes()
					- Q, a6 = Math.floor(ab / Z), X = j[a6];
			if (X == aS) {
				X = j[a6] = W.find("tr:eq(" + a6 + ") td div")[0].offsetTop
			}
			return Math.max(0, Math.round(X - 1 + c * ((ab % Z) / Z)))
		}
		function F(X) {
			return ((X - Math.max(a, d) + o) % o) * A + C
		}
	}
	function aq(c) {
		var e, f, g, a, b, d;
		for (e = c.length - 1; e > 0; e--) {
			a = c[e];
			for (f = 0; f < a.length; f++) {
				b = a[f];
				for (g = 0; g < c[e - 1].length; g++) {
					d = c[e - 1][g];
					if (aH(b, d)) {
						d.forward = Math.max(d.forward || 0,
								(b.forward || 0) + 1)
					}
				}
			}
		}
	}
	var aT = {
		init : function(a, b) {
			this.element = a;
			this.options = b;
			this.eventsByID = {};
			this.eventElements = [];
			this.eventElementsByID = {}
		},
		trigger : function(b, a) {
			if (this.options[b]) {
				return this.options[b].apply(a || this, Array.prototype.slice
						.call(arguments, 2).concat( [ this ]))
			}
		},
		eventEnd : function(a) {
			return a.end ? al(a.end) : this.defaultEventEnd(a)
		},
		reportEvents : function(b) {
			var c, e = b.length, a, d = this.eventsByID = {};
			for (c = 0; c < e; c++) {
				a = b[c];
				if (d[a._id]) {
					d[a._id].push(a)
				} else {
					d[a._id] = [ a ]
				}
			}
		},
		reportEventElement : function(b, c) {
			this.eventElements.push(c);
			var a = this.eventElementsByID;
			if (a[b._id]) {
				a[b._id].push(c)
			} else {
				a[b._id] = [ c ]
			}
		},
		_clearEvents : function() {
			this.eventElements = [];
			this.eventElementsByID = {}
		},
		showEvents : function(a, b) {
			this._eee(a, b, "show")
		},
		hideEvents : function(a, b) {
			this._eee(a, b, "hide")
		},
		_eee : function(c, d, a) {
			var b = this.eventElementsByID[c._id], e, f = b.length;
			for (e = 0; e < f; e++) {
				if (b[e][0] != d[0]) {
					b[e][a]()
				}
			}
		},
		eventDrop : function(g, i, h, f, a, d, e) {
			var c = this, b = i.allDay, j = i._id;
			c.moveEvents(c.eventsByID[j], h, f, a);
			c.trigger("eventDrop", g, i, h, f, a, function() {
				c.moveEvents(c.eventsByID[j], -h, -f, b);
				c.rerenderEvents()
			}, d, e);
			c.eventsChanged = true;
			c.rerenderEvents(j)
		},
		eventResize : function(h, b, e, f, c, a) {
			var g = this, d = b._id;
			g.elongateEvents(g.eventsByID[d], e, f);
			g.trigger("eventResize", h, b, e, f, function() {
				g.elongateEvents(g.eventsByID[d], -e, -f);
				g.rerenderEvents()
			}, c, a);
			g.eventsChanged = true;
			g.rerenderEvents(d)
		},
		moveEvents : function(c, e, f, b) {
			f = f || 0;
			for ( var a, g = c.length, d = 0; d < g; d++) {
				a = c[d];
				if (b != aS) {
					a.allDay = b
				}
				aQ(aG(a.start, e, true), f);
				if (a.end) {
					a.end = aQ(aG(a.end, e, true), f)
				}
				aA(a, this.options)
			}
		},
		elongateEvents : function(b, d, e) {
			e = e || 0;
			for ( var a, f = b.length, c = 0; c < f; c++) {
				a = b[c];
				a.end = aQ(aG(this.eventEnd(a), d, true), e);
				aA(a, this.options)
			}
		},
		showOverlay : function(b) {
			if (!this.dayOverlay) {
				this.dayOverlay = az(
						"<div class='fc-cell-overlay' style='position:absolute;z-index:3;display:none'/>")
						.appendTo(this.element)
			}
			var a = this.element.offset();
			this.dayOverlay.css( {
				top : b.top - a.top,
				left : b.left - a.left,
				width : b.width,
				height : b.height
			}).show()
		},
		hideOverlay : function() {
			if (this.dayOverlay) {
				this.dayOverlay.hide()
			}
		},
		resizableDayEvent : function(a, c, b) {
			var d = this;
			if (!d.options.disableResizing && c.resizable) {
				c.resizable( {
					handles : d.options.isRTL ? {
						w : "div.ui-resizable-w"
					} : {
						e : "div.ui-resizable-e"
					},
					grid : b,
					minWidth : b / 2,
					containment : d.element.parent().parent(),
					start : function(f, e) {
						c.css("z-index", 9);
						d.hideEvents(a, c);
						d.trigger("eventResizeStart", this, a, f, e)
					},
					stop : function(f, e) {
						d.trigger("eventResizeStop", this, a, f, e);
						var g = Math.round((c.width() - e.originalSize.width)
								/ b);
						if (g) {
							d.eventResize(this, a, g, 0, f, e)
						} else {
							c.css("z-index", 8);
							d.showEvents(a, c)
						}
					}
				})
			}
		},
		eventElementHandlers : function(a, b) {
			var c = this;
			b.click(
					function(d) {
						if (!b.hasClass("ui-draggable-dragging")
								&& !b.hasClass("ui-resizable-resizing")) {
							return c.trigger("eventClick", this, a, d)
						}
					}).hover(function(d) {
				c.trigger("eventMouseover", this, a, d)
			}, function(d) {
				c.trigger("eventMouseout", this, a, d)
			})
		},
		option : function(b, a) {
			var c = this.options[b];
			if (typeof c == "object") {
				return aW(c, a || this.name)
			}
			return c
		},
		sliceSegs : function(b, h, l, i) {
			var k = [], g, e = b.length, m, d, f, c, a, n, j;
			for (g = 0; g < e; g++) {
				m = b[g];
				d = m.start;
				f = h[g];
				if (f > l && d < i) {
					if (d < l) {
						c = al(l);
						n = false
					} else {
						c = d;
						n = true
					}
					if (f > i) {
						a = al(i);
						j = false
					} else {
						a = f;
						j = true
					}
					k.push( {
						event : m,
						start : c,
						end : a,
						isStart : n,
						isEnd : j,
						msLength : a - c
					})
				}
			}
			return k.sort(aF)
		}
	};
	function ai(b, c, a) {
		b.unbind("mouseover").mouseover(function(d) {
			var e = d.target, h, f, g;
			while (e != this) {
				h = e;
				e = e.parentNode
			}
			if ((f = h._fci) != aS) {
				h._fci = aS;
				g = c[f];
				a(g.event, g.element, g);
				az(d.target).trigger(d)
			}
			d.stopPropagation()
		})
	}
	function av(e) {
		var a = [], b, g = e.length, f, c, h, d;
		for (b = 0; b < g; b++) {
			f = e[b];
			c = 0;
			while (true) {
				h = false;
				if (a[c]) {
					for (d = 0; d < a[c].length; d++) {
						if (aH(a[c][d], f)) {
							h = true;
							break
						}
					}
				}
				if (h) {
					c++
				} else {
					break
				}
			}
			if (a[c]) {
				a[c].push(f)
			} else {
				a[c] = [ f ]
			}
		}
		return a
	}
	function aF(a, b) {
		return (b.msLength - a.msLength) * 100
				+ (a.event.start - b.event.start)
	}
	function aH(a, b) {
		return a.end > b.start && a.start < b.end
	}
	var aj = 86400000, aO = 3600000, af = 60000;
	function ad(b, a, c) {
		b.setFullYear(b.getFullYear() + a);
		if (!c) {
			aI(b)
		}
		return b
	}
	function aP(b, a, c) {
		if (+b) {
			var e = b.getMonth() + a, d = al(b);
			d.setDate(1);
			d.setMonth(e);
			b.setMonth(e);
			if (!c) {
				aI(b)
			}
			while (b.getMonth() != d.getMonth()) {
				b.setDate(b.getDate() + (b < d ? 1 : -1))
			}
		}
		return b
	}
	function aG(b, a, c) {
		if (+b) {
			var e = b.getDate() + a, d = al(b);
			d.setHours(9);
			d.setDate(e);
			b.setDate(e);
			if (!c) {
				aI(b)
			}
			aN(b, d)
		}
		return b
	}
	au.addDays = aG;
	function aN(a, b) {
		if (+a) {
			while (a.getDate() != b.getDate()) {
				a.setTime(+a + (a < b ? 1 : -1) * aO)
			}
		}
	}
	function aQ(b, a) {
		b.setMinutes(b.getMinutes() + a);
		return b
	}
	function aI(a) {
		a.setHours(0);
		a.setMinutes(0);
		a.setSeconds(0);
		a.setMilliseconds(0);
		return a
	}
	function al(b, a) {
		if (a) {
			return aI(new Date(+b))
		}
		return new Date(+b)
	}
	function a1() {
		var b = 0, a;
		do {
			a = new Date(1970, b++, 1)
		} while (a.getHours() != 0);
		return a
	}
	function ac(c, b, a) {
		b = b || 1;
		while (c.getDay() == 0
				|| (a && c.getDay() == 1 || !a && c.getDay() == 6)) {
			aG(c, b)
		}
		return c
	}
	var aY = au.parseDate = function(a) {
		if (typeof a == "object") {
			return a
		}
		if (typeof a == "number") {
			return new Date(a * 1000)
		}
		if (typeof a == "string") {
			if (a.match(/^\d+$/)) {
				return new Date(parseInt(a) * 1000)
			}
			return aU(a, true) || (a ? new Date(a) : null)
		}
		return null
	};
	var aU = au.parseISO8601 = function(b, e) {
		var f = b
				.match(/^([0-9]{4})(-([0-9]{2})(-([0-9]{2})([T ]([0-9]{2}):([0-9]{2})(:([0-9]{2})(\.([0-9]+))?)?(Z|(([-+])([0-9]{2}):([0-9]{2})))?)?)?)?$/);
		if (!f) {
			return null
		}
		var c = new Date(f[1], 0, 1), d = new Date(f[1], 0, 1, 9, 0), a = 0;
		if (f[3]) {
			c.setMonth(f[3] - 1);
			d.setMonth(f[3] - 1)
		}
		if (f[5]) {
			c.setDate(f[5]);
			d.setDate(f[5])
		}
		aN(c, d);
		if (f[7]) {
			c.setHours(f[7])
		}
		if (f[8]) {
			c.setMinutes(f[8])
		}
		if (f[10]) {
			c.setSeconds(f[10])
		}
		if (f[12]) {
			c.setMilliseconds(Number("0." + f[12]) * 1000)
		}
		aN(c, d);
		if (!e) {
			if (f[14]) {
				a = Number(f[16]) * 60 + Number(f[17]);
				a *= f[15] == "-" ? 1 : -1
			}
			a -= c.getTimezoneOffset()
		}
		return new Date(+c + (a * 60 * 1000))
	};
	var a4 = au.parseTime = function(a) {
		if (typeof a == "number") {
			return a * 60
		}
		if (typeof a == "object") {
			return a.getHours() * 60 + a.getMinutes()
		}
		var c = a.match(/(\d+)(?::(\d+))?\s*(\w+)?/);
		if (c) {
			var b = parseInt(c[1]);
			if (c[3]) {
				b %= 12;
				if (c[3].toLowerCase().charAt(0) == "p") {
					b += 12
				}
			}
			return b * 60 + (c[2] ? parseInt(c[2]) : 0)
		}
	};
	var ao = au.formatDate = function(b, a, c) {
		return aK(b, null, a, c)
	};
	var aK = au.formatDates = function(b, c, d, a) {
		a = a || ax;
		var m = b, k = c, j, i = d.length, g, l, e, h = "";
		for (j = 0; j < i; j++) {
			g = d.charAt(j);
			if (g == "'") {
				for (l = j + 1; l < i; l++) {
					if (d.charAt(l) == "'") {
						if (m) {
							if (l == j + 1) {
								h += "'"
							} else {
								h += d.substring(j + 1, l)
							}
							j = l
						}
						break
					}
				}
			} else {
				if (g == "(") {
					for (l = j + 1; l < i; l++) {
						if (d.charAt(l) == ")") {
							var n = ao(m, d.substring(j + 1, l), a);
							if (parseInt(n.replace(/\D/, ""))) {
								h += n
							}
							j = l;
							break
						}
					}
				} else {
					if (g == "[") {
						for (l = j + 1; l < i; l++) {
							if (d.charAt(l) == "]") {
								var f = d.substring(j + 1, l);
								var n = ao(m, f, a);
								if (n != ao(k, f, a)) {
									h += n
								}
								j = l;
								break
							}
						}
					} else {
						if (g == "{") {
							m = c;
							k = b
						} else {
							if (g == "}") {
								m = b;
								k = c
							} else {
								for (l = i; l > j; l--) {
									if (e = aM[d.substring(j, l)]) {
										if (m) {
											h += e(m, a)
										}
										j = l - 1;
										break
									}
								}
								if (l == j) {
									if (m) {
										h += g
									}
								}
							}
						}
					}
				}
			}
		}
		return h
	};
	var aM = {
		s : function(a) {
			return a.getSeconds()
		},
		ss : function(a) {
			return ak(a.getSeconds())
		},
		m : function(a) {
			return a.getMinutes()
		},
		mm : function(a) {
			return ak(a.getMinutes())
		},
		h : function(a) {
			return a.getHours() % 12 || 12
		},
		hh : function(a) {
			return ak(a.getHours() % 12 || 12)
		},
		H : function(a) {
			return a.getHours()
		},
		HH : function(a) {
			return ak(a.getHours())
		},
		d : function(a) {
			return a.getDate()
		},
		dd : function(a) {
			return ak(a.getDate())
		},
		ddd : function(a, b) {
			return b.dayNamesShort[a.getDay()]
		},
		dddd : function(a, b) {
			return b.dayNames[a.getDay()]
		},
		M : function(a) {
			return a.getMonth() + 1
		},
		MM : function(a) {
			return ak(a.getMonth() + 1)
		},
		MMM : function(a, b) {
			return b.monthNamesShort[a.getMonth()]
		},
		MMMM : function(a, b) {
			return b.monthNames[a.getMonth()]
		},
		yy : function(a) {
			return (a.getFullYear() + "").substring(2)
		},
		yyyy : function(a) {
			return a.getFullYear()
		},
		t : function(a) {
			return a.getHours() < 12 ? "a" : "p"
		},
		tt : function(a) {
			return a.getHours() < 12 ? "am" : "pm"
		},
		T : function(a) {
			return a.getHours() < 12 ? "A" : "P"
		},
		TT : function(a) {
			return a.getHours() < 12 ? "AM" : "PM"
		},
		u : function(a) {
			return ao(a, "yyyy-MM-dd'T'HH:mm:ss'Z'")
		},
		S : function(a) {
			var b = a.getDate();
			if (b > 10 && b < 20) {
				return "th"
			}
			return [ "st", "nd", "rd" ][b % 10 - 1] || "th"
		}
	};
	function ag(b, a, c) {
		b.each(function(d, e) {
			e.style.width = a - aB(e, c) + "px"
		})
	}
	function aX(a, c, b) {
		a.each(function(d, e) {
			e.style.height = c - an(e, b) + "px"
		})
	}
	function aB(a, b) {
		return (parseFloat(jQuery.curCSS(a, "paddingLeft", true)) || 0)
				+ (parseFloat(jQuery.curCSS(a, "paddingRight", true)) || 0)
				+ (parseFloat(jQuery.curCSS(a, "borderLeftWidth", true)) || 0)
				+ (parseFloat(jQuery.curCSS(a, "borderRightWidth", true)) || 0)
				+ (b ? aR(a) : 0)
	}
	function aR(a) {
		return (parseFloat(jQuery.curCSS(a, "marginLeft", true)) || 0)
				+ (parseFloat(jQuery.curCSS(a, "marginRight", true)) || 0)
	}
	function an(a, b) {
		return (parseFloat(jQuery.curCSS(a, "paddingTop", true)) || 0)
				+ (parseFloat(jQuery.curCSS(a, "paddingBottom", true)) || 0)
				+ (parseFloat(jQuery.curCSS(a, "borderTopWidth", true)) || 0)
				+ (parseFloat(jQuery.curCSS(a, "borderBottomWidth", true)) || 0)
				+ (b ? aL(a) : 0)
	}
	function aL(a) {
		return (parseFloat(jQuery.curCSS(a, "marginTop", true)) || 0)
				+ (parseFloat(jQuery.curCSS(a, "marginBottom", true)) || 0)
	}
	function aE(b, a) {
		a = typeof a == "number" ? a + "px" : a;
		b[0].style.cssText += ";min-height:" + a + ";_height:" + a
	}
	var aD;
	function aC(a) {
		if (aD !== false) {
			var b;
			if (a.is("th,td")) {
				a = (b = a).parent()
			}
			if (aD == aS && a.is("tr")) {
				aD = a.position().top != a.children().position().top
			}
			if (aD) {
				return a.parent().position().top
						+ (b ? a.position().top - b.position().top : 0)
			}
		}
		return 0
	}
	function a0(h) {
		var b = this, c = [], j = [], d, e, g, f, a, i;
		b.row = function(k) {
			d = az(k);
			c.push(d.offset().top + aC(d))
		};
		b.col = function(k) {
			e = az(k);
			j.push(e.offset().left)
		};
		b.mouse = function(n, k) {
			if (g == aS) {
				c.push(c[c.length - 1] + d.outerHeight());
				j.push(j[j.length - 1] + e.outerWidth());
				a = i = -1
			}
			var m, l;
			for (m = 0; m < c.length && k >= c[m]; m++) {
			}
			for (l = 0; l < j.length && n >= j[l]; l++) {
			}
			m = m >= c.length ? -1 : m - 1;
			l = l >= j.length ? -1 : l - 1;
			if (m != a || l != i) {
				a = m;
				i = l;
				if (m == -1 || l == -1) {
					b.cell = null
				} else {
					if (g == aS) {
						g = m;
						f = l
					}
					b.cell = {
						row : m,
						col : l,
						top : c[m],
						left : j[l],
						width : j[l + 1] - j[l],
						height : c[m + 1] - c[m],
						isOrig : m == g && l == f,
						rowDelta : m - g,
						colDelta : l - f
					}
				}
				h(b.cell)
			}
		}
	}
	var aS, ah = [ "sun", "mon", "tue", "wed", "thu", "fri", "sat" ], aV = Array.prototype.pop;
	function ak(a) {
		return (a < 10 ? "0" : "") + a
	}
	function aW(a, e) {
		if (a[e] != aS) {
			return a[e]
		}
		var b = e.split(/(?=[A-Z])/), c = b.length - 1, d;
		for (; c >= 0; c--) {
			d = a[b[c].toLowerCase()];
			if (d != aS) {
				return d
			}
		}
		return a[""]
	}
	function ar(a) {
		return a.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g,
				"&gt;").replace(/'/g, "&#039;").replace(/"/g, "&quot;")
	}
	function at(e) {
		var f = this, d = {}, a = {}, b = {};
		function c(g) {
			return d[g] = d[g] || e(g)
		}
		f.left = function(g) {
			return a[g] = a[g] == aS ? c(g).position().left : a[g]
		};
		f.right = function(g) {
			return b[g] = b[g] == aS ? f.left(g) + c(g).width() : b[g]
		};
		f.clear = function() {
			d = {};
			a = {};
			b = {}
		}
	}
	function ae(a) {
		return a.id
				+ "/"
				+ a.className
				+ "/"
				+ a.style.cssText.replace(
						/(^|;)\s*(top|left|width|height)\s*:[^;]*/ig, "")
	}
})(jQuery);
PrimeFaces.widget.Schedule = function(b, a) {
	this.id = b;
	this.cfg = a;
	this.jqId = PrimeFaces.escapeClientId(this.id);
	this.jq = this.jqId + "_container";
	this.setupEventSource();
	if (this.cfg.language) {
		this.applyLocale()
	}
	if (this.cfg.editable) {
		this.setupEventHandlers()
	}
	jQuery(this.jq).fullCalendar(this.cfg)
};
PrimeFaces.widget.Schedule.prototype.applyLocale = function() {
	var a = PrimeFaces.widget.ScheduleResourceBundle[this.cfg.language];
	if (a) {
		this.cfg.monthNames = a.monthNames;
		this.cfg.monthNamesShort = a.monthNamesShort;
		this.cfg.dayNames = a.dayNames;
		this.cfg.dayNamesShort = a.dayNamesShort;
		this.cfg.buttonText = {
			today : a.today,
			month : a.month,
			week : a.week,
			day : a.day
		};
		this.cfg.allDayText = a.allDayText
	}
};
PrimeFaces.widget.Schedule.prototype.setupEventHandlers = function() {
	var a = this;
	this.cfg.dayClick = function(b, f, d, c) {
		var e = {
			source : a.id,
			process : a.id,
			formId : a.cfg.formId
		}, g = {};
		g[a.id + "_ajaxEvent"] = true;
		g[a.id + "_selectedDate"] = b.getTime();
		if (a.cfg.onDateSelectUpdate) {
			e.update = a.cfg.onDateSelectUpdate
		}
		if (a.cfg.onDateSelectStart) {
			a.cfg.onDateSelectStart.call(a, b, f, d, c)
		}
		if (a.cfg.onDateSelectComplete) {
			e.oncomplete = a.cfg.onDateSelectComplete
		}
		PrimeFaces.ajax.AjaxRequest(a.cfg.url, e, g)
	};
	this.cfg.eventClick = function(f, c, b) {
		var d = {
			source : a.id,
			process : a.id,
			formId : a.cfg.formId
		}, e = {};
		e[a.id + "_ajaxEvent"] = true;
		e[a.id + "_selectedEventId"] = f.id;
		if (a.cfg.onEventSelectUpdate) {
			d.update = a.cfg.onEventSelectUpdate
		}
		if (a.cfg.onEventSelectStart) {
			a.cfg.onEventSelectStart.call(a, f, c, b)
		}
		if (a.cfg.onEventSelectComplete) {
			d.oncomplete = a.cfg.onEventSelectComplete
		}
		PrimeFaces.ajax.AjaxRequest(a.cfg.url, d, e)
	};
	this.cfg.eventDrop = function(g, c, e, k, d, f, h, i) {
		var j = {
			source : a.id,
			process : a.id,
			formId : a.cfg.formId
		}, b = {};
		b[a.id + "_ajaxEvent"] = true;
		b[a.id + "_changedEventId"] = g.id;
		b[a.id + "_dayDelta"] = c;
		b[a.id + "_minuteDelta"] = e;
		if (a.cfg.onEventMoveUpdate) {
			j.update = a.cfg.onEventMoveUpdate
		}
		PrimeFaces.ajax.AjaxRequest(a.cfg.url, j, b)
	};
	this.cfg.eventResize = function(g, c, e, d, f, h, i) {
		var j = {
			source : a.id,
			process : a.id,
			formId : a.cfg.formId
		}, b = {};
		b[a.id + "_ajaxEvent"] = true;
		b[a.id + "_changedEventId"] = g.id;
		b[a.id + "_dayDelta"] = c;
		b[a.id + "_minuteDelta"] = e;
		b[a.id + "_resized"] = true;
		if (a.cfg.onEventResizeUpdate) {
			j.update = a.cfg.onEventResizeUpdate
		}
		PrimeFaces.ajax.AjaxRequest(a.cfg.url, j, b)
	}
};
PrimeFaces.widget.Schedule.prototype.setupEventSource = function() {
	var a = this;
	this.cfg.events = function(f, b, e) {
		var c = {
			source : a.id,
			process : a.id,
			update : a.id,
			formId : a.cfg.formId,
			onsuccess : function(o) {
				var m = o.documentElement, n = m.getElementsByTagName("update");
				for ( var k = 0; k < n.length; k++) {
					var p = n[k].attributes.getNamedItem("id").nodeValue, l = n[k].firstChild.data;
					if (p == a.id) {
						var h = jQuery.parseJSON(l).events;
						for ( var g = 0; g < h.length; g++) {
							h[g].start = new Date(h[g].start);
							h[g].end = new Date(h[g].end)
						}
						e(h)
					} else {
						PrimeFaces.ajax.AjaxUtils.updateElement(p, l)
					}
				}
				return false
			}
		};
		var d = {};
		d[a.id + "_start"] = f.getTime();
		d[a.id + "_end"] = b.getTime();
		PrimeFaces.ajax.AjaxRequest(a.cfg.url, c, d)
	}
};
PrimeFaces.widget.Schedule.prototype.update = function() {
	jQuery(this.jq).fullCalendar("refetchEvents")
};
PrimeFaces.widget.ScheduleResourceBundle = {
	tr : {
		monthNamesShort : [ "Oca", "\u015eub", "Mar", "Nis", "May", "Haz",
				"Tem", "A\u011fu", "Eyl", "Eki", "Kas", "Ara" ],
		monthNames : [ "Ocak", "\u015eubat", "Mart", "Nisan", "May\u0131s",
				"Haziran", "Temmuz", "A\u011fustos", "Eyl\u00fcl", "Ekim",
				"Kas\u0131m", "Aral\u0131k" ],
		dayNamesShort : [ "Paz", "Pzt", "Sal", "\u00c7ar", "Per", "Cum", "Cts" ],
		dayNames : [ "Pazar", "Pazartesi", "Sal\u0131", "\u00c7ar\u015famba",
				"Per\u015fembe", "Cuma", "Cumartesi" ],
		today : "bug\u00fcn",
		month : "ay",
		week : "hafta",
		day : "g\u00fcn",
		allDayText : "t\u00fcm-g\u00fcn"
	},
	ca : {
		monthNamesShort : [ "Gen", "Feb", "Mar", "Abr", "Mai", "Jun", "Jul",
				"Ago", "Set", "Oct", "Nov", "Des" ],
		monthNames : [ "Gener", "Febrer", "Mar\u00e7", "Abril", "Maig", "Juny",
				"Juliol", "Agost", "Setembre", "Octubre", "Novembre",
				"Desembre" ],
		dayNamesShort : [ "Diu", "Dil", "Dim", "Dim", "Dij", "Div", "Dis" ],
		dayNames : [ "Diumenge", "Dilluns", "Dimarts", "Dimecres", "Dijous",
				"Divendres", "Dissabte" ],
		today : "avui",
		month : "mes",
		week : "setmana",
		day : "dia",
		allDayText : "tot-el dia"
	},
	pt : {
		monthNamesShort : [ "Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul",
				"Ago", "Set", "Out", "Nov", "Dez" ],
		monthNames : [ "Janeiro", "Fevereiro", "Mar\u00e7o", "Abril", "Maio",
				"Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro",
				"Dezembro" ],
		dayNamesShort : [ "Dom", "Seg", "Ter", "Qua", "Qui", "Sexta",
				"S\u00e1b" ],
		dayNames : [ "Domingo", "Segunda-feira", "Ter\u00e7a-feira",
				"Quarta-feira", "Quinta-feira", "Sexta-feira", "S\u00e1bado" ],
		today : "hoje",
		month : "m\u00eas",
		week : "semana",
		day : "dia",
		allDayText : "todos-os dias"
	},
	it : {
		monthNamesShort : [ "Gen", "Feb", "Mar", "Apr", "Mag", "Giu", "Lug",
				"Ago", "Set", "Ott", "Nov", "Dic" ],
		monthNames : [ "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio",
				"Giugno", "Luglio", "Agosto", "Settembre", "Ottobre",
				"Novembre", "Dicembre" ],
		dayNamesShort : [ "Dom", "Lun", "Mar", "Mer", "Gio", "Ven", "Sab" ],
		dayNames : [ "Domenica", "Luned\u00ec", "Marted\u00ec",
				"Mercoled\u00ec", "Gioved\u00ec", "Venerd\u00ec", "Sabato" ],
		today : "oggi",
		month : "mese",
		week : "settimana",
		day : "giorno",
		allDayText : "tutto-il giorno"
	},
	fr : {
		monthNamesShort : [ "Jan", "F\u00e9v", "Mar", "Avr", "Mai", "Jui",
				"Jui", "Ao\u00fb", "Sep", "Oct", "Nov", "D\u00e9c" ],
		monthNames : [ "Janvier", "F\u00e9vrier", "Mars", "Avril", "Mai",
				"Juin", "Juillet", "Ao\u00fbt", "Septembre", "Octobre",
				"Novembre", "D\u00e9cembre" ],
		dayNamesShort : [ "Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam" ],
		dayNames : [ "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi",
				"Vendredi", "Samedi" ],
		today : "aujourd'hui",
		month : "mois",
		week : "semaine",
		day : "jour",
		allDayText : "toute-la journ\u00e9e"
	},
	es : {
		monthNamesShort : [ "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul",
				"Ago", "Sep", "Oct", "Nov", "Dic" ],
		monthNames : [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
				"Julio", "Agosto", "Septiembre", "Octubre", "Noviembre",
				"Diciembre" ],
		dayNamesShort : [ "Dom", "Lun", "Mar", "Mi\u00e9", "Jue", "Vie",
				"S\u00e1b" ],
		dayNames : [ "Domingo", "Lunes", "Martes", "Mi\00E9rcoles", "Jueves",
				"Viernes", "S\u00e1bado" ],
		today : "hoy",
		month : "mes",
		week : "semana",
		day : "d\u00eda",
		allDayText : "todo-el d\u00eda"
	},
	de : {
		monthNamesShort : [ "Jan", "Feb", "M\u00e4r", "Apr", "Mai", "Jun",
				"Jul", "Aug", "Sep", "Okt", "Nov", "Dez" ],
		monthNames : [ "Januar", "Februar", "M\u00e4rz", "April", "Mai",
				"Juni", "Juli", "August", "September", "Oktober", "November",
				"Dezember" ],
		dayNamesShort : [ "Son", "Mon", "Die", "Mit", "Don", "Fre", "Sam" ],
		dayNames : [ "Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag",
				"Freitag", "Samstag" ],
		today : "heute",
		month : "monat",
		week : "woche",
		day : "tag",
		allDayText : "ganzen-tag"
	},
	ja : {
		monthNamesShort : [ "1\u6708", "2\u6708", "3\u6708", "4\u6708",
				"5\u6708", "6\u6708", "7\u6708", "8\u6708", "9\u6708",
				"10\u6708", "11\u6708", "12\u6708" ],
		monthNames : [ "1\u6708", "2\u6708", "3\u6708", "4\u6708", "5\u6708",
				"6\u6708", "7\u6708", "8\u6708", "9\u6708", "10\u6708",
				"11\u6708", "12\u6708" ],
		dayNamesShort : [ "\u65e5", "\u6708", "\u706b", "\u6c34", "\u6728",
				"\u91d1", "\u571f" ],
		dayNames : [ "\u65e5", "\u6708", "\u706b", "\u6c34", "\u6728",
				"\u91d1", "\u571f" ],
		today : "\u672c\u65e5",
		month : "\u5148\u6708",
		week : "\u9031",
		day : "\u65e5",
		allDayText : "\u7d42-\u65e5"
	},
	fi : {
		monthNamesShort : [ "Tammi", "Helmi", "Maalis", "Huhti", "Touko",
				"Kes\u00E4", "Hein\u00E4", "Elo", "Syys", "Loka", "Marras",
				"Joulu" ],
		monthNames : [ "Tammikuu", "Helmikuu", "Maaliskuu", "Huhtikuu",
				"Toukokuu", "Kes\u00E4kuu", "Hein\u00E4kuu", "Elokuu",
				"Syyskuu", "Lokakuu", "Marraskuu", "Joulukuu" ],
		dayNamesShort : [ "Su", "Ma", "Ti", "Ke", "To", "Pe", "La" ],
		dayNames : [ "Sunnuntai", "Maanantai", "Tiistai", "Keskiviikko",
				"Torstai", "Perjantai", "Lauantai" ],
		today : "t\u00E4n\u00E4\u00E4n",
		month : "kuukausi",
		week : "viikko",
		day : "p\u00E4iv\u00E4",
		allDayText : "koko p\u00E4iv\u00E4"
	},
	hu : {
		monthNamesShort : [ "Jan", "Feb", "M\u00e1r", "\u00c1pr", "M\u00e1j",
				"J\u00fan", "J\u00fal", "Aug", "Szep", "Okt", "Nov", "Dec" ],
		monthNames : [ "Janu\u00e1r", "Febru\u00e1r", "M\u00e1rcius",
				"\u00c1prilis", "M\u00e1jus", "J\u00fanius", "J\u00falius",
				"Augusztus", "Szeptember", "Okt\u00f3ber", "November",
				"December" ],
		dayNamesShort : [ "Vas", "H\u00e9t", "Ked", "Sze", "Cs\u00fc",
				"P\u00e9n", "Szo" ],
		dayNames : [ "Vas\u00e1rnap", "H\u00e9tf\u0151", "Kedd", "Szerda",
				"Cs\u00fct\u00f6rt\u00f6k", "P\u00e9ntek", "Szombat" ],
		today : "ma",
		month : "h\u00f3nap",
		week : "h\u00e9t",
		day : "nap",
		allDayText : "eg\u00e9sz nap"
	}
};