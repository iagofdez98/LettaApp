var RecentEventsView = (function() {
	var dao;

	// Referencia a this que permite acceder a las funciones públicas desde las
	// funciones de jQuery.
	var self;

	var listId = 'events-list';
	var row = 1;
	var eventsOnRow = 0;
	var eventsList;

	function RecentEventsView(eventsDao, formContainerId, listContainerId) {
		dao = eventsDao;
		self = this;

		addHeader($('#' + listContainerId));
		insertEventsList($('#' + listContainerId));

		this.init = function() {

			dao.listEvents(function(events) {
				eventsList = events;
				$.each(events, function(key, event) {
					appendToList(event, listContainerId);
				});
			}, function() {
				alert('No hay eventos para mostrar.');
			});

		};

		this.getEvent = function(id) {
			$('#events-container').empty();
			var view = new EventDetailView(id, eventsList,'events-container');
			view.init();

		};
	};

	var insertEventsList = function(parent) {
		parent
				.append('<div id="'
						+ listId
						+ '-'
						+ row
						+ '" class="section">\
						<div class="container">\
						<div id="'
						+ 'rowId-'
						+ row
						+ '" class="row">\
						</div>\
						</div>\
						</div>\
						</div>');
	};

	var createEventCard = function(event) {
		function addZero(i) {
			  if (i < 10) {
			    i = "0" + i;
			  }
			  return i;
			}
		
		function shortDescription(description, maxLenght){
			if(description.length <= maxLenght){
				return description;
			}else{
				while(description[maxLenght-3] != ' '){
					maxLenght -= 1;
				}
				return description.substring(0, maxLenght-3).concat('...');			
			}
		};
		
		return '<div id= "event-' + event.id + '" class="col-3">\
					<div class="card bg-light mb-3" style="max-width: 18rem;">\
						<a class="detail text-decoration-none text-dark" href="#">\
							<img src="'+ addCategoryBanner(event.category) +'" class="card-img-top" alt="...">\
					        <div class="card-body">\
					          <h5 class="card-title">' + event.title + '</h5>\
					          <p class="card-text">' + shortDescription(event.description, 80) + '</p>\
					          <h6 class="card-subtitle mb-3 text-muted"><i class="fas fa-map-marker-alt mr-2"></i>' + event.location + '</h6>\
					          <div class="d-flex justify-content-between ">\
								<div class="row align-items-center">\
									<div class="col-2">\
										<i class="fas fa-user-friends fa-lg"></i>\
									</div>\
									<div class="col-5 border-right border-dark pr-2">\
										<p class="m-0">' + event.participants + '/'+ event.capacity +'</p>\
										<p class="cardSub m-0">Participantes</p>\
									</div>\
									\
									<div class="col-2">\
										<i class="far fa-calendar-alt fa-lg"></i>\
									</div>\
									<div class="col-3 p-0">\
										<p class="m-0">'+ new Date(event.event_date).getHours() + ':' + addZero(new Date(event.event_date).getMinutes()) +'</p>\
										<p class="cardSub m-0">'+ new Date(event.event_date).toLocaleString().split(' ')[0] +'</p>\
									</div>\
								</div>\
									\
					          </div>\
					        </div>\
						</a>\
					</div>\
				</div>';
	};

	var showErrorMessage = function(jqxhr, textStatus, error) {
		alert(textStatus + ": " + error);
	};

	var addRowListeners = function(event) {
		$('#event-' + event.id + ' a.detail').click(function() {
			self.getEvent(event.id);
		});
	};
	
	var appendToList = function(event, listContainerId) {
		$('#rowId-' + row).append(createEventCard(event));
		eventsOnRow++;
		addRowListeners(event);
	};

	var addHeader = function(parent) {
		parent
				.append('<div class="section ">\
		<div class="container ">\
		<div class="row ">\
			<div class="col-md-12 ">\
				<h1 class="display-4">Eventos recientes</h1>\
			</div>\
			</div>\
		</div>\
		</div>');
	};
	
	var addCategoryBanner = function(category){		
		switch(category.toLowerCase()){
			case 'cine':
				return 'img\\cine.png';
				break;
			case 'deportes':
				return 'img\\deportes.png';
				break;
			case 'literatura':
				return 'img\\literatura.png';
				break;
			case 'musica':
				return 'img\\musica.png';
				break;
			case 'otro':
				return 'img\\otro.png';
				break;
			case 'series':
				return 'img\\series.png';
				break;
			case 'teatro':
				return 'img\\teatro.png';
				break;
			case 'videojuegos':
				return 'img\\videojuegos.png';
				break;
		}
	};

	return RecentEventsView;
})();
