// $(document).ready(function () { });

(function($) {

    var App = {
        init: function () {
            App.FilterEmptyInput();
            App.OrderBy();
        },
        FilterEmptyInput: function () {
            $("form").submit(function () {

                $("input:text").filter(function (){
                    return $(this).val() == "";
                }).attr("disabled", "disabled");

            });
        },
        OrderBy: function () {
            $(".orden").click(function (e) {
                e.preventDefault();
                $("#orden").val($(this).text());
                $("form").submit();
            })
        },
    };

    $(function(){
        App.init();
        $(window).resize();
    });

})(jQuery);