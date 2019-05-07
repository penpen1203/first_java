jQuery.validator.setDefaults({
  success: "valid",
  errorClass:"help-block",
  errorElement:"span",

  highlight:function(element,errorClass){
	  $(element).closest('.form-group').addClass('has-error');
  },
  unhighlight:function(element,errorClass){
	  $(element).closest('.form-group').removeClass('has-error');
  },
});