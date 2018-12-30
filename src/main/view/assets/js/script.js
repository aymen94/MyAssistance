/* Forms namespace */

var utilies =
{
		/**
		 * Validate a form element by checking all of its fields.
		 * Fields must have the @pattern attribute to be validated.
		 * 
		 * @param form the form element to be validated.
		 * 
		 * @return boolean value. True if form is validated, false if not.
		 */
		validate: function(form)
		{
			// Try to get error dialog
			var errorDialog = document.getElementById("error-dialog");
			
			// Get all input fields with pattern
			var d = form.querySelectorAll("input[pattern]");
			
			// Validate them
			for (var i = 0; i < d.length; ++i)
			{
				if (!d[i].value.length || !d[i].checkValidity())
				{
					if (errorDialog !== null)
					{
						var label = form.querySelector('label[for="' + d[i].getAttribute("id") + '"]');
						this.showDialog(errorDialog, "Errore Registrazione", "Il campo " + label.innerHTML + " non è stato compilato in modo corretto.");
					}
					return false;
				}
			}

			// Get all radio button names
			var names = [];
			form.querySelectorAll('input[type=radio][name]').forEach(function(e)
			{
				var name = e.getAttribute("name");
				if (names.indexOf(name) == -1)
					names.push(name);
			});
			
			// Validate radio button selections
			for (var i = 0; i < names.length; ++i)
			{
				if (form.querySelector('input[type=radio][name="' + names[i] + '"]:checked') === null)
				{
					if (errorDialog !== null)
					{
						this.showDialog(errorDialog, "Errore Registrazione", "Ci sono alcune selezioni non ancora effettuate.");
					}
					return false;
				}
			}
			
			// Check for same-value fields
			d = form.querySelectorAll("*[data-same-first]");
			for (var i = 0; i < d.length; ++i)
			{
				var s = form.querySelector('*[data-same-second="' + d[i].getAttribute("data-same-first") + '"]');
				if (d[i].value != s.value)
				{
					if (errorDialog !== null)
					{
						var label = form.querySelector('label[for="' + d[i].getAttribute("id") + '"]');
						this.showDialog(errorDialog, "Errore Registrazione", "I campi " + label.innerHTML + " devono coincidere.");
					}
					return false;
				}
			}
			return false;
			
			// Form is valid
			return true;
		},

		/**
		 * This function triggers a modal bootstrap dialog, with specified parameters.
		 * Element must already exist in source page.
		 * 
		 *  @param dialog element in DOM which represents a bootstrap modal dialog
		 *  @param title title to be shown on dialog. If it's @null, title will not be changed.
		 *  @param message message to be shown on dialog. If it's @null, message will not be changed.
		 */
		showDialog: function(dialog, title, message)
		{
			// Set title, if requested
			if (title !== null)
			{
				dialog.querySelector(".modal-title").innerHTML = title;
			}

			// Set message, if requested
			if (message !== null)
			{
				dialog.querySelector("p").innerHTML = message;
			}
			
			// Show modal dialog
			$('#' + dialog.getAttribute("id")).modal('show');
	}
};