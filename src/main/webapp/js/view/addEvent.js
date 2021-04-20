var AddEventView = (function() {
	var dao;

	var self;

	var event = new Object();


	function AddEventView(login, formContainerId, listContainerId) {
		self = this;

		$("#test").submit(function(event) {
			event.preventDefault()
			alert("entra");
			var eventToAdd = self.getEventInForm();
				dao.addEvent(eventToAdd,
					showErrorMessage,
				);});

		this.getEventInForm = function() {
			var form = $("#addForm");
			return {
				'creator':"user",
				'title':form.find('input[name="title"]').val(),
				'description':form.find('input[name="description"]').val(),
				'category':form.find('input[name="category"]').val(),
				'location':form.find('input[name="location"]').val(),
				'capacity':form.find('input[name="capacity"]').val(),
				'eventDate':form.find('input[name="date"]').val().concat(form.find('input[name="hour"]').val()),
				'duration':form.find('input[name="duration"]').val(),
			};
		};
		$('#events-container').empty();
		insertEventForm($('#' + listContainerId));
	};

	var insertEventForm = function(parent){
		parent.append(`
		<form id="addForm" method="POST">
            <div id="register" class="m-5 row justify-content-center align-items-center">
                <div class="col-xs-9 col-sm-8 col-md-7 col-lg-6 border border-info rounded px-5 py-4">
					<div class="input-group mb-3">
						<div class="col-12">
							<div class="input-group-prepend">
								<span class="input-group-text lblName">Título</span>
								<input type="text" name="title" class="form-control" required>
							</div>
                        </div>
                    </div>

					<div class="input-group mb-3">
						<div class="col-6">
							<div class="input-group-prepend">
								<select class="custom-select" name="category" id="inputGroupSelect01" required>
									<option value="Cine">Cine</option>
									<option value="Deportes" >Deportes</option>
									<option value="Literatura" >Literatura</option>
									<option value="Musica" >Musica</option>
									<option value="Series" >Series</option>
									<option value="Teatro" >Teatro</option>
									<option value="Videojuegos" >Videojuegos</option>
									<option value="Otro" >Otro</option>
								</select>
								<span class="input-group-text lblName">Categoría</span>
							</div>
					   </div>
					   <div class="col-6">
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text lblName">Ubicación</span>
								</div>
								<input type="text" name="location" class="form-control" required>
							</div>
						</div>
					</div>

					<div class="input-group mb-3">
						<div class ="col-12">
							<div class="input-group-prepend">
								<span class="input-group-text lblName">Descripción</span>
								<textarea name="description" class="form-control" required></textarea>
							</div>
							
						</div>
                    </div>
                    
                    <div class="input-group mb-3">
                    	<div class="col-6">
                       		<div class="input-group-prepend">
								<span class="input-group-text lblName">Fecha</span>
								<input type="date" name="date" class="form-control" required>
                        	</div>
                        	
                      	</div>
                     	<div class="col-6">
                       		<div class="input-group-prepend">
								   <span class="input-group-text lblName">Hora</span>
								   <input type="time" name="hour" class="form-control" required>
                        	</div>
                        	
                      		</div>
                      </div>
           
                    <div class="input-group mb-3">
                    	<div class="col-6">
                       		<div class="input-group-prepend">
								<span class="input-group-text lblName">Máx. Asistentes</span>
								<input type="number" name="capacity" class="form-control" required>
                        	</div>
                        	
                        </div>
                        <div class="col-6">
                       		<div class="input-group-prepend">
								<span class="input-group-text lblName">Duración</span>
								<input type="number" step="15" name="duration" class="form-control" required>
                        	</div>
                        	
                        </div>
                   	</div>

                    <span class="text-muted font-weight-light btnAddCenter"></span>

                    <div class="m-0 row justify-content-between button-footer">
                        <a href="/LettaApp/home.html"><button type="button" class="btn btn-primary">Volver</button></a>
                        <button type="submit" id="test" class="btn btn-success">Añadir</button>
                    </div>
                </div>
            </div>
        </form>`);
	}
	
	var showErrorMessage = function(jqxhr, textStatus, error) {
		alert(textStatus + ": " + error);
	};

	var addRowListeners = function(event) {
		$('#event-' + event.id + ' a.detail').click(function() {
			self.getEvent(event.id);
		});
	};

	var addHeader = function(parent, event) {
		parent
				.append('<div class="section p-5" align="center" style="background-color: #0091ff;">\
		<div class="row">\
			<div class="col-md-12 p-10">\
				<h1>' + event.title+'</h1>\
				<h3>' + event.description +'</h3>\
			</div>\
			</div>\
		</div>');
	};

	return AddEventView;
})();
