
$('div.global_nav ul li').click(function (e) {
  $('div.global_nav ul li.active').removeClass('active')
  $(this).parent('li').addClass('active')
})