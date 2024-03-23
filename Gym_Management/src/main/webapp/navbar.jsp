<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gym Management</title>
 <link rel="icon" href="img/gm.jpg" type="image/jpg" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script type="text/javascript" src="JS/chart.js"></script>
<!-- <script type="text/javascript">

	/* gets current time */
		function time() {return document.getElementById("tm").innerText= new Date().toLocaleTimeString();}
			setInterval(() => {time();}, 1000);
		
	/* gets current date */
		function date() {return document.getElementById("dt").innerHTML= new Date().toDateString()}
			setInterval(() => {date();}, 1000);
		
	</script> -->
	<style type="text/css">
	
	#db{
	border: 1px solid;
	height:40px;
	width:40px;
	border-radius: 90px 90px;
	}
	#tmm{
	border: 1px solid;
	height:50px;
	width:35px;
	border-radius: 90px 90px;
	}
	body {
    text-align:start;
	}
	.row{
	display: block;
	}
}
	</style>
	
</head>
<body class="text-start">
<!-- navbar -->
<div class="sticky-top">
<nav class="navbar navbar-expand-lg  navbar-dark bg-secondary postion-static">
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="#"><b><i>Gym Management</i></b></a>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
         </ul>
    </div>
    
     <div class="collapse navbar-collapse" id="navbarTogglerDemo03" style="width: 250px">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
         <li class="nav-item">
         <marquee>
         		<span class="text-danger fs-4 modal-dialog modal-sm">${msgReg}</span>
         </marquee>
        </li>
        </ul>
  </div>
</nav>
</div>
</body>
</html>