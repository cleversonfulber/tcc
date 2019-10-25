$(document).ready(function() {
	 // Owl carousel code
	$('#slider').nivoSlider();
	
	$("#owl-example").owlCarousel({
	  	autoPlay : true
	});

	// Dropdown Visibility on hover
	$('li.dropdown').on('mouseover', function(){
	  		$(this).addClass('open');
	})

	.on('mouseout', function(){
		$(this).removeClass('open');
	});

	$("html").niceScroll({
		cursorcolor : "#ff6200",
		cursorwidth : "10px" ,
		zindex: 9999 ,
		cursorborder: "none"	,
		cursorborderradius : "0" ,
		autohidemode: false,
	});
	
	// fade in #back-top

	$(window).scroll(function () {
		if ($(this).scrollTop() >800) {
			$('#back-top').fadeIn('slow');
		} else {
			$('#back-top').fadeOut('slow');
		}
	});

	// scroll body to 0px on click
	$('#back-top').click(function () {
		$('body,html').animate({
			scrollTop: 0
		}, 800);
		return false;
	});

	$('#myTab a').click(function (e) {
		e.preventDefault()
		$(this).tab('show')
	})

});

//alturaImg = $('#top').height();
//$(window).on('scroll', function() {
//  if($(window).scrollTop() < alturaImg) {
//    $('#navbar').removeClass('fixed-menu');
//    return;
//  }
//  $('#navbar').addClass('fixed-menu');
//});

var divXpto = $('.tamanho');
divXpto.text(divXpto.text().substring(0,10));


