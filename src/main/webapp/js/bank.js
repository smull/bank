/*
 Orginal Page: http://thecodeplayer.com/walkthrough/jquery-multi-step-form-with-progress-bar
)
 */
//jQuery time

//$( document ).ready(function() {

/*#msformF input.error {
 border:1px solid red;
 }
*/
$(document).ready(function () {

    $(function() {
        $( "#datepicker" ).datepicker();
        $( "#anim" ).change(function() {
            $( "#datepicker" ).datepicker( "clip", "showAnim", $( this ).val() );
        });
    });

    var current_fs, next_fs, previous_fs; //fieldsets
    var left, opacity, scale; //fieldset properties which we will animate
    var animating; //flag to prevent quick multi-click glitches
    var countClick = 0;
    var countCheckForm = 0;

    $(".next").click(function (event) {


            $("#msform").validate({
                debug: true,
                ignore: ':hidden',

                    rules: {
                    checkboxHid: {
                          required: true
                    },

                    countries: {
                        required: true
                    },
                    'surname': {
                        required: true
                    },
                    'datepicker': {
                        required: true,
                        check18: true
                    },
                    'creditCard': {
                        required: true
                    },
                    'cvv': {
                        required: true
                    },
                    'secretQuestion': {
                        required: true
                    },
                    'secretAnswer': {
                        required: true
                    },

                    'city': {
                        required: true
                    },
                    'street': {
                        required: true
                    },
                    'building': {
                        required: true
                    }
                },
                messages: {
                    surname: "Please enter your surname",
                    datepicker: {
                        required: "Please enter your birthday",
                        check18: "You aren`t underage!"
                    },
                    creditCard: "Please enter your creditCard",
                    cvv: "Please enter your cvv",
                    secretQuestion: "Please enter your secretQuestion",
                    secretAnswer: "Please enter your secretAnswer",

                    city: "Please enter your city",
                    street: "Please enter your street",
                    building: "Please enter your building"

                }
            });



            jQuery.validator.addMethod("check18", function (value, element) {
                var dateM = new Date(value).getFullYear();
                var now = new Date().getFullYear();
                var adultYear = now - dateM;
                if (adultYear >= 18) {
                    $("#checkboxHid").show();
                    return true;
                }
                return false;
            });

            jQuery.validator.addMethod("checkCountries", function (value, element) {
                var valueText = $("#countries :selected").text();
                if (valueText == "Ukraine") {
                    return true;
                }
                return false;
            });







        var valid = $("#msform").valid();
        if (valid == false) {
            event.preventDefault();
        } else {

            countCheckForm++;
            if (animating) return false;
            animating = true;

            current_fs = $(this).parent();
            next_fs = $(this).parent().next();

            if (countClick == 0) {
                var surname = $("#surname").val();
                var datepicker = $("#datepicker").val();
                sessionStorage.setItem('surname', surname);
                sessionStorage.setItem('datepicker', datepicker);
                alert(surname + " " + datepicker);
                countClick++;
            } else if (countClick == 1) {
                var creditCard = $("#creditCard").val();
                var cvv = $("#cvv").val();
                var secretQuestion = $("#secretQuestion").val();
                var secretAnswer = $("#secretAnswer").val();
                sessionStorage.setItem('creditCard', creditCard);
                sessionStorage.setItem('cvv', cvv);
                sessionStorage.setItem('secretQuestion', secretQuestion);
                sessionStorage.setItem('secretAnswer', secretAnswer);
                alert(creditCard + " " + cvv + " " + secretQuestion + " " + secretAnswer);
                countClick++;
            }

            //activate next step on progressbar using the index of next_fs
            $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

            //show the next fieldset
            next_fs.show();
            //hide the current fieldset with style
            current_fs.animate({opacity: 0}, {
                step: function (now, mx) {
                    //as the opacity of current_fs reduces to 0 - stored in "now"
                    //1. scale current_fs down to 80%
                    scale = 1 - (1 - now) * 0.2;
                    //2. bring next_fs from the right(50%)
                    left = (now * 50) + "%";
                    //3. increase opacity of next_fs to 1 as it moves in
                    opacity = 1 - now;
                    current_fs.css({'transform': 'scale(' + scale + ')'});
                    next_fs.css({'left': left, 'opacity': opacity});
                },
                duration: 800,
                complete: function () {
                    current_fs.hide();
                    animating = false;
                },
                //this comes from the custom easing plugin
                easing: 'easeInOutBack'
            });
        }

    });

    $(".previous").click(function () {
        if (animating) return false;
        animating = true;

        current_fs = $(this).parent();
        previous_fs = $(this).parent().prev();

        //de-activate current step on progressbar
        $("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

        //show the previous fieldset
        previous_fs.show();
        //hide the current fieldset with style
        current_fs.animate({opacity: 0}, {
            step: function (now, mx) {
                //as the opacity of current_fs reduces to 0 - stored in "now"
                //1. scale previous_fs from 80% to 100%
                scale = 0.8 + (1 - now) * 0.2;
                //2. take current_fs to the right(50%) - from 0%
                left = ((1 - now) * 50) + "%";
                //3. increase opacity of previous_fs to 1 as it moves in
                opacity = 1 - now;
                current_fs.css({'left': left});
                previous_fs.css({'transform': 'scale(' + scale + ')', 'opacity': opacity});
            },
            duration: 800,
            complete: function () {
                current_fs.hide();
                animating = false;
            },
            //this comes from the custom easing plugin
            easing: 'easeInOutBack'
        });
    });

    $(".submit").click(function () {
        var valueText = $("#countries :selected").text();
        $("#countries option:selected").val(valueText);

        $("#msform").submit();
        //return false;
    })




    $("#countries").msDropdown();


});
