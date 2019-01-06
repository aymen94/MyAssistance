<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 05/01/2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="submit-dialog" class="modal fade" tabindex="-1" role="dialog" method="post">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Titolo Avviso</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Messaggio Di Avviso</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
				<button type="submit" class="btn btn-primary confirm">Procedi</button>
			</div>
		</div>
	</div>
</form>