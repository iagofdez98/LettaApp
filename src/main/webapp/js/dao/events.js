var EventsDAO = (function() {
	var resourcePath = "rest/events";
	var requestByAjax = function(data, done, fail, always) {
		done = typeof done !== 'undefined' ? done : function() {
		};
		fail = typeof fail !== 'undefined' ? fail : function() {
		};
		always = typeof always !== 'undefined' ? always : function() {
		};

		$.ajax(data).done(done).fail(fail).always(always);
	};

	function EventsDAO() {
		this.listEvents = function(done, fail, always) {
			requestByAjax({
				url : resourcePath + "/recent",
				type : 'GET'
			}, done, fail, always);
		};
		
		this.addEvent = function(event, done, fail, always) {
			requestByAjax({
				url : resourcePath,
				type : 'POST',
				data: event
			}, done, fail, always);
		};

		this.getEvent = function(id, done, fail, always) {
			requestByAjax({
				url : resourcePath + id,
				type : 'GET'
			}, done, fail, always);
		};
	


	}

	return EventsDAO;
})();