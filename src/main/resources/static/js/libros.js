// $(document).ready(function () { });

(function($) {

    var App = {
        init: function () {
            App.FilterEmptyInput();
            App.OrderBy();
            App.Paging();
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
        Paging: function () {
            $("#btn-paging-back").click(function (e) {
                if (parseInt($("#pagina").val()) > 0) {
                    $("#pagina").val(parseInt($("#pagina").val())-1);
                }
                $("form").submit();
            });

            $("#btn-paging-next").click(function (e) {
                $("#pagina").val(parseInt($("#pagina").val())+1);
               $("form").submit();
            });
        }
    };

    $(function(){
        App.init();
        $(window).resize();
    });

})(jQuery);