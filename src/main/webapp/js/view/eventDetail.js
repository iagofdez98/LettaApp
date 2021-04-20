var EventDetailView = (function() {
	var dao;

	// Referencia a this que permite acceder a las funciones públicas desde las
	// funciones de jQuery.
	var self;
	var previous;
	var next;
	var eventToShow;

	function EventDetailView(eventId, eventsList, listContainerId) {
		self = this;

		this.init = function() {
			getPreviousAndNext(eventsList, eventId);
			insertElements($('#' + listContainerId), eventToShow);
		};

		this.getRecents = function() {
			$('#' + 'events-container').empty();
			var view = new RecentEventsView(new EventsDAO(),
					'events-container', 'events-container');

			view.init();
		};

		this.getEvent = function(event) {
			$('#' + 'events-container').empty();
			var view = new EventDetailView(event.id, eventsList,'events-container');
			view.init();

		};
	}
	;

	var getPreviousAndNext = function(eventsList, eventId) {
		encontrado = false;
		previous = null;
		next = null;
		eventToShow = null;
		$.each(eventsList, function(key, eventActual) {
			if (eventActual.id != eventId) {
				if (encontrado) {
					next = eventActual;
					return false;
				} else {
					previous = eventActual;
				}
			} else {
				eventToShow = eventActual;
				encontrado = true;
			}
		});
	}

	var addNavegationLeft = function(parent, event) {
		parent
				.append('<a href="#" class="previous text-secondary"><i class="fas fa-arrow-circle-left fa-3x"></i></a>');
	}

	var addNavegationRight = function(parent, event) {
		parent
				.append('<a href="#" class="next text-secondary"><i class="fas fa-arrow-circle-right fa-3x"></i></a>');
	}

	var showElement = function(parent, event) {
		parent
				.append('<div class="col-10">\
				<div class="section p-5 border rounded mt-5" style="background-color: #DEE1FC;">\
				<div id="return">\
						<a href="#" class="btn btn-secondary return"><i class="fas fa-arrow-left"></i>&nbsp;Volver</a>\
				</div>\
				<div class="section p-5" align="center">\
				<div class="row">\
								<div class="col-md-3"></div>\
								<div class="col-md-6 p-10">\
									<h1 class="display-3">'
						+ event.title
						+ '</h1>\
									<div class="row">\
										<div class="col-3" style="word-break: break-word;"><i class="fas fa-map-marker-alt mr-2"></i>'
						+ event.location
						+ '</div>\
										<div class="col-3"><i class="far fa-calendar-alt mr-2"></i>'
						+ new Date(event.event_date).toLocaleString().split(' ')[0]
						+ '</div>\
										<div class="col-3"><i class="far fa-hourglass mr-2"></i>'
						+ event.duration
						+ ' minutos</div>\
										<div class="col-3"><i class="fas fa-users mr-2"></i>'
						+ event.participants
						+ ' / '
						+ event.capacity
						+ '</div>\
									</div>\
								</div>\
									<div class="col-md-3"><img id="icono" alt="Icono de la categoria" class="img-fluid"></div>\
								</div>\
									</div>\
							</div>\
							<div>\
							<div class="section p-5 border rounded" style="background-color: #FCF6FF;">\
									<div id="event-'
						+ event.id
						+ '">\
									<p>'
						+ event.description
						+ '</p>\
									<p><small class="text-muted">Creador por '
						+ event.creator
						+ ' a '
						+ new Date(event.creation_date).toLocaleString().split(' ')[0]
						+ '</small></p>\
									</div>\
							</div>\
							</div>\
						</div>\
				');
	}

	var insertElements = function(parent, event) {
		parent.append('<div id="fila" class="row"></div>');
		$('#fila').append('<div id="previous" class="col-1 d-flex justify-content-center my-auto">');
		if (previous != null) {
			addNavegationLeft($('#previous'), event);
			addPreviousListener();
		}
		$('#fila').append('</div>');
		showElement($('#fila'), event);
		insertIcon(event);
		$('#fila').append('<div id="next" class="col-1 d-flex justify-content-center my-auto">');
		if (next != null) {
			addNavegationRight($('#next'), event);
			addNextListener();
		}
		$('#fila').append('</div>');
		addReturnListener();
	}

	var insertIcon = function(event) {
		var selectedIcon;
		switch (event.category) {
		case 'Musica':
			selectedIcon = "img\\musica-icon.png";
			break;
		case 'Deportes':
			selectedIcon = "img\\deportes-icon.png";
			break;
		case 'Literatura':
			selectedIcon = "img\\literatura-icon.png";
			break;
		case 'Cine':
			selectedIcon = "img\\cine-icon.png";
			break;
		case 'Videojuegos':
			selectedIcon = "img\\videojuegos-icon.png";
			break;
		case 'Series':
			selectedIcon = "img\\series-icon.png";
			break;
		case 'Teatro':
			selectedIcon = "img\\teatro-icon.png";
			break;
		case 'Otro':
			selectedIcon = "img\\otro-icon.png";
			break;
		}

		$('#icono').attr("src", selectedIcon);

	}

	var showErrorMessage = function(jqxhr, textStatus, error) {
		alert(textStatus + ": " + error);
	};

	var addReturnListener = function() {
		$('#return' + ' a.return').click(function() {
			self.getRecents();
		});
	};

	var addPreviousListener = function() {
		$('#previous' + ' a.previous').click(function() {
			self.getEvent(previous);
		});
	};

	var addNextListener = function() {
		$('#next' + ' a.next').click(function() {
			self.getEvent(next);
		});
	};

	return EventDetailView;
})();
