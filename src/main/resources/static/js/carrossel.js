$(document).ready(function(){

var speed = 5000;
var run = setInterval('rotate()', speed);

var item_width = $('itens li').outerWidth();
var left_value = item_width * (-1);

$('#itens li: first').before($('itens li:last'));

$('#itens ul').css({'left': left_value});

$("prev").click(function(){
    var left_intend = parseInt($('#itens ul').css('left')) + item_width;
    $('#itens ul').animate({'left':left_intend}, 200, function(){
        $('#itens li: first').before($('itens li:last'));
        $('#itens ul').css({'left': left_value});
    });
    clearInterval(run);
    run = setInterval('rotate()', speed);
});

$("next").click(function(){
    var left_intend = parseInt($('#itens ul').css('left')) - item_width;
    $('#itens ul').animate({'left':left_intend}, 200, function(){
        $('#itens li: last').after($('itens li:first'));
        $('#itens ul').css({'left': left_value});
    });

    clearInterval(run);
    run = setInterval('rotate()', speed);
});

$('#itens').hover(
    function(){
        clearInterval(run);
        disableScroll();
    },
    function(){
        clearInterval(run);
        run = setInterval('rotate()',speed);
        enableScroll();
    }
)

function rotate(){
    $('#next').click();
}

var keys = { 37:1, 38:1, 39:1, 40:1};

function preventDefault(e){
    e = e || window.event;
    if(e.preventDefault)
        e.preventDefault();
    e.returnValue = false;
}

function preventDefaultForScrollKeys(e){
    if(keys[e.keyCode]){
        preventDefault(e);
        return false;
    }
}

function disableScroll(){
    window.onwheel = preventDefault;
    window.ontouchmove = preventDefault;
    document.onkeydown = preventDefaultForScrollKeys;
}

function enableScroll(){
    window.onwheel = null;
    window.ontouchmove = null;
    document.onkeydown = null;
}

});