<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>PhoneBook</title>
	<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<div class="container">
		<div class="pure-g">
			<div class="pure-u-1">
				<div class="header">
					<img class="logo" src="img/phonebook.png"/>
					<p>v 1.0</p>
				</div>
				
			</div>
		</div>
		<div class="pure-g">
		    <div class="pure-u-sm-1 pure-u-1-3">
		    	<div class="box">
		    		<h2><i class="fa fa-user-plus"></i>New contact</h2>
		    		<form class="pure-form" method="POST" action = "contacts">
					    <fieldset class="pure-group">
					        <input type="text" class="pure-input-1-2" placeholder="First Name" name = "name" value="${contactDto.name}">
					        <input type="text" class="pure-input-1-2" placeholder="Last Name" name= "lastName" value="${contactDto.lastName}">
					        <input type="text" class="pure-input-1-2" placeholder="Phone" name= "phoneNumber" value="${contactDto.phoneNumber}">
					    </fieldset>
					    <button type="submit" class="pure-button pure-input-1-2 pure-button-primary" name="addContact" value="Add">
					    <i class="fa fa-user-plus"></i>Add</button>
					</form>
				</div>
			</div>
		    <div class="pure-u-sm-1 pure-u-1-3">
				<div class="box">
		    		<h2><i class="fa fa-search"></i>Search contact</h2>
		    		<form class="pure-form" method="GET" action = "contacts">
		    			<fieldset class="pure-group">
					    	<input type="text" class="pure-input-1-2" name="keyword">
					     </fieldset>
					    <button type="submit" class="pure-button pure-input-1-2 pure-button-primary">
					    <i class="fa fa-search"></i>Search</button>
					</form>
				</div>
			</div>
			<div class="pure-u-sm-1 pure-u-1-3">
				<div class="box">
		    		<h2><i class="fa fa-users"></i> Contacts</h2>
		    		<div style="height:170px; overflow:auto">
	    			<table class="pure-table">
					    <thead>
					        <tr>
					            <th>First Name</th>
					            <th>Last Name</th>
					            <th>Phone</th>
					        </tr>
					    </thead>
					
					    <tbody>
					       <c:forEach var="contactDto" items="${contacts}">
				           <tr>
				           	<td>${contactDto.name}</td>
				           <td>${contactDto.lastName}</td>
				           <td>${contactDto.phoneNumber}</td>
				           </tr>
        				</c:forEach>
					    </tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>