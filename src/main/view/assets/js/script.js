/* Forms namespace */

var utilities =
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
			var d = form.querySelectorAll("input[pattern], textarea[pattern], select");

			// Validate them
			for (var i = 0; i < d.length; ++i)
			{
				let c1 = d[i].tagName.toLowerCase() === "select" && d[i].value == -1;
				let c2 = d[i].tagName.toLowerCase() !== "select" && (!d[i].value.length || !d[i].checkValidity());
				if (c1 || c2)
				{
					if (errorDialog !== null)
					{
						var label = form.querySelector('label[for="' + d[i].getAttribute("id") + '"]');
						this.showDialog(errorDialog, "Errore Registrazione", "Il campo " + label.innerHTML + " non é stato compilato in modo corretto.");
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
			// Close submit dialog, if possible
			var sd = document.getElementById("submit-dialog");
			if (sd !== null)
			{
				$('#' + sd.getAttribute("id")).modal('hide');
			}

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

/* Segnalazioni namespace */

var segnalazioni =
{
		/**
		 * This method shows a bootstrap modal dialog, containing the form elements to be
		 * added to the dialog's form, in order to send the report.
		 *
		 * @param dialog the dialog element's id, which must already exist in the DOM.
		 * @param id the report id.
		 *
		 */
		inoltra: function(dialogId, id)
		{
			// Show dialog to the browser
			var form = document.getElementById("inoltra-segnalazione");
			form.querySelector("input").value = id;
			utilities.showDialog(document.getElementById(dialogId), "Inoltra Segnalazione", form.outerHTML);
		},

		/**
		 * This method shows a bootstrap modal dialog, containing the form elements to be
		 * added to the dialog's form, in order to refuse the report.
		 *
		 * @param dialog the dialog element's id, which must already exist in the DOM.
		 * @param id the report id.
		 */
		rifiuta: function(dialogId, id)
		{
			// Show dialog to the browser
			var form = document.getElementById("rifiuta-segnalazione");
			form.querySelector("input").value = id;
			utilities.showDialog(document.getElementById(dialogId), "Rifiuta Segnalazione", form.outerHTML);
		},

		/**
		 * This method shows a bootstrap modal dialog, containing the form elements to be
		 * added to the dialog's form, in order to mark report as solved.
		 *
		 * @param dialog the dialog element's id, which must already exist in the DOM.
		 * @param id the report id.
		 */
		segnaRisolta: function(dialogId, id)
		{
			// Show dialog to the browser
			var form = document.getElementById("segna-risolta");
			form.querySelector("input").value = id;
			utilities.showDialog(document.getElementById(dialogId), "Segna Come Risolta", form.outerHTML);
		},

		/**
		 * This method shows a bootstrap modal dialog, containing the form elements to be
		 * added to the dialog's form, in order to delete the report.
		 *
		 * @param dialog the dialog element's id, which must already exist in the DOM.
		 * @param id the report id.
		 */
		elimina: function(dialogId, id)
		{
			// Show dialog to the browser
			var form = document.getElementById("elimina");
			form.querySelector("input").value = id;
			utilities.showDialog(document.getElementById(dialogId), "Elimina Segnalazione", form.outerHTML);
		}
};

/* Ufficio Tecnico */

var ufficioTecnico =
{
		/**
		 * This method shows a bootstrap modal dialog, containing the form elements to be
		 * added to the dialog's form, in order to delete the support office.
		 *
		 * @param dialog the dialog element's id, which must already exist in the DOM.
		 * @param id the report id.
		 */
		elimina: function(dialogId, id)
		{
			// Show dialog to the browser
			var form = document.getElementById("elimina-ufficio");
			form.querySelector("input").value = id;
			utilities.showDialog(document.getElementById(dialogId), "Elimina Ufficio", form.outerHTML);
		}
};
