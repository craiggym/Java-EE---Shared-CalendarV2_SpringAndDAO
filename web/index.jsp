<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shared Calendar</title>
	
    <!-- css -->
    <link href="styles/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="styles/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="styles/plugins/cubeportfolio/css/cubeportfolio.min.css">
	<link href="styles/css/nivo-lightbox.css" rel="stylesheet" />
	<link href="styles/css/nivo-lightbox-theme/default/default.css" rel="stylesheet" type="text/css" />
	<link href="styles/css/owl.carousel.css" rel="stylesheet" media="screen" />
    <link href="styles/css/owl.theme.css" rel="stylesheet" media="screen" />
	<link href="styles/css/animate.css" rel="stylesheet" />
    <link href="styles/css/style.css" rel="stylesheet">

	<!-- boxed bg -->
	<link id="bodybg" href="styles/bodybg/bg1.css" rel="stylesheet" type="text/css" />
	<!-- template skin -->
	<link id="t-colors" href="styles/color/default.css" rel="stylesheet">


</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">

<div id="wrapper">
	
    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
		<div class="top-area">
			<div class="container">
				<div class="row">
					<div class="col-sm-6 col-md-6">
					<p class="bold text-left">CPSC-476 SPR 2016</p>
					</div>
					<div class="col-sm-6 col-md-6">
					<p class="bold text-right">Craig Marroquin</p>
					</div>
				</div>
			</div>
		</div>
        <div class="container navigation">
		
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    <i class="fa fa-bars"></i>
                </button>


            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
			  <ul class="nav navbar-nav">
				<li class="active"><a href="#intro">Home</a></li>
				<li><a href="#service">My Events</a></li>
				<li><a href="#pricing">Logout</a></li>
				</li>
			  </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
	

	<!-- Section: intro -->
    <section id="intro" class="intro">
		<div class="intro-content">
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
					<div class="wow fadeInDown" data-wow-offset="0" data-wow-delay="0.1s">
					<h2 class="h-ultra">Generic Calendar Site Project</h2>
					</div>
					<div class="wow fadeInUp" data-wow-offset="0" data-wow-delay="0.1s">
					<h4 class="h-light">Providing <span class="color">the most basic</span> event calendar creations</h4>
					</div>
						<div class="well well-trans">
						<div class="wow fadeInRight" data-wow-delay="0.1s">

						<ul class="lead-list">
							<li><span class="fa fa-check fa-2x icon-success"></span> <span class="list"><strong>Log in with credentials</strong><br />Don't have one? Try registering!</span></li>
							<li><span class="fa fa-check fa-2x icon-success"></span> <span class="list"><strong>Add events to your personal event page</strong><br />Must be logged in for this feature.</span></li>
							<li><span class="fa fa-check fa-2x icon-success"></span> <span class="list"><strong>Events are sorted by date</strong><br />Common sense. <br/><br/><em>There are more features but these 3 sound good. Explore around!</em></span></li>
						</ul>

						</div>
						</div>


					</div>
					<div class="col-lg-6">
						<div class="form-wrapper">
						<div class="wow fadeInRight" data-wow-duration="2s" data-wow-delay="0.2s">
						
							<div class="panel panel-skin">
							<div class="panel-heading">
									<h3 class="panel-title"><span class="fa fa-pencil-square-o"></span> Log in here <small>(or click register)</small></h3>
									</div>
									<div class="panel-body">
									<form role="form" class="lead">
										<div class="row">
											<div class="col-xs-6 col-sm-6 col-md-6">
												<div class="form-group">
													<label>Username</label>
													<input type="text" name="fname" id="first_name" class="form-control input-md">
												</div>
											</div>
											<div class="col-xs-6 col-sm-6 col-md-6">
												<div class="form-group">
													<label>Password</label>
													<input type="text" name="lname" id="last_name" class="form-control input-md">
												</div>
											</div>
										</div>
										
										<input type="submit" value="Submit" class="btn btn-skin btn-block btn-lg">
										
										<p class="lead-footer"><input type="submit" value="Register" class="btn btn-skin btn-block"></p>

									</form>
								</div>
							</div>				
						
						</div>
						</div>
					</div>					
				</div>		
			</div>
		</div>		
    </section>
	
	<!-- /Section: intro -->

	<!-- Section: boxes -->
    <section id="boxes" class="home-section paddingtop-80">
	
		<div class="container">
			<div class="row">
				<div class="col-sm-3 col-md-3">
					<div class="wow fadeInUp" data-wow-delay="0.2s">
						<div class="box text-center">
							
							<i class="fa fa-check fa-3x circled bg-skin"></i>
							<h4 class="h-bold">Create events</h4>
							<p>
							Events are made public for everyone so keep that in mind when making events.
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-3 col-md-3">
					<div class="wow fadeInUp" data-wow-delay="0.2s">
						<div class="box text-center">
							
							<i class="fa fa-list-alt fa-3x circled bg-skin"></i>
							<h4 class="h-bold">Follow other peoples events</h4>
							<p>
							Want to make friends? Follow upcoming events and meet some cool people!
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-3 col-md-3">
					<div class="wow fadeInUp" data-wow-delay="0.2s">
						<div class="box text-center">
							
							<i class="fa fa-hospital-o fa-3x circled bg-skin"></i>
							<h4 class="h-bold">Meet in public areas</h4>
							<p>
							To ensure safety of all members, it is recommended that all events be held in public, non-shady areas.
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>

	</section>
	<!-- /Section: boxes -->
	
	

	<!-- Section: team -->
    <section id="doctor" class="home-section bg-gray paddingbot-60">
		<div class="container marginbot-50">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
					<div class="wow fadeInDown" data-wow-delay="0.1s">
					<div class="section-heading text-center">
					<h2 class="h-bold">Events</h2>
					<p>List of events from all members</p>
					</div>
					</div>
					<div class="divider-short"></div>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
		
            <div id="grid-container" class="cbp-l-grid-team">
                <ul>
                    <li class="cbp-item psychiatrist">
                        <a href="styles/doctors/member1.html" class="cbp-caption cbp-singlePage">
                            <div class="cbp-caption-defaultWrap">
								<img src="styles/img/team/1.jpg" alt="" width="100%">
                            </div>
                            <div class="cbp-caption-activeWrap">
                                <div class="cbp-l-caption-alignCenter">
                                    <div class="cbp-l-caption-body">
                                        <div class="cbp-l-caption-text">FOLLOW EVENT</div>
                                    </div>
                                </div>
                            </div>
                        </a>
                        <a href="styles/doctors/member1.html" class="cbp-singlePage cbp-l-grid-team-name">Birthday Party</a>
                        <div class="cbp-l-grid-team-position">AliceNWndrlan2031<p>Mar-25-2016</p></div>

                    </li>
                    <li class="cbp-item cardiologist">
                        <a href="styles/doctors/member2.html" class="cbp-caption cbp-singlePage">
                            <div class="cbp-caption-defaultWrap">
								<img src="styles/img/logo.jpg" alt="" width="100%" height="95%" align="center">
                            </div>
                            <div class="cbp-caption-activeWrap">
                                <div class="cbp-l-caption-alignCenter">
                                    <div class="cbp-l-caption-body">
                                        <div class="cbp-l-caption-text">VIEW PROFILE</div>
                                    </div>
                                </div>
                            </div>
                        </a>
                        <a href="styles/doctors/member2.html" class="cbp-singlePage cbp-l-grid-team-name">Joseph Murphy</a>
                        <div class="cbp-l-grid-team-position">Cardiologist</div>
                    </li>
                    <li class="cbp-item cardiologist">
                        <a href="styles/doctors/member3.html" class="cbp-caption cbp-singlePage">
                            <div class="cbp-caption-defaultWrap">
								<img src="styles/img/logo.jpg" alt="" width="100%" height="95%">
                            </div>
                            <div class="cbp-caption-activeWrap">
                                <div class="cbp-l-caption-alignCenter">
                                    <div class="cbp-l-caption-body">
                                        <div class="cbp-l-caption-text">VIEW PROFILE</div>
                                    </div>
                                </div>
                            </div>
                        </a>
                        <a href="styles/doctors/member3.html" class="cbp-singlePage cbp-l-grid-team-name">Alison Davis</a>
                        <div class="cbp-l-grid-team-position">Cardiologist</div>
                    </li>
                    <li class="cbp-item neurologist">
                        <a href="styles/doctors/member4.html" class="cbp-caption cbp-singlePage">
                            <div class="cbp-caption-defaultWrap">
                                <img src="styles/img/team/4.jpg" alt="" width="100%">
                            </div>
                            <div class="cbp-caption-activeWrap">
                                <div class="cbp-l-caption-alignCenter">
                                    <div class="cbp-l-caption-body">
                                        <div class="cbp-l-caption-text">VIEW PROFILE</div>
                                    </div>
                                </div>
                            </div>
                        </a>
                        <a href="styles/doctors/member4.html" class="cbp-singlePage cbp-l-grid-team-name">Adam Taylor</a>
                        <div class="cbp-l-grid-team-position">Neurologist</div>
                    </li>

                </ul>
            </div>
			</div>
			</div>
		</div>

	</section>
	<!-- /Section: team -->

                    <!-- 
                        All links in the footer should remain intact. 
                        Licenseing information is available at: http://bootstraptaste.com/license/
                        You can buy this theme without footer links online at: http://bootstraptaste.com/buy/?theme=Medicio
                    -->

</div>
<a href="#" class="scrollup"><i class="fa fa-angle-up active"></i></a>

	<!-- Core JavaScript Files -->
    <script src="styles/js/jquery.min.js"></script>
    <script src="styles/js/bootstrap.min.js"></script>
    <script src="styles/js/jquery.easing.min.js"></script>
	<script src="styles/js/wow.min.js"></script>
	<script src="styles/js/jquery.scrollTo.js"></script>
	<script src="styles/js/jquery.appear.js"></script>
	<script src="styles/js/stellar.js"></script>
	<script src="styles/plugins/cubeportfolio/js/jquery.cubeportfolio.min.js"></script>
	<script src="styles/js/owl.carousel.min.js"></script>
	<script src="styles/js/nivo-lightbox.min.js"></script>
    <script src="styles/js/custom.js"></script>


</body>

</html>
