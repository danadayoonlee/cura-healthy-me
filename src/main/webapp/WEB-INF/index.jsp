<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div id="container" style="min-height: 100%">
	<div class="text-right">
		<a href="/logout" title="logout">Logout</a>
	</div>
	<br />
	<div class="row">
		<div class="col">
			<h3>Welcome, ${USERNAME}</h3>
		</div>
	</div>
	<br />
	<div class="row">
		<div class="col">
			<h3>Patient Information</h3>
		</div>
	</div>
	<br />
	<div class="row">
		<div class="col">
			<table class="table no-border">
				<tr>
					<td width="20%">Patient Name</td>
					<td th:inline="text" width="80%">${user.getName()}</td>
				</tr>
				<tr>
					<td>Date of Birth</td>
					<td th:inline="text">${user.getDob()}</td>
				</tr>
				<tr>
					<td>Health Card NUmber</td>
					<td th:inline="text">${ohip}</td>
				</tr>
				<tr>
					<td>Family Doctor</td>
					<td th:inline="text">${familydoctor}</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="container">
							<div class="row">
								<div class="col-4">
									<a class="btn btn-secondary  btn-lg btn-block"
										href="/findfamilydoctor/${user.getPatientId()}"
										title="find family doctor">Find Family Doctor</a>
								</div>
								<div class="col-4">
									<a class="btn btn-secondary  btn-lg btn-block"
										href="/viewmedicalhistory/${user.getPatientId()}"
										title="View Medical History">View Medical History</a>
								</div>
								<div class="col-4">
									<a class="btn btn-secondary  btn-lg btn-block"
										href="/viewtestrequisitions/${user.getPatientId()}"
										title="View My Test Requisitions">View My Test
										Requisitions</a>
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-4">
									<a class="btn btn-secondary  btn-lg btn-block"
										href="/viewtestresults/${user.getPatientId()}"
										title="View My Test Results">View My Test Results</a>
								</div>
								<div class="col-4">
									<a class="btn btn-secondary  btn-lg btn-block"
										href="/viewpatientprescriptions/${user.getPatientId()}"
										title="View My Prescriptions">View My Prescriptions</a>
								</div>
								<div class="col-4">
									<a class="btn btn-secondary  btn-lg btn-block"
										href="/bookappointment/${user.getPatientId()}"
										title="Book An Appointment">Book an Appointment</a>
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-4">
									<a class="btn btn-secondary  btn-lg btn-block"
										href="/patientappointments/${user.getPatientId()}"
										title="View My Appointments">View My Appointments</a>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
